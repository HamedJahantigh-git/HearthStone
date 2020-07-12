
package userInterfaces.myComponent.gameComponent;

import defaults.GraphicsDefault;
import userInterfaces.Sounds;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

abstract public class GameComponent extends MyJPanel {
    protected Bounds bounds;
    protected Sounds click;
    protected JPanel backgroundPanel;
    protected JButton button;

    {
        click = new Sounds("crossButton.wav");
    }

    public GameComponent(String buttonImagePath, Bounds bounds, JPanel backgroundPanel) {
        super(buttonImagePath, bounds, null, true, 0);
        this.bounds = bounds;
        this.backgroundPanel = backgroundPanel;
    }

    public void buildButton() {
        button = new JButton();
        button.setContentAreaFilled(false);
        button.setBounds(0, 0, this.getWidth(), this.getHeight());
        button.setBorder(BorderFactory.createEmptyBorder());
        // pane.add(button, Integer.valueOf(layer));
        this.add(button);
    }

    protected JLabel setText(String text, Bounds bounds, int font, Color color) {
        return ComponentCreator.getInstance().setText(text, this, "Belwe Bd BT Bold",
                font, color, bounds);
    }

    public void selectable(){
        buildButton();
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBorder(BorderFactory.createRaisedSoftBevelBorder());
                click.playOne();
                reShow();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBorder(null);
                reShow();
            }

        });
    };

    protected void setButtonBounds (Bounds newBound){
        setSize(newBound.getWidth(), newBound.getHeight());
        setLocation(newBound.getX(), newBound.getY());
    }

    public JButton getButton() {
        return button;
    }

    abstract public void reShow();

}
