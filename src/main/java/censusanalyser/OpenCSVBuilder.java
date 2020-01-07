package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import csvbuilderexception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuilder<E> implements ICSVBuilder{

    public Iterator<E> getIteratorCsvFile(Reader reader, Class cClass) throws CSVBuilderException {

        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(cClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();

        }catch (IllegalStateException e){
            throw new CSVBuilderException(e.getMessage(),CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
