package userInterfaces.graphicsActions;

import controller.PlayerController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.GameSoundsEnum;
import enums.LogsEnum;
import enums.MainLayer;
import enums.MessageEnum;
import logs.PlayerLogs;
import userInterfaces.Sounds;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MessageCreator;
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.userMenu.UserFrame;

import javax.swing.*;
import java.awt.*;

public class MainMenuAction extends MyAction {

    public MainMenuAction(PlayerController playerController) {
        super(playerController);
    }


    public void backToUserMenu(JButton button, UserFrame userFrame) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("back").getEvent()[0],
                    LogsEnum.valueOf("back").getEvent_description()[0], playerController.getPlayer());
            userFrame.startMainMenu();
        });
    }

    public void playGame(JButton button, UserFrame userFrame) {
        button.addActionListener(actionEvent -> {
            if(playerController.getPlayer().getGameDeck().getName().equals(playerController.getPlayer().getFreeDeck().getName())){
                JButton okButton = MessageCreator.getInstance().errorMessage(
                        MessageEnum.valueOf("emptyGameDeck").getText(), userFrame.getPane(),
                        GraphicsDefault.message.mainMenuMessagePanel, 30,30);
                userFrame.getMainMenu().offEnabledMenu();
                okButton.addActionListener(actionEvent2 -> {
                    for (Component c : userFrame.getPane().getComponentsInLayer(MainLayer.message.getLayer()))
                        userFrame.getPane().remove(c);
                    PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[0],
                            LogsEnum.valueOf("play").getEvent_description()[1], playerController.getPlayer());
                    userFrame.getMainMenu().onEnabledMenu();
                    userFrame.startCollection();
                });
            }else {
                PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[0],
                        LogsEnum.valueOf("play").getEvent_description()[0], playerController.getPlayer());
                userFrame.getMainMenu().offEnabledMenu();
                MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                        GraphicsDefault.GameBoard.menuMessage,  userFrame.getPane(), true, MainLayer.message.getLayer());
                ComponentCreator.getInstance().setText(MessageEnum.playMenu.getText(),
                        messagePanel, "FORTE", 35
                        , Color.black, new Bounds(0, 0, GraphicsDefault.GameBoard.menuMessage.getWidth(), GraphicsDefault.GameBoard.menuMessage.getHeight()));
                JButton mainMenu = ComponentCreator.getInstance().setButton("Play With Mine", messagePanel, "buttons3.png",
                        GraphicsDefault.GameBoard.menuButtons(1), Color.white, 23, 1);
                mainMenu.addActionListener(actionEvent2 -> {
                    PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[7],
                            LogsEnum.valueOf("play").getEvent_description()[7], playerController.getPlayer());
                    userFrame.startMineGame();
                });
                JButton back = ComponentCreator.getInstance().setButton("Back", messagePanel, "buttons3.png",
                        GraphicsDefault.GameBoard.menuButtons(4), Color.white, 25, 1);
                back.addActionListener(actionEvent2 -> {
                    PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[6],
                            LogsEnum.valueOf("play").getEvent_description()[4], playerController.getPlayer());
                    for (Component c : userFrame.getPane().getComponentsInLayer(MainLayer.message.getLayer()))
                        userFrame.getPane().remove(c);
                    userFrame.getMainMenu().onEnabledMenu();
                });
            }
        });

    }

    public void goShop(JButton button, UserFrame userFrame) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[0],
                    LogsEnum.valueOf("shop").getEvent_description()[0], playerController.getPlayer());
            userFrame.startShopMenu();
        });
    }

    public void goStatus(JButton button, UserFrame userFrame) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("status").getEvent()[0],
                    LogsEnum.valueOf("status").getEvent_description()[0], playerController.getPlayer());
            userFrame.startStatus();
        });
    }

    public void goCollection(JButton button, UserFrame userFrame) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[0],
                    LogsEnum.valueOf("collection").getEvent_description()[0], playerController.getPlayer());
            userFrame.startCollection();
        });
    }

    public void goSetting(JButton button, UserFrame userFrame) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("setting").getEvent()[0],
                    LogsEnum.valueOf("setting").getEvent_description()[0], playerController.getPlayer());
            userFrame.startSettingMenu();
        });
    }

    public void deleteAccountAction(JButton deleteAccount,  UserFrame userFrame) {
        deleteAccount.addActionListener(actionEvent -> {
            userFrame.getMainMenu().offEnabledMenu();
            MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                    GraphicsDefault.GameBoard.menuMessage,  userFrame.getPane(), true, MainLayer.message.getLayer());
            ComponentCreator.getInstance().setText(MessageEnum.deleteAccount.getText(),
                    messagePanel, "FORTE", 35
                    , Color.black, new Bounds(0, 0, GraphicsDefault.GameBoard.menuMessage.getWidth(), GraphicsDefault.GameBoard.menuMessage.getHeight()));
            JPasswordField pfPassword = ComponentCreator.getInstance().setPasswordField(messagePanel,
                    new Bounds(GraphicsDefault.AccountMenu.mainBounds.getWidth() / 4,
                            GraphicsDefault.AccountMenu.componentHeight * 4 + 10,
                            GraphicsDefault.AccountMenu.mainBounds.getWidth() / 2,
                            GraphicsDefault.AccountMenu.componentHeight * 2 / 3), 20, new Color(0, 136, 204));

            ComponentCreator.getInstance().setText("Enter Your Password:", messagePanel,
                    "FORTE", 21, Color.BLACK,
                    new Bounds(0, GraphicsDefault.AccountMenu.componentHeight * 6 / 2 + 10,
                            GraphicsDefault.AccountMenu.mainBounds.getWidth(),
                            GraphicsDefault.AccountMenu.componentHeight * 2 / 3));


            JButton delete = ComponentCreator.getInstance().setButton("Delete", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.deleteAccountButtons(0), Color.white, 25, 1);
            delete.addActionListener(actionEvent2 -> {
                try {
                    playerController.deleteAccount(String.valueOf(pfPassword.getPassword()));
                    System.exit(0);
                } catch (Exception e) {
                        new Sounds(GameSoundsEnum.mistake.getPath()).playOne();
                }
            });
            JButton back = ComponentCreator.getInstance().setButton("Back", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.deleteAccountButtons(1), Color.white, 25, 1);
            back.addActionListener(actionEvent2 -> {
                for (Component c : userFrame.getPane().getComponentsInLayer(MainLayer.message.getLayer()))
                    userFrame.getPane().remove(c);
                userFrame.getMainMenu().onEnabledMenu();
            });
        });
    }
}
