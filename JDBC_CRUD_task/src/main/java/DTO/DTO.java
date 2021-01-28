package DTO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Roosbeh Morandi
 * 03.07.18
 *
 * This Dto is only used for testing contents in Resultset and meta
 */


public class DTO {

    private ArrayList<String> klubblagResultsetList = new ArrayList<>();
    private ArrayList<String> klubblagResultsetMetadataList = new ArrayList<>();

    private ArrayList<String> landslagResultsetList = new ArrayList<>();
    private ArrayList<String> landslagResultsetMetadataList = new ArrayList<>();

    public void addResaultsetForKlubblagTable(ResultSet res) throws SQLException {

        if (!klubblagResultsetList.isEmpty()) {
            klubblagResultsetList.clear();
        }

        while (res.next()) {
            for (int i = 1; i < 4; i++) {
                klubblagResultsetList.add(res.getString(i));
            }
        }
    }

    public void addResaultsetMetaDataForKlubblagTable(ResultSetMetaData resMeta) throws SQLException {

        if (!klubblagResultsetMetadataList.isEmpty()) {
            klubblagResultsetMetadataList.clear();
        }

        for (int i = 1; i < 4; i++) {
            klubblagResultsetMetadataList.add(resMeta.getColumnName(i));
        }

    }

    public void addResaultsetMetaDataLandslagTable(ResultSetMetaData resMeta) throws SQLException {

        if (!landslagResultsetMetadataList.isEmpty()) {
            landslagResultsetMetadataList.clear();
        }

            landslagResultsetMetadataList.add(resMeta.getColumnName(1));

            landslagResultsetMetadataList.add(resMeta.getColumnName(2));

            landslagResultsetMetadataList.add(resMeta.getColumnName(3));
            landslagResultsetMetadataList.add(resMeta.getColumnName(4));
            landslagResultsetMetadataList.add(resMeta.getColumnName(5));

    }

    public void addResaultsetForLandslagTable(ResultSet res) throws SQLException {
        if (!landslagResultsetList.isEmpty()){ landslagResultsetList.clear();}



    }

    public ArrayList<String> getKlubblagResultsetList(){
        return klubblagResultsetList;
    }

    public ArrayList<String> getKlubblagResultsetMetadataList(){
        return klubblagResultsetMetadataList;
    }

    public ArrayList<String> getLandslagResultsetList(){
        return landslagResultsetList;
    }

    public ArrayList<String> getLandslagResultsetMetadataList(){
        return landslagResultsetMetadataList;
    }

}
