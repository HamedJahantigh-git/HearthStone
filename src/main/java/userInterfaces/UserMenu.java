package userInterfaces;

import controller.PlayerController;


import javax.swing.*;


public class UserMenu {
    private static final UserMenu instance = new UserMenu();

    private PlayerController playerController;
    private MyFrame userFrame;
    private JLayeredPane pane;

    private UserMenu() {
        userFrame = new MyFrame("User Menu",
                new Bounds(0, 0, 100, 100));
        pane = userFrame.getLayeredPane();
    }

    public static UserMenu getInstance() {
        return instance;
    }

    public void start(PlayerController playerController) {

        this.playerController = playerController;
    }

}
