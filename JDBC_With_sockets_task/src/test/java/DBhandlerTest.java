
import org.junit.Test;
import org.junit.*;


import java.io.IOException;
import java.sql.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @By: Roosbeh Morandi
 *
 *
 * Testing Database communication using H2.
 * Allows the database to be embedded inside the
 * java application (inMemory)
 * without using localServer.
 *
 * Requires to comment out the contents inside
 * the constructor of DBhandler
 */


public class DBhandlerTest {

    private H2DbConnection h2connection = new H2DbConnection();

    public DBhandlerTest() throws Exception {
    }




    @After
    public void tearDown() {
        deleteTable();
    }



    /**
     * Run this method first to have a table to work with.
     * Before testing this you must comment out method inside @before
     *
     */

    @Test
    public void tableisCreatedWithDataFromFile() throws Exception {
        //arrange
        DBhandler dBhandler = new DBhandler(h2connection);
        //act
        dBhandler.createTableSubjects(h2connection);
        //Verify(when().then());
    }


    /**
     * Requires to comment out 'stmt.executeUpdate(query2) in Dbhandler.class
     * because this test doesnt have the same schema .
     */
    @Test
    public void tableIsfilledWithDataFromFile() {
        try {
            //arrange
            DBhandler dBhandler = new DBhandler(h2connection);
            dBhandler.createTableSubjects(h2connection);
            //act
            dBhandler.fillTableSubjectsWithDataFromFile(h2connection);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @Test
    public void deleteTable() {

        try {
            Connection connection = h2connection.getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute("drop table Subjects");
            System.out.println("\nAll data deleted from database after test ..");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }




}
