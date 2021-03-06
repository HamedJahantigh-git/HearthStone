package userInterfaces.myComponent.gameComponent;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.FontEnum;
import model.hero.Hero;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeroHeroPowerDrawer extends LayerDrawer {

    private Bounds cardBound;
    private JLabel[] text;
    private int fontSize;
    private String fontName = FontEnum.CARD.getName();

    private Hero hero;

    public HeroHeroPowerDrawer(Hero hero, String background, Bounds bounds,
                               JPanel backgroundPanel, int fontSize, JLayeredPane pane, int layer) {
        super(background, bounds, backgroundPanel, pane, layer);
        this.fontSize = fontSize;
        cardBound = new Bounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.hero = hero;
    }

    public void setHeroHealth() {
        if (text!= null)
            this.remove(text[0]);
        text = new JLabel[1];
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(hero.getHealth()), this,
                new MyFont(fontName,fontSize), Color.white,
                GraphicsDefault.GameBoard.heroBounds(-1, 2));
    }

    public void setHeroPowerMana() {
        text = new JLabel[1];
        text[0] = ComponentCreator.getInstance().setText(String.valueOf(hero.getHeroPowerMana()), this,
                new MyFont(fontName,fontSize), Color.white,
                GraphicsDefault.GameBoard.heroPowerBounds(-1, 2));
    }
}
