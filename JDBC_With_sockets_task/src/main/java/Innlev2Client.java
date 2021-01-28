import DTO.DtoClient;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Innlev2Client {

    /**
     * @by Roosbeh Morandi
     *
     * This class  handles all the communication
     * with input/outputStream
     */



    public static void main(String[] args) {
        DtoClient dtoClient = new DtoClient();
        PrintHandler printHandler = new PrintHandler();
        try (Socket socket = new Socket("localhost", 1212);
             ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
             Scanner scan = new Scanner(System.in)) {

            System.out.println("welcome!");
            thread:
            while (true) {
                String mainMenu = fromServer.readUTF();
                System.out.println(mainMenu);
                String choice;
                choice = scan.next().toLowerCase();
                switch (choice) {
                    case "e":// if exit
                        toServer.writeUTF(choice + "\n//:" + socket.getInetAddress().getHostName() + " (disconnected)");
                        toServer.flush();
                        printHandler.goodbye();
                        break thread;
                    case "pgr200":
                        toServer.writeUTF(choice);
                        toServer.flush();
                        @SuppressWarnings("unchecked")
                        ArrayList<String> dtoListFromServer = (ArrayList<String>) fromServer.readObject();
                        printHandler.printTables(dtoListFromServer);
                        break;
                    case "pg3300":
                        toServer.writeUTF(choice);
                        toServer.flush();
                        @SuppressWarnings("unchecked")
                        ArrayList<String> dtoTable = (ArrayList<String>) fromServer.readObject();
                        printHandler.printTables(dtoTable);
                        break;
                    case "pg4200":
                        toServer.writeUTF(choice);
                        toServer.flush();
                        @SuppressWarnings("unchecked")
                        ArrayList<String> dtoListFromServer1 = (ArrayList<String>) fromServer.readObject();
                        printHandler.printTables(dtoListFromServer1);
                        break;
                    case "all":
                        toServer.writeUTF(choice);
                        toServer.flush();
                        @SuppressWarnings("unchecked") // an alternative solution to remove unchecked warnings.
                                ArrayList<String> dtoListFromServer2 = (ArrayList<String>) fromServer.readObject();
                        printHandler.printTables(dtoListFromServer2);
                        break;
                    case "u"://Update tables
                        toServer.writeUTF(choice);
                        toServer.flush();

                        //menuUpdate
                        String menuUpdate = "Choose a tablename to Update\n'1' for Subjects\n'2' " +
                                "\n'3'\n--------------------------------------";
                        System.out.println(menuUpdate);
                        String choice2 = scan.next().toLowerCase();

                        if (choice2.equals("1")) {
                            toServer.writeUTF(choice2);
                            toServer.flush();

                            dtoClient.clear();
                            System.out.print("Type the subject to update by subjectCode: ");
                            String subjCodeInput = scan.next().toLowerCase();
                            dtoClient.add(subjCodeInput);

                            printHandler.printTableLine();

                            System.out.println("'1' for Campus | '2' LectureForm |'3' ProgrBelong | '4' Duration |'5' Participants |'6' SubjectCode");
                            printHandler.printTableLine();
                            System.out.print("choose column nr to update: ");
                            String columnNameInput = scan.next();
                            dtoClient.add(columnNameInput);

                            System.out.print("Type new value: ");
                            String valueInput = scan.next().toLowerCase();
                            dtoClient.add(valueInput);

                            //sending
                            toServer.writeObject(dtoClient.getList());
                            toServer.flush();
                            toServer.reset();
                        } else{
                            String errorMessage = fromServer.readUTF();
                            System.out.println(errorMessage);
                        }

                        break;
                    case "a"://add new subject
                        toServer.writeUTF(choice);
                        toServer.flush();
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

                        toServer.writeObject(dtoClient.getList());
                        toServer.flush();
                        toServer.reset();
                        break;
                    case "d"://delete
                        toServer.writeUTF(choice);
                        toServer.flush();
                        System.out.print("\nType the subject to delete by subjectCode\n//: ");
                        String subjectToDelete = scan.next().toLowerCase();
                        toServer.writeUTF(subjectToDelete);
                        toServer.flush();
                        break;
                    default:
                        toServer.writeUTF(choice);
                        toServer.flush();
                        printHandler.unknown();
                }
            }
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println(exc.getMessage());
        } finally {
            printHandler.disconnected();
        }
    }
}