package userInterfaces.graphicsActions;

import controller.FileManagement;
import controller.PlayerController;
import controller.ShopController;
import defaults.GraphicsDefault;
import enums.ExceptionsEnum;
import enums.LogsEnum;
import enums.MessageEnum;
import logs.PlayerLogs;
import model.Deck;
import model.card.Card;
import userInterfaces.myComponent.MessageCreator;
import userInterfaces.myComponent.MyCardButton;
import userInterfaces.userMenu.CollectionMenu;

import javax.swing.*;
import java.util.ArrayList;

public class CollectionMenuAction extends MainMenuAction {

    private CollectionMenu collectionMenu;

    public CollectionMenuAction(PlayerController playerController, CollectionMenu collectionMenu) {
        super(playerController);
        this.collectionMenu = collectionMenu;
    }

    public void showHeroCards(ArrayList<MyCardButton> heroes) {
        for (MyCardButton button : heroes) {
            button.addActionListener(actionEvent -> {
                for (MyCardButton object : heroes) {
                    if (object.getY() != GraphicsDefault.Collection.heroesButtonBounds(1).getY())
                        object.setLocation(object.getX(), GraphicsDefault.Collection.heroesButtonBounds(1).getY());
                }
                button.setLocation(button.getX(), GraphicsDefault.Collection.heroesButtonBounds(1).getY() + 30);
                collectionMenu.setCurrentHeroSelected(button.getButtonName());
                PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[1],
                        button.getButtonName() + LogsEnum.valueOf("collection").getEvent_description()[1], playerController.getPlayer());
                collectionMenu.startShowCardPanelContent(button.getButtonName(),
                        playerController.getCollectionController().getCollectionCardsShow(
                                button.getButtonName(), 0, "", true, true));
            });
        }

    }

    public void filterAction(JButton button, JTextField searchCardName,
                             JComboBox<Integer> manaFilter, JComboBox<String> userHaveFilter) {
        button.addActionListener(actionEvent -> {
            boolean userCards = true;
            boolean closedCards = true;
            if (userHaveFilter.getSelectedItem().toString().equals("User Cards"))
                closedCards = false;
            if (userHaveFilter.getSelectedItem().toString().equals("Close Cards"))
                userCards = false;
            if (collectionMenu.getCurrentHeroSelected() != null)
                PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[2],
                        "search:\"" + searchCardName.getText() + "\"_mana:\"" + (Integer) manaFilter.getSelectedItem()
                                + "\"_Card:\"" + userHaveFilter.getSelectedItem().toString() + "\"", playerController.getPlayer());
            collectionMenu.startShowCardPanelContent(collectionMenu.getCurrentHeroSelected(),
                    playerController.getCollectionController().getCollectionCardsShow(
                            collectionMenu.getCurrentHeroSelected(), (Integer) manaFilter.getSelectedItem(),
                            searchCardName.getText(), userCards, closedCards));
        });

    }

    public void closedCardsSelect(MyCardButton button, Card card) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[3],
                    LogsEnum.valueOf("shop").getEvent_description()[3] + card.getName() + "/" + LogsEnum.valueOf("collection").getEvent_description()[2], playerController.getPlayer());
            collectionMenu.getUserMenu().startShopMenu();
            collectionMenu.getUserMenu().getShopMenu().offEnabledMenu();
            if (playerController.getPlayer().getMoney() < card.getBuyCost()) {
                JButton okbutton = MessageCreator.getInstance().errorMessage(
                        "<html><center>You haven't Enough Money for Buy." + "</center><br><center>Please Try other</center><br><center></center><br><center></center></html>",
                        collectionMenu.getUserMenu().getShopMenu().getUserMenu().getPane(), 29, 30);
                okbutton.addActionListener(actionEvent2 -> {
                    PlayerLogs.addToLogBody(LogsEnum.valueOf("error").getEvent()[0],
                            LogsEnum.valueOf("error").getEvent_description()[0], playerController.getPlayer());
                    collectionMenu.getUserMenu().getShopMenu().onEnabledMenu();
                    collectionMenu.getUserMenu().getShopMenu().showCards(FileManagement.getInstance().getAllCardsFromFile(), false);
                });
            } else {
                JButton[] buttons = MessageCreator.getInstance().yesNoMessage(
                        "<html><center>You Pay to Buy this Card: " + card.getBuyCost() + "</center><br><center>Do you like Buy this?</center><br><center></center><br><center></center></html>",
                        collectionMenu.getUserMenu().getShopMenu().getUserMenu().getPane(), 29, 30);
                buttons[0].addActionListener(actionEvent2 -> {
                    PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[5],
                            LogsEnum.valueOf("shop").getEvent_description()[6] + card.getName(), playerController.getPlayer());
                    ShopController.getInstance().buyCard(playerController.getPlayer(), card);
                    FileManagement.getInstance().savePlayerToFile(playerController.getPlayer());
                    collectionMenu.getUserMenu().getShopMenu().onEnabledMenu();
                    collectionMenu.getUserMenu().getShopMenu().showCards(FileManagement.getInstance().getAllCardsFromFile(), false);
                });
                buttons[1].addActionListener(actionEvent2 -> {
                    PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[4],
                            LogsEnum.valueOf("shop").getEvent_description()[4], playerController.getPlayer());
                    collectionMenu.getUserMenu().getShopMenu().onEnabledMenu();
                });
            }
        });
    }

    public void playerCardSelect(MyCardButton button, Card card) {
        button.addActionListener(actionEvent -> {
            try {
                playerController.getCollectionController().moveCardFromFreeToDeck(collectionMenu.getSelectedDeck(), card);
                collectionMenu.startShowCardPanelContent(collectionMenu.getCurrentHeroSelected(),
                        playerController.getCollectionController().getCollectionCardsShow(
                                collectionMenu.getCurrentHeroSelected(), 0, "", true, true));
                collectionMenu.showSelectedDeck(collectionMenu.getSelectedDeck(), collectionMenu.getCardInDeckIndex());
                PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[6],
                        card.getName() + "_add_to:" + collectionMenu.getSelectedDeck().getName(), playerController.getPlayer());
                FileManagement.getInstance().savePlayerToFile(playerController.getPlayer());
            } catch (Exception e) {
                if (e.getMessage().equals(ExceptionsEnum.valueOf("unSelectedDeck").getMessage())) {
                    collectionMenu.offEnabledMenu();
                    JButton okbutton = MessageCreator.getInstance().errorMessage(MessageEnum.valueOf("unSelectedDeck").getText(),
                            collectionMenu.getUserMenu().getPane(), 19, 30);
                    okbutton.addActionListener(actionEvent2 -> {
                        PlayerLogs.addToLogBody(LogsEnum.valueOf("error").getEvent()[0],
                                LogsEnum.valueOf("error").getEvent_description()[1], playerController.getPlayer());
                        collectionMenu.onEnabledMenu();
                    });
                }
                if (e.getMessage().equals(ExceptionsEnum.valueOf("unMatchingCardAndDeck").getMessage())) {
                    collectionMenu.offEnabledMenu();
                    JButton okbutton = MessageCreator.getInstance().errorMessage(
                            MessageEnum.valueOf("unMatchingCardAndDeck").getText(), collectionMenu.getUserMenu().getPane(), 19, 30);
                    okbutton.addActionListener(actionEvent2 -> {
                        PlayerLogs.addToLogBody(LogsEnum.valueOf("error").getEvent()[0],
                                LogsEnum.valueOf("error").getEvent_description()[1], playerController.getPlayer());
                        collectionMenu.onEnabledMenu();
                    });
                }
                if (e.getMessage().equals(ExceptionsEnum.valueOf("moreTowCardExist").getMessage())) {
                    collectionMenu.offEnabledMenu();
                    JButton okbutton = MessageCreator.getInstance().errorMessage(
                            MessageEnum.valueOf("moreTowCardExist").getText(), collectionMenu.getUserMenu().getPane(), 19, 30);
                    okbutton.addActionListener(actionEvent2 -> {
                        PlayerLogs.addToLogBody(LogsEnum.valueOf("error").getEvent()[0],
                                LogsEnum.valueOf("error").getEvent_description()[2], playerController.getPlayer());
                        collectionMenu.onEnabledMenu();
                    });
                }
                if (e.getMessage().equals(ExceptionsEnum.valueOf("fullDeckCards").getMessage())) {
                    collectionMenu.offEnabledMenu();
                    JButton okbutton = MessageCreator.getInstance().errorMessage(
                            MessageEnum.valueOf("fullDeckCards").getText(), collectionMenu.getUserMenu().getPane(), 19, 30);
                    okbutton.addActionListener(actionEvent2 -> {
                        PlayerLogs.addToLogBody(LogsEnum.valueOf("error").getEvent()[0],
                                LogsEnum.valueOf("error").getEvent_description()[3], playerController.getPlayer());
                        collectionMenu.onEnabledMenu();
                    });
                }
            }
        });
    }

    public void deckCardSelect(MyCardButton button, Card card) {
        button.addActionListener(actionEvent -> {
            playerController.getCollectionController().moveCardFromDeckToFree(collectionMenu.getSelectedDeck(), card);
            collectionMenu.startShowCardPanelContent(collectionMenu.getCurrentHeroSelected(),
                    playerController.getCollectionController().getCollectionCardsShow(
                            collectionMenu.getCurrentHeroSelected(), 0, "", true, true));
            collectionMenu.showSelectedDeck(collectionMenu.getSelectedDeck(), collectionMenu.getCardInDeckIndex());
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[7],
                    card.getName() + "_removed_from" + collectionMenu.getSelectedDeck().getName(), playerController.getPlayer());
            FileManagement.getInstance().savePlayerToFile(playerController.getPlayer());
        });
    }

    public void selectDeckForGame(JButton button) {
        button.addActionListener(actionEvent -> {
            collectionMenu.offEnabledMenu();
            try {
                playerController.getCollectionController().setGameDeck(collectionMenu.getSelectedDeck());
                PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[8],
                        LogsEnum.valueOf("collection").getEvent_description()[6] + collectionMenu.getSelectedDeck().getName(),
                        playerController.getPlayer());
                FileManagement.getInstance().savePlayerToFile(playerController.getPlayer());
                JButton okbutton = MessageCreator.getInstance().errorMessage(
                        MessageEnum.valueOf("changeGameDeck").getText(), collectionMenu.getUserMenu().getPane(), 19, 30);
                okbutton.addActionListener(actionEvent2 -> {
                    collectionMenu.onEnabledMenu();
                });
            } catch (Exception e) {
                JButton okbutton = MessageCreator.getInstance().errorMessage(
                        MessageEnum.valueOf("lowDeckCards").getText(), collectionMenu.getUserMenu().getPane(), 19, 30);
                okbutton.addActionListener(actionEvent2 -> {
                    collectionMenu.onEnabledMenu();
                });
            }

        });
    }

    public void newDeckAction(JButton button) {
        button.addActionListener(actionEvent -> {
            collectionMenu.offEnabledMenu();
            MessageCreator.getInstance().creatDeckMessage(collectionMenu.getUserMenu().getPane(),
                    playerController.getPlayer().getPlayerHeroes(), collectionMenu);
        });
    }

    public void deleteDeck(JButton button) {
        button.addActionListener(actionEvent -> {
            collectionMenu.offEnabledMenu();
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[9],
                    LogsEnum.valueOf("collection").getEvent_description()[7],
                    playerController.getPlayer());
            JButton[] buttons = MessageCreator.getInstance().yesNoMessage(MessageEnum.valueOf("deleteDeck").getText()
                    , collectionMenu.getUserMenu().getPane(), 19, 30);
            buttons[0].addActionListener(actionEvent2 -> {
                playerController.getCollectionController().deleteDeck(collectionMenu.getSelectedDeck());
                collectionMenu.startShowCardPanelContent(collectionMenu.getCurrentHeroSelected(),
                        playerController.getCollectionController().getCollectionCardsShow(
                                collectionMenu.getCurrentHeroSelected(), 0, "", true, true));
                collectionMenu.showDeckList();
                PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[10],
                        LogsEnum.valueOf("collection").getEvent_description()[8] + collectionMenu.getSelectedDeck().getName(),
                        playerController.getPlayer());
                FileManagement.getInstance().savePlayerToFile(playerController.getPlayer());
                collectionMenu.onEnabledMenu();
            });
            buttons[1].addActionListener(actionEvent2 -> {
                PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[11],
                        LogsEnum.valueOf("collection").getEvent_description()[9], playerController.getPlayer());
                collectionMenu.onEnabledMenu();
            });
        });
    }

    public void selectDeck(JButton button, Deck deck) {
        button.addActionListener(actionEvent -> {
            collectionMenu.setSelectedDeck(deck);
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[5],
                    LogsEnum.valueOf("collection").getEvent_description()[5] + deck.getName(), playerController.getPlayer());
            collectionMenu.showSelectedDeck(deck, 0);
        });
    }

    public void editDeck(JButton button) {
        button.addActionListener(actionEvent -> {
            collectionMenu.offEnabledMenu();
            MessageCreator.getInstance().editDeckMessage(collectionMenu.getUserMenu().getPane(),
                    playerController.getPlayer().getPlayerHeroes(), collectionMenu);
        });
    }

    public void backToDecksAction(JButton button) {
        button.addActionListener(actionEvent -> {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("back").getEvent()[0],
                    LogsEnum.valueOf("back").getEvent_description()[1], playerController.getPlayer());
            collectionMenu.setSelectedDeck(null);
            collectionMenu.showDeckList();
        });
    }
}

