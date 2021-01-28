package DBHandler;

import Connections.ConnectionProvider;
import Connections.DbConnection;
import FileReader.FileHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Schemas {

    private String query;
    private FileHandler filehandler = new FileHandler();

    private boolean schemaVMRussiaCreated = false;


    public void createSchema(ConnectionProvider dataSource, String schemaName) {
        filehandler.readFromFileSchemas();

        String databaseName = schemaName;

        query = "CREATE SCHEMA IF NOT EXISTS " + databaseName;
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement()) {

                stmt.executeUpdate(query);
                this.schemaVMRussiaCreated = true;
                System.out.println("New Schema '" + databaseName + "' created");

        } catch (SQLException sqlExc) {

            System.out.println(" failed while creating schema " + databaseName + " \nException:  " +
                    sqlExc.getMessage());
            sqlExc.printStackTrace();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void deleteSchema(ConnectionProvider dbConnection, String dbName) {
        query = "DROP SCHEMA IF EXISTS " + dbName;
        try (Connection con = dbConnection.getConnection();

             Statement stmt = con.createStatement()) {
                stmt.executeUpdate(query);
                this.schemaVMRussiaCreated = false;
                System.out.println("Old Schema '" + dbName + "' deleted");

        } catch (SQLException e) {
            System.out.println("Error while trying to delete Schema TimeEdit");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public boolean schemaExists(DbConnection dbConnection, String schemaName) {

        try (Connection connection = dbConnection.getConnection()) {

            ResultSet resultSet = connection.getMetaData().getCatalogs();
            while (resultSet.next()) {
                if (resultSet.getString(1).equalsIgnoreCase(schemaName)) {
                    return true;
                }
            }

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isSchemaVMRussiaCreated() {
        return schemaVMRussiaCreated;
    }
}
