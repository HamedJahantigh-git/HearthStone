package userInterfaces;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyJPanel extends JPanel {
    private String imagePath;

    public MyJPanel(String imagePath, Bounds bounds, JLayeredPane pane, boolean visible, int layer) {
        this.imagePath = imagePath;
        setLayout(null);
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        pane.add(this, Integer.valueOf(layer));
        setVisible(visible);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image image = null;
        try {
            image = ImageIO.read(new File(this.imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        setOpaque(false);
        super.paintComponent(g);
        setOpaque(true);
    }

}
