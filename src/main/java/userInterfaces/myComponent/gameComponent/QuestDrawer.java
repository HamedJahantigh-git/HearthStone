package userInterfaces.myComponent.gameComponent;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.FontEnum;
import model.Quest;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyFont;

import javax.swing.*;
import java.awt.*;

public class QuestDrawer extends LayerDrawer {

    private Quest quest;
    private JLabel text;

    private int fontSize;
    private String fontName = FontEnum.CARD.getName();

    public QuestDrawer(Quest quest, Bounds bounds, int fontSize, JPanel backgroundPanel, JLayeredPane pane, int layer) {
        super(FilesPath.graphicsPath.gameCardsPath + "/" + quest.getQuestCard().getName() + ".png", bounds, backgroundPanel, pane, layer);
        text = null;
        this.fontSize = fontSize;
        this.quest = quest;
        setPercent();
    }

    public void setPercent() {
        text = ComponentCreator.getInstance().setText(String.valueOf(quest.getManaDoing()) + " / " +
                        String.valueOf(quest.getManaHaveTo()), this,
                new MyFont(fontName, fontSize), Color.RED,
                GraphicsDefault.GameBoard.questBounds(-1, 1));
    }
}
