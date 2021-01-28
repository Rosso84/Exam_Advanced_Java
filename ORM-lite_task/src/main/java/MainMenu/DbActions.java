package MainMenu;


import ConnectionProvider.Connection;
import Tables.City;
import Tables.CountryLanguage;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

public class DbActions {

    Connection con = new Connection();
    Dao<City, String> cityDao;


    public DbActions() {
        try {
            cityDao = DaoManager.createDao(con.getConnection(), City.class);
        } catch (SQLException e) {


        }
    }


    /**
     * @param city
     */
    public void createNewRow(City city) {
        try {
            cityDao.create(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param columnName
     * @param rowName
     */
    public void readDataFromTable(String columnName, Object rowName) {
        try {
            cityDao.queryForEq(columnName, rowName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void updateTable(City city) {
        try {
            cityDao.update(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteDataFromTable(City city) {
        try {
            cityDao.delete(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
