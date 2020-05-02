package userInterfaces.myComponent;

import defaults.GraphicsDefault;
import model.card.Card;
import userInterfaces.Sounds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameComponent extends MyJPanel {
    private JLayeredPane pane;
    private int layer;
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
        this.bounds = bounds;
        this.pane = pane;
        this.layer = layer;
        pane.add(this, Integer.valueOf(layer));
    }

    public void setEventText(String[] event){
        this.removeAll();
        String result1 = "<html><center>Game Event</center></html>";
        String result2 = "<html>";
        for (int i = 0; i <event.length ; i++) {
            result2 = result2.concat(event[i]+"<br>");
        }
        result2 = result2.concat("</html>");
        ComponentCreator.getInstance().setText(result1, this,
                "Belwe Bd BT Bold", 25, Color.black,
                new Bounds(0, GraphicsDefault.GameBoard.eventBounds.getHeight()/10, GraphicsDefault.GameBoard.eventBounds.getWidth(), GraphicsDefault.GameBoard.eventBounds.getHeight()/10));
        JLabel text=ComponentCreator.getInstance().setText(result2, this,
                "Belwe Bd BT Bold", 15, Color.white,
                new Bounds(GraphicsDefault.GameBoard.eventBounds.getWidth()/10, GraphicsDefault.GameBoard.eventBounds.getHeight()/9, GraphicsDefault.GameBoard.eventBounds.getWidth()*8/10, GraphicsDefault.GameBoard.eventBounds.getHeight()*4/5));
        text.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public void setMinionText(Card card) {
        text = new JLabel[3];
        textBounds = new Bounds[3];
        textBounds[0] = new Bounds(0, 0, bounds.getWidth() / 5, bounds.getHeight() / 6);
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(card.getMana()), this, "Belwe Bd BT Bold", 20, Color.white,
                textBounds[0]);
    }

    public void setSpellText(Card card) {
        text = new JLabel[3];
        textBounds = new Bounds[3];
        textBounds[0] = new Bounds(0, 0, bounds.getWidth() / 5, bounds.getHeight() / 6);
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(card.getMana()), this, "Belwe Bd BT Bold", 20, Color.white,
                textBounds[0]);
    }

    public void setWeaponText(Card card) {
        text = new JLabel[3];
        textBounds = new Bounds[3];
        textBounds[0] = new Bounds(0, 0, bounds.getWidth() / 5, bounds.getHeight() / 6);
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(card.getMana()), this, "Belwe Bd BT Bold", 20, Color.white,
                textBounds[0]);
    }

    public void setManaText(int currentMana, int randMana) {
        text = new JLabel[2];
        text[0] = ComponentCreator.getInstance().setText(Integer.toString(currentMana), this,
                "Belwe Bd BT Bold", 42, Color.white,
                new Bounds(0, 0, GraphicsDefault.GameBoard.manaBar.getWidth() / 2, GraphicsDefault.GameBoard.manaBar.getHeight()));
        text[1] = ComponentCreator.getInstance().setText(Integer.toString(randMana), this,
                "Belwe Bd BT Bold", 42, Color.white,
                new Bounds(GraphicsDefault.GameBoard.manaBar.getWidth() / 2, 0, GraphicsDefault.GameBoard.manaBar.getWidth() / 2, GraphicsDefault.GameBoard.manaBar.getHeight()));

    }

    public void moveListener(boolean resize) {
        button = new JButton();
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        pane.add(button, Integer.valueOf(layer));
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                click.playOne();
                setBorder(BorderFactory.createRaisedSoftBevelBorder());
                if (resize) {
                    setSize(bounds.getWidth() * 2, bounds.getHeight() * 2);
                    setLocation(bounds.getX(), bounds.getY() - 2 * bounds.getWidth());
                    text[0].setBounds(textBounds[0].getX() * 2, textBounds[0].getY() * 2,
                            textBounds[0].getWidth() * 2, textBounds[0].getHeight() * 2);
                    text[0].setFont(new Font("Belwe Bd BT Bold", Font.ITALIC, 40));

                }
                reShow();
            }

            public void mouseExited(MouseEvent me) {
                setBorder(null);
                if (resize) {
                    setSize(bounds.getWidth(), bounds.getHeight());
                    setLocation(bounds.getX(), bounds.getY());
                    text[0].setBounds(textBounds[0].getX(), textBounds[0].getY(),
                            textBounds[0].getWidth(), textBounds[0].getHeight());
                    text[0].setFont(new Font("Belwe Bd BT Bold", Font.ITALIC, 20));
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