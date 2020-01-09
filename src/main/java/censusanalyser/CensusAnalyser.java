package censusanalyser;

import com.google.gson.Gson;
import csvbuilder.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CensusAnalyser {

    List<IndiaCensusCSV> censusCSVList = null;
    List<IndiaStateCodeCSV> censusStateCodeCSVList = null;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {

        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusCSVList = csvBuilder.getListCsvFile(reader,IndiaCensusCSV.class);
            return censusCSVList.size();

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

    public int loadIndiaStateCode(String csvFilePath) throws CensusAnalyserException {

        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusStateCodeCSVList = csvBuilder.getListCsvFile(reader,IndiaStateCodeCSV.class);
            return censusStateCodeCSVList.size();

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

    public String getSortedStates() throws CensusAnalyserException {

        if (censusCSVList == null || censusCSVList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
           Comparator<IndiaCensusCSV> censusCSVComparator = (o1,o2)-> ((o1.state.compareTo(o2.state)) < 0)?-1:1;
           Collections.sort(censusCSVList,censusCSVComparator);

           String sortedState = new Gson().toJson(censusCSVList);
           System.out.println(sortedState);
           return sortedState;
    }

    public String getSortedStateCode() throws CensusAnalyserException {

        if (censusStateCodeCSVList == null || censusStateCodeCSVList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
            Comparator<IndiaStateCodeCSV> codeCSVComparator = (o1,o2)-> ((o1.stateCode.compareTo(o2.stateCode))< 0)?-1:1;
            Collections.sort(censusStateCodeCSVList ,codeCSVComparator);

            String sortedStateCode = new Gson().toJson(censusStateCodeCSVList);
            System.out.println(sortedStateCode);
            return sortedStateCode;
    }
}
