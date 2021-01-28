package MainMenu;

import Tables.City;

public class main {

    static DbActions dbActions = new DbActions();



    public static void main(String[] args) {

       City city = new City( "NewCity","NCY", "North_city","100");

        System.out.println("Inserting a new row...");
        dbActions.createNewRow(city);
        System.out.println();

        System.out.println("Reading..");
        dbActions.readDataFromTable("Name", "CityName");

        System.out.println("Updating cityName");
        city.setName("Eventyrland");
        dbActions.updateTable(city);

        System.out.println("deleting a city from table..");
        dbActions.deleteDataFromTable(city);
    }
}
