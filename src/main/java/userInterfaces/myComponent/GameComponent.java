package userInterfaces.myComponent;

import defaults.GraphicsDefault;
import model.card.Card;
import model.card.Minion;
import userInterfaces.Sounds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameComponent extends MyJPanel {
    private Bounds bounds;
    private Sounds click;
    private JButton button;
    private JLabel[] text;
    private Bounds[] textBounds;
    private boolean haveText;

    {
        click = new Sounds("crossButton.wav");
    }

    public JButton getButton() {
        return button;
    }

    public GameComponent(JLayeredPane pane, int layer, String buttonImagePath, Bounds bounds) {
        super(buttonImagePath, bounds, null, true, 0);
        button = new JButton();
        this.bounds = bounds;
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        pane.add(button, Integer.valueOf(layer));
        pane.add(this, Integer.valueOf(layer));
    }



    public void setMinionText(Card card) {
        text = new JLabel[3];
        textBounds = new Bounds[3];
        textBounds[0] = new Bounds(0,0,bounds.getWidth()/5,bounds.getHeight()/6);
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(card.getMana()), this, "Belwe Bd BT Bold", 20, Color.white,
               textBounds[0]);
    }

    public void setSpellText(Card card) {
        text = new JLabel[3];
        textBounds = new Bounds[3];
        textBounds[0] = new Bounds(0,0,bounds.getWidth()/5,bounds.getHeight()/6);
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(card.getMana()), this, "Belwe Bd BT Bold", 20, Color.white,
                textBounds[0]);
    }

    public void setWeaponText(Card card) {
        text = new JLabel[3];
        textBounds = new Bounds[3];
        textBounds[0] = new Bounds(0,0,bounds.getWidth()/5,bounds.getHeight()/6);
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(card.getMana()), this, "Belwe Bd BT Bold", 20, Color.white,
                textBounds[0]);
    }

    public void setManaText(int currentMana, int randMana) {
        text = new JLabel[2];
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(currentMana), this,
                "Belwe Bd BT Bold", 42, Color.white,
                new Bounds(0,0,GraphicsDefault.GameBoard.manaBar.getWidth()/2,GraphicsDefault.GameBoard.manaBar.getHeight()));
        text[1] = ComponentCreator.getInstance().setText(Integer.toString(randMana), this,
                "Belwe Bd BT Bold", 42, Color.white,
                new Bounds(GraphicsDefault.GameBoard.manaBar.getWidth()/2,0,GraphicsDefault.GameBoard.manaBar.getWidth()/2,GraphicsDefault.GameBoard.manaBar.getHeight()));

    }

    public void moveListener(boolean resize) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                click.playOne();
                setBorder(BorderFactory.createRaisedSoftBevelBorder());
                if (resize) {
                    setSize(bounds.getWidth() * 2, bounds.getHeight() * 2);
                    setLocation(bounds.getX(), bounds.getY() - 2 * bounds.getWidth());
                    text[0].setBounds(textBounds[0].getX()*2,textBounds[0].getY()*2,
                    textBounds[0].getWidth()*2,textBounds[0].getHeight()*2);
                    text[0].setFont(new Font("Belwe Bd BT Bold", Font.ITALIC , 40));

                }
                reShow();
            }

            public void mouseExited(MouseEvent me) {
                setBorder(null);
                if (resize) {
                    setSize(bounds.getWidth(), bounds.getHeight());
                    setLocation(bounds.getX(), bounds.getY());
                    text[0].setBounds(textBounds[0].getX(),textBounds[0].getY(),
                            textBounds[0].getWidth(),textBounds[0].getHeight());
                    text[0].setFont(new Font("Belwe Bd BT Bold", Font.ITALIC , 20));
                }
                reShow();
            }
        });
    }

    public void addClick() {
        button.addActionListener(actionEvent -> {
            button.setEnabled(false);
            button.setVisible(false);
            /*setVisible(false);
            setEnabled(false);*/
        });
    }

    public void reShow() {
        this.paintComponent(this.getGraphics());
    }

}