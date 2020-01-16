package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusDataCSV {

    @CsvBindByName(column = "State Id" , required = true)
    public String stateCode;

    @CsvBindByName(column = "State" , required = true)
    public String state;

    @CsvBindByName(column = "Population" , required = true)
    public int population;

    @CsvBindByName(column = "Housing units" , required = true)
    public int housingUnits;

    @CsvBindByName(column = "Total area" , required = true)
    public double totalArea;

    @CsvBindByName(column = "Water area" , required = true)
    public double waterArea;

    @CsvBindByName(column = "Land area" , required = true)
    public double landArea;

    @CsvBindByName(column = "Population Density" , required = true)
    public double  populationDensity;

    @CsvBindByName(column = "Housing Density" , required = true)
    public double housingDensity;


    @Override
    public String toString() {
        return "USCensusDataCSV{" +
                "stateCode='" + stateCode + '\'' +
                ", state='" + state + '\'' +
                ", population=" + population +
                ", housingUnits=" + housingUnits +
                ", totalArea=" + totalArea +
                ", waterArea=" + waterArea +
                ", landArea=" + landArea +
                ", populationDensity=" + populationDensity +
                ", housingDensity=" + housingDensity +
                '}';
    }

    public USCensusDataCSV() {
    }

    public USCensusDataCSV(String state, String stateCode, int population, int housingUnits, int areaInSqKm, double waterArea, double landArea, int densityPerSqKm, double housingDensity) {
        this.state = state;
        this.stateCode = stateCode;
        this.population = population;
        this.housingUnits = housingUnits;
        this.totalArea = areaInSqKm;
        this.waterArea = waterArea;
        this.landArea = landArea;
        this.populationDensity = densityPerSqKm;
        this.housingDensity = housingDensity;
    }
}
