package userInterfaces.graphicsActions;

import controller.PlayerController;
import defaults.GraphicsDefault;
import enums.ExceptionsEnum;
import enums.LogsEnum;
import enums.MessageEnum;
import logs.PlayerLogs;
import model.hero.Hero;
import network.client.ClientNetwork;
import userInterfaces.AccountMenu;
import userInterfaces.MyGraphics;
import userInterfaces.Sounds;
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.userMenu.UserFrame;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MessageCreator;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class AccountMenuAction extends MyAction {

    private MyJPanel messagePanel;
    private MyJPanel mainPanel;

    public AccountMenuAction() {
        super(null);
    }

    public void networkConnect(JButton button, JTextField tfIp, JTextField tfPort,
                               MyGraphics myGraphics) {
        button.addActionListener(actionEvent -> {
            try {
                checkStringIsNumber(tfPort.getText());
                setGraphics(myGraphics);
                messagePanel = myGraphics.getAccountMenu().getMessagePanel();
                mainPanel = myGraphics.getAccountMenu().getMainPanel();
                clientNetwork = new ClientNetwork(myGraphics, tfIp.getText(),
                        Integer.parseInt(tfPort.getText()));
                myGraphics.setClientNetwork(clientNetwork);
                clientNetwork.start();
            } catch (Exception e) {
            }
        });
    }

    private void checkStringIsNumber(String str) throws Exception {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)))
                throw new Exception(ExceptionsEnum.WRONG_PORT.getMessage());
        }
    }

    public void connectSuccessful() {
        myGraphics.getAccountMenu().startAccount();
    }

    public void signIn(JButton button, JTextField username, JPasswordField password) {
        button.addActionListener(actionEvent -> {
            clientNetwork.getSender().getAccountHandler().signIn(
                    username.getText(), String.valueOf(password.getPassword()));
                    });
    }

    public void signUp(JButton button, JTextField username, JPasswordField password) {
        button.addActionListener(actionEvent -> {
            clientNetwork.getSender().getAccountHandler().signUp(
                    username.getText(), String.valueOf(password.getPassword()));
        });
    }

    public void signSuccess(ArrayList<String> playerHeroesName) {
        myGraphics.getAccountMenu().getAccountSound().stopAudio();
        myGraphics.getAccountMenu().getAccountFrame().dispose();
        myGraphics.setPlayerHeroesName(playerHeroesName);
        UserFrame userFrame = new UserFrame(myGraphics);
        myGraphics.setUserFrame(userFrame);
        userFrame.startMainMenu();
    }

    public void emptyImport() {
        JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                GraphicsDefault.AccountMenu.messageButtonBounds, Color.white, 30, 0);
        okMessage(messagePanel, new JPanel[]{mainPanel}, okButton);
        MessageCreator.getInstance().accountMessage(MessageEnum.valueOf("emptyImport"), messagePanel,
                new JPanel[]{mainPanel}, 25, new JButton[]{okButton});

    }

    public void repeatedUsername() {
        JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                GraphicsDefault.AccountMenu.messageButtonBounds, Color.white, 30, 0);
        okMessage(messagePanel, new JPanel[]{mainPanel}, okButton);
        MessageCreator.getInstance().accountMessage(MessageEnum.valueOf("repeatedUsername"), messagePanel,
                new JPanel[]{mainPanel}, 25, new JButton[]{okButton});

    }

    public void wrongPassword() {
        JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                GraphicsDefault.AccountMenu.messageButtonBounds, Color.white, 30, 0);
        okMessage(messagePanel, new JPanel[]{mainPanel}, okButton);
        MessageCreator.getInstance().accountMessage(MessageEnum.valueOf("wrongPassword"), messagePanel,
                new JPanel[]{mainPanel}, 25, new JButton[]{okButton});
    }

    public void userNoExist() {
        JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                GraphicsDefault.AccountMenu.messageButtonBounds, Color.white, 35, 0);
        okMessage(messagePanel, new JPanel[]{mainPanel}, okButton);
        MessageCreator.getInstance().accountMessage(MessageEnum.valueOf("userNoExist"), messagePanel,
                new JPanel[]{mainPanel}, 25, new JButton[]{okButton});
    }
}
