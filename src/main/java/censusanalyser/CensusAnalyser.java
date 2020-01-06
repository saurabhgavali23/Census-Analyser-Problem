package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {

        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            Iterator<IndiaCensusCSV> censusCSVIterator = new OpenCSVBuilder().getIteratorCsvFile(reader,IndiaCensusCSV.class);
            return getCount(censusCSVIterator);

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (IllegalStateException e){
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException r){
            throw new CensusAnalyserException(r.getMessage(),CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
    }

    public int loadIndiaStateCode(String csvFilePath) throws CensusAnalyserException {

        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            Iterator<IndiaStateCodeCSV> censusCSVIterator = new OpenCSVBuilder().getIteratorCsvFile(reader,IndiaStateCodeCSV.class);
            return getCount(censusCSVIterator);

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (IllegalStateException e){
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException r){
            throw new CensusAnalyserException(r.getMessage(),CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
    }

    private <E> int getCount(Iterator<E> censusCSVIterator) {
        Iterable<E> csvIterable = ()-> censusCSVIterator;
        int numOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
        return numOfEateries;
    }
}
