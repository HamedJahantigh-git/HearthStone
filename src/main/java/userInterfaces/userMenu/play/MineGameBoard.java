package userInterfaces.userMenu.play;

import controller.gameController.GameController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.MineGameLayer;
import model.Game;
import model.Player;
import userInterfaces.Sounds;
import userInterfaces.graphicsActions.GameAction;
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.userMenu.UserFrame;

import javax.swing.*;
import java.awt.*;

public class MineGameBoard {

    private BaseGameThread baseGameThread;
    private PlayerGraphicThread playerGraphicThread;
    private GameController gameController;

    private UserFrame userFrame;
    private JPanel mainPanel;
    private Sounds mainGameSounds;
    private GameAction action;


    public MineGameBoard(UserFrame userFrame, Player minePlayer, String battleBackground) {
        this.userFrame = userFrame;
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/" + battleBackground + ".jpg",
                GraphicsDefault.GameBoard.mainBounds, userFrame.getPane(), true, MineGameLayer.mainPanel.getLayer());

        mainGameSounds = new Sounds("GameMenu.wav");
        mainGameSounds.playLoop();

        this.gameController = new GameController();
        action = new GameAction(this);
        baseGameThread = new BaseGameThread(userFrame, this, minePlayer,
                action, gameController);
        playerGraphicThread = new PlayerGraphicThread(userFrame, this,
                action, gameController);

        baseGameThread.start();
        delay(500);
        playerGraphicThread.start();
    }

    public BaseGameThread getBaseGameThread() {
        return baseGameThread;
    }

    public PlayerGraphicThread getPlayerGraphicThread() {
        return playerGraphicThread;
    }

    private void delay(int milliSecond){
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Sounds getMainGameSounds() {
        return mainGameSounds;
    }

    public void cleanGameBoard() {
        for (int i = 41; i < 60; i++) {
            for (Component component : userFrame.getPane().getComponentsInLayer(i)) {
                userFrame.getPane().remove(component);
            }
        }
        for (Component component : mainPanel.getComponents()) {
            mainPanel.remove(component);
        }
    }

    public void offEnabledMenu() {
        for (int i = 41; i < 49; i++) {
            for (Component component : userFrame.getPane().getComponentsInLayer(i)) {
                component.setEnabled(false);
                component.setVisible(false);
            }
        }
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(false);
            component.setVisible(false);
        }

    }

    public void onEnabledMenu() {
        for (Component c : userFrame.getPane().getComponentsInLayer(MineGameLayer.message.getLayer())) {
            userFrame.getPane().remove(c);
        }
        for (Component c : userFrame.getPane().getComponentsInLayer(MineGameLayer.messageContent.getLayer())) {
            userFrame.getPane().remove(c);
        }
        for (int i = 41; i < 49; i++) {
            for (Component component : userFrame.getPane().getComponentsInLayer(i)) {
                component.setEnabled(true);
                component.setVisible(true);
            }
        }
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(true);
            component.setVisible(true);
        }
        mainPanel.paint(mainPanel.getGraphics());
    }
}
