import DTO.DtoServer;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.*;

/**
 * By: Roosbeh Morandi
 * date: 27.10.17
 * <p>
 * This class handles output from database
 * and adds the Resultset of Metadata /data
 * to a DTO.DtoServer arrayList.
 */


public class AccessTables {

    private String query;
    private String query2 = "USE Time_edit";

    public AccessTables() {
    }


    /**
     *
     * @param dbConnection the connection to database
    // * @param input the subjectCode
     * @throws Exception if connection fails
     * @throws SQLException if sql error occurs
     */
    public void getSingleSubject(DbConnection dbConnection, String input) throws Exception {
        query = "SELECT SUBCOD, CAMPUS, LECTUREFRM, PRGRBEL, DURATI, PARTICIPPANTS " +
                "FROM subjects WHERE SUBCOD = '" + input + "'";
        getResultsetData(dbConnection, query2, query);

    }


    /**
     * @param dbConnection  to connect to database
     * @throws Exception if..connection fails
     * @throws SQLException if sql error occurs
     */
    public void getAllSubjects(DbConnection dbConnection) throws Exception {
        query = "SELECT SUBCOD, CAMPUS, LECTUREFRM, PRGRBEL, DURATI, PARTICIPPANTS " +
                "FROM subjects";
        getResultsetData(dbConnection, query2, query);
    }


    /**
     * Gets the Resultset data and metadata and adds it to
     * the Data transfer object to Innlev2Client from server
     *
     * @param dbConnection
     * @param query2       specifies DatabaseName to use
     * @param query       String of Sql Select query
     * @throws Exception    if a database access error occurs
     * @throws SQLException if sql error occurs
     */
    private void getResultsetData(DbConnection dbConnection, String query2, String query) throws Exception {
        try (Connection con = dbConnection.getConnection();
            Statement stmt = con.createStatement()){
            stmt.executeUpdate(query2);
            ResultSet res = stmt.executeQuery(query);
            ResultSetMetaData resMeta = res.getMetaData();
            DtoServer.add(res, resMeta);
        }
    }



}




