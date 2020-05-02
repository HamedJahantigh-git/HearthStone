package userInterfaces.userMenu;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.GameLayer;
import enums.StatusLayer;
import model.Deck;
import userInterfaces.graphicsActions.StatusMenuAction;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.GameComponent;
import userInterfaces.myComponent.MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatusMenu {
    private UserMenu userMenu;
    private StatusMenuAction action;
    private MyJPanel mainPanel;

    public StatusMenu(UserMenu userMenu) {
        this.userMenu = userMenu;
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Status Menu.jpg",
                GraphicsDefault.StatusMenu.mainBounds, userMenu.getPane(), false, StatusLayer.base.getLayer());
        action = new StatusMenuAction(userMenu.getPlayerController(), this);
    }

    public UserMenu getUserMenu() {
        return userMenu;
    }

    private void initMainPanel() {
        ComponentCreator.getInstance().setText("Status Menu", mainPanel, "FORTE", 45, Color.green,
                GraphicsDefault.StatusMenu.titleBounds);
        JButton exitGame = ComponentCreator.getInstance().setButton("Exit Game", mainPanel, "buttons1.png",
                GraphicsDefault.StatusMenu.exitGameBounds, Color.white, 30, 0);
        action.exitGame(exitGame);
        JButton back = ComponentCreator.getInstance().setButton("Back", mainPanel, "buttons1.png",
                GraphicsDefault.StatusMenu.backBounds, Color.white, 30, 0);
        action.backToUserMenu(back, userMenu);
    }

    public void showTopDecks() {
        cleanStatusMenu();
        initMainPanel();
        ArrayList<Deck> topDeck = action.getPlayerController().getStatusController().topDeck();
        for (int i = 0; i < topDeck.size(); i++) {
            GameComponent deckShow = new GameComponent(userMenu.getPane(), StatusLayer.deckShow.getLayer(),
                    FilesPath.graphicsPath.collectionPath + "/" +
                            topDeck.get(i).getHero().getHeroName() + " Deck.jpg",
                    GraphicsDefault.StatusMenu.deckSection(i, 1));
            ComponentCreator.getInstance().setText("Top#" + (i + 1) + " (" + topDeck.get(i).getName() + ")", mainPanel, "FORTE",
                    20, Color.white, GraphicsDefault.StatusMenu.deckSection(i, 2));
            deckShow.moveListener(false);
            action.deckSelect(deckShow.getButton(), topDeck.get(i), i+1);
        }
    }

    public void offEnabledMenu() {
        for (int i = 36; i < 40; i++) {
            for (Component component : userMenu.getPane().getComponentsInLayer(i)) {
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
        for (Component c : userMenu.getPane().getComponentsInLayer(StatusLayer.deckDetail.getLayer())) {
            userMenu.getPane().remove(c);
        }
        for (int i = 36; i < 40; i++) {
            for (Component component : userMenu.getPane().getComponentsInLayer(i)) {
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
            for (Component component : userMenu.getPane().getComponentsInLayer(i)) {
                userMenu.getPane().remove(component);
            }
        }
    }
}
