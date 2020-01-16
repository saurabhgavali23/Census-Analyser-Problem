package censusanalyser;

public class CensusAnalyserException extends Exception {

    ExceptionType type;

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE, INCORRECT_FILE_DATA, NO_CENSUS_DATA, INVALID_COUNTRY;
    }

    public CensusAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
