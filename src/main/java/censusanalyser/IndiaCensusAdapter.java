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

public class IndiaCensusAdapter extends CensusAdapter {

    @Override
    public <E> Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
       Map<String,CensusDAO> censusStateMap = super.loadCensusData(IndiaCensusCSV.class,csvFilePath[0]);
       this.loadIndiaStateCode(censusStateMap,csvFilePath[1]);
       return censusStateMap;
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
