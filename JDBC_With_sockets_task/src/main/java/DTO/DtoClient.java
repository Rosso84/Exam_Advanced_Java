package DTO;

import java.util.ArrayList;

public class DtoClient implements Dto{

    /**
     *
     * This list will transfer larger
     * data from Client to Server.
     *
     */



    private ArrayList<String> list = new ArrayList<String>();


    @Override
    public ArrayList<String> getList() {
        return list;
    }


    /**
     *
     * @param input
     */
    public void add(String input) {
        list.add(input);
    }

    public void clear(){
        list.clear();
    }





}
