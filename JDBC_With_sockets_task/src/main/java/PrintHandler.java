
import java.util.ArrayList;

class PrintHandler {

    void disconnected() {
        System.out.println("<disconnected >");
    }

    void waitingConnection() {
        System.out.println("Waiting for clients actions..");
    }

    void unknown() {
        System.out.println("-Uknown input, try again-");
    }

    void goodbye() {
        System.out.println("\nHave a nice day!");
    }

    void printNotImplemented() {
        System.out.println("\n(Function not yet implemented)\nChoose something else\n");
    }

    void printTableLine() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }


    /**
     *
     * @param dtoList a list of data filled with data from Database
     */
    public void printTables(ArrayList<String> dtoList) {
        printTableLine();
        for (int i = 0; i < dtoList.size(); i++) {
            if (i % 6 == 0) {
                System.out.println();
            }
            if (i == 6) {
                printTableLine();
            }
            //System.out.print(dtoList.get(i) + "   |  ");
            System.out.printf("%.14s", dtoList.get(i) + " | ");
        }
        System.out.println();
        printTableLine();

    }

}
