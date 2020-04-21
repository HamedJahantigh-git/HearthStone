package userInterfaces.myComponent;

import userInterfaces.Sounds;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyCardButton extends JButton  {
    private Bounds bounds;
    private Sounds click;
    private boolean enable;

    {
        click = new Sounds("crossButton.wav");
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public MyCardButton(JPanel panel, String buttonImagePath, Bounds bounds) {
        this.bounds = bounds;
        enable = true;
        setIcon(new ImageIcon(ComponentCreator.getInstance().setImage(
                bounds.getWidth(), bounds.getHeight(),
                buttonImagePath)));
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        panel.add(this);
    }

    public void moveListener() {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                    click.playOne();
                    //setSize(bounds.getWidth() * 3 / 2, bounds.getHeight() * 3 / 2);
                    setBorder(BorderFactory.createRaisedSoftBevelBorder());
                    // setLocation(bounds.getX(), bounds.getY());

            }

            public void mouseExited(MouseEvent me) {
                    // setSize(bounds.getWidth(), bounds.getHeight());
                    setBorder(null);
                    //setLocation(bounds.getX(), bounds.getY());

            }
        });
        /*button.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent E)
            {
                int X=E.getX()+button.getX();
                int Y=E.getY()+button.getY();
                button.setBounds(X,Y,150,40);
            }
        });*/
    }

    public void choseListener() {

    }


}
