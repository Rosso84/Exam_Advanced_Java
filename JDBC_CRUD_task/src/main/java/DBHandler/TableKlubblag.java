package DBHandler;

import Connections.ConnectionProvider;
import DTO.DTO;
import FileReader.FileHandler;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class TableKlubblag {

    private FileHandler file = new FileHandler();
    private String query;

    /**
     * @param provider connection for your database
     */
    public void createTable(ConnectionProvider provider) {

        query = "CREATE TABLE  IF NOT EXISTS " + file.getItemFromKlubblagList(0) + "( " +
                file.getItemFromKlubblagList(1) + " " + file.getItemFromKlubblagList(2) + "  " + file.getItemFromKlubblagList(3) + " " + file.getItemFromKlubblagList(4) + " " + file.getItemFromKlubblagList(5) + " ," +
                file.getItemFromKlubblagList(6) + " " + file.getItemFromKlubblagList(7) + " " + file.getItemFromKlubblagList(8) + " " + file.getItemFromKlubblagList(9) + " ," +
                file.getItemFromKlubblagList(10) + " " + file.getItemFromKlubblagList(11) + " " + file.getItemFromKlubblagList(12) + " " + file.getItemFromKlubblagList(13) + ", " +
                "  PRIMARY KEY (" + file.getItemFromKlubblagList(1) + " ) )";

        try (Connection con = provider.getConnection()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("New Table '" + file.getItemFromKlubblagList(0) + "' created");
        } catch (SQLException e) {
            System.out.println("Failed to create Table '" + file.getItemFromKlubblagList(0) + "' \n" + e.getMessage());
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param connProv connection for your database
     * @param navn name of klubb
     * @param land name of country
     */
    public void createNewKlubblag(ConnectionProvider connProv, String navn, String land){
        String tabelname = file.getItemFromKlubblagList(0);
        query = "INSERT INTO " + tabelname + "(navn, land) VALUES(?, ?)" ;
        try (Connection con = connProv.getConnection()) {
            PreparedStatement pStmt = con.prepareStatement(query);
            pStmt.setString(1, navn);
            pStmt.setString(2, land);

            int rows = pStmt.executeUpdate();
            System.out.print("Added new klubblag to table '" + tabelname + "'");
            System.out.println(" (" + rows + "rows  affected)");


        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param conProv the connection to your database
     */
    public void createDefaultKlubblags(ConnectionProvider conProv){
        createNewKlubblag(conProv, "Club Brugge KVdsdd", "Russland");
        createNewKlubblag(conProv, "FC Lokomotic Moscow", "Russland");
        createNewKlubblag(conProv, "FC Spartak Moscow", "Russland");

        createNewKlubblag(conProv, "Hildesheim", "Tyskland");
        createNewKlubblag(conProv, "Bremensen", "Tyskland");
        createNewKlubblag(conProv, "Bissingen", "Tyskland");

    }


    /**
     * @param conProv the connection to your database
     * @param klubbId the id of row to delete
     */
    public void deleteRow(ConnectionProvider conProv, int klubbId){
        query = "DELETE FROM klubblag WHERE  klubb_id = ?";
        try (Connection connection = conProv.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, klubbId);

            int rows = stmt.executeUpdate();
            System.out.println("\nKlubb with id: " + klubbId +" deleted successfully..");
            System.out.println("(" + rows + " rows affected)");
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    //for testing
    public ArrayList<String> getTableNames(ConnectionProvider connectionProvider) {
        ArrayList<String> listOfTables = new ArrayList<>();
        query = "SELECT * FROM klubblag";

        try (Connection con = connectionProvider.getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsMetaData = rs.getMetaData();

            int numberOfColumns = rsMetaData.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++) {
                listOfTables.add(rsMetaData.getTableName(i));
            }

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (listOfTables.isEmpty()) {
            return null;
        }
        return listOfTables;
    }


}
