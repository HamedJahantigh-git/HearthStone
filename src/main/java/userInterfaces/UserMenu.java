package userInterfaces;

import controller.PlayerController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.MessageEnum;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class UserMenu {
    private static final UserMenu instance = new UserMenu();

    private PlayerController playerController;
    private MyFrame userFrame;
    private JLayeredPane pane;

    private UserMenu() {
        userFrame = new MyFrame("User Menu",
                GraphicsDefault.UserMenu.mainBounds);
        pane = userFrame.getLayeredPane();
        initMainMenuPanel();
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public static UserMenu getInstance() {
        return instance;
    }

    private void showPanel(String s) {
        switch (s) {
            case "Main Menu":
                for (Component component : pane.getComponents()) {
                    component.setVisible(false);
                    component.setEnabled(false);
                }
                for (int i = 0; i <10 ; i++) {
                    for (Component component : pane.getComponentsInLayer(i)) {
                        component.setVisible(true);
                        component.setEnabled(true);
                    }
                }
                break;
            case "Shop":

                break;
        }
    }

    public void startMainMenu() {
        showPanel("Main Menu");
    }

    private void initMainMenuPanel() {
        MyJPanel mainMenuPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/UserMenu1.jpg",
                GraphicsDefault.UserMenu.mainBounds, pane, false, 1);
        MyJPanel boxPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/UserMenu2.png",
                GraphicsDefault.UserMenu.boxMainBounds, pane, false, 2);
        JButton exitGame = ComponentCreator.getInstance().setButton("Exit Game", mainMenuPanel, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth()-GraphicsDefault.UserMenu.mainBounds.getWidth()/6-40,
                        GraphicsDefault.UserMenu.mainBounds.getHeight()-GraphicsDefault.UserMenu.mainBounds.getHeight()/12-50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth()/6,
                        GraphicsDefault.UserMenu.mainBounds.getHeight()/12), Color.white, 35);
        MyActionListener.getInstance().exitGame(exitGame, playerController);
        JButton setting = ComponentCreator.getInstance().setButton("", mainMenuPanel, "setting1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth()-GraphicsDefault.UserMenu.mainBounds.getHeight()/8-40,
                        20,
                        GraphicsDefault.UserMenu.mainBounds.getHeight()/8,
                        GraphicsDefault.UserMenu.mainBounds.getHeight()/8), Color.white, 35);
        MyActionListener.getInstance().goSetting();
        setting.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                setting.setIcon(new ImageIcon(ComponentCreator.getInstance().setImage(
                        setting.getWidth(), setting.getHeight(),
                        FilesPath.graphicsPath.backgroundsPath + "/setting2.png")));
            }
            public void mouseExited(MouseEvent me) {
                setting.setIcon(new ImageIcon(ComponentCreator.getInstance().setImage(
                        setting.getWidth(), setting.getHeight(),
                        FilesPath.graphicsPath.backgroundsPath + "/setting1.png")));
            }
        });
        JButton playButton = ComponentCreator.getInstance().setButton("Play", boxPanel, "buttons3.png",
                new Bounds(GraphicsDefault.UserMenu.boxMainBounds.getWidth() / 5,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() * 3 / 12,
                        GraphicsDefault.UserMenu.boxMainBounds.getWidth() * 6 / 10,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() / 7 - 10), Color.white, 30);
        MyActionListener.getInstance().playGame();
        JButton shopButton = ComponentCreator.getInstance().setButton("Shop", boxPanel, "buttons3.png",
                new Bounds(GraphicsDefault.UserMenu.boxMainBounds.getWidth() / 5,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() * 5 / 12,
                        GraphicsDefault.UserMenu.boxMainBounds.getWidth() * 6 / 10,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() / 7 - 10), Color.white, 30);
        MyActionListener.getInstance().goShop();
        JButton collectionButton = ComponentCreator.getInstance().setButton("Collection", boxPanel, "buttons3.png",
                new Bounds(GraphicsDefault.UserMenu.boxMainBounds.getWidth() / 5,
                        GraphicsDefault.UserMenu.boxMainBounds.getHeight() * 7 / 12,
                        GraphicsDefault.UserMenu.boxMainBounds.getWidth() * 6 / 10,
        GraphicsDefault.UserMenu.boxMainBounds.getHeight() / 7 - 10), Color.white, 27);
        MyActionListener.getInstance().goCollection();
        mainMenuPanel.paint(mainMenuPanel.getGraphics());
        boxPanel.paint(boxPanel.getGraphics());
    }
}
