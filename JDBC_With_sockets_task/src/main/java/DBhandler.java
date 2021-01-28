
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @By: Roosbeh Morandi
 *
 * This class creates the Database/schema 'Time_edit'
 * and table(s) and populates them.
 * First off you need to comment out the 'deleteSchemaTimeEdit'
 * if Server wont start. Once Schema is created its necessary
 * uncomment afterwards.
 *
 */


public class DBhandler {


    private MysqlDataSource dataSource;
    private FileHandler fileHandler;
    private String query;
    private String query2 = "USE Time_edit";
    private String tableName, subjectCode, campus, lectureForm, programBelonging, duration, participants;
    private String varchar1;


    /**
     * Constructor
     * @throws Exception if connection fails
     * @throws SQLException if sql error occurs
     */
    public DBhandler(ConnectionProvider dbConnection) throws Exception {
        //deleteSchemaTimeEdit();
        createSchema();
        createTableSubjects(dbConnection);
        fillTableSubjectsWithDataFromFile(dbConnection);
    }


    /**
     * Creates Database 'Time_Edit'
     *
     * @throws SQLException if sql query is wrong .
     */
     void createSchema()throws SQLException {
        dataSource = new MysqlDataSource();
        try (Connection con = dataSource.getConnection()) {
            Statement stmt = con.createStatement();
            query = "CREATE SCHEMA IF NOT EXISTS time_edit";
            stmt.executeUpdate(query);
            System.out.println("--New Schema 'TimeEdit' created--");

        }

    }


    /**
     * Fills metadata in table 'Subjects' from file 'Properties'.
     * @param dbConnection Connection for database
     * @throws Exception  if connection fails
     * @throws SQLException if sql error occurs.
     */
    public void createTableSubjects(ConnectionProvider dbConnection) throws Exception {
        try (Connection con = dbConnection.getConnection()) {
            Statement stmt = con.createStatement();
            fileHandler = new FileHandler();
            tableName = fileHandler.getList(25);
            subjectCode = fileHandler.getList(0);
            campus = fileHandler.getList(1);
            lectureForm = fileHandler.getList(2);
            programBelonging = fileHandler.getList(3);
            duration = fileHandler.getList(4);
            participants = fileHandler.getList(5);
            varchar1 = fileHandler.getList(24);

            query = "CREATE TABLE  IF NOT EXISTS " + tableName + "( " +
                    "  " + subjectCode + "       " + varchar1 + " unique not null," +
                    "  " + campus + "            " + varchar1 + " default 'null'," +
                    "  " + lectureForm + "       " + varchar1 + " default 'null'," +
                    "  " + programBelonging + "  " + varchar1 + " default 'null'," +
                    "  " + duration + "          " + varchar1 + " default 'null'," +
                    "  " + participants + "      " + varchar1 + " default 'null'," +
                    "  PRIMARY KEY (" + subjectCode + ")" +
                    ")";

            //comment query2 out during testing
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query);
            System.out.println("--New Table 'Subjects' created--");
        }
    }


    /**
     * Fills  data in table 'Subjects' from file 'Properties'.
     * @param dbConnection to connect to a database
     * @throws SQLException and SQLException if sql error occurs
     */
    public void fillTableSubjectsWithDataFromFile(ConnectionProvider dbConnection)throws Exception  {
        query = "INSERT INTO " + tableName + "(" + subjectCode + ", " + campus + ", "
                + lectureForm + ", " + programBelonging + ", " + duration + ", " + participants + ") VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection con = dbConnection.getConnection()) {
            fileHandler = new FileHandler();
            PreparedStatement stmt = con.prepareStatement(query);

            int data = 6; //(from 6 and up) indexes from list
            int rows = 0;//number of rows affected

            //populating 3 rows
            for(int i = 0;i < 3;i++){
                // 6 colums
                for(int j = 1; j <= 6; j++){
                    stmt.setString(j, fileHandler.getList(data));
                    data++;
                }
                //comment this out during test
                 stmt.executeUpdate(query2);
                rows +=  stmt.executeUpdate();
            }
            System.out.println("(" + rows + " rows affected/filled)\n\n");
        }
    }


    /**
     *Deletes existing schema with same name for each time the
     *server needs to restart. Uncomment this method in the constructor
     *after first startup with server.
     *
     * @throws Exception if Connection fails or if execution fails
     */
    public void deleteSchemaTimeEdit()throws Exception {
        DbConnection dbConnection = new DbConnection();
        query = "drop schema Time_edit";
        try (Connection con = dbConnection.getConnection()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query);
            System.out.println("Old Schema deleted");
        }
    }
}
