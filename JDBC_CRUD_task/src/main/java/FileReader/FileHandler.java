package FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private ArrayList<String> landslagsList = new ArrayList<>();
    private ArrayList<String> spillerList = new ArrayList<>();
    private ArrayList<String> klubblagList = new ArrayList<>();
    private ArrayList<String> schemasList = new ArrayList<>();

    private String landslagFile = "landslag.txt";
    private String spillereFile = "spillere.txt";
    private String klubblagFile = "klubblag.txt";
    private String schemasFile = "schemas.txt";



    public void readFromFileSchemas(){
        try {
            chooseFileToReadFrom(schemasFile, schemasList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readFromFileLandslag(){
        try {
            chooseFileToReadFrom(landslagFile, landslagsList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readFromFileSpillere(){
        try {
            chooseFileToReadFrom(spillereFile, spillerList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readFromFileKlubblag(){
        try {
            chooseFileToReadFrom(klubblagFile, klubblagList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void chooseFileToReadFrom(String file, ArrayList<String> list) throws FileNotFoundException {
        try (Scanner scan = new Scanner(new File(file))) {
            while (scan.hasNext()) {
                String read = scan.next();
                list.add(read);
            }
        }
    }

    public String getItemFromSchemasList(int index){
        readFromFileSchemas();
        String item = schemasList.get(index);
        return item;
    }

    public String getItemFromLandslagsList(int index){
        readFromFileLandslag();
        String item = landslagsList.get(index);
        return item;
    }

    public String getItemFromSpillereList(int index){
        readFromFileSpillere();
        String item = spillerList.get(index);
        return item;
    }

    public String getItemFromKlubblagList(int index){
        readFromFileKlubblag();
        String item = klubblagList.get(index);
        return item;
    }

    public ArrayList<String> getLandslagsList() {
        return landslagsList;
    }

    public ArrayList<String> getSpillerList() {
        return spillerList;
    }

    public ArrayList<String> getKlubblagList() {
        return klubblagList;
    }

    public ArrayList<String> getSchemasList() {
        return schemasList;
    }

}
