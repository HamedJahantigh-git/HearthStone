package userInterfaces;

import controller.PlayerController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
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

    public UserMenu(PlayerController playerController) {
        this.playerController = playerController;
        userFrame = new MyFrame("User Menu",
                GraphicsDefault.UserMenu.mainBounds);
        pane = userFrame.getLayeredPane();
        mainSounds = new Sounds("MainMenu.wav");
        initMainMenuPanel();
        initCollection();
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

    private void initMainMenuPanel() {
        MyJPanel mainMenuPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/UserMenu1.jpg",
                GraphicsDefault.UserMenu.mainBounds, pane, false, 1);
        MyJPanel boxPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/UserMenu2.png",
                GraphicsDefault.UserMenu.boxMainBounds, pane, false, 2);
        mainSounds.playLoop();
        UserMenuAction userMenuAction = new UserMenuAction(playerController);
        JButton exitGame = ComponentCreator.getInstance().setButton("Exit Game", mainMenuPanel, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() - GraphicsDefault.UserMenu.mainBounds.getWidth() / 6 - 40,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth() / 6,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 12), Color.white, 35);
        userMenuAction.exitGame(exitGame, playerController);
        JButton setting = ComponentCreator.getInstance().setButton("", mainMenuPanel, "setting1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 8 - 40,
                        20,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 8,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 8), Color.white, 35);
        userMenuAction.goSetting();
        JButton playButton = ComponentCreator.getInstance().setButton("Play", boxPanel, "buttons3.png",
                new Bounds(GraphicsDefault.UserMenu.boxMainBounds.getWidth() / 5,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() * 3 / 12,
                        GraphicsDefault.UserMenu.boxMainBounds.getWidth() * 6 / 10,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() / 7 - 10), Color.white, 30);
        userMenuAction.playGame(playButton);
        JButton shopButton = ComponentCreator.getInstance().setButton("Shop", boxPanel, "buttons3.png",
                new Bounds(GraphicsDefault.UserMenu.boxMainBounds.getWidth() / 5,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() * 5 / 12,
                        GraphicsDefault.UserMenu.boxMainBounds.getWidth() * 6 / 10,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() / 7 - 10), Color.white, 30);
        userMenuAction.goShop(shopButton);
        JButton collectionButton = ComponentCreator.getInstance().setButton("Collection", boxPanel, "buttons3.png",
                new Bounds(GraphicsDefault.UserMenu.boxMainBounds.getWidth() / 5,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() * 7 / 12,
                        GraphicsDefault.UserMenu.boxMainBounds.getWidth() * 6 / 10,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() / 7 - 10), Color.white, 27);
        userMenuAction.goCollection(collectionButton, this);
        JButton statusButton = ComponentCreator.getInstance().setButton("Status", boxPanel, "buttons3.png",
                new Bounds(GraphicsDefault.UserMenu.boxMainBounds.getWidth() / 5,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() * 9 / 12,
                        GraphicsDefault.UserMenu.boxMainBounds.getWidth() * 6 / 10,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() / 7 - 10), Color.white, 27);
        userMenuAction.goStatus(collectionButton);
    }

    private void initCollection() {
        MyJPanel mainCollection = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Main Collection.jpg",
                GraphicsDefault.UserMenu.mainBounds, pane, false, 10);
        CollectionMenuAction collectionMenuAction = new CollectionMenuAction(playerController);
        JButton exitGame = ComponentCreator.getInstance().setButton("Exit Game", mainCollection, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() * 5 / 24 + 5,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth() / 9,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 14), Color.white, 30);
        collectionMenuAction.exitGame(exitGame, playerController);
        JButton back = ComponentCreator.getInstance().setButton("Back", mainCollection, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth() / 12,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 14), Color.white, 30);
        collectionMenuAction.backToUserMenu(back, this);
    }

}
