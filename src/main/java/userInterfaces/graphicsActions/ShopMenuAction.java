package userInterfaces.graphicsActions;

import controller.FileManagement;
import controller.PlayerController;
import controller.ShopController;
import defaults.GraphicsDefault;
import enums.LogsEnum;
import logs.PlayerLogs;
import model.card.Card;
import network.protocol.ShopProtocol;
import userInterfaces.MyGraphics;
import userInterfaces.myComponent.MessageCreator;
import userInterfaces.myComponent.MyCardButton;
import userInterfaces.userMenu.ShopMenu;

import javax.swing.*;
import java.util.ArrayList;

public class ShopMenuAction extends MainMenuAction {

    public ShopMenuAction(MyGraphics myGraphics) {
        super(myGraphics);
    }

    public void sellButton(JButton button) {
        button.addActionListener(actionEvent -> {
            clientNetwork.getSender().getShopMenuHandler().startSelling();
            myGraphics.getUserFrame().getShopMenu().showCards(true);
        });
    }

    public void buyButton(JButton button) {
        button.addActionListener(actionEvent -> {
            clientNetwork.getSender().getShopMenuHandler().startBuying();
            myGraphics.getUserFrame().getShopMenu().showCards(false);
        });
    }

    public void sellCardAction(MyCardButton cardButton, Card card) {
        cardButton.addActionListener(actionEvent -> {
            ShopMenu shopMenu = myGraphics.getUserFrame().getShopMenu();
            shopMenu.offEnabledMenu();
            JButton[] buttons = MessageCreator.getInstance().yesNoMessage(
                    "<html><center>Your Income: " + card.getIncomeSell() + "</center><br><center>Do you like do it?</center><br><center></center><br><center></center></html>",
                    shopMenu.getUserFrame().getPane(), 29, 30);
            buttons[0].addActionListener(actionEvent2 -> {
                clientNetwork.getSender().getShopMenuHandler().sellCard(card);
            });
            buttons[1].addActionListener(actionEvent2 -> {
               shopMenu.onEnabledMenu();
            });
        });
    }

    public void buyCardAction(MyCardButton cardButton, Card card) {
        cardButton.addActionListener(actionEvent -> {
            ShopMenu shopMenu = myGraphics.getUserFrame().getShopMenu();
            shopMenu.offEnabledMenu();
            if (shopMenu.getShopProtocol().getPlayerMoney() < card.getBuyCost()) {
                JButton okbutton = MessageCreator.getInstance().errorMessage(
                        "<html><center>You haven't Enough Money for Buy." + "</center><br><center>Please Try other</center><br><center></center><br><center></center></html>",
                        shopMenu.getUserFrame().getPane(), GraphicsDefault.message.messagePanel, 29, 30);
                okbutton.addActionListener(actionEvent2 -> {
                    shopMenu.onEnabledMenu();
                    shopMenu.showCards(false);
                });
            } else {
                JButton[] buttons = MessageCreator.getInstance().yesNoMessage(
                        "<html><center>You Pay to Buy this Card: " + card.getBuyCost() + "</center><br><center>Do you like Buy this?</center><br><center></center><br><center></center></html>",
                        shopMenu.getUserFrame().getPane(), 29, 30);
                buttons[0].addActionListener(actionEvent2 -> {
                    clientNetwork.getSender().getShopMenuHandler().buyCard(card);
                });
                buttons[1].addActionListener(actionEvent2 -> {
                   shopMenu.onEnabledMenu();
                });
            }
        });
    }

    public void sellSuccessful(ShopProtocol shopProtocol) {
        myGraphics.getUserFrame().getShopMenu().setShopProtocol(shopProtocol);
        myGraphics.getUserFrame().getShopMenu().showCards(true);
        myGraphics.getUserFrame().getShopMenu().onEnabledMenu();
    }

    public void buySuccessful(ShopProtocol shopProtocol) {
        myGraphics.getUserFrame().getShopMenu().setShopProtocol(shopProtocol);
        myGraphics.getUserFrame().getShopMenu().showCards(false);
        myGraphics.getUserFrame().getShopMenu().onEnabledMenu();
    }


}
