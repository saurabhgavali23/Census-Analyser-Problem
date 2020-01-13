package censusanalyser;

import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CensusLoader {

    List<CensusDAO> censusCSVDAOList = new ArrayList<CensusDAO>();

    public  <E> List<CensusDAO> loadCensusData(String csvFilePath, Class<E> CensusCSVClass) throws CensusAnalyserException {

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> censusList = csvBuilder.getListCsvFile(reader, CensusCSVClass);

            if(CensusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                censusList.stream().filter(censusData -> censusCSVDAOList
                        .add(new CensusDAO((IndiaCensusCSV) censusData)))
                        .collect(Collectors.toList());
            }
            else if (CensusCSVClass.getName().equals("censusanalyser.USCensusDataCSV")){
                censusList.stream().filter(censusData -> censusCSVDAOList
                        .add(new CensusDAO((USCensusDataCSV) censusData)))
                        .collect(Collectors.toList());
            }

            return this.censusCSVDAOList;

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
