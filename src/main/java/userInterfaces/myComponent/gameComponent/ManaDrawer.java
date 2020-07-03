package userInterfaces.myComponent.gameComponent;

import defaults.GraphicsDefault;
import model.Player;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;

import javax.swing.*;
import java.awt.*;

public class ManaDrawer extends LayerDrawer {
    private Player.PlayerGame playerGame;

    private JLabel[] text;

    public ManaDrawer(String buttonImagePath, Bounds bounds, JPanel backgroundPanel, JLayeredPane pane,
                      int layer, Player.PlayerGame playerGame) {
        super(buttonImagePath, bounds, backgroundPanel, pane, layer);
        this.playerGame = playerGame;
    }

    public void setManaBarText() {
        removeAll();
        text = new JLabel[2];
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(playerGame.getCurrentMana()), this,
                "Belwe Bd BT Bold", 42, Color.white,
                new Bounds(0, 0, GraphicsDefault.GameBoard.manaBar.getWidth() / 2, GraphicsDefault.GameBoard.manaBar.getHeight()));
        text[1] = ComponentCreator.getInstance().setText(Integer.toString(playerGame.getRandMana()), this,
                "Belwe Bd BT Bold", 42, Color.white,
                new Bounds(GraphicsDefault.GameBoard.manaBar.getWidth() / 2, 0, GraphicsDefault.GameBoard.manaBar.getWidth() / 2, GraphicsDefault.GameBoard.manaBar.getHeight()));
    }

    public static void updateAllManaBars (ManaDrawer[] manaDrawers){
        for (ManaDrawer manaDrawer:manaDrawers) {
            manaDrawer.setManaBarText();
        }
    }
}
