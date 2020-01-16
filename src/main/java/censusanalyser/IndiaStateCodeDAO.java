package censusanalyser;

public class IndiaStateCodeDAO {

    public String stateCode;
    public int tin;
    public String state;
    public int srNo;

    public IndiaStateCodeDAO(IndiaStateCodeCSV indiaStateCodeCSV) {
        srNo = indiaStateCodeCSV.srNo;
        state = indiaStateCodeCSV.state;
        tin = indiaStateCodeCSV.tin;
        stateCode = indiaStateCodeCSV.stateCode;
    }
}
