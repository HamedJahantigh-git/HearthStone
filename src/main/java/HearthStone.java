import controller.PlayerController;
import initializer.InitCLI;
import initializer.InitCards;
import userInterfaces.AccountMenu;
import userInterfaces.userMenu.UserMenu;

public class HearthStone {
    public static void main(String[] args) {
        initializer();
        //startCLIGame();
        //startGraphicalGame();
        test();
    }

    private static void initializer() {
        InitCLI.createNeedsFolder();
        InitCards.initSpell();
        InitCards.initMinion();
        InitCards.initWeapon();
    }

    private static void startCLIGame() {
        //CLIMenu.getInstance().accountMenu();
    }

    private static void test(){
        PlayerController hamed = new PlayerController();
        try {
            hamed.signInPlayer("hamed","hamed");
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserMenu userMenu = new UserMenu(hamed);
        userMenu.startCollection();
    }

    private static void startGraphicalGame() {
        AccountMenu.getInstance().start();
    }

}
