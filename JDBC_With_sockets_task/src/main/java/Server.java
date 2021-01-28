import java.io.*;
import java.net.*;

/**
 * @By Roosbeh  Morandi
 * <p>
 * This Class will create a server provided with 24/7
 * running for multiple Clients.
 */

public class Server {

    /**
     * The first main to run before running client1 or Client2.
     * Make sure to UNcomment DBHandler.deleteSchema() inside
     * the constructor of DbHandler.class after
     * first time you run this main Server.
     * This will allow The Server to restart and reCreate schema and default tables.
     */
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(1212, 100)) {
            PrintHandler printOut = new PrintHandler();
            DbConnection dbConnection = new DbConnection();
            DBhandler dbhandler = new DBhandler(dbConnection);
            while (true) {
                printOut.waitingConnection();
                Socket socket = acceptConnection(server);
                ServerStream serverStream = new ServerStream(server, socket);
                serverStream.start();
            }
        } catch (Exception ioexception) {
            ioexception.printStackTrace();
        }
    }


    /**
     * @param server socket to connect with
     * @return Socket if connection accepted
     * @throws IOException if communication fails
     */
    private static Socket acceptConnection(ServerSocket server) throws IOException {
        Socket socket = server.accept();
        System.out.println("Connection accepted!\nConnected to Clientadress: /"
                + socket.getInetAddress().getHostName() + "\n");
        return socket;
    }




}
