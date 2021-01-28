import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @By Roosbeh Morandi
 *
 * class handles CRUD operations for table Subjects
 *
 */

public class SubjectsTest {

    H2DbConnection h2DbConnection = new H2DbConnection();

    public SubjectsTest() throws Exception {
    }


    /**
     * Creates the table and fills data
     */
    @Before
    public void createTablesAndFill() {
        try {
            DBhandler dBhandler = new DBhandler(h2DbConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Deletes all data from Database after test is done
     */
    @After
    public void tearDown() {
        try {
            DBhandlerTest db = new DBhandlerTest();
            db.deleteTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Requirements before running testing:
     * -commment out 'preStmt.executeUpdate(query2)'
     * in method subjects.preparedStatementForUpdating()
     *
     * Updates subjectCode with a new value(new subjectCode).
     */
    @Test
    public void subjectIsUpdated() {
        Subjects sub = new Subjects();
        try {
            sub.updateSubjectCode(h2DbConnection, "pgr101", "pgr200");
            sub.updateParticiants(h2DbConnection, "DisneyLand", "pgr200");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Requirements before running testing:
     * -commment out 'preStmt.executeUpdate(query2)'
     * inside method subjects.deleteSubject().
     *
     * Deletes a subject
     */
    @Test
    public void subjectIsDeleted() {
        Subjects sub = new Subjects();
        try {
            sub.deleteSubject(h2DbConnection, "pgr200");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

