package Connections;

import DBHandler.InputVerifier;
import DBHandler.Schemas;
import DBHandler.TableLandslag;
import DTO.DTO;
import FileReader.FileHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TableLandslagTest {

    private H2Connection h2 = new H2Connection();
    private Schemas schemas = new Schemas();

    private TableLandslag tableLandslag = new TableLandslag();

    private FileHandler fileHandler = new FileHandler();
    private String dbNameVMRussia = fileHandler.getItemFromSchemasList(0);

    private DTO dto = new DTO();


    @After
    public void tearDown() {
        schemas.deleteSchema(h2, dbNameVMRussia);

    }


    //TODO dto vil ikke lagre output data av en eller annen grunn
    @Test
    public void testIfTableContentExists() {
        //tableLandslag.createTableLandslag(h2);
        //tableLandslag.createDefaultLandslags(h2);

        try {
            //tableLandslag.displayData(h2, "all");
            //assertEquals(5, dto.getLandslagResultsetList().size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    @Test
    public void testIfNewLandslagIscreated() {

    }

    @Test
    public void testIfcreatesDefaultLandslags() {

    }

    @Test
    public void testIfUpdatesRanking() {

    }

    @Test
    public void testIfdeletesRow() {

    }
}
