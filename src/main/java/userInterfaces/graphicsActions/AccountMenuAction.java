package userInterfaces.graphicsActions;

import controller.PlayerController;
import defaults.GraphicsDefault;
import enums.ExceptionsEnum;
import enums.LogsEnum;
import enums.MessageEnum;
import logs.PlayerLogs;
import userInterfaces.Sounds;
import userInterfaces.userMenu.UserFrame;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MessageCreator;

import javax.swing.*;
import java.awt.*;

public class AccountMenuAction extends MyAction {

    public AccountMenuAction(PlayerController playerController) {
        super(playerController);
    }

    public void signIn(JFrame accountFrame, JPanel messagePanel, JPanel mainPanel, JButton button, JTextField username, JPasswordField password,
                       Sounds sounds) {
        button.addActionListener(actionEvent -> {
            try {
                PlayerController playerController = new PlayerController();
                playerController.signInPlayer(username.getText(), String.valueOf(password.getPassword()));
                PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[1],
                        LogsEnum.valueOf("sign").getEvent_description()[1], playerController.getPlayer());
                PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[1],
                        LogsEnum.valueOf("sign").getEvent_description()[1], playerController.getPlayer());
                sounds.stopAudio();
                accountFrame.dispose();
                UserFrame userFrame = new UserFrame(playerController);
                userFrame.startMainMenu();
            } catch (Exception e) {
                if (e.getMessage().equals(ExceptionsEnum.valueOf("userNoExist").getMessage())) {
                    JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                            GraphicsDefault.AccountMenu.messageButtonBounds, Color.white, 35,0);
                    okMessage(messagePanel, new JPanel[]{mainPanel}, okButton);
                    MessageCreator.getInstance().accountMessage(MessageEnum.valueOf("userNoExist"), messagePanel,
                            new JPanel[]{mainPanel}, 25, new JButton[]{okButton});
                }
                if (e.getMessage().equals(ExceptionsEnum.valueOf("wrongPassword").getMessage())) {
                    JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                            GraphicsDefault.AccountMenu.messageButtonBounds, Color.white, 30,0);
                    okMessage(messagePanel, new JPanel[]{mainPanel}, okButton);
                    MessageCreator.getInstance().accountMessage(MessageEnum.valueOf("wrongPassword"), messagePanel,
                            new JPanel[]{mainPanel}, 25, new JButton[]{okButton});
                }
            }
        });
    }

    public void signUp(JFrame accountFrame, JPanel messagePanel, JPanel mainPanel, JButton button, JTextField username,
                       JPasswordField password, Sounds sounds) {
        button.addActionListener(actionEvent -> {
            try {
                PlayerController playerController = new PlayerController();
                playerController.signUpPlayer(username.getText(), String.valueOf(password.getPassword()));
                PlayerLogs.creatLogFile(playerController.getPlayer());
                PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[0],
                        LogsEnum.valueOf("sign").getEvent_description()[0], playerController.getPlayer());
                sounds.stopAudio();
                accountFrame.dispose();
                UserFrame userFrame = new UserFrame(playerController);
                userFrame.startMainMenu();
            } catch (Exception e) {
                if (e.getMessage().equals(ExceptionsEnum.valueOf("emptyImport").getMessage())) {
                    JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                            GraphicsDefault.AccountMenu.messageButtonBounds, Color.white, 30,0);
                    okMessage(messagePanel, new JPanel[]{mainPanel}, okButton);
                    MessageCreator.getInstance().accountMessage(MessageEnum.valueOf("emptyImport"), messagePanel,
                            new JPanel[]{mainPanel}, 25, new JButton[]{okButton});
                }
                if (e.getMessage().equals(ExceptionsEnum.valueOf("userRepeated").getMessage())) {
                    JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                            GraphicsDefault.AccountMenu.messageButtonBounds, Color.white, 30,0);
                    okMessage(messagePanel, new JPanel[]{mainPanel}, okButton);
                    MessageCreator.getInstance().accountMessage(MessageEnum.valueOf("repeatedUsername"), messagePanel,
                            new JPanel[]{mainPanel}, 25, new JButton[]{okButton});
                }
            }
        });
    }

}
