package userInterfaces;

import enums.MessageEnum;

import javax.swing.*;
import java.awt.*;

public class MessageCreator {
    private static MessageCreator instance = new MessageCreator();
    private JLabel label;

    private MessageCreator() {
    }

    public static MessageCreator getInstance() {
        return instance;
    }

    public void panelMessage(MessageEnum message, JPanel jPanel, JPanel[] offPanels , int fontSize, JButton[] jButtons) {
        label = ComponentCreator.getInstance().setText(message.getText(), jPanel, "FORTE", fontSize
                , Color.black, new Bounds(0, 0, jPanel.getWidth(), jPanel.getHeight()));
        if (jButtons != null)
            for (JButton button : jButtons)
                jPanel.add(button);
        jPanel.setVisible(true);
        jPanel.paint(jPanel.getGraphics());
        for(JPanel panel:offPanels){
            for (Component c : panel.getComponents()) c.setEnabled(false);
        }
    }

}
