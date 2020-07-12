
import CLI.CLIMenu;
import controller.PlayerController;
import controller.game.playingCard.CardController;
import enums.CardMechanicsEnum;
import initializer.InitCLI;
import initializer.InitCards;
import initializer.InitHero;
import model.card.Card;
import model.card.Minion;
import model.hero.Hero;
import model.hero.Mage;
import userInterfaces.AccountMenu;
import userInterfaces.userMenu.UserFrame;

import java.util.ArrayList;


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

    private static void test() {
        PlayerController hamed = new PlayerController();
        try {
            hamed.signInPlayer("test1", "test1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserFrame userFrame = new UserFrame(hamed);
        userFrame.startMainMenu();
        //userFrame.startMineGame();
    }
}

