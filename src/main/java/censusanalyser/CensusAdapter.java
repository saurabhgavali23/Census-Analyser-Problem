package censusanalyser;

import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {

    public  abstract <E> Map<String,CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException;

    public  <E> Map<String,CensusDAO> loadCensusData(Class<E> CensusCSVClass, String csvFilePath) throws CensusAnalyserException {
        Map<String,CensusDAO> censusStateMap = new TreeMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
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
            return censusStateMap;

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (IllegalStateException e){
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException r){
            throw new CensusAnalyserException(r.getMessage(),
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),e.type.name());
        }
    }
}
