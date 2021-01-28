package DBHandler;

import Connections.ConnectionProvider;
import FileReader.FileHandler;

import java.io.IOException;
import java.sql.*;

public class TableLandslag {

    private FileHandler file = new FileHandler();
    private String query;
    private InputVerifier verifier = new InputVerifier();


    public void createTableLandslag(ConnectionProvider provider) {

        query = "CREATE TABLE  IF NOT EXISTS " + file.getItemFromLandslagsList(0) + "( " +
                file.getItemFromLandslagsList(1) + " " + file.getItemFromLandslagsList(2) + "  " + file.getItemFromLandslagsList(3) + " " + file.getItemFromLandslagsList(4) + " " + file.getItemFromLandslagsList(5) + " ," +
                file.getItemFromLandslagsList(6) + " " + file.getItemFromLandslagsList(7) + " " + file.getItemFromLandslagsList(8) + " " + file.getItemFromLandslagsList(9) + " ," +
                file.getItemFromLandslagsList(10) + " " + file.getItemFromLandslagsList(11) + " " + file.getItemFromLandslagsList(12) + " " + file.getItemFromLandslagsList(13) + " ," +
                file.getItemFromLandslagsList(14) + " " + file.getItemFromLandslagsList(15) + " " + file.getItemFromLandslagsList(16) + " " + file.getItemFromLandslagsList(17) + " ," +
                file.getItemFromLandslagsList(18) + "  enum( '" + LandslagContinent.AFRIKA + "' , '" + LandslagContinent.ASIA + "', '" + LandslagContinent.EUROPA + "', '" + LandslagContinent.NORD_MELLOM_AMERIKA + "', '" + LandslagContinent.KARIBIA + "', '" + LandslagContinent.OSEANIA + "', '" + LandslagContinent.INGEN_VERTSLAND + "' )," +
                "  PRIMARY KEY (" + file.getItemFromLandslagsList(1) + " ) )";

        try (Connection con = provider.getConnection()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("New Table '" + file.getItemFromLandslagsList(0) + "' created");
        } catch (SQLException e) {
            System.out.println("Failed to create Table '" + file.getItemFromLandslagsList(0) + "' \n" + e.getMessage());
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void createNewLandslag(ConnectionProvider connProv, String navn, String trener, int ranking, String kvalifisering) {
        query = "INSERT  INTO landslag(navn, trener, fifaranking, kvalifisering) VALUES(?, ?, ?, ?)";
        try (Connection con = connProv.getConnection()) {
            PreparedStatement pStmt = con.prepareStatement(query);
            pStmt.setString(1, navn);
            pStmt.setString(2, trener);
            pStmt.setInt(3, ranking);
            pStmt.setString(4, kvalifisering);

            int rows = pStmt.executeUpdate();

            String tablename = file.getItemFromLandslagsList(0);
            System.out.print("Added new country to table '" + tablename + "'");
            System.out.println(" (" + rows + " rows affected)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void createDefaultLandslags(ConnectionProvider connProv){
        createNewLandslag(connProv, "Tyskland", "Joachim_Low", 1, "EUROPA");
        createNewLandslag(connProv, "Russland", "Stanisv_Sal", 5, "INGEN_VERTSLAND");
    }

    public void updateRanking(ConnectionProvider conProv, int newRanking, int id){
        query = "UPDATE landslag SET fifaranking = ? WHERE landslag_id = ?";
        try {
            preparedStatementForUpdating(conProv, newRanking, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparedStatementForUpdating(ConnectionProvider dbCon, int newValue, int whereValue) throws Exception {
        try (Connection con = dbCon.getConnection()) {
            PreparedStatement preStmt = con.prepareStatement(query);
            preStmt.setInt(1, newValue);
            preStmt.setInt(2, whereValue);

            int rows = preStmt.executeUpdate();
            System.out.println(" Table update (" + rows + " rows affected)\n");
        }
    }

    public void displayData(ConnectionProvider dbConnection, String input) throws Exception {
        Connection con = null;
        PreparedStatement prstmt = null;
        ResultSet res = null;
        ResultSetMetaData resMeta = null;

        String all = "SELECT landslag_id, navn, trener, fifaranking, kvalifisering FROM landslag";
        String singleByID = "SELECT landslag_id, navn, trener, fifaranking, kvalifisering FROM landslag WHERE landslag_id = ?";

        verifier.prepareAndExecuteUpdate(con, dbConnection, input, prstmt, all, singleByID, res, resMeta);
    }

    public void deleteRow(ConnectionProvider conProv, int Id){
        query = "DELETE FROM landslag WHERE  landslag_id = ?";
        verifier.prepareAndExecuteDelete(conProv, query, Id);
    }





}
