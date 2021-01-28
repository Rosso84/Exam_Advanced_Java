import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Subjects {


    private String query;
    protected String query2 = "USE Time_edit";


    public Subjects() {
    }



    /**
     *
     * @param dbCon the connection to your database
     * @param value the new value user wish to replace existing data with
     * @param subjectCode The subject to update
     * @throws Exception if connection fails
     * @throws SQLException if sql error occurs
     */
    public void updateSubjectCode(DbConnection dbCon, String value, String subjectCode) throws Exception {
        query = "UPDATE Subjects SET SUBCOD = ? WHERE SUBCOD = ?";
        preparedStatementForUpdating(dbCon, value, subjectCode);
    }


    /**
     *
     * @param dbCon the connection to your database
     * @param value the new value user wish to replace existing data with
     * @param subjectCode The subject to update
     * @throws Exception if connection fails
     * @throws SQLException if sql error occurs
     */
    public void updateCampus(DbConnection dbCon, String value, String subjectCode) throws Exception {
        query = "UPDATE Subjects SET CAMPUS = ?" +
                "WHERE SUBCOD = ?";
        preparedStatementForUpdating(dbCon, value, subjectCode);
    }


    /**
     *
     * @param dbCon the connection to your database
     * @param value the new value user wish to replace existing data with
     * @param subjectCode The subject to update
     * @throws Exception if connection fails
     * @throws SQLException if sql error occurs
     */
    public void updateLectureForm(DbConnection dbCon, String value, String subjectCode) throws Exception {
        query = "UPDATE Subjects SET LECTUREFRM = ? WHERE SUBCOD = ?";
        preparedStatementForUpdating(dbCon, value, subjectCode);
    }


    /**
     *
     * @param dbCon the connection to your database
     * @param value the new value user wish to replace existing data with
     * @param subjectCode The subject to update
     * @throws Exception if connection fails
     * @throws SQLException if sql error occurs
     */
    public void updateProgramBelonging(DbConnection dbCon, String value, String subjectCode) throws Exception {
        query = "UPDATE Subjects SET PRGRBEL = ? WHERE SUBCOD = ?";
        preparedStatementForUpdating(dbCon, value, subjectCode);
    }


    /**
     *
     * @param dbCon the connection to your database
     * @param value the new value user wish to replace existing data with
     * @param subjectCode The subject to update
     * @throws Exception if connection fails
     * @throws SQLException if sql error occurs
     */
    public void updateDuration(DbConnection dbCon, String value, String subjectCode) throws Exception {
        query = "UPDATE Subjects SET durati = ? WHERE SUBCOD = ?";
        preparedStatementForUpdating(dbCon, value, subjectCode);
    }

    /**
     *
     * @param dbCon the connection to your database
     * @param value the new value user wish to replace existing data with
     * @param subjectCode The subject to update
     * @throws Exception if connection fails
     * @throws SQLException if sql error occurs
     */
    public void updateParticiants(DbConnection dbCon, String value, String subjectCode) throws Exception, SQLException {
        query = "UPDATE Subjects SET PARTICIPPANTS = ? WHERE SUBCOD = ?";
        preparedStatementForUpdating(dbCon, value, subjectCode);
    }



    /**
     *
     * @param dbCon connection to database
     * @param DTOlist containing all the  new columns of the table added by user
     * @throws Exception if Connection to database fails
     * @throws SQLException if sql error occurs
     */
    public void createNewSubjectThroughUserInput(DbConnection dbCon, ArrayList<String> DTOlist) throws Exception, SQLException {
        query = "INSERT INTO Subjects(SUBCOD, CAMPUS, LECTUREFRM, PRGRBEL, DURATI, PARTICIPPANTS)" +
                " values (?, ?, ?, ?, ?, ?)";
        try (Connection con = dbCon.getConnection()) {
            PreparedStatement preStmt = con.prepareStatement(query);
            int setString = 1;
            for (int i = 0; i < DTOlist.size(); i++){
                preStmt.setString(setString, DTOlist.get(i));
                setString++;
            }

            //comment out this preStmt before  testing.
            preStmt.executeUpdate(query2);
            int rows = preStmt.executeUpdate();
            System.out.println(" new column added (" + rows + " rows affected)");
        }
    }


    /**
     *
     * @param dbCon to connect to your database
     * @param value of what to update the table column
     * @param subjectCode of the subject to update
     * @throws Exception if connection fails
     * @throws java.sql.SQLException if sql error occurs
     */
    private void preparedStatementForUpdating(DbConnection dbCon, String value, String subjectCode) throws Exception {
        try (Connection con = dbCon.getConnection()) {
            PreparedStatement preStmt = con.prepareStatement(query);
            preStmt.setString(1, value);
            preStmt.setString(2, subjectCode);

            //comment this preStmt out before testing
            preStmt.executeUpdate(query2);
            int rows = preStmt.executeUpdate();
            System.out.println(" Subject updated (" + rows + " rows affected)\n");
        }
    }


    /**
     *  Deletes a subject from the database by a subjectCode
     *
     * @param dbConnection the connection to database
     * @param subjectCode the ID of the subject to delete
     * @throws Exception  if Connection fails
     * @throws SQLException if sql error occurs
     */
    public void deleteSubject(DbConnection dbConnection, String subjectCode) throws Exception, SQLException {
        query = "DELETE FROM Subjects " +
                "WHERE SUBCOD = ?";
        try (Connection connection = dbConnection.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, subjectCode);

            //Comment out this method before testing
            stmt.executeUpdate(query2);
            int rows = stmt.executeUpdate();
            System.out.println("\nSubject deleted successfully..");
            System.out.println("(" + rows + " rows affected)");
        }
    }



}
