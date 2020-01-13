package censusanalyser;

public class CensusDAO {

    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;
    public String state;
    public String stateId;
    public int housingUnits;
    public double waterArea;
    public double landArea;
    public double housingDensity;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV){
        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public CensusDAO(USCensusDataCSV usCensusDataCSV){
        stateId = usCensusDataCSV.stateId;
        state = usCensusDataCSV.state;
        population = usCensusDataCSV.population;
        housingUnits = usCensusDataCSV.housingUnits;
        areaInSqKm = (int) usCensusDataCSV.totalArea;
        waterArea = usCensusDataCSV.waterArea;
        landArea = usCensusDataCSV.landArea;
        densityPerSqKm = (int) usCensusDataCSV.populationDensity;
        housingDensity = usCensusDataCSV.housingDensity;
    }
}