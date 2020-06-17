
import CLI.CLIMenu;
import controller.PlayerController;
import initializer.InitCLI;
import initializer.InitCards;
import initializer.InitHero;
import userInterfaces.AccountMenu;
import userInterfaces.userMenu.UserFrame;


public class HearthStone {
    public static void main(String[] args) {
        initializer();
        //startGraphicalGame();
        test();

    }

    private static void initializer() {
        InitCLI.createNeedsFolder();
        InitHero.saveHeroModels();
        InitCards.initSpell();
        InitCards.initMinion();
        InitCards.initWeapon();
    }

    private static void startGraphicalGame() {
        AccountMenu.getInstance().start();
    }

    private static void startCLIGame() {
        CLIMenu.getInstance();
    }

    private static void test(){
        PlayerController hamed = new PlayerController();
        try {
            hamed.signInPlayer("hamed","hamed");
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserFrame userFrame = new UserFrame(hamed);
        userFrame.startMainMenu();
    }

}
