import DTO.DtoServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



/**
 * @By Roosbeh Morandi
 *
 * Before testing :
 * comment out all 'stmt.executeUpdate(query2)'
 * actions inside DBhandler.getResultset()
 * and allso comment out deleteSchemaTimeEdit() method inside constructor
 * of DBHandler.class but DO NOT comment out the rest inside constructor of DBHandler
 */


public class AccessTablesTest {

    H2DbConnection h2DbConnection = new H2DbConnection();

    public AccessTablesTest() throws Exception {
    }

    /**
     * Creates table and fllls data to test with
     */
    @Before
    public void createAndFillTable() {
        try {
            DBhandler dBhandler = new DBhandler(h2DbConnection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes all data after each test is done
     */
    @After
    public void deleteAfterwards() {
        DBhandlerTest db = null;
        try {
            db = new DBhandlerTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.deleteTable();
    }


    /**
     * Requirements before testing:
     * Comment out 'stmt.executeUpdate(query2)' method
     * inside 'AccessTables.getSingleSubject()'.
     * This allows you to test on H2's embedded database
     * instead of the current.
     *
     * <p>
     * <p>
     * Test Should fill data to DTOList if readable from Database
     */
    @Test
    public void readsSingleSubjectDatafromDatabase() {
        try {

            //arrange
            AccessTables ac = new AccessTables();
            DtoServer dtoServer = new DtoServer();

            //act
            ac.getSingleSubject(h2DbConnection, "Pgr200");

            //assert list is added
            assertEquals(6, dtoServer.getList().size()); // 6 columns to write to clientSocket from ServerSocket
            //assert right data is added
            assertTrue(dtoServer.getList().get(0).equals("SUBCOD"));
            assertTrue(dtoServer.getList().get(1).equals("CAMPUS"));
            assertTrue(dtoServer.getList().get(2).equals("LECTUREFRM"));
            assertTrue(dtoServer.getList().get(3).equals("PRGRBEL"));
            assertTrue(dtoServer.getList().get(4).equals("DURATI"));
            assertTrue(dtoServer.getList().get(5).equals("PARTICIPPANTS"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * If it is readable the DtoServer.list
     * should be filled with new data
     */
    @Test
    public void readsAllSubjectsFromDataBase(){

        try {

            //arrange
            AccessTables ac = new AccessTables();
            DtoServer dtoServer = new DtoServer();
            //act
            ac.getAllSubjects(h2DbConnection);
            //assert
            assertEquals(24, dtoServer.getList().size()); // 6 columns * 3 ROWS =  24 to write to clientSocket from ServerSocket

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
