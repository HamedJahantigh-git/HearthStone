package userInterfaces.userMenu;

import controller.PlayerController;
import defaults.GraphicsDefault;
import model.Player;
import model.card.Card;
import userInterfaces.Sounds;
import userInterfaces.myComponent.MyFrame;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class UserMenu {

    private PlayerController playerController;
    private MyFrame userFrame;
    private JLayeredPane pane;
    private Sounds mainSounds;
    private MainMenu mainMenu;
    private CollectionMenu collectionMenu;
    private ShopMenu shopMenu;
    private StatusMenu statusMenu;
    private GameBoard gameBoard;

    public UserMenu(PlayerController playerController) {
        this.playerController = playerController;
        userFrame = new MyFrame("User Menu",
                GraphicsDefault.UserMenu.mainBounds);
        pane = userFrame.getLayeredPane();
        mainSounds = new Sounds("MainMenu.wav");
        mainMenu = new MainMenu(this);
        shopMenu = new ShopMenu(this);
        collectionMenu = new CollectionMenu(this);
        statusMenu = new StatusMenu(this);
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public JLayeredPane getPane() {
        return pane;
    }

    public Sounds getMainSounds() {
        return mainSounds;
    }

    public ShopMenu getShopMenu() {
        return shopMenu;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public void startCollection() {
        showPanel("Collection");
        ArrayList<Card> cards = new ArrayList<>();
        collectionMenu.startShowCardPanelContent("Pleas Select Hero To Show Cards", cards);
    }

    public void startShopMenu() {
        showPanel("Shop");
        shopMenu.getCardPanel().removeAll();
    }

    public void startGame() {
        deleteLayer(40,60);
        gameBoard = new GameBoard(this, new Player[] {playerController.getPlayer()},"Battle Ground 1");
        showPanel("Play");
    }

    public void startSettingMenu() {

    }

    public void startStatus() {
        showPanel("Status");
    }

    public void startMainMenu() {
        showPanel("Main Menu");
    }

    void showPanel(String s) {
        switch (s) {
            case "Main Menu":
                offAllLayer();
                onLayer(0, 10);
                break;
            case "Play":
                offAllLayer();
                onLayer(40, 60);
                break;
            case "Shop":
                offAllLayer();
                onLayer(20, 30);
                break;
            case "Collection":
                offAllLayer();
                onLayer(10, 20);
                break;
            case "Setting":
                offAllLayer();
                onLayer(30, 35);
                break;
            case "Status":
                offAllLayer();
                onLayer(35, 40);
                break;
        }
    }

    private void offAllLayer() {
        for (Component component : pane.getComponents()) {
            component.setVisible(false);
            component.setEnabled(false);
        }
    }

    private void onLayer(int start, int end) {
        for (int i = start; i < end; i++) {
            for (Component component : pane.getComponentsInLayer(i)) {
                component.setVisible(true);
                component.setEnabled(true);
            }
        }
    }

    private void deleteLayer(int start, int end) {
        for (int i = start; i < end; i++) {
            for (Component component : pane.getComponentsInLayer(i)) {
                pane.remove(component);
            }
        }
    }

}
