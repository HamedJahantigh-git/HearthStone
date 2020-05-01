package userInterfaces.userMenu;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import userInterfaces.graphicsActions.ShopMenuAction;
import userInterfaces.graphicsActions.StatusMenuAction;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyJPanel;

import javax.swing.*;
import java.awt.*;

public class StatusMenu {
    private UserMenu userMenu;
    private StatusMenuAction action;
    private MyJPanel mainPanel;

    public StatusMenu(UserMenu userMenu){
        this.userMenu = userMenu;
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Status Menu.jpg",
                GraphicsDefault.UserMenu.mainBounds, userMenu.getPane(), false, 35);
        action = new StatusMenuAction(userMenu.getPlayerController());
        initMainPanel();
    }

    private void initMainPanel() {
        JButton exitGame = ComponentCreator.getInstance().setButton("Exit Game", mainPanel, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() * 5 / 24 + 5,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth() / 9,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 14), Color.white, 30, 0);
        action.exitGame(exitGame);
        JButton back = ComponentCreator.getInstance().setButton("Back", mainPanel, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth() / 12,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 14), Color.white, 30, 0);
        action.backToUserMenu(back,userMenu);
    }
}
