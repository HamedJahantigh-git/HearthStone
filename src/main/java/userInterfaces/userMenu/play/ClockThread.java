package userInterfaces.userMenu.play;

import controller.FileManagement;
import controller.game.GameController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import defaults.ModelDefault;
import enums.GameEventEnum;
import enums.GameSoundsEnum;
import enums.MineGameLayer;
import model.Game;
import model.MyThread;
import userInterfaces.Sounds;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MouseManager;
import userInterfaces.myComponent.gameComponent.LayerDrawer;
import userInterfaces.myComponent.gameComponent.PanelComponentDrawer;

import javax.swing.*;
import java.awt.*;

public class ClockThread extends MyThread {
    private JPanel mainPanel;

    private BaseGameThread baseGameThread;
    private GameController gameController;
    private Game game;

    private boolean doAlarm;
    private boolean isPause;
    private long bufferTime;
    private Sounds alarmSound;
    private LayerDrawer clockPanel;


    ClockThread(BaseGameThread baseGameThread) {
        this.baseGameThread = baseGameThread;
        this.mainPanel = baseGameThread.getMainPanel();
        this.gameController = baseGameThread.getGameController();
        this.game = baseGameThread.getGameController().getGame();
        alarmSound = new Sounds(GameSoundsEnum.TimeAlarm.getPath());
        doAlarm = false;
        isPause = false;
        start();
    }

    public void drawClock() {
        clockPanel = new LayerDrawer(FilesPath.graphicsPath.backgroundsPath + "/Clock.png",
                GraphicsDefault.GameBoard.clockBounds, mainPanel,baseGameThread.getUserFrame().getPane(),
                MineGameLayer.clock.getLayer());
        int time;
        while (!game.isFinish()) {
            checkPause();
            checkTurn();
            alarm();
            clockPanel.clearAllComponent();
            time = ModelDefault.gameDefaults.PLAYER_Time - game.getDifferPlayerSecondsTime();
            ComponentCreator.getInstance().setText(Integer.toString(time), clockPanel,
                    "Belwe Bd BT Bold", 40, timerColor(),
                    new Bounds(0, 0, GraphicsDefault.GameBoard.clockBounds.getWidth() * 9 / 10,
                            GraphicsDefault.GameBoard.clockBounds.getHeight() * 10 / 9));
            clockPanel.reShow();
            MyThread.delay(200);
        }
    }

    private Color timerColor() {
        Color color;
        if (game.getDifferPlayerSecondsTime() < ModelDefault.gameDefaults.PLAYER_ALARM_TIME)
            color = Color.black;
        else
            color = Color.red;
        return color;
    }

    private void alarm() {
        if (game.getDifferPlayerSecondsTime() > ModelDefault.gameDefaults.PLAYER_ALARM_TIME && (!doAlarm)) {
            alarmSound.playOne();
            doAlarm = true;
        }
    }

    private void checkTurn() {
        if (game.getDifferPlayerSecondsTime() > ModelDefault.gameDefaults.PLAYER_Time) {
            gameController.addGameEvent(gameController.getGame().getPlayerIndex(), GameEventEnum.endTurn, "time_ended");
            gameController.endTurn();
            MouseManager.getInstance().defaultCursorImage(baseGameThread.getUserFrame().getUserFrame());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            baseGameThread.drawAfterEndTurn();
            doAlarm = false;
        }
    }

    private void checkPause() {
        if (isPause)
            doWait();
    }

    public void pausing() {
        bufferTime = game.getDifferPlayerMilliTime();
        isPause = true;
    }

    public void resuming() {
        game.setStartPlayerTime(System.currentTimeMillis() - bufferTime);
        isPause = false;
        doNotify();
    }

    @Override
    public void run() {
        drawClock();
    }

    public Sounds getAlarmSound() {
        return alarmSound;
    }
}
