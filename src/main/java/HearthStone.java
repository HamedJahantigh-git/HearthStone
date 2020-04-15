import CLI.CLIMenu;
import controller.PlayerController;
import initializer.InitCLI;
import initializer.InitCards;
import userInterfaces.AccountMenu;
import userInterfaces.UserMenu;

public class HearthStone {
    public static void main(String[] args) {
        initializer();
        //startCLIGame();
        //startGraphicalGame();
        PlayerController hamed = new PlayerController();
        try {
            hamed.signInPlayer("hamed","hamed");
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserMenu.getInstance().setPlayerController(hamed);
        UserMenu.getInstance().startMainMenu();
    }

    private static void initializer() {
        InitCLI.createNeedsFolder();
        InitCards.initSpell();
        InitCards.initMinion();
    }

    private static void startCLIGame() {
        CLIMenu.getInstance().accountMenu();
    }

    private static void startGraphicalGame() {
        AccountMenu.getInstance().start();
    }

}
