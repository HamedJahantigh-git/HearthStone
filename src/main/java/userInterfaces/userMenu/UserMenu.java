package userInterfaces.userMenu;

import controller.PlayerController;
import defaults.GraphicsDefault;
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

    public UserMenu(PlayerController playerController) {
        this.playerController = playerController;
        userFrame = new MyFrame("User Menu",
                GraphicsDefault.UserMenu.mainBounds);
        pane = userFrame.getLayeredPane();
        mainSounds = new Sounds("MainMenu.wav");
        mainMenu = new MainMenu(this);
        shopMenu = new ShopMenu(this);
        collectionMenu = new CollectionMenu(this);
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



    public void startCollection() {
        showPanel("Collection");
        ArrayList<Card> cards = new ArrayList<>();
        collectionMenu.startShowCardPanelContent("Pleas Select Hero To Show Cards", cards);
    }

    public void startShopMenu() {
        showPanel("Shop");
        shopMenu.getCardPanel().removeAll();
    }

    public void startMainMenu() {
        showPanel("Main Menu");
    }

    void showPanel(String s) {
        switch (s) {
            case "Main Menu":
                offAllLayer();
                for (int i = 0; i < 10; i++) {
                    for (Component component : pane.getComponentsInLayer(i)) {
                        component.setVisible(true);
                        component.setEnabled(true);
                    }
                }
                break;
            case "Shop":
                offAllLayer();
                for (int i = 20; i < 30; i++) {
                    for (Component component : pane.getComponentsInLayer(i)) {
                        component.setVisible(true);
                        component.setEnabled(true);
                    }
                }
                break;
            case "Collection":
                offAllLayer();
                for (int i = 10; i < 20; i++) {
                    for (Component component : pane.getComponentsInLayer(i)) {
                        component.setVisible(true);
                        component.setEnabled(true);
                    }
                }
                break;
        }
    }

    private void offAllLayer (){
        for (Component component : pane.getComponents()) {
            component.setVisible(false);
            component.setEnabled(false);
        }
    }

}
