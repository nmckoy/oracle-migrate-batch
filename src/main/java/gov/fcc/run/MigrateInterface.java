package gov.fcc.run;


public interface MigrateInterface {
    public static final String sqlTest               = "SELECT ACCOUNT_ID, PAYLOAD FROM DOCUMENT WHERE ACCOUNT_ID = 'KidVid_75650'";

    public static final String fromClause            = "DOCUMENT";
    public static final String selectClause          = "ACCOUNT_ID, PAYLOAD";
    public static final String whereClause           = "DOCUMENT_STATE = 'confirmed' AND last_update_ts > To_Date('22.07.2015 12:00:00', 'DD.MM.YYYY HH24:MI:SS') AND ROWNUM >= :min AND ROWNUM <= :max";
//    public static final String whereClause           = "DOCUMENT_STATE = 'confirmed' " +
//                                                       "AND ROWNUM &lt;= :min AND ROWNUM &lt;= :max";
    public static final String sortKey               = "ACCOUNT_ID";


    public static final String sqlSelectSomeGetCount = "SELECT Count(rownum) FROM DOCUMENT WHERE DOCUMENT_STATE = 'confirmed' " +
                                                        "AND last_update_ts > To_Date('22.07.2015 12:00:00', 'DD.MM.YYYY HH24:MI:SS')";
    public static final String sqlSelectAllGetCount  = "SELECT Count(*) FROM DOCUMENT WHERE DOCUMENT_STATE = 'confirmed'";



}
