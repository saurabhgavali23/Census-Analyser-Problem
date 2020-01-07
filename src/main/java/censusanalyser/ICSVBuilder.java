package censusanalyser;

import csvbuilderexception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {

    public Iterator<E> getIteratorCsvFile(Reader reader, Class cClass) throws CSVBuilderException;
}
