package userInterfaces.myComponent.gameComponent;

import defaults.GraphicsDefault;
import model.Game;
import userInterfaces.myComponent.Bounds;

import javax.swing.*;
import java.awt.*;

public class EventDrawer extends LayerDrawer {
    private Game game;

    public EventDrawer(String buttonImagePath, Bounds bounds, JPanel backgroundPanel, JLayeredPane pane,
                       int layer, Game game) {
        super(buttonImagePath, bounds, backgroundPanel, pane, layer);
        this.game = game;
    }

    public void setEventText() {
        String[] event = game.getEvent();
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
}
