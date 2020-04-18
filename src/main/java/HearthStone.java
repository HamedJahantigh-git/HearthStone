
import controller.FileManagement;
import controller.PlayerController;
import defaults.FilesPath;
import defaults.ModelDefault;
import initializer.InitCLI;
import initializer.InitCards;
import model.card.Card;
import org.w3c.dom.css.Counter;
import userInterfaces.AccountMenu;
import userInterfaces.UserMenu;

import java.util.ArrayList;

public class HearthStone {
    public static void main(String[] args) {
        initializer();
        //startCLIGame();
        startGraphicalGame();
/*
        PlayerController hamed = new PlayerController();
        try {
            hamed.signInPlayer("hamed","hamed");
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserMenu userMenu = new UserMenu(hamed);
        userMenu.startCollection();
        */

    }

    private static void initializer() {
        InitCLI.createNeedsFolder();
        InitCards.initSpell();
        InitCards.initMinion();
        //todo
    }

    private static void startCLIGame() {
        //CLIMenu.getInstance().accountMenu();
    }

    private static void startGraphicalGame() {
        AccountMenu.getInstance().start();
    }

}
