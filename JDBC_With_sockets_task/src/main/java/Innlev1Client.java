import DTO.DtoClient;
import DTO.DtoServer;

import java.util.ArrayList;
import java.util.Scanner;

public class Innlev1Client {

    /**
     * @By Roosbeh Morandi
     *
     * The main class to run first filing
     */




    /**
     * @param args
     * @throws Exception             If Conncetion fails
     * @throws java.sql.SQLException if sql error occurs
     */
    public static void main(String[] args) {
        try {
            DbConnection dbConnection = new DbConnection();
            DBhandler dbHandler = new DBhandler(dbConnection);
            AccessTables accessTables = new AccessTables();
            PrintHandler print = new PrintHandler();
            Scanner scan = new Scanner(System.in);
            DtoServer dtoServer = new DtoServer();
            DtoClient dtoClient = new DtoClient();
            Subjects subjects = new Subjects();
            MenuTasks menuTask =  new MenuTasks();

            boolean finished = false;
            System.out.println("Welcome!");
            while (!finished) {
                System.out.print("\nType:" +
                        "\n-------------------" +
                        "\n'1' to search tables" +
                        "\n'2' to create new subject" +
                        "\n'3' to update" +
                        "\n'4' to delete" +
                        "\n'e' to exit" +
                        "\n//:");
                String input = scan.next().toLowerCase();
                switch (input) {
                    case "1"://search tables
                        System.out.println();
                        System.out.print("Type:\n" +
                                "------------------\n" +
                                "'pgr200' for Advanced Java \n" +
                                "'pg3300' for Software Design\n" +
                                "'pg4200' for Algorithms\n" +
                                "'all' for all subjects\n" +
                                "'r' to return\n" +
                                "------------------\n//:");
                        String input2 = scan.next().toLowerCase();
                        menuTask.searchTablesMenuForClient1(dbConnection, input2, accessTables, dtoServer, print);

                        break;
                    case "2"://Create new subject

                        menuTask.createNewSubjectMenuForClient1(dbConnection, dtoClient, scan);
                        break;
                    case "3"://Update Table
                        String menuUpdate = "Choose a tablen to Update\n'1' for Subjects\n'2' for Room (Not implented)" +
                                "\n'3' for Teachers (not implemented)\n--------------------------------------\n//:";
                        System.out.print(menuUpdate);
                        String choice2 = scan.next().toLowerCase();

                        switch (choice2) {
                            case "1":
                                dtoClient.clear();
                                System.out.println("\nSubjects:  'pgr200' | 'pg3300' | 'pg4200'\n--------------------------------------------");
                                System.out.print("Type the subject to update by subjectCode: ");
                                String subjCodeInput = scan.next().toLowerCase();
                                dtoClient.add(subjCodeInput);

                                print.printTableLine();
                                System.out.println("'1' for Campus \n'2' LectureForm\n'3' ProgrBelong\n'4' Duration \n'5' Participants \n'6' SubjectCode");
                                print.printTableLine();
                                System.out.print("choose column nr to update/:");
                                String columnNameInput = scan.next();
                                dtoClient.add(columnNameInput);
                                System.out.print("Type new value: ");
                                String valueInput = scan.next().toLowerCase();
                                dtoClient.add(valueInput);

                                ArrayList<String> listOfDataInputByUser = dtoClient.getList();
                                String subjectcode = listOfDataInputByUser.get(0);
                                String columnName = listOfDataInputByUser.get(1);
                                String value = listOfDataInputByUser.get(2);
                                menuTask.UpdateMenuForClient2(dbConnection, columnName, value, subjectcode);
                                break;
                            case "2":
                                System.out.println("--empty--");
                                break;
                            case "3":
                                System.out.println("--empty--");
                                break;
                            case "r":
                                break;
                        }



                        break;
                    case "4"://Delete a subject
                        System.out.print("\nType table to delete from:" +
                                "\n'1' for Subjects" +
                                "\n'2' (Not implented)" +
                                "\n'3' (not implemented)" +
                                "\n--------------------------------------\n//:");
                        String menuChoice = scan.next().toLowerCase();
                        switch (menuChoice){
                            case "1":
                                System.out.println("\nSubjects:  'pgr200' | 'pg3300' | 'pg4200'\n--------------------------------------------");
                                System.out.print("Type a subject to delete by subjectcode: ");
                                String subcode = scan.next().toLowerCase();
                                if (subcode.equals("pgr200") || subcode.equals("pg3300") || subcode.equals("pg4200")){
                                    subjects.deleteSubject(dbConnection, subcode);
                                }else{
                                    System.out.println("--Subject does not exist---");
                                }
                                break;
                            case "2":
                                System.out.println("--empty--");
                                break;
                            case "3":
                                System.out.println("--empty--");
                                break;
                            case "r":
                                break;
                        }


                        break;
                    case "e": //exit
                        print.goodbye();
                        finished = true;
                        break;
                    default:
                        print.unknown();
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            System.out.println("-Database off-");
        }
    }
}
