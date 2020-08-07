package userInterfaces.userMenu.play;

import controller.game.GameController;
import enums.FontEnum;
import model.MyThread;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import defaults.ModelDefault;
import enums.InfoPassiveEnum;
import enums.MineGameLayer;
import model.Player;
import userInterfaces.graphicsActions.gameAction.GameAction;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyFont;
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.myComponent.gameComponent.PanelComponentDrawer;
import userInterfaces.userMenu.UserFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerGraphicThread extends MyThread {

    private UserFrame userFrame;
    private JPanel mainPanel;
    private GameAction action;

    private MineGameBoard mineGameBoard;
    private GameController gameController;

    public PlayerGraphicThread(UserFrame userFrame, MineGameBoard mineGameBoard,
                               GameAction action, GameController gameController) {
        super();
        this.userFrame = userFrame;
        this.mineGameBoard = mineGameBoard;
        this.gameController = gameController;
        this.action = action;
        this.action.setPlayerGraphicThread(this);
        mainPanel = mineGameBoard.getMainPanel();
    }

    @Override
    public void run() {
        handleInfoPassive();
        handleChangeStartCard();
        mineGameBoard.getBaseGameThread().startGame();
    }

    private void handleInfoPassive() {
        for (int i = 0; i < 2; i++) {
            doWait();
            drawInfoPassiveMessage();
        }
    }

    private void handleChangeStartCard() {
        for (int i = 0; i < 2; i++) {
            doWait();
            gameController.moveStartPlayerCardToHand();
            drawChangeStartCard();
        }
        doWait();
    }

    private void drawInfoPassiveMessage() {
        mineGameBoard.offEnabledMenu();
        MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.GameBoard.infoPassivePanelBounds, userFrame.getPane(), false, MineGameLayer.message.getLayer());
        ComponentCreator.getInstance().setText("Info Passive P#" +
                        (gameController.getGame().getPlayerIndex() + 1),
                messagePanel, new MyFont(FontEnum.LABEl.getName(),40), Color.black,
                GraphicsDefault.GameBoard.infoPassiveBounds(2, 1));
        ArrayList<InfoPassiveEnum> randomInfo = gameController.getInfoPassiveController().creatRandomInfoPassive(3);
        for (int i = 0; i < randomInfo.size(); i++) {
            JButton info = ComponentCreator.getInstance().setButton(randomInfo.get(i).getTitle(), messagePanel,
                    "Info Passive Button.png",
                    GraphicsDefault.GameBoard.infoPassiveBounds(1, i + 1), Color.white, 35, 1);
            action.infoButtonAction(info, randomInfo.get(i));
        }
        messagePanel.setVisible(true);
        messagePanel.setEnabled(true);
    }

    public void drawChangeStartCard() {
        mineGameBoard.offEnabledMenu();
        mineGameBoard.cleanGameBoard();
        MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.GameBoard.changeStartCardBound(0, 0), userFrame.getPane(), false, MineGameLayer.message.getLayer());
        ComponentCreator.getInstance().setText("Change Start Cards P#" +
                        (gameController.getGame().getPlayerIndex() + 1),
                messagePanel, new MyFont(FontEnum.LABEl.getName(),40), Color.black,
                GraphicsDefault.GameBoard.changeStartCardBound(1, 0));
        JButton finishButton = ComponentCreator.getInstance().setButton("Finish", messagePanel, "buttons1.png",
                GraphicsDefault.GameBoard.changeStartCardBound(2, 0), Color.white, 30, 0);
        action.finishChangeStartCard(finishButton);
        messagePanel.setVisible(true);
        messagePanel.setEnabled(true);
        for (int i = 0; i < ModelDefault.gameDefaults.MAX_START_PLAYER_CARDS; i++) {
            Player.PlayerGame temp = gameController.getGame().getPlayerGames(
                    gameController.getGame().getPlayerIndex());
            PanelComponentDrawer cardImage = new PanelComponentDrawer(FilesPath.graphicsPath.cardsPath + "/" +
                            temp.getHandCard().get(i).getName() + ".png",
                    GraphicsDefault.GameBoard.changeStartCardBound(3, i), messagePanel, mainPanel);
            cardImage.selectable();
            action.selectStartCardToChange(cardImage.getButton(), i);
        }
    }
}
