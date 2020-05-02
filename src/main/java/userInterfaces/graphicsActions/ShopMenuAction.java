package userInterfaces.graphicsActions;

import controller.FileManagement;
import controller.PlayerController;
import controller.ShopController;
import defaults.GraphicsDefault;
import enums.LogsEnum;
import logs.PlayerLogs;
import model.card.Card;
import model.card.Minion;
import userInterfaces.myComponent.MessageCreator;
import userInterfaces.myComponent.MyCardButton;
import userInterfaces.userMenu.ShopMenu;

import javax.swing.*;
import java.util.ArrayList;

public class ShopMenuAction extends MainMenuAction {

    public ShopMenuAction(PlayerController playerController) {
        super(playerController);
    }

    public void sellButton(JButton button, ShopMenu shopMenu) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[1],
                    LogsEnum.valueOf("shop").getEvent_description()[1], playerController.getPlayer());
            shopMenu.showCards(playerController.getPlayer().getFreeDeck().getCards(), true);
        });
    }

    public void buyButton(JButton button, ShopMenu shopMenu) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[2],
                    LogsEnum.valueOf("shop").getEvent_description()[2], playerController.getPlayer());
            ArrayList<Card> a = FileManagement.getInstance().getAllCardsFromFile();
            shopMenu.showCards(FileManagement.getInstance().getAllCardsFromFile(), false);
        });
    }

    public void sellCardAction(MyCardButton cardButton, Card card, ShopMenu shopMenu) {
        cardButton.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[3],
                    LogsEnum.valueOf("shop").getEvent_description()[3] + card.getName(), playerController.getPlayer());
            shopMenu.offEnabledMenu();
            JButton[] buttons = MessageCreator.getInstance().yesNoMessage(
                    "<html><center>Your Income: " + card.getIncomeSell() + "</center><br><center>Do you like do it?</center><br><center></center><br><center></center></html>",
                    shopMenu.getUserMenu().getPane(), 29, 30);
            buttons[0].addActionListener(actionEvent2 -> {
                PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[5],
                        LogsEnum.valueOf("shop").getEvent_description()[5] + card.getName(), playerController.getPlayer());
                ShopController.getInstance().sellCard(playerController.getPlayer(), card);
                FileManagement.getInstance().savePlayerToFile(playerController.getPlayer());
                shopMenu.onEnabledMenu();
                shopMenu.showCards(playerController.getPlayer().getFreeDeck().getCards(), true);
            });
            buttons[1].addActionListener(actionEvent2 -> {
                PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[4],
                        LogsEnum.valueOf("shop").getEvent_description()[4], playerController.getPlayer());
                shopMenu.onEnabledMenu();
            });
        });
    }

    public void buyCardAction(MyCardButton cardButton, Card card, ShopMenu shopMenu) {
        cardButton.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[3],
                    LogsEnum.valueOf("shop").getEvent_description()[3] + card.getName(), playerController.getPlayer());
            shopMenu.offEnabledMenu();
            if (playerController.getPlayer().getMoney() < card.getBuyCost()) {
                JButton okbutton = MessageCreator.getInstance().errorMessage(
                        "<html><center>You haven't Enough Money for Buy." + "</center><br><center>Please Try other</center><br><center></center><br><center></center></html>",
                        shopMenu.getUserMenu().getPane(), GraphicsDefault.message.messagePanel, 29, 30);
                okbutton.addActionListener(actionEvent2 -> {
                    PlayerLogs.addToLogBody(LogsEnum.valueOf("error").getEvent()[0],
                            LogsEnum.valueOf("error").getEvent_description()[0], playerController.getPlayer());
                    shopMenu.onEnabledMenu();
                    shopMenu.showCards(FileManagement.getInstance().getAllCardsFromFile(), false);
                });
            } else {
                JButton[] buttons = MessageCreator.getInstance().yesNoMessage(
                        "<html><center>You Pay to Buy this Card: " + card.getBuyCost() + "</center><br><center>Do you like Buy this?</center><br><center></center><br><center></center></html>",
                        shopMenu.getUserMenu().getPane(), 29, 30);
                buttons[0].addActionListener(actionEvent2 -> {
                    PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[5],
                            LogsEnum.valueOf("shop").getEvent_description()[6] + card.getName(), playerController.getPlayer());
                    ShopController.getInstance().buyCard(playerController.getPlayer(), card);
                    FileManagement.getInstance().savePlayerToFile(playerController.getPlayer());
                    shopMenu.onEnabledMenu();
                    shopMenu.showCards(FileManagement.getInstance().getAllCardsFromFile(), false);
                });
                buttons[1].addActionListener(actionEvent2 -> {
                    PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[4],
                            LogsEnum.valueOf("shop").getEvent_description()[4], playerController.getPlayer());
                    shopMenu.onEnabledMenu();
                });
            }
        });
    }
}
