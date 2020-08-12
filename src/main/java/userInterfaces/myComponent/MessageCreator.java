package userInterfaces.myComponent;

import controller.FileManagement;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.FontEnum;
import enums.LogsEnum;
import enums.MessageEnum;
import logs.PlayerLogs;
import model.hero.Hero;
import userInterfaces.userMenu.CollectionMenu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageCreator {
    private static MessageCreator instance = new MessageCreator();
    private JLabel label;

    private MessageCreator() {
    }

    public static MessageCreator getInstance() {
        return instance;
    }

    public void accountMessage(MessageEnum message, JPanel jPanel, JPanel[] offPanels, int fontSize, JButton[] jButtons) {
        label = ComponentCreator.getInstance().setText(message.getText(), jPanel,   new MyFont(FontEnum.MESSAGE.getName(),fontSize)
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
        label = ComponentCreator.getInstance().setText(text, messagePanel,  new MyFont(FontEnum.MESSAGE.getName(),fontSize)
                , Color.black, new Bounds(0, 0, messagePanel.getWidth(), messagePanel.getHeight()));
        JButton yesButton = ComponentCreator.getInstance().setButton("Yes", messagePanel, "buttons2.png",
                GraphicsDefault.message.component(2), Color.white, 30, 0);
        JButton noButton = ComponentCreator.getInstance().setButton("No", messagePanel, "buttons2.png",
                GraphicsDefault.message.component(3), Color.white, 30, 0);
        messagePanel.setVisible(true);
        messagePanel.setEnabled(true);
        return new JButton[]{yesButton, noButton};
    }

    public JButton errorMessage(String text, JLayeredPane pane,Bounds bounds ,int layer, int fontSize) {
        MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
               bounds, pane, false, layer);
        label = ComponentCreator.getInstance().setText(text, messagePanel,  new MyFont(FontEnum.MESSAGE.getName(),fontSize)
                , Color.black, new Bounds(0, 0, messagePanel.getWidth(), messagePanel.getHeight()));
        JButton okButton = ComponentCreator.getInstance().setButton("OK", messagePanel, "buttons2.png",
                GraphicsDefault.message.component(1), Color.white, 30, 0);
        messagePanel.setVisible(true);
        messagePanel.setEnabled(true);
        return okButton;
    }

}
