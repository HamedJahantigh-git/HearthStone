package userInterfaces.userMenu.play;

import controller.gameController.GameController;
import controller.gameController.GameSwitcherThread;

public class PlayerGraphicThread extends Thread {

    private Object lock;

    private GameController gameController;
    private GameSwitcherThread gameSwitcherThread;
    private BaseGameThread baseGameThread;

    public PlayerGraphicThread(){
        lock = new Object();
        start();
    }

    public void setGraphicThread(GameController gameController, BaseGameThread baseGameThread) {

        this.gameController = gameController;
        this.gameSwitcherThread = gameController.getGameSwitcherThread();
        this.baseGameThread = baseGameThread;

    }

    public void doWait() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void doNotify() {
        synchronized (lock) {
            try {
               lock.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    void handleInfoPassive() {
        for (int i = 0; i < 2; i++) {
            doWait();
            baseGameThread.drawInfoPassiveMessage();
        }
    }

    void handleDrawGameBoard() {

    }

    @Override
    public void run() {
        System.out.println("run");
    }
}
