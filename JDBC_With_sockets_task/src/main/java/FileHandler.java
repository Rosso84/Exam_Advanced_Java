import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    /**
     * This class adds all data from a
     * file to an ArrayList
     */

    private ArrayList<String> fileList = new ArrayList<>();
    private String file1 = "Subjects.txt";


    /**
     * @throws FileNotFoundException if filename does not exist
     */
    public FileHandler(){
        try {
            getDataFromFile(file1);
        }catch (FileNotFoundException FileNotFound){
            System.out.println("Theres no file in such filename.. \n" + FileNotFound.getMessage());
        }
    }


    /**
     * Gets single datas from fileList
     * @param index #int
     * @return a String to fill database.
     */
    public String getList(int index) {
        String data = fileList.get(index);
        return data;
    }

    /**
     * Reads the data from file
     * to  ArrayList 'fileList'.
     * @throws FileNotFoundException if filename does not exit
     */
     public void getDataFromFile(String file)throws FileNotFoundException {
        try (Scanner scan = new Scanner(new File(file1))) {
            while (scan.hasNext()) {
                String read = scan.next();
                fileList.add(read);
            }
        }
    }


}

