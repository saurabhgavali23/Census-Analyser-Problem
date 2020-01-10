package censusanalyser;

public class IndiaStateCodeDAO {

    public String stateCode;
    public int tin;
    public String stateName;
    public int srNo;

    public IndiaStateCodeDAO(IndiaStateCodeCSV indiaStateCodeCSV) {
        srNo = indiaStateCodeCSV.srNo;
        stateName = indiaStateCodeCSV.stateName;
        tin = indiaStateCodeCSV.tin;
        stateCode = indiaStateCodeCSV.stateCode;
    }
}
