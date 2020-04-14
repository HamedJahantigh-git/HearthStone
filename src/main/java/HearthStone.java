import CLI.CLIMenu;
import initializer.InitCLI;
import initializer.InitCards;
import userInterfaces.AccountMenu;

import java.util.concurrent.TimeUnit;

public class HearthStone {
    public static void main(String[] args) {
        initializer();
        //startCLIGame();
        startGraphicalGame();
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
