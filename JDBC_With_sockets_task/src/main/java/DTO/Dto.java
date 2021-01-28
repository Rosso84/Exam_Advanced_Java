package DTO;

        import java.util.ArrayList;


/**
 * This transfers an object of data between sockets
 */

public interface Dto {

    /**
     *
     * @return a list of Strings either from Server og Client
     */


    ArrayList<String> getList();;
}
