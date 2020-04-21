package userInterfaces.myComponent;

import defaults.FilesPath;
import defaults.GraphicsDefault;
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

    public void accountMessage(MessageEnum message, JPanel jPanel, JPanel[] offPanels, int fontSize, JButton[] jButtons) {
        label = ComponentCreator.getInstance().setText(message.getText(), jPanel, "FORTE", fontSize
                , Color.black, new Bounds(0, 0, jPanel.getWidth(), jPanel.getHeight()));
        if (jButtons != null)
            for (JButton button : jButtons)
                jPanel.add(button);
        jPanel.setVisible(true);
        jPanel.paint(jPanel.getGraphics());
        for (JPanel panel : offPanels) {
            for (Component c : panel.getComponents()) c.setEnabled(false);
        }
    }

    public JButton[] yesNoMessage(String text, JLayeredPane pane, int layer, int fontSize) {
        MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.message.messagePanel, pane, false, layer);
        label = ComponentCreator.getInstance().setText(text, messagePanel, "FORTE", fontSize
                , Color.black, new Bounds(0, 0, messagePanel.getWidth(), messagePanel.getHeight()));
        JButton yesButton = ComponentCreator.getInstance().setButton("Yes", messagePanel, "buttons2.png",
                GraphicsDefault.message.button(2), Color.white, 30, 0);
        JButton noButton = ComponentCreator.getInstance().setButton("No", messagePanel, "buttons2.png",
                GraphicsDefault.message.button(3), Color.white, 30, 0);
        messagePanel.setVisible(true);
        messagePanel.setEnabled(true);
        return new JButton[]{yesButton,noButton};
    }

    public JButton errorMessage(String text, JLayeredPane pane, int layer, int fontSize){
        MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.message.messagePanel, pane, false, layer);
        label = ComponentCreator.getInstance().setText(text, messagePanel, "FORTE", fontSize
                , Color.black, new Bounds(0, 0, messagePanel.getWidth(), messagePanel.getHeight()));
        JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                GraphicsDefault.message.button(1), Color.white, 30, 0);
        messagePanel.setVisible(true);
        messagePanel.setEnabled(true);
        return okButton;
    }


}
