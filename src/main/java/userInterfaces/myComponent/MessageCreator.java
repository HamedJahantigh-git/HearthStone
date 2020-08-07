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


    public void creatDeckMessage(JLayeredPane pane, ArrayList<Hero> heroes, CollectionMenu collectionMenu) {
        MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.message.messagePanel, pane, false, 19);
        label = ComponentCreator.getInstance().setText(
                "<html></center><br><center>\"Create New Deck\"</center><br><center></center><br><center></center>" +
                        "<br><center></center><br><center></center></html><br><center></center></html></html>",
                messagePanel,  new MyFont(FontEnum.MESSAGE.getName(),30)
                , Color.black, new Bounds(0, 0, messagePanel.getWidth(), messagePanel.getHeight()));
        ComponentCreator.getInstance().setText("Enter Your Deck Name:", messagePanel,
                new MyFont(FontEnum.MESSAGE.getName(),20), Color.black,
                GraphicsDefault.message.component(6));
        JTextField deckName = ComponentCreator.getInstance().setImportBox(messagePanel, 30, new Color(0, 136, 204),
                GraphicsDefault.message.component(7));
        ComponentCreator.getInstance().setText("Select Hero:", messagePanel,
                new MyFont(FontEnum.MESSAGE.getName(),20), Color.black,
                GraphicsDefault.message.component(4));
        String[] heroesName = new String[heroes.size()];
        for (int i = 0; i < heroes.size(); i++) {
            heroesName[i] = heroes.get(i).getHeroName();
        }
        JComboBox<String> heroDeck = ComponentCreator.getInstance().setStrComboBox(messagePanel,
                heroesName, 3, GraphicsDefault.message.component(5),18);
        JButton createButton = ComponentCreator.getInstance().setButton("Create", messagePanel, "buttons2.png",
                GraphicsDefault.message.component(2), Color.white, 30, 0);
        JButton cancelButton = ComponentCreator.getInstance().setButton("Cancel", messagePanel, "buttons2.png",
                GraphicsDefault.message.component(3), Color.white, 30, 0);
        createButton.addActionListener(actionEvent2 -> {
            collectionMenu.getAction().getPlayerController().getCollectionController().createNewDeck(
                    deckName.getText(),heroDeck.getSelectedItem().toString());
           PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[4],
                    LogsEnum.valueOf("collection").getEvent_description()[3]+"_"+deckName.getText()+":"+heroDeck.getSelectedItem().toString(),
                   collectionMenu.getAction().getPlayerController().getPlayer());
            FileManagement.getInstance().getPlayerFile().savePlayerToFile(collectionMenu.getAction().getPlayerController().getPlayer());
            collectionMenu.onEnabledMenu();
            collectionMenu.showDeckList();
        });
        cancelButton.addActionListener(actionEvent2 -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[4],
                    LogsEnum.valueOf("collection").getEvent_description()[4], collectionMenu.getAction().getPlayerController().getPlayer());
            collectionMenu.onEnabledMenu();
        });
        messagePanel.setVisible(true);
        messagePanel.setEnabled(true);
    }

    public void editDeckMessage(JLayeredPane pane, ArrayList<Hero> heroes, CollectionMenu collectionMenu) {
        MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.message.messagePanel, pane, false, 19);
        label = ComponentCreator.getInstance().setText(
                "<html></center><br><center>\"Edit Deck Characteristics\"</center><br><center></center><br><center></center>" +
                        "<br><center></center><br><center></center></html><br><center></center></html></html>",
                messagePanel,  new MyFont(FontEnum.MESSAGE.getName(),30)
                , Color.black, new Bounds(0, 0, messagePanel.getWidth(), messagePanel.getHeight()));
        ComponentCreator.getInstance().setText("Enter New Deck Name:", messagePanel,
                new MyFont(FontEnum.MESSAGE.getName(),20), Color.black,
                GraphicsDefault.message.component(6));
        JTextField deckName = ComponentCreator.getInstance().setImportBox(messagePanel, 30, new Color(0, 136, 204),
                GraphicsDefault.message.component(7));
        ComponentCreator.getInstance().setText("Select New Hero:", messagePanel,
                new MyFont(FontEnum.MESSAGE.getName(),20), Color.black,
                GraphicsDefault.message.component(4));
        String[] heroesName = new String[heroes.size()];
        for (int i = 0; i < heroes.size(); i++) {
            heroesName[i] = heroes.get(i).getHeroName();
        }
        JComboBox<String> heroDeck = ComponentCreator.getInstance().setStrComboBox(messagePanel,
                heroesName, 3, GraphicsDefault.message.component(5),18);
        JButton editButton = ComponentCreator.getInstance().setButton("Edit", messagePanel, "buttons2.png",
                GraphicsDefault.message.component(2), Color.white, 30, 0);
        JButton cancelButton = ComponentCreator.getInstance().setButton("Cancel", messagePanel, "buttons2.png",
                GraphicsDefault.message.component(3), Color.white, 30, 0);
        editButton.addActionListener(actionEvent2 -> {
            try {
                collectionMenu.getAction().getPlayerController().getCollectionController().editDeckCharacteristics(
                        collectionMenu.getSelectedDeck(),deckName.getText(),heroDeck.getSelectedItem().toString());
                PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[12],
                        LogsEnum.valueOf("collection").getEvent_description()[10]+deckName.getText()+"/"+heroDeck.getSelectedItem().toString(),
                        collectionMenu.getAction().getPlayerController().getPlayer());
                FileManagement.getInstance().getPlayerFile().savePlayerToFile(collectionMenu.getAction().getPlayerController().getPlayer());
                collectionMenu.onEnabledMenu();
                collectionMenu.showSelectedDeck(collectionMenu.getSelectedDeck(),collectionMenu.getCardInDeckIndex());
            } catch (Exception e) {
                collectionMenu.onEnabledMenu();
                collectionMenu.offEnabledMenu();
                JButton okbutton = MessageCreator.getInstance().errorMessage(
                        MessageEnum.valueOf("editDeckMistake").getText(), collectionMenu.getUserFrame().getPane(),  GraphicsDefault.message.messagePanel,19, 30);
                okbutton.addActionListener(actionEvent3 -> {
                    PlayerLogs.addToLogBody(LogsEnum.valueOf("error").getEvent()[0],
                            LogsEnum.valueOf("error").getEvent_description()[4],
                            collectionMenu.getAction().getPlayerController().getPlayer());
                    collectionMenu.onEnabledMenu();
                });
            }

        });
        cancelButton.addActionListener(actionEvent2 -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[12],
                    LogsEnum.valueOf("collection").getEvent_description()[4], collectionMenu.getAction().getPlayerController().getPlayer());
            collectionMenu.onEnabledMenu();
        });
        messagePanel.setVisible(true);
        messagePanel.setEnabled(true);
    }



}
