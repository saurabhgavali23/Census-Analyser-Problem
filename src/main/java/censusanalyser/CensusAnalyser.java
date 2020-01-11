package censusanalyser;

import com.google.gson.Gson;
import csvbuilder.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class CensusAnalyser {

    List<IndiaCensusDAO> censusCSVDAOList = null;
    List<IndiaStateCodeDAO> censusStateCodeDAOList = null;
    List<USCensusDAO> usCensusCSVDAOList = null;

    public CensusAnalyser() {
        this.censusCSVDAOList = new ArrayList<IndiaCensusDAO>();
        this.censusStateCodeDAOList = new ArrayList<IndiaStateCodeDAO>();
        this.usCensusCSVDAOList = new ArrayList<USCensusDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {

        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> censusList = csvBuilder.getListCsvFile(reader, IndiaCensusCSV.class);
            int i=0;
            while (i < censusList.size()){
                this.censusCSVDAOList.add(new IndiaCensusDAO(censusList.get(i)));
                i++;
            }
            return censusCSVDAOList.size();

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
            List<IndiaStateCodeCSV> censusStateCodeList = csvBuilder.getListCsvFile(reader,IndiaStateCodeCSV.class);

            censusStateCodeList.stream().filter(censusStateCode ->
                    censusStateCodeDAOList.add(new IndiaStateCodeDAO(censusStateCode)))
                    .collect(Collectors.toList());

            return censusStateCodeDAOList.size();

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

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {

        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<USCensusDataCSV> censusUSCensusList = csvBuilder.getListCsvFile(reader,USCensusDataCSV.class);

           censusUSCensusList.stream().filter(usCensusData ->
                    usCensusCSVDAOList.add(new USCensusDAO(usCensusData)))
                    .collect(Collectors.toList());

            return censusUSCensusList.size();

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

        if (censusCSVDAOList == null || censusCSVDAOList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
           Comparator<IndiaCensusDAO> censusCSVComparator = (o1,o2)-> ((o1.state.compareTo(o2.state)) < 0)?-1:1;
           Collections.sort(censusCSVDAOList,censusCSVComparator);

           String sortedState = new Gson().toJson(censusCSVDAOList);
           System.out.println(sortedState);
           return sortedState;
    }

    public String getSortedStateCode() throws CensusAnalyserException {

        if (censusStateCodeDAOList == null || censusStateCodeDAOList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
            Comparator<IndiaStateCodeDAO> codeCSVComparator = (o1,o2)-> ((o1.stateCode.compareTo(o2.stateCode)) < 0)?-1:1;
            Collections.sort(censusStateCodeDAOList,codeCSVComparator);

            String sortedStateCode = new Gson().toJson(censusStateCodeDAOList);
            System.out.println(sortedStateCode);
            return sortedStateCode;
    }

    public String getSortedPopulatedStates() throws CensusAnalyserException {

        if (censusCSVDAOList == null || censusCSVDAOList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusCSVComparator = (o1,o2)-> ((o1.population - (o2.population)) > 0)?-1:1;
        Collections.sort(censusCSVDAOList,censusCSVComparator);

        String sortedPopulationState = new Gson().toJson(censusCSVDAOList);
        System.out.println(sortedPopulationState);
        return sortedPopulationState;
    }

    public String getSortedPopulatedStatesByDensity() throws CensusAnalyserException {

        if (censusCSVDAOList == null || censusCSVDAOList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusCSVComparator = (o1,o2)-> ((o1.densityPerSqKm - (o2.densityPerSqKm)) > 0)?-1:1;
        Collections.sort(censusCSVDAOList,censusCSVComparator);

        String sortedPopulationStateByDensity = new Gson().toJson(censusCSVDAOList);
        System.out.println(sortedPopulationStateByDensity);
        return sortedPopulationStateByDensity;
    }

    public String getSortedPopulatedStatesByArea() throws CensusAnalyserException {

        if (censusCSVDAOList == null || censusCSVDAOList.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusCSVComparator = (o1,o2)-> ((o1.areaInSqKm - o2.areaInSqKm) > 0)?-1:1;
        Collections.sort(censusCSVDAOList,censusCSVComparator);

        String sortedStatesByArea = new Gson().toJson(censusCSVDAOList);
        System.out.println(sortedStatesByArea);
        return sortedStatesByArea;
    }
}
