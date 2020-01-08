package censusanalyser;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import csvbuilder.*;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {

        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getIteratorCsvFile(reader,IndiaCensusCSV.class);
            return getCount(censusCSVIterator);

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
            Iterator<IndiaStateCodeCSV> censusCSVIterator = csvBuilder.getIteratorCsvFile(reader,IndiaStateCodeCSV.class);
            return getCount(censusCSVIterator);

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

    private <E> int getCount(Iterator<E> censusCSVIterator) {
        Iterable<E> csvIterable = ()-> censusCSVIterator;
        int numOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
        return numOfEateries;
    }

    public static String getSortedStates(String csvFilePath){

       try(Reader reader = new BufferedReader(new FileReader(csvFilePath));){
           ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
           Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getIteratorCsvFile(reader,IndiaCensusCSV.class);
           List list = new ArrayList();

           while (censusCSVIterator.hasNext()){
               list.add(censusCSVIterator.next());
           }

           Comparator<IndiaCensusCSV> censusCSVComparator = (o1,o2)-> ((o1.toString().compareTo(o2.toString())) < 0)?-1:1;
           Collections.sort(list,censusCSVComparator);

           String recoreds = new Gson().toJson(list);
           System.out.println(recoreds);
           return recoreds;

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (CSVBuilderException e) {
           e.printStackTrace();
       }
       return null;
    }
}
