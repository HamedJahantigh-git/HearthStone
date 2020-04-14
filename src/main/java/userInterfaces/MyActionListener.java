package userInterfaces;

import controller.FileManagement;
import controller.PlayerController;
import defaults.GraphicsDefault;
import enums.ExceptionsEnum;
import enums.LogsEnum;
import enums.MessageEnum;
import logs.PlayerLogs;


import javax.swing.*;
import java.awt.*;

public class MyActionListener {

    private static MyActionListener instance = new MyActionListener();

    private MyActionListener() {

    }

    public static MyActionListener getInstance() {
        return instance;
    }

    public void exitGame(JButton button, PlayerController playerController) {
        button.addActionListener(actionEvent -> {
            if (playerController != null) {
                PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[2],
                        LogsEnum.valueOf("sign").getEvent_description()[3], playerController.getPlayer());
                FileManagement.savePlayerToFile(playerController.getPlayer());
            }
            System.exit(0);
        });
    }

    public void signIn(JFrame accountFrame, JPanel messagePanel, JPanel mainPanel, JButton button, JTextField username, JPasswordField password) {
        button.addActionListener(actionEvent -> {
            try {
                PlayerController playerController = new PlayerController();
                playerController.signInPlayer(username.getText(), String.valueOf(password.getPassword()));
                PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[1],
                        LogsEnum.valueOf("sign").getEvent_description()[1], playerController.getPlayer());
                PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[1],
                        LogsEnum.valueOf("sign").getEvent_description()[1], playerController.getPlayer());
                accountFrame.dispose();
                UserMenu.getInstance().start(playerController);
            } catch (Exception e) {
                if (e.getMessage().equals(ExceptionsEnum.valueOf("userNoExist").getMessage())) {
                    JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                            GraphicsDefault.AccountMenu.messageButtonBounds, Color.white, 35);
                    okMessage(messagePanel, new JPanel[]{mainPanel}, okButton);
                    MessageCreator.getInstance().panelMessage(MessageEnum.valueOf("userNoExist"), messagePanel,
                            new JPanel[]{mainPanel}, 25, new JButton[]{okButton});
                }
                if (e.getMessage().equals(ExceptionsEnum.valueOf("wrongPassword").getMessage())) {
                    JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                            GraphicsDefault.AccountMenu.messageButtonBounds, Color.white, 30);
                    okMessage(messagePanel, new JPanel[]{mainPanel}, okButton);
                    MessageCreator.getInstance().panelMessage(MessageEnum.valueOf("wrongPassword"), messagePanel,
                            new JPanel[]{mainPanel}, 25, new JButton[]{okButton});
                }
            }
        });
    }

    public void signUp(JFrame accountFrame, JPanel messagePanel, JPanel mainPanel, JButton button, JTextField username, JPasswordField password) {
        button.addActionListener(actionEvent -> {
            try {
                PlayerController playerController = new PlayerController();
                playerController.signUpPlayer(username.getText(), String.valueOf(password.getPassword()));
                PlayerLogs.creatLogFile(playerController.getPlayer());
                PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[0],
                        LogsEnum.valueOf("sign").getEvent_description()[0], playerController.getPlayer());
                accountFrame.dispose();
                UserMenu.getInstance().start(playerController);
            } catch (Exception e) {
                if (e.getMessage().equals(ExceptionsEnum.valueOf("emptyImport").getMessage())) {
                    JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                            GraphicsDefault.AccountMenu.messageButtonBounds, Color.white, 30);
                    okMessage(messagePanel, new JPanel[]{mainPanel}, okButton);
                    MessageCreator.getInstance().panelMessage(MessageEnum.valueOf("emptyImport"), messagePanel,
                            new JPanel[]{mainPanel}, 25, new JButton[]{okButton});
                }
                if (e.getMessage().equals(ExceptionsEnum.valueOf("userRepeated").getMessage())) {
                    JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                            GraphicsDefault.AccountMenu.messageButtonBounds, Color.white, 30);
                    okMessage(messagePanel, new JPanel[]{mainPanel}, okButton);
                    MessageCreator.getInstance().panelMessage(MessageEnum.valueOf("repeatedUsername"), messagePanel,
                            new JPanel[]{mainPanel}, 25, new JButton[]{okButton});
                }
            }
        });
    }

    public void okMessage(JPanel messagePanel, JPanel[] onPanels, JButton button) {
        button.addActionListener(actionEvent -> {
            for (JPanel panel : onPanels) {
                for (Component c : panel.getComponents()) c.setEnabled(true);
            }
            messagePanel.setVisible(false);
            messagePanel.removeAll();
        });
    }

}
