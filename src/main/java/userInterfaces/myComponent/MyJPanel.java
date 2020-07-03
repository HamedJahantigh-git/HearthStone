package userInterfaces.myComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyJPanel extends JPanel {
    private String imagePath;

    public MyJPanel(String imagePath, Bounds bounds, JLayeredPane pane, boolean visible, int layer) {
        this.imagePath = imagePath;
        setVisible(visible);
        setLayout(null);
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        if(pane!=null) {
            pane.add(this, Integer.valueOf(layer));
        }

    }

    public void clearAllComponent(){
        for (Component component : this.getComponents()) {
            this.remove(component);
        }
    }

    public void showAllComponent(){
        for (Component component : this.getComponents()) {
            component.setVisible(true);
            component.setEnabled(true);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image image = null;
        if (this.imagePath != null) {
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


}
