package censusanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class CensusAnalyser {

    public enum Country{INDIA,US}

    Map<String,CensusDAO> censusDAOMap = new HashMap<>();

    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {

        censusDAOMap = CensusAdapterFactory.getCensusData(country,csvFilePath);
        return censusDAOMap.size();
    }

    public String getSortedStates() throws CensusAnalyserException {

        if (censusDAOMap == null || censusDAOMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }

        List<CensusDAO> censusList = censusDAOMap.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> censusCSVComparator = (o1,o2)-> ((o1.state.compareTo(o2.state)) < 0)?-1:1;
        Collections.sort(censusList,censusCSVComparator);
        String sortedState = new Gson().toJson(censusList);
        System.out.println(sortedState);
        return sortedState;
    }

    public String getSortedStateCode() throws CensusAnalyserException {

        if (censusDAOMap == null || censusDAOMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
            List<CensusDAO> censusList = censusDAOMap.values().stream().collect(Collectors.toList());
            Comparator<CensusDAO> codeCSVComparator = (o1,o2)-> ((o1.stateCode.compareTo(o2.stateCode)) < 0)?-1:1;
            Collections.sort(censusList,codeCSVComparator);
            String sortedStateCode = new Gson().toJson(censusList);
            System.out.println(sortedStateCode);
            return sortedStateCode;
    }

    public String getSortedPopulatedStates() throws CensusAnalyserException {

        if (censusDAOMap == null || censusDAOMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List<CensusDAO> censusList = censusDAOMap.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> censusCSVComparator = (o1,o2)-> ((o1.population - (o2.population)) > 0)?-1:1;
        Collections.sort(censusList,censusCSVComparator);
        String sortedPopulationState = new Gson().toJson(censusList);
        System.out.println(sortedPopulationState);
        return sortedPopulationState;
    }

    public String getSortedPopulatedStatesByDensity() throws CensusAnalyserException {

        if (censusDAOMap == null || censusDAOMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List<CensusDAO> censusList = censusDAOMap.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> censusCSVComparator = (o1,o2)-> ((o1.densityPerSqKm - (o2.densityPerSqKm)) > 0)?-1:1;
        Collections.sort(censusList,censusCSVComparator);
        String sortedPopulationStateByDensity = new Gson().toJson(censusList);
        System.out.println(sortedPopulationStateByDensity);
        return sortedPopulationStateByDensity;
    }

    public String getSortedPopulatedStatesByArea() throws CensusAnalyserException {

        if (censusDAOMap == null || censusDAOMap.size() == 0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List<CensusDAO> censusList = censusDAOMap.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> censusCSVComparator = (o1,o2)-> ((o1.areaInSqKm - (o2.areaInSqKm)) > 0)?-1:1;
        Collections.sort(censusList,censusCSVComparator);
        String sortedStatesByArea = new Gson().toJson(censusList);
        System.out.println(sortedStatesByArea);
        return sortedStatesByArea;
    }
}
