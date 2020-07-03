package model;

public class MyThread extends Thread {
    private Object lock;

    public MyThread() {
        lock = new Object();
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

    public static void delay(int milliSecond){
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void endThread(){
    }

    @Override
    public void run() {

    }
}
