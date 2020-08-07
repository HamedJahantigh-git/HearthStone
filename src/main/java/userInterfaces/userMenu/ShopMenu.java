package userInterfaces.userMenu;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.FontEnum;
import model.card.Card;
import network.protocol.ShopProtocol;
import userInterfaces.graphicsActions.ShopMenuAction;
import userInterfaces.myComponent.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShopMenu {
    private UserFrame userFrame;
    private MyJPanel mainPanel;
    private MyJPanel cardPanel;
    private ShopMenuAction action;
    private int sellIndex, buyIndex;

    private ShopProtocol shopProtocol;

    public ShopMenu(UserFrame userFrame) {
        this.userFrame = userFrame;
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Main Shop.jpg",
                GraphicsDefault.UserMenu.mainBounds, userFrame.getPane(), false, 20);
        action = new ShopMenuAction(userFrame.getMyGraphics());
        initMainPanel();
        initCardPanel();
        sellIndex = 0;
        buyIndex = 0;
    }

    public void setShopProtocol(ShopProtocol shopProtocol) {
        this.shopProtocol = shopProtocol;
    }

    public MyJPanel getCardPanel() {
        return cardPanel;
    }

    public ShopMenuAction getAction() {
        return action;
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
        action.backToMainMenu(back);
        action.exitGame(exitGame);
        action.buyButton(buyButton);
        action.sellButton(sellButton);

    }

    private void initCardPanel() {
        cardPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/CardShop.png",
                GraphicsDefault.ShopMenu.cardPanel, userFrame.getPane(), false, 21);

    }

    public UserFrame getUserFrame() {
        return userFrame;
    }

    public void showCards(boolean sell) {
        cardPanel.removeAll();
        JButton nextPage = ComponentCreator.getInstance().setButton("", cardPanel, "Right Arrow.png",
                GraphicsDefault.ShopMenu.cardsSection(0, 1), Color.white, 30, 3);
        JButton backPage = ComponentCreator.getInstance().setButton("", cardPanel, "Left Arrow.png",
                GraphicsDefault.ShopMenu.cardsSection(0, 2), Color.white, 30, 2);
        ComponentCreator.getInstance().setText("Your Money: " + shopProtocol.getPlayerMoney(),
                cardPanel, new MyFont(FontEnum.LABEl.getName(),30), Color.black, GraphicsDefault.ShopMenu.cardsSection(0, 3));
        if (sell) {
            ArrayList<Card> cards = shopProtocol.getSellCard();
            nextPage.addActionListener(actionEvent -> {
                if (cards.size() > sellIndex + 8)
                    sellIndex += 8;
                showCards(true);
            });
            backPage.addActionListener(actionEvent -> {
                if (0 <= sellIndex - 8)
                    sellIndex -= 8;
                showCards(true);
            });
            for (int i = sellIndex; i < Math.min((sellIndex + 8), cards.size()); i++) {

                MyCardButton card = new MyCardButton(cardPanel, FilesPath.graphicsPath.cardsPath + "/"
                        + cards.get(i).getName() + ".png", GraphicsDefault.ShopMenu.cardsSection(i, 0));
                card.moveListener();
                action.sellCardAction(card,cards.get(i));
            }
        } else {
            ArrayList<Card> cards = shopProtocol.getBuyCard();
            nextPage.addActionListener(actionEvent -> {
                if (cards.size() > buyIndex + 8)
                    buyIndex += 8;
                showCards(false);
            });
            backPage.addActionListener(actionEvent -> {
                if (0 <= buyIndex - 8)
                    buyIndex -= 8;
                showCards(false);
            });
            for (int i = buyIndex; i < Math.min((buyIndex + 8), cards.size()); i++) {
                MyCardButton card = new MyCardButton(cardPanel, FilesPath.graphicsPath.cardsPath + "/"
                        + cards.get(i).getName() + ".png", GraphicsDefault.ShopMenu.cardsSection(i, 0));
                JLabel price = ComponentCreator.getInstance().setText("Card Price: "+cards.get(i).getBuyCost(),
                        cardPanel, new MyFont(FontEnum.LABEl.getName(),20), Color.black,  GraphicsDefault.ShopMenu.cardsSection(i, 4));
                card.moveListener();
                action.buyCardAction(card,cards.get(i));
            }
        }
        cardPanel.paint(cardPanel.getGraphics());
    }

    public void offEnabledMenu(){
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(false);
            component.setVisible(false);
        }
        for (Component component : cardPanel.getComponents()) {
            component.setEnabled(false);
            component.setVisible(false);
        }
    }

    public void onEnabledMenu() {
        userFrame.getPane().remove(userFrame.getPane().getComponentsInLayer(29)[0]);
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(true);
            component.setVisible(true);
        }
        for (Component component : cardPanel.getComponents()) {
            component.setEnabled(true);
            component.setVisible(true);
        }
    }

    public ShopProtocol getShopProtocol() {
        return shopProtocol;
    }
}
