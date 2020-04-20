package userInterfaces.userMenu;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import userInterfaces.graphicsActions.CollectionMenuAction;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyCard;
import userInterfaces.myComponent.MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CollectionMenu {
    private MyJPanel mainPanel;
    private MyJPanel heroPanel;
    private MyJPanel deckPanel;
    private MyJPanel cardPanel;
    private CollectionMenuAction action;
    private UserMenu userMenu;

    private JButton exitGame, back;
    private MyCard neutral, mage, warlock, priest, hunter, rogue;
    private ArrayList<MyCard> heroes;

    public CollectionMenu(UserMenu userMenu) {
        this.userMenu = userMenu;
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Main Collection.jpg",
                GraphicsDefault.UserMenu.mainBounds, userMenu.getPane(), false, 10);
        action = new CollectionMenuAction(userMenu.getPlayerController());
        heroes = new ArrayList<>();
        initMainPanel();
        initRight();
        initCenter();
    }

    public void initMainPanel() {
        for (Component component : mainPanel.getComponents()) {
            mainPanel.remove(component);
        }
        initDown();
        initUpper();
    }

    private void initDown() {
        exitGame = ComponentCreator.getInstance().setButton("Exit Game", mainPanel, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() * 5 / 24 + 5,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth() / 9,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 14), Color.white, 30, 0);
        action.exitGame(exitGame);
        back = ComponentCreator.getInstance().setButton("Back", mainPanel, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth() / 12,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 14), Color.white, 30, 0);
        action.backToUserMenu(back, userMenu);
    }

    private void initUpper() {

        neutral = new MyCard(mainPanel, FilesPath.graphicsPath.collectionPath + "/neutralCollection1.png",
                new Bounds(GraphicsDefault.Collection.cardPanel.getX() + GraphicsDefault.Collection.cardPanel.getWidth() * 2 / 11
                        + 0 * GraphicsDefault.Collection.cardPanel.getWidth() / 10
                        , GraphicsDefault.Collection.heroesUpper.getY()
                        , GraphicsDefault.Collection.heroesUpper.getWidth(),
                        GraphicsDefault.Collection.heroesUpper.getWidth()));
        mage = new MyCard(mainPanel, FilesPath.graphicsPath.collectionPath + "/MageCollection1.png",
                new Bounds(GraphicsDefault.Collection.cardPanel.getX() + GraphicsDefault.Collection.cardPanel.getWidth() * 2 / 11
                        + 1 * GraphicsDefault.Collection.cardPanel.getWidth() / 10
                        , GraphicsDefault.Collection.heroesUpper.getY()
                        , GraphicsDefault.Collection.heroesUpper.getWidth(),
                        GraphicsDefault.Collection.heroesUpper.getWidth()));

        hunter = new MyCard(mainPanel, FilesPath.graphicsPath.collectionPath + "/HunterCollection1.png",
                new Bounds(GraphicsDefault.Collection.cardPanel.getX() + GraphicsDefault.Collection.cardPanel.getWidth() * 2 / 11
                        + 2 * GraphicsDefault.Collection.cardPanel.getWidth() / 10
                        , GraphicsDefault.Collection.heroesUpper.getY()
                        , GraphicsDefault.Collection.heroesUpper.getWidth(),
                        GraphicsDefault.Collection.heroesUpper.getWidth()));
        priest = new MyCard(mainPanel, FilesPath.graphicsPath.collectionPath + "/PriestCollection1.png",
                new Bounds(GraphicsDefault.Collection.cardPanel.getX() + GraphicsDefault.Collection.cardPanel.getWidth() * 2 / 11
                        + 3 * GraphicsDefault.Collection.cardPanel.getWidth() / 10
                        , GraphicsDefault.Collection.heroesUpper.getY()
                        , GraphicsDefault.Collection.heroesUpper.getWidth(),
                        GraphicsDefault.Collection.heroesUpper.getWidth()));
        rogue = new MyCard(mainPanel, FilesPath.graphicsPath.collectionPath + "/RogueCollection1.png",
                new Bounds(GraphicsDefault.Collection.cardPanel.getX() + GraphicsDefault.Collection.cardPanel.getWidth() * 2 / 11
                        + 4 * GraphicsDefault.Collection.cardPanel.getWidth() / 10
                        , GraphicsDefault.Collection.heroesUpper.getY()
                        , GraphicsDefault.Collection.heroesUpper.getWidth(),
                        GraphicsDefault.Collection.heroesUpper.getWidth()));

        warlock = new MyCard(mainPanel, FilesPath.graphicsPath.collectionPath + "/WarlockCollection1.png",
                new Bounds(GraphicsDefault.Collection.cardPanel.getX() + GraphicsDefault.Collection.cardPanel.getWidth() * 2 / 11
                        + 5 * GraphicsDefault.Collection.cardPanel.getWidth() / 10
                        , GraphicsDefault.Collection.heroesUpper.getY()
                        , GraphicsDefault.Collection.heroesUpper.getWidth(),
                        GraphicsDefault.Collection.heroesUpper.getWidth()));
        heroes.add(neutral);
        heroes.add(mage);
        heroes.add(hunter);
        heroes.add(rogue);
        heroes.add(warlock);
        heroes.add(priest);
        neutral.moveListener();
        mage.moveListener();
        hunter.moveListener();
        rogue.moveListener();
        warlock.moveListener();
        priest.moveListener();
        action.showCards(mage, "Mage", heroes);
        action.showCards(hunter, "Hunter", heroes);
        action.showCards(priest, "Priest", heroes);
        action.showCards(rogue, "Rogue", heroes);
        action.showCards(neutral, "Neutral", heroes);
        action.showCards(warlock, "Warlock", heroes);
    }

    private void initRight() {
        deckPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/deckCollection.png",
                GraphicsDefault.Collection.rightPanel, userMenu.getPane(), false, 12);
        /*JLabel title = ComponentCreator.getInstance().setText("My Decks", deckPanel,
                "FORTE", 27, Color.black,
                new Bounds(0, GraphicsDefault.Collection.rightPanel.getHeight() / 80,
                        GraphicsDefault.Collection.rightPanel.getWidth(),
                        GraphicsDefault.Collection.rightPanel.getHeight() / 20));
        JTextPane test = new JTextPane();
        test.setBounds(0, 0, 100, 100);
        //test.setOpaque(false);
        JLabel ali = new JLabel("alli");
        test.add(ali);
        deckPanel.add(test);
        test.add(title);*/

    }

    private void initCenter() {
        cardPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/CardCollection.png",
                GraphicsDefault.Collection.cardPanel, userMenu.getPane(), false, 13);
    }

}
