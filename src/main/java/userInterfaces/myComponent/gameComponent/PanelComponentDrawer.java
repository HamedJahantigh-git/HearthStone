package userInterfaces.myComponent.gameComponent;

import defaults.GraphicsDefault;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelComponentDrawer extends GameComponent {

    private JPanel basePanel;

    public PanelComponentDrawer(String buttonImagePath, Bounds bounds, JPanel basePanel, JPanel backgroundPanel) {
        super(buttonImagePath, bounds, backgroundPanel);
        this.basePanel = basePanel;
        this.basePanel.add(this);
    }

    public JButton getButton() {
        return button;
    }

    @Override
    public void reShow() {

        basePanel.validate();
        basePanel.repaint();
    }
}
