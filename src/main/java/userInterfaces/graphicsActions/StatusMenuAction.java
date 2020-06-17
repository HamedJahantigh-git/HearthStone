package userInterfaces.graphicsActions;

import controller.PlayerController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.LogsEnum;
import enums.StatusLayer;
import logs.PlayerLogs;
import model.Deck;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.userMenu.StatusMenu;

import javax.swing.*;
import java.awt.*;

public class StatusMenuAction extends MainMenuAction {

    private StatusMenu statusMenu;
    public StatusMenuAction(PlayerController playerController, StatusMenu statusMenu) {
        super(playerController);
        this.statusMenu = statusMenu;
    }

    public void deckSelect(JButton button, Deck deck, int rank) {
        button.addActionListener(actionEvent -> {
            statusMenu.offEnabledMenu();
            PlayerLogs.addToLogBody(LogsEnum.valueOf("status").getEvent()[1], deck.getName(), playerController.getPlayer());


            MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Status Message.png",
                    GraphicsDefault.StatusMenu.messageBounds, statusMenu.getUserFrame().getPane(), false, StatusLayer.deckDetail.getLayer());
            String title = "<html><center>\""+deck.getName()+"\"</center></html>";
            ComponentCreator.getInstance().setText(title, messagePanel,
                    "Belwe Bd BT Bold", 60, Color.white, GraphicsDefault.StatusMenu.messageTitleBounds);
            String content = "<html>" +
                    "Victory Density: "+deck.getVictoryDensity()+"<br>"+
                    "Number of Victory: " +deck.getVictoryNumber()+"<br>"+
                    "Number of DeckGame: " +deck.getGameNumber()+"<br>"+
                    "Average Cards Mana Cost: " +deck.aveCardsMana()+"<br>"+
                    "Deck Hero: " +deck.getHero().getHeroName()+"<br>"+
                    "Top Deck Card Name: " +playerController.getStatusController().sortDeckCard(deck)+"<br>"+
                    "</html>";
            ComponentCreator.getInstance().setText(content, messagePanel,
                    "FORTE", 30, Color.white,
                    GraphicsDefault.StatusMenu.messageDetailBounds).setHorizontalAlignment(SwingConstants.LEFT);
            JButton backButton = ComponentCreator.getInstance().setButton("Back", messagePanel, "buttons2.png",
                    GraphicsDefault.StatusMenu.backMessageBounds, Color.white, 30, 0);
            backButton.addActionListener(actionEvent2 -> {
                statusMenu.onEnabledMenu();
            });
            messagePanel.setVisible(true);
            messagePanel.setEnabled(true);
        });
    }
}
