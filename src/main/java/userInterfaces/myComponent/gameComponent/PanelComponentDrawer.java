package userInterfaces.myComponent.gameComponent;

import defaults.GraphicsDefault;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelComponentDrawer extends GameComponent {

    private JPanel backgroundPanel;
    private JLabel[] text;

    public PanelComponentDrawer(String buttonImagePath, Bounds bounds, JPanel basePanel, JPanel backgroundPanel) {
        super(buttonImagePath, bounds, basePanel);
        this.basePanel.add(this);
        this.backgroundPanel = backgroundPanel;
    }

    public void setEventText(String[] event) {
        this.removeAll();
        String result1 = "<html><center>Game Event</center></html>";
        String result2 = "<html>";
        for (int i = 0; i < event.length; i++) {
            result2 = result2.concat(event[i] + "<br>");
        }
        result2 = result2.concat("</html>");
        setText(result1, GraphicsDefault.GameBoard.eventBounds(2), 25, Color.BLACK);
        JLabel text = setText(result2, GraphicsDefault.GameBoard.eventBounds(3), 15, Color.WHITE);
        text.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public JButton getButton() {
        return button;
    }

    @Override
    public void selectable() {
        buildButton();
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                setBorder(BorderFactory.createRaisedSoftBevelBorder());
                click.playOne();
                reShow();
            }

            public void mouseExited(MouseEvent me) {
                setBorder(null);
                reShow();
            }

        });
    }

    public void setManaBarText(int currentMana, int randMana) {
       text = new JLabel[2];
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(currentMana), this,
                "Belwe Bd BT Bold", 42, Color.white,
                new Bounds(0, 0, GraphicsDefault.GameBoard.manaBar.getWidth() / 2, GraphicsDefault.GameBoard.manaBar.getHeight()));
        text[1] = ComponentCreator.getInstance().setText(Integer.toString(randMana), this,
                "Belwe Bd BT Bold", 42, Color.white,
                new Bounds(GraphicsDefault.GameBoard.manaBar.getWidth() / 2, 0, GraphicsDefault.GameBoard.manaBar.getWidth() / 2, GraphicsDefault.GameBoard.manaBar.getHeight()));
    }

    @Override
    public void reShow() {
        super.reShow();
    }
}
