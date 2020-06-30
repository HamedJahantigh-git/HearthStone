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

public class HeroHeroPowerDrawer extends GameComponent {

    private JPanel backgroundPanel;
    private JLayeredPane pane;
    private Bounds cardBound;
    private JLabel[] text;
    private int fontSize;
    private String fontName = "Belwe Bd BT Bold";

    private Hero hero;

    public HeroHeroPowerDrawer(Hero hero, String background,Bounds bounds, JPanel basePanel,
                               JPanel backgroundPanel, int fontSize, JLayeredPane pane, int layer) {
        super(background, bounds, basePanel);
        this.backgroundPanel = backgroundPanel;
        this.pane = pane;
        this.fontSize = fontSize;
        cardBound = new Bounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        basePanel.add(this);
        this.hero = hero;
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

    public void setHeroHealth() {
        text = new JLabel[1];
        System.out.println(hero.getHealth());
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(hero.getHealth()), this,
                fontName, fontSize, Color.white,
                GraphicsDefault.GameBoard.heroBounds(-1, 2));
    }

    public void setHeroPowerMana() {
        //todo hero power cost
        text = new JLabel[1];
        System.out.println(hero.getHealth());
        text[0] = ComponentCreator.getInstance().setText("2", this,
                fontName, fontSize, Color.white,
                GraphicsDefault.GameBoard.heroPowerBounds(-1, 2));
    }
}
