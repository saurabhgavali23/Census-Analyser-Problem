package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String NULL_CSV_FILE = "./src/test/resources/NullCSV.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String INCORRECT_FILE_DATA = "./src/test/resources/IncorrectData.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String INCORRECT_INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IncorrectIndiaStateCode.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "/home/admin1/Documents/CensusAnalyser/CensusAnalyser/src/test/resources/USCensusData .csv";

    // Test Cases For IndianCensusCSV File
    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            int numOfRecords = censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithIncorrectDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INCORRECT_FILE_DATA);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithIncorrectHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INCORRECT_FILE_DATA);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }
    }

    // Test Cases For Indian State Code Csv
    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            int stateCode = censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(29, stateCode);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaStateCode_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithIncorrectDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INCORRECT_INDIA_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithIncorrectHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INCORRECT_INDIA_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }
    }

    // Test Case For Sort IndiaCensusCSV File and return Json Format
    @Test
    public void givenIndiaStates_ShouldReturnSortedData() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        try {
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedStates = censusAnalyser.getSortedStates();
            IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(sortedStates, IndiaCensusCSV[].class);
            Assert.assertEquals(indiaCensusCSVS[0].state, "Andhra Pradesh");
            Assert.assertEquals(indiaCensusCSVS[28].state, "West Bengal");
        } catch (CensusAnalyserException e) {
        }
    }

    // Test Case For Pass Null IndiaCensusCSV File
    @Test
    public void givenNullIndiaStatesCSV_ShouldReturnNull() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        try {
            censusAnalyser.loadCensusData(NULL_CSV_FILE, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedStates = censusAnalyser.getSortedStates();
            IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(sortedStates, IndiaCensusCSV[].class);
            Assert.assertEquals(indiaCensusCSVS[0].state, "Andhra Pradesh");
            Assert.assertEquals(indiaCensusCSVS[28].state, "West Bengal");
        } catch (CensusAnalyserException e) {
        }
    }

    // Test Cases For Sort IndiaStateCodeCSV File and return Json Formate
    @Test
    public void givenIndiaStatesCodeCSV_ShouldReturnSortedData() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        try {
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedStatesCode = censusAnalyser.getSortedStateCode();
            IndiaStateCodeCSV[] indiaStateCodeCSVS = new Gson().fromJson(sortedStatesCode, IndiaStateCodeCSV[].class);
            Assert.assertEquals(indiaStateCodeCSVS[0].state, "Andhra Pradesh");
            Assert.assertEquals(indiaStateCodeCSVS[28].state, "West Bengal");
        } catch (CensusAnalyserException e) {
        }
    }

    // Test Case For Pass Null IndiaStateCodeCSV File
    @Test
    public void givenNullIndiaStatesCodeCSV_ShouldReturnNull() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        try {
            censusAnalyser.loadCensusData(NULL_CSV_FILE, NULL_CSV_FILE);
            String sortedStatesCode = censusAnalyser.getSortedStateCode();
            IndiaStateCodeCSV[] indiaStateCodeCSVS = new Gson().fromJson(sortedStatesCode, IndiaStateCodeCSV[].class);
            Assert.assertEquals(indiaStateCodeCSVS[0].state, "Andhra Pradesh New");
            Assert.assertEquals(indiaStateCodeCSVS[36].state, "West Bengal");
        } catch (CensusAnalyserException e) {
        }
    }

    // Test Cases For IndiaCensusState Population
    @Test
    public void givenIndiaStatesPopulation_ShouldReturnSortedData() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        try {
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedPopulatedStates = censusAnalyser.getSortedPopulatedStates();
            IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(sortedPopulatedStates, IndiaCensusCSV[].class);
            Assert.assertEquals(indiaCensusCSVS[0].state, "Uttar Pradesh");
            Assert.assertEquals(indiaCensusCSVS[28].state, "Sikkim");
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenNullIndiaStatesPopulation_ShouldReturnSortedData() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        try {
            censusAnalyser.loadCensusData(NULL_CSV_FILE, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedPopulatedStates = censusAnalyser.getSortedPopulatedStates();
            IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(sortedPopulatedStates, IndiaCensusCSV[].class);
            Assert.assertEquals(indiaCensusCSVS[0].state, "Uttar Pradesh");
            Assert.assertEquals(indiaCensusCSVS[28].state, "Sikkim");
        } catch (CensusAnalyserException e) {
        }
    }

    // Test Cases For IndiaCensusState Density
    @Test
    public void givenIndiaStatesCSV_ShouldReturnSortedDataByDensity() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        try {
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedPopulatedStatesByDensity = censusAnalyser.getSortedPopulatedStatesByDensity();
            IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(sortedPopulatedStatesByDensity, IndiaCensusCSV[].class);
            Assert.assertEquals(indiaCensusCSVS[0].state, "Bihar");
            Assert.assertEquals(indiaCensusCSVS[28].state, "Arunachal Pradesh");
        } catch (CensusAnalyserException e) {
        }
    }

    // Test Cases For IndiaCensusState Area
    @Test
    public void givenIndiaStatesCSV_ShouldReturnSortedDataByArea() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        try {
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedPopulatedStatesByArea = censusAnalyser.getSortedPopulatedStatesByArea();
            IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(sortedPopulatedStatesByArea, IndiaCensusCSV[].class);
            Assert.assertEquals(indiaCensusCSVS[0].state, "Rajasthan");
            Assert.assertEquals(indiaCensusCSVS[28].state, "Goa");
        } catch (CensusAnalyserException e) {
        }
    }

    // Test Case For USCensusCSV File
    @Test
    public void givenUSCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            int numOfRecords = censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }
}
