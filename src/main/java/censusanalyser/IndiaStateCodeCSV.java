package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {

    @CsvBindByName(column = "SrNo", required = true)
    public int srNo;

    @CsvBindByName(column = "StateName", required = true)
    public String state;

    @CsvBindByName(column = "TIN", required = true)
    public int tin;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "SrNo='" + srNo + '\'' +
                ", StateName='" + state + '\'' +
                ", TIN='" + tin + '\'' +
                ", StateCode='" + stateCode + '\'' +
                '}';
    }
}
