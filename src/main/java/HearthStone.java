
import controller.FileManagement;
import controller.PlayerController;
import initializer.InitCLI;
import initializer.InitCards;
import initializer.InitHero;
import userInterfaces.AccountMenu;
import userInterfaces.userMenu.UserMenu;


public class HearthStone {
    public static void main(String[] args) {
        initializer();
        //startCLIGame();
        startGraphicalGame();

    }

    private static void initializer() {
        InitCLI.createNeedsFolder();
        InitHero.saveHeroModels();
        InitCards.initSpell();
        InitCards.initMinion();
        InitCards.initWeapon();
    }

    private static void startCLIGame() {
        //CLIMenu.getInstance().accountMenu();
    }

    /*private static void test(){
        PlayerController hamed = new PlayerController();
        try {
            hamed.signInPlayer("ali","ali");
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserMenu userMenu = new UserMenu(hamed);
        userMenu.startMainMenu();
    }*/

    private static void startGraphicalGame() {
        AccountMenu.getInstance().start();
    }

}
