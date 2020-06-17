package controller.gameController;


public class GameSwitcherThread extends Thread {

    private Object lock;

    public GameSwitcherThread() {
        lock = new Object();
        start();
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

    @Override
    public void run() {

    }
}
