package DisplayView;

import Connections.DbConnection;
import DBHandler.TableKlubblag;
import DBHandler.TableLandslag;
import DBHandler.TableSpillere;
import FileReader.FileHandler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menus {


    private FileHandler fileHandler = new FileHandler();

    public void showMainMenu() {

        String dbName = fileHandler.getItemFromSchemasList(0);

        try {
            DbConnection dbConnection = new DbConnection();
            dbConnection.setDataBaseName(dbName);
            Scanner scan = new Scanner(System.in);
            TableSpillere spillere = new TableSpillere();
            TableKlubblag klubblag = new TableKlubblag();
            TableLandslag landslag = new TableLandslag();

            boolean finished = false;
            System.out.println("Welcome!");
            while (!finished) {
                System.out.print("\nType:" +
                        "\n--------------------------" +
                        "\n'1' to search " +
                        "\n'2' to create " +
                        "\n'3' to update" +
                        "\n'4' to to delete" +
                        "\n'e' to exit" +
                        "\n--------------------------" +
                        "\n//:");
                String mainMenuChoice = scan.next();
                label:
                switch (mainMenuChoice) {
                    case "1":
                        //search tables
                        System.out.println();
                        showSearchMenu();
                        int searchChoice = scan.nextInt();
                        switch (searchChoice) {
                            case 1:
                                System.out.println("type a 'spiller_id' for single row or 'all' for all spillere\n//:");
                                String spillerChoice = scan.next().toLowerCase();
                                spillere.getSingleSpillereByIdOrALLSPillere(dbConnection, spillerChoice);
                                break;
                            case 2:

                                System.out.println("Klubblag is not implemented yet");
                                //TODO: Dto kills the data
                                /*System.out.println("Type id or 'all' to search all klubblag");
                                String searchChoiceKlubblag = scan.next().toLowerCase();
                                klubblag.displayData(dbConnection, searchChoiceKlubblag);*/
                                break;
                            case 3:
                                System.out.println("type a 'landslag_id' for single row or 'all' for all landslag\n//:");
                                String searchlandslagChoice = scan.next().toLowerCase();
                                landslag.displayData(dbConnection, searchlandslagChoice);
                                break;
                            case 4:
                                break label;
                                default:
                                    System.out.println("'" + searchChoice + "' is not an action");
                        }
                        break;
                    case "2":
                        //create
                        showCreateMenu();
                        String createMenuChoice = scan.next();
                        switch (createMenuChoice) {
                            case "1":
                                System.out.println("(Klubblag)");

                                System.out.println("Type the name of the klubblag: ");
                                String klubbName = scan.next().toLowerCase();

                                System.out.println("Type the countryname: ");
                                String land = scan.next().toLowerCase();

                                klubblag.createNewKlubblag(dbConnection, klubbName, land);
                                break;
                            case "2":
                                System.out.println("(Spiller)");

                                System.out.println("Type the name of the player: ");
                                String spillername = scan.next().toLowerCase();

                                System.out.println("Type the date of birth 'YYYY-MM-DD': ");
                                String dateOfBirth = scan.next();


                                landslag.displayData(dbConnection, "all");
                                System.out.println("Type the landslag_id: ");
                                int landsLagID = scan.nextInt();

                                System.out.println("Type the klubb_id if any or '0'(zero) if not belonged to any klubblag: ");
                                int klubbID = scan.nextInt();

                                spillere.createNewSpiller(dbConnection, spillername, dateOfBirth, landsLagID, klubbID);

                                break;
                            case "3":
                                System.out.println("(Landslag)");
                                System.out.println("countryname: ");
                                String countryname = scan.next().toLowerCase();

                                System.out.println("Coach name (no spaces): ");
                                String coachName = scan.next().toLowerCase();

                                System.out.println("Fifa rankingnumber (1-..): ");
                                int newRanking = scan.nextInt();

                                System.out.println("Qualified from continent? \n" +
                                        "Choose between: EUROPA, AFRIKA, SOR_AMERIKA, ASIA, NORD_MELLOM_AMERIKA," +
                                        " KARIBIA, OSEANIA, INGEN_VERTSLAND\n//:");
                                String qualifiedFrom = scan.next().toUpperCase();
                                landslag.createNewLandslag(dbConnection, countryname, coachName, newRanking, qualifiedFrom);
                                break;
                        }
                        break;
                    case "3":
                        //update
                        showUpdateMenu();
                        String updateMenuChoice = scan.next().toLowerCase();
                        switch (updateMenuChoice) {
                            case "1":
                                showUpdateLandslagMenu();
                                String updatelandslagChoice = scan.next();
                                switch (updatelandslagChoice) {
                                    case "1": //update FifaRanking
                                        try {
                                            System.out.println("Type new fifa rankingposition: ");
                                            int chosenNewRanking = scan.nextInt();
                                            System.out.println("Type landslag_id to update (e.g '1'(Germany)or '2'(Russia)");
                                            int landslagId = scan.nextInt();
                                            landslag.updateRanking(dbConnection, chosenNewRanking, landslagId);
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid value!");
                                            scan.next();
                                        }
                                        break;
                                    case "2":
                                        break;
                                    case "3":
                                        break;
                                    case "r":
                                        break;
                                    default:
                                        System.out.println("chosen input is not an action..");
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
                            default:
                                System.out.println("'" + updateMenuChoice + "' Is not an action");
                        }
                        break;
                    case "4":
                        //delete
                        System.out.println();
                        System.out.println("\n(Delete)");
                        showDeleteMenu();
                        String chosenDeleteMenu = scan.next();
                        switch (chosenDeleteMenu) {
                            case "1":
                                spillere.getSingleSpillereByIdOrALLSPillere(dbConnection,"all");
                                System.out.println("Delete from Table 'spillere'");
                                System.out.println("Please type the players id (id can only contain numbers) \n//: ");
                                try {
                                    int chosenId = scan.nextInt();
                                    spillere.deleteRow(dbConnection, chosenId);
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid value!");
                                    scan.next();
                                }
                                break;
                            case "2":
                                landslag.displayData(dbConnection, "all");
                                System.out.println("\nDelete from Table 'landslag'");
                                System.out.println("Please type the landslag_id (id can only contain numbers) \n//: ");
                                try {
                                    int chosenId = scan.nextInt();
                                    landslag.deleteRow(dbConnection, chosenId);
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid value!");
                                    scan.next();
                                }
                                break;
                            case "3":

                                break;
                            case "R":
                                break;
                            default:
                                System.out.println("'" + chosenDeleteMenu +"' is not an option");
                        }

                        break;
                    case "e": //exit
                        System.out.println("Have a nice day!");
                        finished = true;
                        break;
                    default:
                        System.out.println("Wrong input, '" + mainMenuChoice + "' is not an action");
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            System.out.println("Disconnected..");
        }

    }

    private void showDeleteMenu() {
        System.out.println("1 to delete a spiller");
        System.out.println("2 to delete a landslag");
        System.out.println("3 to delete a klubblag");
    }

    private void showUpdateLandslagMenu() {
        System.out.println("(table landslag)");
        System.out.println("'1' to update FifaRanking");
        System.out.println("'2' to update countryName (not implemented yet)");
        System.out.println("'3' to update coachName(not implemented yet)");
        System.out.println("'4' to update qualyfier continent (not implemented yet)");
        System.out.println("'R' to return");
    }

    private void showUpdateMenu() {
        System.out.println(
                "1 to update Table landslag \n" +
                        "2 to update Table spiller (not implemented yet)\n" +
                        "3 to update Table klubblag (not implemented yet)");
    }

    private void showCreateMenu() {
        System.out.println(
                "'1' to create new klubblag\n" +
                        "'2' to add new spiller \n" +
                        "'3' to add new landslag \n"
        );
    }

    private void showSearchMenu() {
        System.out.println("Type:\n" +
                " '1' for spillere \n" +
                " '2' for klubb  (not implemented yet)\n" +
                " '3' for landslag \n" +
                " '4' to return\n" +
                "--------------------------\n//:");
    }

}
