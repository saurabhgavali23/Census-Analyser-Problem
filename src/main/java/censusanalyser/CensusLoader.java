package censusanalyser;

import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusLoader {

    public  <E> Map<String,CensusDAO> loadCensusData(Class<E> CensusCSVClass, String... csvFilePath) throws CensusAnalyserException {
        Map<String,CensusDAO> censusStateMap = new TreeMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> censusList = csvBuilder.getListCsvFile(reader, CensusCSVClass);

            if(CensusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                StreamSupport.stream(censusList.spliterator(),false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusData -> censusStateMap.put(censusData.state,new CensusDAO(censusData)));
            }
            else if(CensusCSVClass.getName().equals("censusanalyser.USCensusDataCSV")) {
                StreamSupport.stream(censusList.spliterator(),false)
                        .map(USCensusDataCSV.class::cast)
                        .forEach(censusData -> censusStateMap.put(censusData.state,new CensusDAO(censusData)));
            }
           if(csvFilePath.length == 1) return censusStateMap;
           this.loadIndiaStateCode(censusStateMap,csvFilePath[1]);
           return censusStateMap;

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (IllegalStateException e){
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException r){
            throw new CensusAnalyserException(r.getMessage(),CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),e.type.name());
        }
    }


    public int loadIndiaStateCode(Map<String,CensusDAO> censusStateMap,String csvFilePath) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<IndiaStateCodeCSV> censusStateCodeList = csvBuilder.getListCsvFile(reader,IndiaStateCodeCSV.class);

            StreamSupport.stream(censusStateCodeList.spliterator(),false)
                    .filter(csvState->censusStateMap.get(csvState.state)!=null)
                    .forEach(csvData->censusStateMap.get(csvData.state).stateCode = csvData.stateCode);
            return censusStateMap.size();

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (IllegalStateException e){
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException r){
            throw new CensusAnalyserException(r.getMessage(),CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),e.type.name());
        }
    }
}
