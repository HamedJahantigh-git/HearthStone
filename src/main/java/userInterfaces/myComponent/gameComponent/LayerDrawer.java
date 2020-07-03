package userInterfaces.myComponent.gameComponent;

import defaults.GraphicsDefault;
import userInterfaces.myComponent.Bounds;

import javax.swing.*;
import java.awt.*;

public class LayerDrawer extends GameComponent {
    protected JLayeredPane pane;

    public LayerDrawer(String buttonImagePath, Bounds bounds, JPanel backgroundPanel, JLayeredPane pane, int layer) {
        super(buttonImagePath, bounds, backgroundPanel);
        this.pane = pane;
        pane.add(this, Integer.valueOf(layer));
    }


    @Override
    public void reShow() {
        backgroundPanel.validate();
        backgroundPanel.repaint();
    }
}
