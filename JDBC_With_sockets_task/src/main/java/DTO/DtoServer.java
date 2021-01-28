package DTO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class DtoServer implements Dto{

    /**
     * @By Roosbeh Morandi
     * The list collects Resultset of data from Database
     * and transfers a String of data to clients as objects.
     */




    private static ArrayList<String> list = new ArrayList<>();

    /**
     * @param res  Resultset of data read from database
     * @throws Exception if SQL actions fails or if sql error occurs
     */

    public static void add(ResultSet res, ResultSetMetaData resMeta) throws Exception {
        //clear list for each time a client does a new search
        if (!list.isEmpty()) {
            list.clear();
        }
        //adding metaData
        for (int i = 1; i < 7; i++) {
            list.add(resMeta.getColumnName(i));
        }
        //adding data
        while (res.next()) {
            for (int i = 1; i < 7; i++) {
                list.add(res.getString(i));
            }
        }
    }


    @Override
    public ArrayList<String> getList() {
        return list;
    }


}