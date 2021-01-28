package Connections;

import DBHandler.Schemas;
import DBHandler.TableKlubblag;
import DTO.DTO;
import FileReader.FileHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TableKlubblagTest {
    H2Connection h2 = new H2Connection();
    Schemas schemas = new Schemas();
    TableKlubblag klubb = new TableKlubblag();
    FileHandler fileHandler = new FileHandler();
    String dbNameVMRussia =  fileHandler.getItemFromSchemasList(0);
    DTO dto = new DTO();

    @Before
    public void setup() {
        schemas.createSchema(h2,dbNameVMRussia);
    }

    @After
    public void tearDown() {
        schemas.deleteSchema(h2, dbNameVMRussia);
    }

    @Test
    public void testIfTableKlubblagIsCreated() {
        klubb.createTable(h2);
        ArrayList<String> tables = klubb.getTableNames(h2);

        assertTrue(tables.contains("KLUBBLAG"));
    }

    @Test
    public void testIfpopulatesTableKlubblag() {

        klubb.createTable(h2);
        klubb.createDefaultKlubblags(h2);

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testIfNewKlubblagIsCreated(){

    }
}
