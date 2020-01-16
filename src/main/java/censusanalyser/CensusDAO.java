package censusanalyser;

public class CensusDAO {

    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;
    public String state;
    public String stateCode;
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
        stateCode = usCensusDataCSV.stateCode;
        state = usCensusDataCSV.state;
        population = usCensusDataCSV.population;
        housingUnits = usCensusDataCSV.housingUnits;
        areaInSqKm = (int) usCensusDataCSV.totalArea;
        waterArea = usCensusDataCSV.waterArea;
        landArea = usCensusDataCSV.landArea;
        densityPerSqKm = (int) usCensusDataCSV.populationDensity;
        housingDensity = usCensusDataCSV.housingDensity;
    }

    public Object getCensusDTO(CensusAnalyser.Country country) {
        if(country.equals(CensusAnalyser.Country.US))
            return new USCensusDataCSV(state,stateCode,population,housingUnits,areaInSqKm,waterArea,landArea,densityPerSqKm,housingDensity);
        return new IndiaCensusCSV(state,areaInSqKm,densityPerSqKm,population);
    }
}
