package userInterfaces.graphicsActions;

import controller.PlayerController;
import enums.LogsEnum;
import logs.PlayerLogs;
import userInterfaces.userMenu.UserMenu;

import javax.swing.*;

public class UserMenuAction extends MyAction {

    public UserMenuAction(PlayerController playerController) {
        super(playerController);
    }


    public void backToUserMenu(JButton button, UserMenu userMenu) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("back").getEvent()[0],
                    LogsEnum.valueOf("back").getEvent_description()[0], playerController.getPlayer());
            userMenu.startMainMenu();
        });
    }

    public void playGame(JButton button) {
        button.addActionListener(actionEvent -> {

        });

    }

    public void goShop(JButton button) {
        button.addActionListener(actionEvent -> {

        });
    }

    public void goStatus(JButton button) {
        button.addActionListener(actionEvent -> {

        });
    }

    public void goCollection(JButton button, UserMenu userMenu) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[0],
                    LogsEnum.valueOf("collection").getEvent_description()[0], playerController.getPlayer());
            userMenu.startCollection();
        });
    }

    public void goSetting() {

    }
}
