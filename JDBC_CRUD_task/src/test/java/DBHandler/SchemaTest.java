package DBHandler;

import Connections.H2Connection;
import FileReader.FileHandler;
import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class SchemaTest {
    private H2Connection h2connection = new H2Connection();
    private Schemas schemas = new Schemas();
    private FileHandler fh = new FileHandler();


    @After
    public void tearDown(){
        String dbNameVMRussia = fh.getItemFromSchemasList(0);
        schemas.deleteSchema(h2connection, dbNameVMRussia);
    }


    @Test
    public void testConnecting(){
        String dbNameVMRussia = fh.getItemFromSchemasList(0);
        schemas.createSchema(h2connection,dbNameVMRussia);

        assertTrue(schemas.isSchemaVMRussiaCreated());
    }

}
