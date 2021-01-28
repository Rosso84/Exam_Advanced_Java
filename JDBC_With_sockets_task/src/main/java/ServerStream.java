import DTO.DtoServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerStream extends Thread {


    private Socket socket;

    /**
     * @param server ServerSocket Server
     * @param socket Socket Client
     */
    public ServerStream(ServerSocket server, Socket socket) {
        ServerSocket server1 = server;
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            stream();
        } catch (Exception IoExc) {
            IoExc.getStackTrace();
        }
    }


    /**
     * @throws Exception
     * @throws IOException if writing to ClientTest outputStream fails.
     */
    private void stream() throws Exception {

        try (ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream())) {
            DbConnection dbConnection = new DbConnection();
            AccessTables accessTables = new AccessTables();
            DtoServer dtoServer = new DtoServer();
            Subjects subjects = new Subjects();
            MenuTasks menuTasks = new MenuTasks();

            String menu =
                    "\n\n(Stream on)" +
                            "\n choose action" +
                            "\n-------------------------------------- " +
                            "\n'pgr200' for Advanced Java" +
                            "\n'pg3300' for Software Design" +
                            "\n'pg4200' for Algorithms" +
                            "\n'all' for all subjects" +
                            "\n'u' to update " +
                            "\n'a' to add new subject " +
                            "\n'd' to delete a subject " +
                            "\n'e' to exit" +
                            "\n-------------------------------------- \n//:";

            while (true) {
                toClient.writeUTF(menu);
                toClient.flush();
                String clientChoice = fromClient.readUTF();
                System.out.println("Client typed: " + clientChoice);

                switch (clientChoice) {
                    case "pgr200":
                        MenuTasks.getMainMenuSubjectOutput(dbConnection, clientChoice, accessTables, toClient, dtoServer);
                        break;
                    case "pg3300":
                        MenuTasks.getMainMenuSubjectOutput(dbConnection, clientChoice, accessTables, toClient, dtoServer);

                        break;
                    case "pg4200":
                        MenuTasks.getMainMenuSubjectOutput(dbConnection, clientChoice, accessTables, toClient, dtoServer);
                        break;
                    case "all":
                        MenuTasks.getMainMenuSubjectOutput(dbConnection, clientChoice, accessTables, toClient, dtoServer);
                        break;
                    case "u"://update
                        String choice2 = fromClient.readUTF();
                        if (choice2.equals("1")) {
                            @SuppressWarnings("unchecked")
                            ArrayList<String> dtoFromClient = (ArrayList<String>) fromClient.readObject();
                            String subjectcode = dtoFromClient.get(0);
                            String columnName = dtoFromClient.get(1);
                            String value = dtoFromClient.get(2);
                            menuTasks.UpdateMenuForClient2(dbConnection, columnName, value, subjectcode);
                        } else {
                            toClient.writeUTF("Wrong input");
                            toClient.flush();
                        }
                        break;
                    case "a":  //add new subject
                        System.out.println("waiting for respons..");
                        @SuppressWarnings("unchecked")
                        ArrayList<String> dtoFromClient = (ArrayList<String>) fromClient.readObject();
                        System.out.println("Dto from Innlev2Client contains:");
                        subjects.createNewSubjectThroughUserInput(dbConnection, dtoFromClient);
                        break;
                    case "d":  //delete subject
                        String subjectToDelete = fromClient.readUTF();
                        subjects.deleteSubject(dbConnection, subjectToDelete);
                        break;
                    default:
                        System.out.println("Wrong action");
                }
            }
        } finally {
            System.out.println("Something went wrong during communication");
        }
    }


}




