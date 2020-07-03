package userInterfaces.myComponent.gameComponent;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import model.hero.Hero;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeroHeroPowerDrawer extends LayerDrawer {

    private Bounds cardBound;
    private JLabel[] text;
    private int fontSize;
    private String fontName = "Belwe Bd BT Bold";

    private Hero hero;

    public HeroHeroPowerDrawer(Hero hero, String background,Bounds bounds,
                               JPanel backgroundPanel, int fontSize, JLayeredPane pane, int layer) {
        super(background, bounds, backgroundPanel, pane,layer);
        this.fontSize = fontSize;
        cardBound = new Bounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.hero = hero;
    }

    public void setHeroHealth() {
        text = new JLabel[1];
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(hero.getHealth()), this,
                fontName, fontSize, Color.white,
                GraphicsDefault.GameBoard.heroBounds(-1, 2));
    }

    public void setHeroPowerMana() {
        //todo hero power cost
        text = new JLabel[1];
        text[0] = ComponentCreator.getInstance().setText("2", this,
                fontName, fontSize, Color.white,
                GraphicsDefault.GameBoard.heroPowerBounds(-1, 2));
    }
}
