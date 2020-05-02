package userInterfaces.graphicsActions;

import controller.FileManagement;
import controller.GameController;
import controller.PlayerController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.*;
import logs.PlayerLogs;
import model.Game;
import model.card.Card;
import model.card.Minion;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.GameComponent;
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.userMenu.GameBoard;
import userInterfaces.userMenu.UserMenu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameAction extends MainMenuAction {
    private GameController gameController;
    private GameBoard gameBoard;

    public GameAction(PlayerController playerController, GameController gameController, GameBoard gameBoard) {
        super(playerController);
        this.gameController = gameController;
        this.gameBoard = gameBoard;
    }

    public void menuIconClick(JButton button, UserMenu userMenu) {
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
                gameBoard.getMainGameSounds().stopAudio();
                userMenu.getMainSounds().playLoop();
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

    public void showAroundDeck(JButton button) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[4],
                    LogsEnum.valueOf("play").getEvent_description()[5], gameBoard.getAction().getPlayerController().getPlayer());
            gameBoard.offEnabledMenu();
            MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                    GraphicsDefault.GameBoard.aroundDeckPanel, gameBoard.getUserMenu().getPane(), false, GameLayer.message.getLayer());
            ArrayList<Card> showCard=gameController.getGame().getPlayerGames(0).getAroundCard();
            for (int i = 0; i <showCard.size() ; i++) {
                GameComponent cardImage = new GameComponent( gameBoard.getUserMenu().getPane(), GameLayer.messageContent.getLayer(),
                        FilesPath.graphicsPath.cardsPath + "/"+showCard.get(i).getName()+".png",
                        GraphicsDefault.GameBoard.aroundDeckCard(2,i));
            }
            JButton back = ComponentCreator.getInstance().setButton("Back", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.aroundDeckCard(1,0), Color.white, 25, 1);
            back.addActionListener(actionEvent2 -> {
                gameBoard.onEnabledMenu();
            });
            messagePanel.setVisible(true);
            messagePanel.setEnabled(true);
        });
    }


    public void endTurn(JButton button) {
        button.addActionListener(actionEvent -> {
            gameController.endTurn();
            gameController.addGameEvent(GameEventEnum.endTurn,"next_player",0,playerController.getPlayer());
            gameBoard.drawGameBoard();
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
        });
    }

    public void minionCardSelect(JButton button, Card card) {
        button.addActionListener(actionEvent -> {
            try {
                gameController.playMinion(card);
                gameController.addGameEvent(GameEventEnum.playMinion,card.getName(),0,playerController.getPlayer());
                FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
                gameBoard.drawGameBoard();
            }catch (Exception e){
            }
        });
    }

    public void spellCardSelect(JButton button, Card card) {
        button.addActionListener(actionEvent -> {
            try {
                gameController.playSpell(card);
                gameController.addGameEvent(GameEventEnum.playSpell,card.getName(),0,playerController.getPlayer());
                FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
                gameBoard.drawGameBoard();
            }catch (Exception e){
            }
        });
    }

    public void weaponCardSelect(JButton button, Card card) {
        button.addActionListener(actionEvent -> {
            try {
                gameController.playWeapon(card);
                gameController.addGameEvent(GameEventEnum.playWeapon,card.getName(),0,playerController.getPlayer());
                FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
                gameBoard.drawGameBoard();
            }catch (Exception e){
            }
        });
    }

    public void heroPowerSelect(JButton button) {
        button.addActionListener(actionEvent -> {
           /* gameController.addGameEvent(GameEventEnum.playHeroPower,
                    gameController.getGame().getPlayerGames(0).getHero().getHeroName(),0,playerController.getPlayer());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());*/
            gameBoard.drawGameBoard();
        });
    }

    public void infoButtonAction (JButton button,InfoPassiveEnum infoPassiveEnum){
        button.addActionListener(actionEvent2 -> {
            gameController.addGameEvent(GameEventEnum.selectInfoPassive,infoPassiveEnum.getTitle(),0,playerController.getPlayer());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            gameController.getGame().getPlayerGames(0).setInfoPassive(infoPassiveEnum);
            gameBoard.onEnabledMenu();
        });
    }
}
