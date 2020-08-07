package userInterfaces;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.ComponentEnum;
import enums.FontEnum;
import userInterfaces.graphicsActions.AccountMenuAction;
import userInterfaces.myComponent.*;

import javax.swing.*;
import java.awt.*;


public class AccountMenu {
    private MyGraphics myGraphics;
    private MyJFrame accountFrame;
    private JLayeredPane pane;
    private MyJPanel mainPanel, messagePanel;
    private String buttonsImagePath = "buttons1.png";

    private JTextField tfUsername, tfIp, tfPort;
    private JPasswordField pfPassword;
    private AccountMenuAction accountMenuAction;
    private Sounds accountSound;


    public AccountMenu(MyGraphics myGraphics) {
        this.myGraphics = myGraphics;
        accountSound = new Sounds("AccountMenu.wav");
        accountFrame = new MyJFrame("Account Menu", GraphicsDefault.AccountMenu.mainBounds);
        pane = accountFrame.getLayeredPane();
        pane.setLayout(null);
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/accountMenu.jpg",
                GraphicsDefault.AccountMenu.mainBounds, pane, false, 1);
        messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.AccountMenu.messageBounds, pane, false, 2);
    }

    public void startClientNetwork() {
        accountMenuAction = new AccountMenuAction();
        accountSound.playLoop();
        drawNetworkPanel();
    }

    private void drawNetworkPanel() {
        ComponentCreator.getInstance().setText("Network Connection", messagePanel,
                new MyFont(FontEnum.TITLE.getName(), 27), Color.black,
                GraphicsDefault.AccountMenu.networkPanel(ComponentEnum.TITLE));

        ComponentCreator.getInstance().setText("Enter Network IP:", messagePanel,
                new MyFont(FontEnum.LABEl.getName(), 20), Color.black,
                GraphicsDefault.AccountMenu.networkPanel(ComponentEnum.IP_LABEL));
        ComponentCreator.getInstance().setText("Enter Network Port:", messagePanel,
                new MyFont(FontEnum.LABEl.getName(), 20), Color.black,
                GraphicsDefault.AccountMenu.networkPanel(ComponentEnum.PORT_LABEL));

        tfIp = ComponentCreator.getInstance().setImportBox(messagePanel, 30, new Color(0, 136, 204),
                GraphicsDefault.AccountMenu.networkPanel(ComponentEnum.IP_BOX));
        tfPort = ComponentCreator.getInstance().setImportBox(messagePanel, 30, new Color(0, 136, 204),
                GraphicsDefault.AccountMenu.networkPanel(ComponentEnum.PORT_BOX));
        JButton confirmButton = ComponentCreator.getInstance().setButton("Confirm",
                messagePanel, buttonsImagePath, GraphicsDefault.AccountMenu.networkPanel(ComponentEnum.BUTTON),
                Color.black, 21, 0);
        accountMenuAction.networkConnect(confirmButton, tfIp, tfPort, myGraphics);
        mainPanel.setVisible(true);
        messagePanel.setVisible(true);
    }

    public void startAccount() {
        messagePanel.clearAllComponent();
        messagePanel.setVisible(false);
        boxes();
        buttons();
        mainPanel.repaint();
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
        ComponentCreator.getInstance().setText("Enter Your Usename:", mainPanel,
                new MyFont(FontEnum.LABEl.getName(), 20), Color.black,
                new Bounds(30, GraphicsDefault.AccountMenu.componentHeight * 3 / 2 + 10,
                        GraphicsDefault.AccountMenu.mainBounds.getWidth() / 2 - 30,
                        GraphicsDefault.AccountMenu.componentHeight * 2 / 3));
        ComponentCreator.getInstance().setText("Enter Your Password:", mainPanel,
                new MyFont(FontEnum.LABEl.getName(), 20), Color.BLACK,
                new Bounds(20, GraphicsDefault.AccountMenu.componentHeight * 5 / 2 + 10,
                        GraphicsDefault.AccountMenu.mainBounds.getWidth() / 2 - 30,
                        GraphicsDefault.AccountMenu.componentHeight * 2 / 3));
    }

    private void buttons() {
        JButton exitGameButton = ComponentCreator.getInstance().setButton(
                "Exit Game", mainPanel, buttonsImagePath,
                new Bounds((GraphicsDefault.AccountMenu.mainBounds.getWidth() - GraphicsDefault.AccountMenu.componentWidth) / 2 - 5,
                        GraphicsDefault.AccountMenu.componentHeight * 9 / 2 + 20,
                        GraphicsDefault.AccountMenu.componentWidth,
                        GraphicsDefault.AccountMenu.componentHeight), Color.black, 30, 0);
        accountMenuAction.exitGame(exitGameButton);
        JButton signInButton = ComponentCreator.getInstance().setButton(
                "Sign In", mainPanel, buttonsImagePath,
                new Bounds(5 + GraphicsDefault.AccountMenu.mainBounds.getWidth() / 2,
                        GraphicsDefault.AccountMenu.componentHeight * 7 / 2 + 10,
                        GraphicsDefault.AccountMenu.componentWidth / 2,
                        GraphicsDefault.AccountMenu.componentHeight * 7 / 10), Color.black, 18, 0);
        accountMenuAction.signIn(signInButton, tfUsername, pfPassword);
        JButton signUpButton = ComponentCreator.getInstance().setButton(
                "Sign Up", mainPanel, buttonsImagePath,
                new Bounds(GraphicsDefault.AccountMenu.mainBounds.getWidth() / 4 - 5,
                        GraphicsDefault.AccountMenu.componentHeight * 7 / 2 + 10,
                        GraphicsDefault.AccountMenu.componentWidth / 2,
                        GraphicsDefault.AccountMenu.componentHeight * 7 / 10), Color.black, 18, 0);
        accountMenuAction.signUp( signUpButton, tfUsername, pfPassword);

    }

    public AccountMenuAction getAccountMenuAction() {
        return accountMenuAction;
    }

    public Sounds getAccountSound() {
        return accountSound;
    }

    public MyJFrame getAccountFrame() {
        return accountFrame;
    }

    public MyJPanel getMainPanel() {
        return mainPanel;
    }

    public MyJPanel getMessagePanel() {
        return messagePanel;
    }
}
