package userInterfaces.userMenu;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import userInterfaces.graphicsActions.UserMenuAction;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyJPanel;

import javax.swing.*;
import java.awt.*;

public class MainMenu {

    private MyJPanel mainPanel;
    private MyJPanel boxPanel;
    private UserMenuAction action;
    private UserMenu userMenu;

    public MainMenu(UserMenu userMenu) {
        this.userMenu = userMenu;
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/UserMenu1.jpg",
                GraphicsDefault.UserMenu.mainBounds, userMenu.getPane(), false, 1);
        boxPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/UserMenu2.png",
                GraphicsDefault.UserMenu.boxMainBounds, userMenu.getPane(), false, 2);
        action = new UserMenuAction(userMenu.getPlayerController());
        init();
    }

    private void init() {
        userMenu.getMainSounds().playLoop();
        JButton exitGame = ComponentCreator.getInstance().setButton("Exit Game", mainPanel, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() - GraphicsDefault.UserMenu.mainBounds.getWidth() / 6 - 40,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth() / 6,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 12), Color.white, 35, 0);
        action.exitGame(exitGame);
        JButton setting = ComponentCreator.getInstance().setButton("", mainPanel, "setting1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 8 - 40,
                        20,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 8,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 8), Color.white, 35, 1);
        action.goSetting();
        JButton playButton = ComponentCreator.getInstance().setButton("Play", boxPanel, "buttons3.png",
                new Bounds(GraphicsDefault.UserMenu.boxMainBounds.getWidth() / 5,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() * 3 / 12,
                        GraphicsDefault.UserMenu.boxMainBounds.getWidth() * 6 / 10,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() / 7 - 10), Color.white, 30, 1);
        action.playGame(playButton);
        JButton shopButton = ComponentCreator.getInstance().setButton("Shop", boxPanel, "buttons3.png",
                new Bounds(GraphicsDefault.UserMenu.boxMainBounds.getWidth() / 5,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() * 5 / 12,
                        GraphicsDefault.UserMenu.boxMainBounds.getWidth() * 6 / 10,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() / 7 - 10), Color.white, 30, 1);
        action.goShop(shopButton, userMenu);
        JButton collectionButton = ComponentCreator.getInstance().setButton("Collection", boxPanel, "buttons3.png",
                new Bounds(GraphicsDefault.UserMenu.boxMainBounds.getWidth() / 5,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() * 7 / 12,
                        GraphicsDefault.UserMenu.boxMainBounds.getWidth() * 6 / 10,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() / 7 - 10), Color.white, 27, 1);
        action.goCollection(collectionButton, userMenu);
        JButton statusButton = ComponentCreator.getInstance().setButton("Status", boxPanel, "buttons3.png",
                new Bounds(GraphicsDefault.UserMenu.boxMainBounds.getWidth() / 5,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() * 9 / 12,
                        GraphicsDefault.UserMenu.boxMainBounds.getWidth() * 6 / 10,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() / 7 - 10), Color.white, 27, 1);
        action.goStatus(collectionButton);
    }
}