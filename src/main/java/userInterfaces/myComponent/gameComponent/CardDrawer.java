package userInterfaces.myComponent.gameComponent;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.CardType;
import enums.FontEnum;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;
import model.card.Weapon;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardDrawer extends LayerDrawer {

    private Bounds cardBound;
    private JLabel[] text;
    private MyFont font;
    private Card card;

    public CardDrawer(Card card, Bounds bounds,
                      JPanel backgroundPanel, int fontSize, JLayeredPane pane, int layer) {
        super(FilesPath.graphicsPath.gameCardsPath + "/" + card.getName() + ".png", bounds, backgroundPanel, pane, layer);
        this.card = card;
        this.font = new MyFont(FontEnum.CARD.getName(),fontSize);
        cardBound = new Bounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        handleCardText();
    }

    public void rightClickMagnifier(boolean UpperCard) {
        selectable();
        Bounds boldBound;
        int newX;
        int newY;
        if (UpperCard) {
            boldBound = new Bounds(bounds.getX(), bounds.getY() + bounds.getWidth(),
                    bounds.getWidth() * 2, bounds.getHeight() * 2);
            newX = boldBound.getX() + boldBound.getWidth() / 2;
            newY = boldBound.getY();
        } else {
            boldBound = new Bounds(bounds.getX(), bounds.getY() - 2 * bounds.getWidth(),
                    bounds.getWidth() * 2, bounds.getHeight() * 2);
            newX = boldBound.getX() + boldBound.getWidth() / 2;
            newY = boldBound.getY() + boldBound.getWidth();
        }
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    setButtonBounds(boldBound);
                    for (int i = 0; i < text.length; i++) {
                        text[i].setFont(new MyFont(font.getFontName(),  font.getFontSize() * 2).getFont());
                        setTextBounds(text[i], GraphicsDefault.GameBoard.cardTextBounds(i + 1, boldBound));
                    }
                    //moveMouseCursor(newX, newY);
                    reShow();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setButtonBounds(bounds);
                for (int i = 0; i < text.length; i++) {
                    text[i].setFont(font.getFont());
                    setTextBounds(text[i], GraphicsDefault.GameBoard.cardTextBounds(i + 1, bounds));
                }
                reShow();
            }
        });
    }

    private void setTextBounds(JLabel text, Bounds newBound) {
        text.setSize(newBound.getWidth(), newBound.getHeight());
        text.setLocation(newBound.getX(), newBound.getY());
    }

    private void setMinionText(Minion card) {
        text = new JLabel[3];
        setCardManaText(Integer.toString(card.getMana()));
        setBottomLeftCardText(Integer.toString(card.getAttack()));
        setBottomRightCardText(Integer.toString(card.getHealth()));
    }

    private void setSpellText(Spell card) {
        text = new JLabel[1];
        setCardManaText(Integer.toString(card.getMana()));
    }

    private void setWeaponText(Weapon card) {
        text = new JLabel[3];
        setCardManaText(Integer.toString(card.getMana()));
        setBottomLeftCardText(Integer.toString(card.getAttack()));
        setBottomRightCardText(Integer.toString(card.getDurability()));
    }

    private void setCardManaText(String message) {
        if (text[0] != null)
            remove(text[0]);
        text[0] = ComponentCreator.getInstance().setText(message, this,
                font, Color.white, GraphicsDefault.GameBoard.cardTextBounds(1, cardBound));
    }

    private void setBottomLeftCardText(String message) {
        if (text[1] != null)
            remove(text[1]);
        text[1] = ComponentCreator.getInstance().setText(message, this,
                font, Color.white, GraphicsDefault.GameBoard.cardTextBounds(2, cardBound));
    }

    private void setBottomRightCardText(String message) {
        if (text[2] != null)
            remove(text[2]);
        text[2] = ComponentCreator.getInstance().setText(message, this,
                font, Color.white, GraphicsDefault.GameBoard.cardTextBounds(3, cardBound));
    }

    public void handleCardText() {
        String cardType = card.getType();
        if (cardType.equals(CardType.Minion.name()))
            setMinionText((Minion) (card));
        if (cardType.equals(CardType.Spell.name()))
            setSpellText((Spell) (card));
        if (cardType.equals(CardType.Weapon.name()))
            setWeaponText((Weapon) (card));
    }

}
