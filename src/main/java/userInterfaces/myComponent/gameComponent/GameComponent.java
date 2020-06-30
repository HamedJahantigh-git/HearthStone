
package userInterfaces.myComponent.gameComponent;

import defaults.GraphicsDefault;
import userInterfaces.Sounds;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyJPanel;

import javax.swing.*;
import java.awt.*;

abstract public class GameComponent extends MyJPanel {
    protected Bounds bounds;
    protected Sounds click;
    protected JPanel basePanel;
    protected JButton button;

    {
        click = new Sounds("crossButton.wav");
    }

    public GameComponent(String buttonImagePath, Bounds bounds, JPanel basePanel) {
        super(buttonImagePath, bounds, null, true, 0);
        this.bounds = bounds;
        this.basePanel = basePanel;
    }

    protected void buildButton() {
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

    abstract public void selectable();

    protected void setButtonBounds (Bounds newBound){
        setSize(newBound.getWidth(), newBound.getHeight());
        setLocation(newBound.getX(), newBound.getY());
    }

    public void reShow() {
        basePanel.validate();
        basePanel.repaint();
    }

}
