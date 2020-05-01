package userInterfaces.graphicsActions;

import controller.FileManagement;
import controller.GameController;
import controller.PlayerController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.GameLayer;
import enums.LogsEnum;
import enums.MessageEnum;
import logs.PlayerLogs;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MessageCreator;
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.userMenu.GameBoard;
import userInterfaces.userMenu.UserMenu;

import javax.swing.*;
import java.awt.*;

public class GameAction extends MainMenuAction {
    private GameController gameController;

    public GameAction(PlayerController playerController, GameController gameController) {
        super(playerController);
        this.gameController = gameController;
    }

    public void menuIconClick(JButton button, GameBoard gameBoard) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[1],
                    LogsEnum.valueOf("play").getEvent_description()[2], gameBoard.getAction().getPlayerController().getPlayer());
            gameBoard.offEnabledMenu();
            MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                    GraphicsDefault.GameBoard.menuMessage, gameBoard.getUserMenu().getPane(), false, GameLayer.message.getLayer());
            ComponentCreator.getInstance().setText(MessageEnum.gameMenu.getText(),
                    messagePanel, "FORTE", 35
                    , Color.black, new Bounds(0, 0, GraphicsDefault.GameBoard.menuMessage.getWidth(), GraphicsDefault.GameBoard.menuMessage.getHeight()));
            JButton mainMenu = ComponentCreator.getInstance().setButton("Main Menu", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.menuButtons(1), Color.white, 25, 1);
            mainMenu.addActionListener(actionEvent2 -> {
                PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[3],
                        LogsEnum.valueOf("play").getEvent_description()[4], gameBoard.getAction().getPlayerController().getPlayer());
                gameBoard.onEnabledMenu();
                gameBoard.getUserMenu().startMainMenu();
            });
            JButton exitGameButton = ComponentCreator.getInstance().setButton("Exit Game", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.menuButtons(4), Color.white, 25, 1);
            super.exitGame(exitGameButton);
            JButton back = ComponentCreator.getInstance().setButton("Resume", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.menuButtons(2), Color.white, 25, 1);
            back.addActionListener(actionEvent2 -> {
                PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[2],
                        LogsEnum.valueOf("play").getEvent_description()[3], gameBoard.getAction().getPlayerController().getPlayer());
                gameBoard.onEnabledMenu();
            });
            messagePanel.setVisible(true);
            messagePanel.setEnabled(true);
        });
    }

    public void showAroundDeck(JButton button, UserMenu userMenu) {
        button.addActionListener(actionEvent -> {
            MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                    GraphicsDefault.GameBoard.menuMessage, userMenu.getPane(), false, 49);

            messagePanel.setVisible(true);
            messagePanel.setEnabled(true);
        });
    }


    public void endTurn(JButton button, UserMenu userMenu) {
        button.addActionListener(actionEvent -> {
            FileManagement.getInstance().savePlayerToFile(playerController.getPlayer());
        });
    }
}
