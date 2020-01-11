package censusanalyser;

public class USCensusDAO {

    public String stateId;
    public String state;
    public int population;
    public int housingUnits;
    public double totalArea;
    public double waterArea;
    public double landArea;
    public double  populationDensity;
    public double housingDensity;

   public USCensusDAO(USCensusDataCSV usCensusDataCSV){

       stateId = usCensusDataCSV.stateId;
       state = usCensusDataCSV.state;
       population = usCensusDataCSV.population;
       housingUnits = usCensusDataCSV.housingUnits;
       totalArea = usCensusDataCSV.totalArea;
       waterArea = usCensusDataCSV.waterArea;
       landArea = usCensusDataCSV.landArea;
       populationDensity = usCensusDataCSV.populationDensity;
       housingDensity = usCensusDataCSV.housingDensity;
    }
}
