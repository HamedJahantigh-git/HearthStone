package userInterfaces.userMenu;

import controller.PlayerController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import userInterfaces.Sounds;
import userInterfaces.graphicsActions.CollectionMenuAction;
import userInterfaces.graphicsActions.UserMenuAction;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyFrame;
import userInterfaces.myComponent.MyJPanel;


import javax.swing.*;
import java.awt.*;


public class UserMenu {

    private PlayerController playerController;
    private MyFrame userFrame;
    private JLayeredPane pane;
    private Sounds mainSounds;
    private MainMenu mainMenu;
    private CollectionMenu collectionMenu;

    public UserMenu(PlayerController playerController) {
        this.playerController = playerController;
        userFrame = new MyFrame("User Menu",
                GraphicsDefault.UserMenu.mainBounds);
        pane = userFrame.getLayeredPane();
        mainSounds = new Sounds("MainMenu.wav");
        mainMenu = new MainMenu(this);
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

    public void startCollection() {
        showPanel("Collection");
    }

    public void startMainMenu() {
        showPanel("Main Menu");
    }

    void showPanel(String s) {
        switch (s) {
            case "Main Menu":
                for (Component component : pane.getComponents()) {
                    component.setVisible(false);
                    component.setEnabled(false);
                }
                for (int i = 0; i < 10; i++) {
                    for (Component component : pane.getComponentsInLayer(i)) {
                        component.setVisible(true);
                        component.setEnabled(true);
                    }
                }
                break;
            case "Shop":

                break;
            case "Collection":
                for (Component component : pane.getComponents()) {
                    component.setVisible(false);
                    component.setEnabled(false);
                }
                for (int i = 10; i < 20; i++) {
                    for (Component component : pane.getComponentsInLayer(i)) {
                        component.setVisible(true);
                        component.setEnabled(true);
                    }
                }
                break;
        }
    }

}
