package DBHandler;

import Connections.ConnectionProvider;
import DTO.DTO;

import java.io.IOException;
import java.sql.*;

public class InputVerifier {


    public static void setIntOrNull(PreparedStatement pstmt, int column, int value) throws SQLException {
        if (value != 0) {
            pstmt.setInt(column, value);
        } else {
            pstmt.setNull(column, java.sql.Types.INTEGER);
        }
    }

    public void prepareAndExecuteUpdate(Connection con, ConnectionProvider dbConnection, String input,
                                        PreparedStatement prstmt, String all, String singleByID, ResultSet res,
                                        ResultSetMetaData resMeta){
        DTO dto = new DTO();
        try {
            con = dbConnection.getConnection();
            if (input.equalsIgnoreCase("all")) {
                prstmt = con.prepareStatement(all);
            }else {
                int convertedInputToInt = Integer.parseInt(input);
                prstmt = con.prepareStatement(singleByID);
                prstmt.setInt(1, convertedInputToInt);
            }
            res = prstmt.executeQuery();
            resMeta = res.getMetaData();

            displayResultsetMetadata(resMeta);
              if (!res.isBeforeFirst()) {
                System.out.println("\n--No data found in database--");
            } else {
                while (res.next()) {
                    displayResultsetData(res);
                }
                res = prstmt.executeQuery();
                  dto.addResaultsetForLandslagTable(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null)
                    res.close();
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
            }
            try {
                if (prstmt != null)
                    prstmt.close();
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
            }
        }
    }


    public void displayResultsetMetadata(ResultSetMetaData resMeta) throws SQLException {
        System.out.println(resMeta.getColumnName(1) + "\t    " + resMeta.getColumnName(2) + "\t            " + resMeta.getColumnName(3) + "\t  " +
                resMeta.getColumnName(4) + "\t" + resMeta.getColumnName(5));
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void displayResultsetData(ResultSet res) throws SQLException {
        System.out.println(res.getInt(1) + "\t            " + res.getString(2) + "\t     "
                + res.getString(3) + "\t      " + res.getString(4) + "\t\t\t\t" +
                res.getString(5));
    }

    public void prepareAndExecuteDelete(ConnectionProvider conProv, String query,int Id){
        try (Connection connection = conProv.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, Id);

            int rows = stmt.executeUpdate();
            System.out.println("\nRow with id: " + Id + " deleted successfully..");
            System.out.println("(" + rows + " rows affected)");
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean tableExist(Connection conn, String tableName) throws SQLException {
        boolean tExists = false;
        try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
            while (rs.next()) {
                String tName = rs.getString("TABLE_NAME");
                if (tName != null && tName.equals(tableName)) {
                    tExists = true;
                    break;
                }
            }
        }
        return tExists;
    }
}
