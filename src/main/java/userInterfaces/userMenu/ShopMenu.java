package userInterfaces.userMenu;

import controller.FileManagement;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.LogsEnum;
import logs.PlayerLogs;
import model.card.Card;
import userInterfaces.graphicsActions.ShopMenuAction;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyCardButton;
import userInterfaces.myComponent.MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShopMenu {
    private UserMenu userMenu;
    private MyJPanel mainPanel;
    private MyJPanel cardPanel;
    private ShopMenuAction action;
    private int sellIndex, buyIndex;


    public ShopMenu(UserMenu userMenu) {
        this.userMenu = userMenu;
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Main Collection.jpg",
                GraphicsDefault.UserMenu.mainBounds, userMenu.getPane(), false, 20);
        action = new ShopMenuAction(userMenu.getPlayerController());
        initMainPanel();
        initCardPanel();
        sellIndex = 0;
        buyIndex = 0;
    }

    private void initMainPanel() {
        JButton exitGame = ComponentCreator.getInstance().setButton("Exit Game", mainPanel, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() * 5 / 24 + 5,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth() / 9,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 14), Color.white, 30, 0);

        JButton back = ComponentCreator.getInstance().setButton("Back", mainPanel, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth() / 12,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 14), Color.white, 30, 0);
        JButton buyButton = ComponentCreator.getInstance().setButton("Buy", mainPanel, "buttons3.png",
                GraphicsDefault.ShopMenu.rightButton1, Color.white, 27, 1);
        JButton sellButton = ComponentCreator.getInstance().setButton("Sell", mainPanel, "buttons3.png",
                GraphicsDefault.ShopMenu.rightButton2, Color.white, 27, 1);
        action.backToUserMenu(back, userMenu);
        action.exitGame(exitGame);
        action.buyButton(buyButton, this);
        action.sellButton(sellButton, this);

    }

    private void initCardPanel() {
        cardPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/CardShop.png",
                GraphicsDefault.ShopMenu.cardPanel, userMenu.getPane(), false, 21);

    }

    public UserMenu getUserMenu() {
        return userMenu;
    }

    public void showCards(ArrayList<Card> cards, boolean sell) {
        cardPanel.removeAll();
        JButton nextPage = ComponentCreator.getInstance().setButton("", cardPanel, "Right Arrow.png",
                GraphicsDefault.ShopMenu.cardsSection(0, 1), Color.white, 30, 3);
        JButton backPage = ComponentCreator.getInstance().setButton("", cardPanel, "Left Arrow.png",
                GraphicsDefault.ShopMenu.cardsSection(0, 2), Color.white, 30, 2);
        JLabel money = ComponentCreator.getInstance().setText("Your Money: " + action.getPlayerController().getPlayer().getMoney(),
                cardPanel, "FORTE", 30, Color.black, GraphicsDefault.ShopMenu.cardsSection(0, 3));
        if (sell) {
            nextPage.addActionListener(actionEvent -> {
                if (cards.size() > sellIndex + 8)
                    sellIndex += 8;
                showCards(cards, true);
            });
            backPage.addActionListener(actionEvent -> {
                if (0 <= sellIndex - 8)
                    sellIndex -= 8;
                showCards(cards, true);
            });
            for (int i = sellIndex; i < Math.min((sellIndex + 8), cards.size()); i++) {

                MyCardButton card = new MyCardButton(cardPanel, FilesPath.graphicsPath.cardsPath + "/"
                        + cards.get(i).getName() + ".png", GraphicsDefault.ShopMenu.cardsSection(i, 0));
                card.moveListener();
                action.sellCardAction(card,cards.get(i),this);
            }
        } else {
            nextPage.addActionListener(actionEvent -> {
                if (cards.size() > buyIndex + 8)
                    buyIndex += 8;
                showCards(cards, false);
            });
            backPage.addActionListener(actionEvent -> {
                if (0 <= buyIndex - 8)
                    buyIndex -= 8;
                showCards(cards, false);
            });
            for (int i = buyIndex; i < Math.min((buyIndex + 8), cards.size()); i++) {
                MyCardButton card = new MyCardButton(cardPanel, FilesPath.graphicsPath.cardsPath + "/"
                        + cards.get(i).getName() + ".png", GraphicsDefault.ShopMenu.cardsSection(i, 0));
                JLabel price = ComponentCreator.getInstance().setText("Card Price: "+cards.get(i).getBuyCost(),
                        cardPanel, "FORTE", 20, Color.black,  GraphicsDefault.ShopMenu.cardsSection(i, 4));
                card.moveListener();
                action.buyCardAction(card,cards.get(i),this);
            }
        }
        cardPanel.paint(cardPanel.getGraphics());
    }

    public void offEnabledPanel(){
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(false);
        }
        for (Component component : cardPanel.getComponents()) {
            component.setEnabled(false);
        }
    }
    public void onEnabledPanel() {
        userMenu.getPane().remove(userMenu.getPane().getComponentsInLayer(29)[0]);
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(true);
        }
        for (Component component : cardPanel.getComponents()) {
            component.setEnabled(true);
        }
        mainPanel.paint(mainPanel.getGraphics());
        cardPanel.paint(cardPanel.getGraphics());
    }
}
