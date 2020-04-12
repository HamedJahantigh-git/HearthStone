package userInterfaces;

import javax.swing.*;
import java.awt.*;

public class MyJPanel extends JPanel {
    private Image background;
    private int a;

    public MyJPanel(Image background) {
        this.background = background;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        setOpaque(false);
        super.paintComponent(g);
        setOpaque(true);
    }
}
