package userInterfaces.graphicsActions;

import controller.PlayerController;
import defaults.GraphicsDefault;
import enums.LogsEnum;
import enums.MessageEnum;
import logs.PlayerLogs;
import userInterfaces.myComponent.MessageCreator;
import userInterfaces.userMenu.UserMenu;

import javax.swing.*;
import java.awt.*;

public class MainMenuAction extends MyAction {

    public MainMenuAction(PlayerController playerController) {
        super(playerController);
    }


    public void backToUserMenu(JButton button, UserMenu userMenu) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("back").getEvent()[0],
                    LogsEnum.valueOf("back").getEvent_description()[0], playerController.getPlayer());
            userMenu.startMainMenu();
        });
    }

    public void playGame(JButton button, UserMenu userMenu) {
        button.addActionListener(actionEvent -> {
            if(playerController.getPlayer().getGameDeck().getName().equals(playerController.getPlayer().getFreeDeck().getName())){
                JButton okbutton = MessageCreator.getInstance().errorMessage(
                        MessageEnum.valueOf("emptyGameDeck").getText(), userMenu.getPane(),
                        GraphicsDefault.message.mainMenuMessagePanel, 30,30);
                userMenu.getMainMenu().offEnabledMenu();
                okbutton.addActionListener(actionEvent2 -> {
                    for (Component c : userMenu.getPane().getComponentsInLayer(9))
                        userMenu.getPane().remove(c);
                    PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[0],
                            LogsEnum.valueOf("play").getEvent_description()[1], playerController.getPlayer());
                    userMenu.getMainMenu().onEnabledMenu();
                    userMenu.startCollection();
                });
            }else {
                PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[0],
                        LogsEnum.valueOf("play").getEvent_description()[0], playerController.getPlayer());
                userMenu.startGame();
            }
        });

    }

    public void goShop(JButton button, UserMenu userMenu) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[0],
                    LogsEnum.valueOf("shop").getEvent_description()[0], playerController.getPlayer());
            userMenu.startShopMenu();
        });
    }

    public void goStatus(JButton button, UserMenu userMenu) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("status").getEvent()[0],
                    LogsEnum.valueOf("status").getEvent_description()[0], playerController.getPlayer());
            userMenu.startStatus();
        });
    }

    public void goCollection(JButton button, UserMenu userMenu) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[0],
                    LogsEnum.valueOf("collection").getEvent_description()[0], playerController.getPlayer());
            userMenu.startCollection();
        });
    }

    public void goSetting(JButton button, UserMenu userMenu) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("setting").getEvent()[0],
                    LogsEnum.valueOf("setting").getEvent_description()[0], playerController.getPlayer());
            userMenu.startSettingMenu();
        });
    }
}
