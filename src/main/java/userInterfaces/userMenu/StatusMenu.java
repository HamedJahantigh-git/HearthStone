package userInterfaces.userMenu;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.FontEnum;
import enums.StatusLayer;
import model.Deck;
import userInterfaces.graphicsActions.StatusMenuAction;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyFont;
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.myComponent.gameComponent.LayerDrawer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatusMenu {
    private UserFrame userFrame;
    private StatusMenuAction action;
    private MyJPanel mainPanel;

    public StatusMenu(UserFrame userFrame) {
        this.userFrame = userFrame;
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Status Menu.jpg",
                GraphicsDefault.StatusMenu.mainBounds, userFrame.getPane(), false, StatusLayer.mainPanel.getLayer());
        //action = new StatusMenuAction(userFrame.getPlayerController(), this);
    }

    public UserFrame getUserFrame() {
        return userFrame;
    }

    private void initMainPanel() {
        ComponentCreator.getInstance().setText("Status Menu", mainPanel, new MyFont(FontEnum.LABEl.getName(),45), Color.green,
                GraphicsDefault.StatusMenu.titleBounds);
        JButton exitGame = ComponentCreator.getInstance().setButton("Exit Game", mainPanel, "buttons1.png",
                GraphicsDefault.StatusMenu.exitGameBounds, Color.white, 30, 0);
        action.exitGame(exitGame);
        JButton back = ComponentCreator.getInstance().setButton("Back", mainPanel, "buttons1.png",
                GraphicsDefault.StatusMenu.backBounds, Color.white, 30, 0);
        action.backToMainMenu(back);
    }

    public void showTopDecks() {
        cleanStatusMenu();
        initMainPanel();
        ArrayList<Deck> topDeck = action.getPlayerController().getStatusController().topDeck();
        for (int i = 0; i < topDeck.size(); i++) {
            LayerDrawer deckShow = new LayerDrawer(FilesPath.graphicsPath.collectionPath + "/" +
                    topDeck.get(i).getHero().getHeroName() + " Deck.jpg",  GraphicsDefault.StatusMenu.deckSection(i, 1)
            ,mainPanel,userFrame.getPane(),StatusLayer.deckShow.getLayer());
            ComponentCreator.getInstance().setText("Top#" + (i + 1) + " (" + topDeck.get(i).getName() + ")",mainPanel,
                    new MyFont(FontEnum.LABEl.getName(),20), Color.white,GraphicsDefault.StatusMenu.deckSection(i, 2));
            deckShow.selectable();
            action.deckSelect(deckShow.getButton(), topDeck.get(i), i+1);
        }
    }

    public void offEnabledMenu() {
        for (int i = 36; i < 40; i++) {
            for (Component component : userFrame.getPane().getComponentsInLayer(i)) {
                component.setEnabled(false);
                component.setVisible(false);
            }
        }
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(false);
            component.setVisible(false);
        }
    }

    public void onEnabledMenu() {
        for (Component c : userFrame.getPane().getComponentsInLayer(StatusLayer.deckDetail.getLayer())) {
            userFrame.getPane().remove(c);
        }
        for (int i = 36; i < 40; i++) {
            for (Component component : userFrame.getPane().getComponentsInLayer(i)) {
                component.setEnabled(true);
                component.setVisible(true);
            }
        }
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(true);
            component.setVisible(true);
        }
        mainPanel.paint(mainPanel.getGraphics());
    }

    private void cleanStatusMenu() {
        mainPanel.removeAll();
        for (int i = 36; i < 40; i++) {
            for (Component component : userFrame.getPane().getComponentsInLayer(i)) {
                userFrame.getPane().remove(component);
            }
        }
    }
}
