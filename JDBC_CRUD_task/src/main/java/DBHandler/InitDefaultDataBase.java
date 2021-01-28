package DBHandler;

import Connections.DbConnection;
import FileReader.FileHandler;

import java.util.Scanner;

public class InitDefaultDataBase {


    public InitDefaultDataBase() {
    }

    private DbConnection dbConnection;
    private Schemas schemas;
    private TableLandslag landslag;
    private TableSpillere spiller;
    private FileHandler fileHandler;
    private TableKlubblag klubblag;
    private Scanner scanner = new Scanner(System.in);


    public void setup() {

        dbConnection = new DbConnection();
        schemas = new Schemas();
        fileHandler = new FileHandler();
        landslag = new TableLandslag();
        spiller = new TableSpillere();
        klubblag = new TableKlubblag();

        String dbNAmeVMrussia = fileHandler.getItemFromSchemasList(0);

        if (schemas.schemaExists(dbConnection, dbNAmeVMrussia)) {
            System.out.println("Connected to database '" + dbNAmeVMrussia + "'..\n");
            dbConnection.setDataBaseName(dbNAmeVMrussia);
        } else if (!schemas.schemaExists(dbConnection, dbNAmeVMrussia)) {
            schemas.createSchema(dbConnection, dbNAmeVMrussia);
            dbConnection.setDataBaseName(dbNAmeVMrussia);
            System.out.println("Connected to database '" + dbNAmeVMrussia + "'..\n");

            //create tables
            landslag.createTableLandslag(dbConnection);
            spiller.createTable(dbConnection);
            klubblag.createTable(dbConnection);
            System.out.println();

            //Default data clubs
            klubblag.createDefaultKlubblags(dbConnection);
            System.out.println();
            //default data Countries
            landslag.createDefaultLandslags(dbConnection);
            System.out.println();
            //default players
            spiller.createDefaultSpillere(dbConnection);

        }

    }




}
