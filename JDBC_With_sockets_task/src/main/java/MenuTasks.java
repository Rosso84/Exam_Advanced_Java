import DTO.Dto;
import DTO.DtoClient;
import DTO.DtoServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuTasks {

    Subjects subjects = new Subjects();
    PrintHandler printHandler = new PrintHandler();


    public MenuTasks() {
    }


    static void getMainMenuSubjectOutput(DbConnection dbCon, String clientChoice, AccessTables accessTables,
                                         ObjectOutputStream toClient, DtoServer dtoServer) throws Exception {
        if (clientChoice.equals("pgr200") || clientChoice.equals("pg4200") || clientChoice.equals("pg3300")) {
            accessTables.getSingleSubject(dbCon, clientChoice);

        } else if (clientChoice.equals("all")) {
            accessTables.getAllSubjects(dbCon);
        }
        toClient.writeObject(dtoServer.getList());
        toClient.flush();
        toClient.reset();

    }


    public void UpdateMenuForClient2(DbConnection dbConnection, String columnName, String value, String subjectcode) {


        try {
            switch (columnName) {
                case "1":
                    subjects.updateCampus(dbConnection, value, subjectcode);
                    break;
                case "2":
                    subjects.updateLectureForm(dbConnection, value, subjectcode);
                    break;
                case "3":
                    subjects.updateProgramBelonging(dbConnection, value, subjectcode);
                    break;
                case "4":
                    subjects.updateDuration(dbConnection, value, subjectcode);
                    break;
                case "5":
                    subjects.updateParticiants(dbConnection, value, subjectcode);
                    break;
                case "6":
                    subjects.updateSubjectCode(dbConnection, value, subjectcode);
                    break;
                default:
                    printHandler.unknown();
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //public void updateMenuForClient1(DbConnection dbConnection,) {}


    public void searchTablesMenuForClient1(DbConnection dbConnection, String input2, AccessTables accessTables,
                                           DtoServer dtoServer, PrintHandler print) {
        try {
            switch (input2) {
                case "pgr200":
                    System.out.println("\nPGR200:");
                    accessTables.getSingleSubject(dbConnection, input2);
                    print.printTables(dtoServer.getList());
                    System.out.println();
                    break;
                case "pg3300":
                    System.out.println("\nPG3300: ");
                    accessTables.getSingleSubject(dbConnection, input2);
                    print.printTables(dtoServer.getList());
                    System.out.println();
                    break;
                case "pg4200":
                    System.out.println("PG4200:");
                    accessTables.getSingleSubject(dbConnection, input2);
                    print.printTables(dtoServer.getList());
                    System.out.println();
                    break;
                case "all":
                    System.out.println("\nAll subjects: ");
                    accessTables.getAllSubjects(dbConnection);
                    print.printTables(dtoServer.getList());
                    System.out.println();
                    break;
                case "r":
                    break;
                default:
                    print.unknown();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void createNewSubjectMenuForClient1(DbConnection dbConnection, DtoClient dtoClient, Scanner scan) {
        try {
            dtoClient.clear();
            System.out.print("Type a SubjectCode (6 digits): ");
            String newSubject = scan.next().toLowerCase();
            dtoClient.add(newSubject);
            System.out.print("Type a campus (6 digits): ");
            String newCampus = scan.next().toLowerCase();
            dtoClient.add(newCampus);
            System.out.print("Type LectureForm (10 digits): ");
            String newLectForm = scan.next().toLowerCase();
            dtoClient.add(newLectForm);
            System.out.print("Type a Programbelonging  (7 digits): ");
            String newProgrBelonging = scan.next().toLowerCase();
            dtoClient.add(newProgrBelonging);
            System.out.print("Type the Duration (6 digits): ");
            String newDuration = scan.next().toLowerCase();
            dtoClient.add(newDuration);
            System.out.print("Type the number of participants : ");
            String newParticipants = scan.next().toLowerCase();
            dtoClient.add(newParticipants);
            subjects.createNewSubjectThroughUserInput(dbConnection, dtoClient.getList());
            System.out.println();
            System.out.println("\n\n--New subject '" + newSubject + "' Created--\n");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


}
