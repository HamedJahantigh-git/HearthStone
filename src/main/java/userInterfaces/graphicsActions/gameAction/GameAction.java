package userInterfaces.graphicsActions.gameAction;

import controller.FileManagement;
import controller.game.GameController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.*;
import logs.PlayerLogs;
import model.Player;
import model.card.Card;
import model.hero.Hero;
import userInterfaces.Sounds;
import userInterfaces.graphicsActions.MainMenuAction;
import userInterfaces.myComponent.*;
import userInterfaces.myComponent.gameComponent.CardDrawer;
import userInterfaces.myComponent.gameComponent.HeroHeroPowerDrawer;
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

    private CardAction cardAction;

    private MineGameBoard mineGameBoard;
    private BaseGameThread baseGameThread;
    private PlayerGraphicThread playerGraphicThread;

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
            cardAction = new CardAction(gameController, baseGameThread);
            mineGameBoard.onEnabledMenu();
            playerGraphicThread.doNotify();
        });
    }

    public void infoButtonAction(JButton button, InfoPassiveEnum infoPassiveEnum) {
        button.addActionListener(actionEvent2 -> {
            gameController.addGameEvent(gameController.getGame().getPlayerIndex(),
                    GameEventEnum.selectInfoPassive, infoPassiveEnum.getTitle());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            Player player = gameController.getGame().getPlayers(gameController.getGame().getPlayerIndex());
            gameController.getGame().getPlayerGames(gameController.getGame().getPlayerIndex()).setInfoPassive(infoPassiveEnum,
                    player);
            mineGameBoard.onEnabledMenu();
            gameController.getGame().switchPlayerIndex();
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
            gameController.getGame().switchPlayerIndex();
            playerGraphicThread.doNotify();
        });
    }

    public void menuIconClick(JButton button, UserFrame userFrame) {
        button.addActionListener(actionEvent -> {
            if (!checkSelectedOn()) {
                mineGameBoard.offEnabledMenu();
                baseGameThread.getClockThread().pausing();
                MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                        GraphicsDefault.GameBoard.menuMessage, baseGameThread.getUserFrame().getPane(), false, MineGameLayer.message.getLayer());
                ComponentCreator.getInstance().setText(MessageEnum.gameMenu.getText(),
                        messagePanel, new MyFont(FontEnum.LABEl.getName(),35)
                        , Color.black, new Bounds(0, 0, GraphicsDefault.GameBoard.menuMessage.getWidth(), GraphicsDefault.GameBoard.menuMessage.getHeight()));
                JButton mainMenu = ComponentCreator.getInstance().setButton("Main Menu", messagePanel, "buttons3.png",
                        GraphicsDefault.GameBoard.menuButtons(1), Color.white, 25, 1);
                mainMenu.addActionListener(actionEvent2 -> {
                    MouseManager.getInstance().defaultCursorImage(baseGameThread.getUserFrame().getUserFrame());
                    PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[3],
                            LogsEnum.valueOf("play").getEvent_description()[4],
                            gameController.getGame().getPlayers(gameController.getGame().getPlayerIndex()));
                    gameController.addGameEvent(gameController.getGame().getPlayerIndex(), GameEventEnum.stopGame, "click_main_menu");
                    FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
                    mineGameBoard.endGame();

                });
                JButton exitGameButton = ComponentCreator.getInstance().setButton("Exit Game", messagePanel, "buttons3.png",
                        GraphicsDefault.GameBoard.menuButtons(4), Color.white, 25, 1);
                exitGame(exitGameButton);
                JButton back = ComponentCreator.getInstance().setButton("Resume", messagePanel, "buttons3.png",
                        GraphicsDefault.GameBoard.menuButtons(2), Color.white, 25, 1);
                back.addActionListener(actionEvent2 -> {
                    baseGameThread.getClockThread().resuming();
                    mineGameBoard.onEnabledMenu();
                });
                messagePanel.setVisible(true);
                messagePanel.setEnabled(true);
            } else {
                new Sounds(GameSoundsEnum.notValidTarget.getPath()).playOne();
            }
        });
    }

    public void showAroundDeck(JButton button, int playerIndex) {
        button.addActionListener(actionEvent -> {
            if (!checkSelectedOn()) {
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
            } else {
                new Sounds(GameSoundsEnum.notValidTarget.getPath()).playOne();
            }
        });
    }

    public void endTurn(JButton button) {
        button.addActionListener(actionEvent -> {
            if (!checkSelectedOn()) {
                gameController.addGameEvent(gameController.getGame().getPlayerIndex(), GameEventEnum.endTurn, "next_player");
                MouseManager.getInstance().defaultCursorImage(baseGameThread.getUserFrame().getUserFrame());
                gameController.endTurn();
                baseGameThread.drawAfterEndTurn();
                baseGameThread.rePaintClock();
                FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            } else {
                new Sounds(GameSoundsEnum.notValidTarget.getPath()).playOne();
            }
        });
    }

    public void handCardSelectAction(CardDrawer cardDrawer, Card card, int playerIndex) {
        cardAction.handleHandCardSelectionAction(cardDrawer, card, playerIndex);
    }

    public void groundCardSelectAction(CardDrawer cardDrawer, Card card, int playerIndex) {
        cardAction.handleGroundSelectionAction(cardDrawer, card, playerIndex);
    }

    public void heroPowerSelect(JButton button, Hero hero, int playerIndex) {
        button.addActionListener(actionEvent -> {
            if (checkSelectedOn()) {
                new Sounds(GameSoundsEnum.notValidTarget.getPath()).playOne();
            } else {
                try {
                    gameController.heroPowerSelection(playerIndex);
                    cardAction.actionForStartTargeter();
                    gameController.addGameEvent(gameController.getGame().getPlayerIndex(),
                            GameEventEnum.playHeroPower, hero.getHeroName());
                    FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
                    baseGameThread.drawAfterAttackAndTarget();
                } catch (Exception e) {
                    if (e.getMessage().equals(ExceptionsEnum.illegalTurn.getMessage()))
                        new Sounds(GameSoundsEnum.cantDoThat.getPath()).playOne();
                    if (e.getMessage().equals(ExceptionsEnum.lowMoney.getMessage()))
                        new Sounds(GameSoundsEnum.cantPlay.getPath()).playOne();
                    if (e.getMessage().equals(ExceptionsEnum.doMoreHeroPower.getMessage()))
                        new Sounds(GameSoundsEnum.cantPlay.getPath()).playOne();
                }
            }
        });
    }

    public void heroSelection(HeroHeroPowerDrawer heroPaint, Hero hero, int playerIndex) {
        cardAction.handleHeroAttackedAction(heroPaint, hero, playerIndex);
    }

    public void groundWeaponSelectAction(CardDrawer weaponPaint, Card weapon, int i) {
        cardAction.handleGroundWeaponSelectionAction(weaponPaint, weapon, i);
    }

    public boolean checkSelectedOn() {
        if (gameController.getGame().isAttackSelected() || gameController.getTargetManager().isTargeter())
            return true;
        return false;
    }


}
