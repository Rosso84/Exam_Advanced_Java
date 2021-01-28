package DBHandler;

import Connections.ConnectionProvider;
import FileReader.FileHandler;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TableSpillere {

    private FileHandler filehandler = new FileHandler();
    private String query;
    private InputVerifier verifier = new InputVerifier();


    /**
     * @param provider Connection for your database
     */
    public void createTable(ConnectionProvider provider) {

        query = "CREATE TABLE  IF NOT EXISTS " + filehandler.getItemFromSpillereList(0) + "( " +
                filehandler.getItemFromSpillereList(1) + " " + filehandler.getItemFromSpillereList(2) + "  " + filehandler.getItemFromSpillereList(3) + " " + filehandler.getItemFromSpillereList(4) + " " + filehandler.getItemFromSpillereList(5) + " ," +
                filehandler.getItemFromSpillereList(6) + " " + filehandler.getItemFromSpillereList(7) + " " + filehandler.getItemFromSpillereList(8) + " " + filehandler.getItemFromSpillereList(9) + " ," +
                filehandler.getItemFromSpillereList(10) + " " + filehandler.getItemFromSpillereList(11) + " ," +
                filehandler.getItemFromSpillereList(12) + " " + filehandler.getItemFromSpillereList(13) + " " + filehandler.getItemFromSpillereList(14) + " " + filehandler.getItemFromSpillereList(15) + " ," +
                filehandler.getItemFromSpillereList(16) + " " + filehandler.getItemFromSpillereList(17) + ", " +
                "  PRIMARY KEY (" + filehandler.getItemFromSpillereList(1) + " )," +
                " FOREIGN KEY (" + filehandler.getItemFromLandslagsList(1)+ ") REFERENCES "+ filehandler.getItemFromLandslagsList(0) +" ("+ filehandler.getItemFromLandslagsList(1)+") )";

        try (Connection con = provider.getConnection()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("New Table '" + filehandler.getItemFromSpillereList(0) + "' created");
        } catch (SQLException e) {
            System.out.println("Failed to create Table '" + filehandler.getItemFromSpillereList(0) + "' \n" + e.getMessage());
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createNewSpiller(ConnectionProvider connProv, String navn, String foedselsdato, int landsId, int  klubbId){
        query = "INSERT  INTO spiller(navn, foedselsdato, landslag_id, klubb_id) VALUES(?, ?, ?, ?)";

        try (Connection con = connProv.getConnection()) {
            PreparedStatement pStmt = con.prepareStatement(query);

            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(foedselsdato);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            pStmt.setString(1, navn);
            pStmt.setDate(2, sqlDate);
            pStmt.setInt(3, landsId);
            InputVerifier.setIntOrNull(pStmt,4, klubbId);

            int rows = pStmt.executeUpdate();

            String tablename = filehandler.getItemFromSpillereList(0);
            System.out.print("Added new spiller to table '" + tablename + "'");
            System.out.println(" (" + rows + " rows affected)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void createDefaultSpillere(ConnectionProvider conprov){
        //Germany players
        createNewSpiller(conprov, "Arnold Swartsz", "1905-03-22", 1, 0);
        createNewSpiller(conprov, "Einstein Alber", "1805-03-21", 1, 1);
        createNewSpiller(conprov, "Mauelee Neueru", "1975-03-25", 1, 2);
        //Russian players
        createNewSpiller(conprov,"Igor Akinfeevee", "1986-04-08", 2, 0);
        createNewSpiller(conprov,"Vladimir Putine", "1996-03-08", 2, 1);
        createNewSpiller(conprov,"Vladimir batman", "1906-06-06", 2, 2);
    }


    public void deleteRow(ConnectionProvider conProv, int Id){
        query = "DELETE FROM spiller WHERE  spiller_id = ?";
        verifier.prepareAndExecuteDelete(conProv, query, Id);
    }


    public void getSingleSpillereByIdOrALLSPillere(ConnectionProvider dbConnection, String input) throws Exception {
        Connection con = null;
        PreparedStatement prstmt = null;
        ResultSet res = null;
        ResultSetMetaData resMeta = null;

        String all = "SELECT spiller_id, navn, foedselsdato, landslag_id, klubb_id FROM spiller";
        String singleByID = "SELECT spiller_id, navn, foedselsdato, landslag_id, klubb_id FROM spiller WHERE spiller_id = ?";

        verifier.prepareAndExecuteUpdate(con, dbConnection, input, prstmt, all, singleByID, res, resMeta);
    }




}
