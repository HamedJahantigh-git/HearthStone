package userInterfaces;

import defaults.FilesPath;
import defaults.GraphicsDefault;

import javax.swing.*;
import java.awt.*;


public class AccountMenu {
    private static final AccountMenu instance = new AccountMenu();
    private MyFrame accountFrame;
    private JLayeredPane pane;
    private MyJPanel mainPanel, messagePanel;
    private JButton exitGameButton, signInButton, signUpButton;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel usernameLabel, passwordLabel;


    private AccountMenu() {
        accountFrame = new MyFrame("Account Menu", GraphicsDefault.AccountMenu.mainBounds);
        pane = accountFrame.getLayeredPane();
        pane.setLayout(null);
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/accountMenu.jpg",
                GraphicsDefault.AccountMenu.mainBounds, pane, false, 1);
        messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.AccountMenu.messageBounds, pane, false, 2);
    }

    public static AccountMenu getInstance() {
        return instance;
    }

    public void start() {
        boxes();
        buttons();
        mainPanel.setVisible(true);
        mainPanel.paint(mainPanel.getGraphics());
    }

    private void boxes() {
        tfUsername = ComponentCreator.getInstance().setImportBox(mainPanel, 30, new Color(0, 136, 204),
                new Bounds(GraphicsDefault.AccountMenu.mainBounds.getWidth() / 2,
                        GraphicsDefault.AccountMenu.componentHeight * 3 / 2 + 10,
                        GraphicsDefault.AccountMenu.mainBounds.getWidth() / 2 - 30,
                        GraphicsDefault.AccountMenu.componentHeight * 2 / 3));
        pfPassword = ComponentCreator.getInstance().setPasswordField(mainPanel,
                new Bounds(GraphicsDefault.AccountMenu.mainBounds.getWidth() / 2,
                        GraphicsDefault.AccountMenu.componentHeight * 5 / 2 + 10,
                        GraphicsDefault.AccountMenu.mainBounds.getWidth() / 2 - 30,
                        GraphicsDefault.AccountMenu.componentHeight * 2 / 3), 20, new Color(0, 136, 204));
        usernameLabel = ComponentCreator.getInstance().setText("Enter Your Usename:", mainPanel,
                "FORTE", 20, Color.black,
                new Bounds(30, GraphicsDefault.AccountMenu.componentHeight * 3 / 2 + 10,
                        GraphicsDefault.AccountMenu.mainBounds.getWidth() / 2 - 30,
                        GraphicsDefault.AccountMenu.componentHeight * 2 / 3));
        passwordLabel = ComponentCreator.getInstance().setText("Enter Your Password:", mainPanel,
                "FORTE", 21, Color.BLACK,
                new Bounds(20, GraphicsDefault.AccountMenu.componentHeight * 5 / 2 + 10,
                        GraphicsDefault.AccountMenu.mainBounds.getWidth() / 2 - 30,
                        GraphicsDefault.AccountMenu.componentHeight * 2 / 3));
    }

    private void buttons() {
        String buttonsImagePath = "buttons1.png";
        exitGameButton = ComponentCreator.getInstance().setButton(
                "Exit Game", mainPanel, buttonsImagePath,
                new Bounds((GraphicsDefault.AccountMenu.mainBounds.getWidth() - GraphicsDefault.AccountMenu.componentWidth) / 2 - 5,
                        GraphicsDefault.AccountMenu.componentHeight * 9 / 2 + 20,
                        GraphicsDefault.AccountMenu.componentWidth,
                        GraphicsDefault.AccountMenu.componentHeight), Color.black, 30);
        MyActionListener.getInstance().exitGame(exitGameButton, null);
        signInButton = ComponentCreator.getInstance().setButton(
                "Sign In", mainPanel, buttonsImagePath,
                new Bounds(5 + GraphicsDefault.AccountMenu.mainBounds.getWidth() / 2,
                        GraphicsDefault.AccountMenu.componentHeight * 7 / 2 + 10,
                        GraphicsDefault.AccountMenu.componentWidth / 2,
                        GraphicsDefault.AccountMenu.componentHeight * 7 / 10), Color.black, 18);
        MyActionListener.getInstance().signIn(accountFrame, messagePanel, mainPanel, signInButton, tfUsername, pfPassword);
        signUpButton = ComponentCreator.getInstance().setButton(
                "Sign Up", mainPanel, buttonsImagePath,
                new Bounds(GraphicsDefault.AccountMenu.mainBounds.getWidth() / 4 - 5,
                        GraphicsDefault.AccountMenu.componentHeight * 7 / 2 + 10,
                        GraphicsDefault.AccountMenu.componentWidth / 2,
                        GraphicsDefault.AccountMenu.componentHeight * 7 / 10), Color.black, 18);
        MyActionListener.getInstance().signUp(accountFrame, messagePanel, mainPanel, signUpButton, tfUsername, pfPassword);

    }
}
