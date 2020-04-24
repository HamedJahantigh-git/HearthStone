package userInterfaces.myComponent;

import userInterfaces.Sounds;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MyCardButton extends JButton {
    private Bounds bounds;
    private Sounds click;
    private String buttonName;
    private String buttonImagePath;
    //private Background background;

    {
        click = new Sounds("crossButton.wav");
    }

    public MyCardButton(JPanel panel, String buttonImagePath, Bounds bounds) {
        this.bounds = bounds;
        this.buttonName = null;
        this.buttonImagePath = buttonImagePath;
        setIcon(new ImageIcon(ComponentCreator.getInstance().setImage(
                bounds.getWidth(), bounds.getHeight(),
                buttonImagePath)));
        //background = new Background(buttonImagePath);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        //this.add(background);
        panel.add(this);
    }

    public MyCardButton(String name, JPanel panel, String buttonImagePath, Bounds bounds) {
        this(panel, buttonImagePath, bounds);
        this.buttonName = name;
    }

    public String getButtonName() {
        return buttonName;
    }


    public void moveListener() {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                click.playOne();
                setBorder(BorderFactory.createRaisedSoftBevelBorder());

            }

            public void mouseExited(MouseEvent me) {
                setBorder(null);

            }
        });
    }


    /*class Background extends JPanel {
        private String imagePath;

        public Background(String imagePath) {
            this.imagePath = imagePath;
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
    }*/
}
/*
public class MyCardButton extends MyJPanel {
    private Bounds bounds;
    private Sounds click;
    private JButton button;

    {
        click = new Sounds("crossButton.wav");
    }

    public JButton getButton() {
        return button;
    }

    public MyCardButton(JPanel panel, String buttonImagePath, Bounds bounds) {
        super(buttonImagePath, bounds, null, true, 0);
        button = new JButton();
        this.bounds = bounds;
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        panel.add(button);
        panel.add(this);
    }

    public void moveListener() {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                click.playOne();
                setBorder(BorderFactory.createRaisedSoftBevelBorder());
                r();
            }

            public void mouseExited(MouseEvent me) {
                setBorder(null);
                r();
            }
        });

    }

    public void r(){
        this.paint(this.getGraphics());
    }


}
 */

