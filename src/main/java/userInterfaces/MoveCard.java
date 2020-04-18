package userInterfaces;

import userInterfaces.myComponent.Bounds;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveCard implements ActionListener {
    private Bounds beginBound;
    private Bounds nextBound;
    private Timer timer;
    private int step;
    private int stepMilliSecond;

    public MoveCard(Bounds beginBound, int step, int stepMilliSecond) {
        this.beginBound = beginBound;
        nextBound = beginBound;
        this.step = step;
        this.stepMilliSecond = stepMilliSecond;
        timer = new Timer(stepMilliSecond, this); // Timer for run actionPerformed
        timer.start();
    }

    public Timer getTimer() {
        return timer;
    }

    public Bounds getNextBound() {
        return nextBound;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (step > 0) {
            nextBound.setX(nextBound.getX() - 1);
            nextBound.setY(nextBound.getY() - 1);
            step--;
        }
    }
}
