package userInterfaces.graphicsActions;

import controller.FileManagement;
import controller.gameController.GameController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.*;
import logs.PlayerLogs;
import model.Player;
import model.card.Card;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.GameComponent;
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.userMenu.play.PlayerGraphicThread;
import userInterfaces.userMenu.play.BaseGameThread;
import userInterfaces.userMenu.UserFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameAction extends MainMenuAction {
    private GameController gameController;
    private BaseGameThread baseGameThread;
    private PlayerGraphicThread playerGraphicThread;

    public GameAction(BaseGameThread baseGameThread, PlayerGraphicThread playerGraphicThread) {
        super(null);
        this.playerGraphicThread = playerGraphicThread;
        this.baseGameThread = baseGameThread;
    }

    public void selectCompetitorAction (JButton button, String competitorUsername,
                                        GameController gameController){
        button.addActionListener(actionEvent2 -> {
            Player temporaryPlayer = FileManagement.getInstance().getPlayerFile().creatPlayerFromFile(competitorUsername);
            gameController.setPlayers(new Player[]{baseGameThread.getMinePlayer(), temporaryPlayer});
            this.gameController = gameController;
            baseGameThread.onEnabledMenu();
            playerGraphicThread.doNotify();
        });
    }

    public void menuIconClick(JButton button, UserFrame userFrame) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[1],
                    LogsEnum.valueOf("play").getEvent_description()[2], baseGameThread.getAction().getPlayerController().getPlayer());
            baseGameThread.offEnabledMenu();
            MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                    GraphicsDefault.GameBoard.menuMessage, baseGameThread.getUserFrame().getPane(), false, MineGameLayer.message.getLayer());
            ComponentCreator.getInstance().setText(MessageEnum.gameMenu.getText(),
                    messagePanel, "FORTE", 35
                    , Color.black, new Bounds(0, 0, GraphicsDefault.GameBoard.menuMessage.getWidth(), GraphicsDefault.GameBoard.menuMessage.getHeight()));
            JButton mainMenu = ComponentCreator.getInstance().setButton("Main Menu", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.menuButtons(1), Color.white, 25, 1);
            mainMenu.addActionListener(actionEvent2 -> {
                PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[3],
                        LogsEnum.valueOf("play").getEvent_description()[4], baseGameThread.getAction().getPlayerController().getPlayer());
                baseGameThread.getMainGameSounds().stopAudio();
                userFrame.getMainSounds().playLoop();
                baseGameThread.onEnabledMenu();
                baseGameThread.getUserFrame().startMainMenu();
            });
            JButton exitGameButton = ComponentCreator.getInstance().setButton("Exit Game", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.menuButtons(4), Color.white, 25, 1);
            super.exitGame(exitGameButton);
            JButton back = ComponentCreator.getInstance().setButton("Resume", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.menuButtons(2), Color.white, 25, 1);
            back.addActionListener(actionEvent2 -> {
                PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[2],
                        LogsEnum.valueOf("play").getEvent_description()[3], baseGameThread.getAction().getPlayerController().getPlayer());
                baseGameThread.onEnabledMenu();
            });
            messagePanel.setVisible(true);
            messagePanel.setEnabled(true);
        });
    }

    public void showAroundDeck(JButton button) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[4],
                    LogsEnum.valueOf("play").getEvent_description()[5], baseGameThread.getAction().getPlayerController().getPlayer());
            baseGameThread.offEnabledMenu();
            MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                    GraphicsDefault.GameBoard.aroundDeckPanel, baseGameThread.getUserFrame().getPane(), false, MineGameLayer.message.getLayer());
            ArrayList<Card> showCard = gameController.getGame().getPlayerGames(0).getAroundCard();
            for (int i = 0; i < showCard.size(); i++) {
                GameComponent cardImage = new GameComponent(baseGameThread.getUserFrame().getPane(), MineGameLayer.messageContent.getLayer(),
                        FilesPath.graphicsPath.cardsPath + "/" + showCard.get(i).getName() + ".png",
                        GraphicsDefault.GameBoard.aroundDeckCard(2, i));
            }
            JButton back = ComponentCreator.getInstance().setButton("Back", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.aroundDeckCard(1, 0), Color.white, 25, 1);
            back.addActionListener(actionEvent2 -> {
                baseGameThread.onEnabledMenu();
            });
            messagePanel.setVisible(true);
            messagePanel.setEnabled(true);
        });
    }


    public void endTurn(JButton button) {
        button.addActionListener(actionEvent -> {
            gameController.endTurn();
            gameController.addGameEvent(GameEventEnum.endTurn, "next_player");
            baseGameThread.drawGameBoard();
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
        });
    }

    public void minionCardSelect(JButton button, Card card) {
        button.addActionListener(actionEvent -> {
            try {
                gameController.playMinion(card);
                gameController.addGameEvent(GameEventEnum.playMinion, card.getName());
                FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
                baseGameThread.drawGameBoard();
            } catch (Exception e) {
            }
        });
    }

    public void spellCardSelect(JButton button, Card card) {
        button.addActionListener(actionEvent -> {
            try {
                gameController.playSpell(card);
                gameController.addGameEvent(GameEventEnum.playSpell, card.getName());
                FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
                baseGameThread.drawGameBoard();
            } catch (Exception e) {
            }
        });
    }

    public void weaponCardSelect(JButton button, Card card) {
        button.addActionListener(actionEvent -> {
            try {
                gameController.playWeapon(card);
                gameController.addGameEvent(GameEventEnum.playWeapon, card.getName());
                FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
                baseGameThread.drawGameBoard();
            } catch (Exception e) {
            }
        });
    }

    public void heroPowerSelect(JButton button) {
        button.addActionListener(actionEvent -> {
           /* gameController.addGameEvent(GameEventEnum.playHeroPower,
                    gameController.getGame().getPlayerGames(0).getHero().getHeroName(),0,playerController.getPlayer());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());*/
            baseGameThread.drawGameBoard();
        });
    }

    public void infoButtonAction(JButton button, InfoPassiveEnum infoPassiveEnum) {
        button.addActionListener(actionEvent2 -> {
            gameController.addGameEvent(GameEventEnum.selectInfoPassive, infoPassiveEnum.getTitle());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            gameController.getGame().getPlayerGames(0).setInfoPassive(infoPassiveEnum);
            baseGameThread.onEnabledMenu();
            playerGraphicThread.doNotify();
        });
    }

}
