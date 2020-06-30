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
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.myComponent.gameComponent.PanelComponentDrawer;
import userInterfaces.userMenu.UserFrame;
import userInterfaces.userMenu.play.MineGameBoard;
import userInterfaces.userMenu.play.PlayerGraphicThread;
import userInterfaces.userMenu.play.BaseGameThread;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class GameAction extends MainMenuAction {
    private MineGameBoard mineGameBoard;
    private BaseGameThread baseGameThread;
    private PlayerGraphicThread playerGraphicThread;
    int c = 1;

    private GameController gameController;

    public GameAction(MineGameBoard mineGameBoard) {
        super(null);
        this.mineGameBoard = mineGameBoard;
    }

    @Override
    public void exitGame(JButton button) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[2],
                    LogsEnum.valueOf("sign").getEvent_description()[3], gameController.getGame().getPlayers(
                            gameController.getGame().getPlayerIndex()));
            gameController.addGameEvent(gameController.getGame().getPlayerIndex(),
                    GameEventEnum.stopGame, "click_exit_game");
            System.exit(0);
        });
    }

    public void setBaseGameThread(BaseGameThread baseGameThread) {
        this.baseGameThread = baseGameThread;
    }

    public void setPlayerGraphicThread(PlayerGraphicThread playerGraphicThread) {
        this.playerGraphicThread = playerGraphicThread;
    }

    public void selectCompetitorAction(JButton button, JComboBox combo,
                                       GameController gameController) {
        button.addActionListener(actionEvent2 -> {
            Player temporaryPlayer = FileManagement.getInstance().getPlayerFile().creatPlayerFromFile(
                    combo.getSelectedItem().toString());
            gameController.setPlayers(new Player[]{baseGameThread.getMinePlayer(), temporaryPlayer});
            this.gameController = gameController;
            mineGameBoard.onEnabledMenu();
            playerGraphicThread.doNotify();
        });
    }

    public void infoButtonAction(JButton button, InfoPassiveEnum infoPassiveEnum) {
        button.addActionListener(actionEvent2 -> {
            gameController.addGameEvent(gameController.getGame().getPlayerIndex(),
                    GameEventEnum.selectInfoPassive, infoPassiveEnum.getTitle());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            gameController.getGame().getPlayerGames(gameController.getGame().getPlayerIndex()).setInfoPassive(infoPassiveEnum);
            mineGameBoard.onEnabledMenu();
            gameController.switchPlayerIndex();
            playerGraphicThread.doNotify();
        });
    }

    public void selectStartCardToChange(JButton button, int index) {
        button.addActionListener(actionEvent -> {
            try {
                gameController.changeStartCard(gameController.getGame().getPlayerIndex(), index);
                playerGraphicThread.drawChangeStartCard();
            } catch (Exception e) {
                button.setBorder(new LineBorder(Color.red));
            }
        });
    }

    public void finishChangeStartCard(JButton button) {
        button.addActionListener(actionEvent2 -> {
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            mineGameBoard.onEnabledMenu();
            gameController.switchPlayerIndex();
            playerGraphicThread.doNotify();
        });
    }

    public void menuIconClick(JButton button, UserFrame userFrame) {
        button.addActionListener(actionEvent -> {
            mineGameBoard.offEnabledMenu();
            MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                    GraphicsDefault.GameBoard.menuMessage, baseGameThread.getUserFrame().getPane(), false, MineGameLayer.message.getLayer());
            ComponentCreator.getInstance().setText(MessageEnum.gameMenu.getText(),
                    messagePanel, "FORTE", 35
                    , Color.black, new Bounds(0, 0, GraphicsDefault.GameBoard.menuMessage.getWidth(), GraphicsDefault.GameBoard.menuMessage.getHeight()));
            JButton mainMenu = ComponentCreator.getInstance().setButton("Main Menu", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.menuButtons(1), Color.white, 25, 1);
            mainMenu.addActionListener(actionEvent2 -> {
                PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[3],
                        LogsEnum.valueOf("play").getEvent_description()[4],
                        gameController.getGame().getPlayers(gameController.getGame().getPlayerIndex()));
                gameController.addGameEvent(gameController.getGame().getPlayerIndex(), GameEventEnum.stopGame, "click_main_menu");
                mineGameBoard.getMainGameSounds().stopAudio();
                userFrame.getMainSounds().playLoop();
                mineGameBoard.onEnabledMenu();
                baseGameThread.getUserFrame().startMainMenu();
            });
            JButton exitGameButton = ComponentCreator.getInstance().setButton("Exit Game", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.menuButtons(4), Color.white, 25, 1);
            exitGame(exitGameButton);
            JButton back = ComponentCreator.getInstance().setButton("Resume", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.menuButtons(2), Color.white, 25, 1);
            back.addActionListener(actionEvent2 -> {
                mineGameBoard.onEnabledMenu();
            });
            messagePanel.setVisible(true);
            messagePanel.setEnabled(true);
        });
    }

    public void showAroundDeck(JButton button, int playerIndex) {
        button.addActionListener(actionEvent -> {
            mineGameBoard.offEnabledMenu();
            MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                    GraphicsDefault.GameBoard.aroundDeckPanel, baseGameThread.getUserFrame().getPane(), false, MineGameLayer.message.getLayer());
            ArrayList<Card> showCard = gameController.getGame().getPlayerGames(playerIndex).getAroundCard();
            for (int i = 0; i < showCard.size(); i++) {
                PanelComponentDrawer cardImage1 = new PanelComponentDrawer(FilesPath.graphicsPath.cardsPath + "/" +
                        showCard.get(i).getName() + ".png", GraphicsDefault.GameBoard.aroundDeckCardBound(playerIndex, 2, i),
                        messagePanel, this.mineGameBoard.getMainPanel());
            }
            JButton back = ComponentCreator.getInstance().setButton("Back", messagePanel, "buttons3.png",
                    GraphicsDefault.GameBoard.aroundDeckCardBound(playerIndex, 1, 0), Color.white, 25, 1);
            back.addActionListener(actionEvent2 -> {
                mineGameBoard.onEnabledMenu();
            });
            messagePanel.setVisible(true);
            messagePanel.setEnabled(true);
        });
    }

    public void endTurn(JButton button) {
        button.addActionListener(actionEvent -> {
            /*gameController.endTurn();
            gameController.addGameEvent(GameEventEnum.endTurn, "next_player");
            baseGameThread.drawGameBoard();
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());*/
        });

    /*
    }*/

        /**/


    /*
    }*/

   /* public void minionCardSelect(JButton button, Card card) {
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
*/
    /*public void heroPowerSelect(JButton button) {
        button.addActionListener(actionEvent -> {
           *//* gameController.addGameEvent(GameEventEnum.playHeroPower,
                    gameController.getGame().getPlayerGames(0).getHero().getHeroName(),0,playerController.getPlayer());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());*//*
            //baseGameThread.drawGameBoard();
        });
    }*/

    }

}
