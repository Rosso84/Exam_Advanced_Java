import DBHandler.InitDefaultDataBase;
import DisplayView.Menus;

public class runApllication {

    private static InitDefaultDataBase init = new InitDefaultDataBase();


    public static void main(String[] args) {
        Menus menu = new Menus();

        init.setup();
        menu.showMainMenu();

    }
}
