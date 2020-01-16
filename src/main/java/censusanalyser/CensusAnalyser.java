package censusanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

public class CensusAnalyser {

    public enum Country {INDIA, US}

    private Country country;
    Map<String, CensusDAO> censusDAOMap = new HashMap<>();

    public CensusAnalyser(Country country) {
        this.country = country;
    }

    public int loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        censusDAOMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        return censusDAOMap.size();
    }

    public String getSortedStates() throws CensusAnalyserException {

        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List censusList = censusDAOMap.values().stream()
                .sorted(Comparator.comparing(censusDAO -> censusDAO.state))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(toCollection(ArrayList::new));
        String sortedState = new Gson().toJson(censusList);
        return sortedState;
    }

    public String getSortedStateCode() throws CensusAnalyserException {

        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List censusList = censusDAOMap.values().stream()
                .sorted(Comparator.comparing(censusDAO -> censusDAO.stateCode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(toCollection(ArrayList::new));
        String sortedStateCode = new Gson().toJson(censusList);
        return sortedStateCode;
    }

    public String getSortedPopulatedStates() throws CensusAnalyserException {

        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List censusList = censusDAOMap.values().stream()
                .sorted(Comparator.comparing(censusDAO -> censusDAO.population))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(toCollection(ArrayList::new));
        Collections.reverse(censusList);
        String sortedPopulationState = new Gson().toJson(censusList);
        return sortedPopulationState;
    }

    public String getSortedPopulatedStatesByDensity() throws CensusAnalyserException {

        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List censusList = censusDAOMap.values().stream()
                .sorted(Comparator.comparing(censusDAO -> censusDAO.densityPerSqKm))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(toCollection(ArrayList::new));
        Collections.reverse(censusList);
        String sortedPopulationStateByDensity = new Gson().toJson(censusList);
        return sortedPopulationStateByDensity;
    }

    public String getSortedPopulatedStatesByArea() throws CensusAnalyserException {

        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List censusList = censusDAOMap.values().stream()
                .sorted(Comparator.comparing(censusDAO -> censusDAO.areaInSqKm))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(toCollection(ArrayList::new));
        Collections.reverse(censusList);
        String sortedStatesByArea = new Gson().toJson(censusList);
        return sortedStatesByArea;
    }
}
