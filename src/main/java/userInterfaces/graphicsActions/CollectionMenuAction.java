package userInterfaces.graphicsActions;

import controller.FileManagement;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.FontEnum;
import enums.LogsEnum;
import enums.MessageEnum;
import model.card.Card;
import network.protocol.CollectionProtocol;
import network.protocol.DeckProtocol;
import userInterfaces.MyGraphics;
import userInterfaces.myComponent.*;
import userInterfaces.userMenu.CollectionMenu;
import userInterfaces.userMenu.ShopMenu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class CollectionMenuAction extends MainMenuAction {

    private CollectionMenu collectionMenu;
    private ColGraphControl colGraphControl;

    public CollectionMenuAction(MyGraphics myGraphics) {
        super(myGraphics);
    }

    public void setCollectionMenu(CollectionMenu collectionMenu) {
        this.collectionMenu = collectionMenu;
        this.colGraphControl = new ColGraphControl(collectionMenu);
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
                clientNetwork.getSender().saveToLog(LogsEnum.valueOf("collection").getEvent()[1],
                        button.getButtonName() + LogsEnum.valueOf("collection").getEvent_description()[1]);
                collectionMenu.startShowCardPanelContent(button.getButtonName(),
                        colGraphControl.getCollectionCardsShow(
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
                clientNetwork.getSender().saveToLog(LogsEnum.valueOf("collection").getEvent()[2],
                        "search:\"" + searchCardName.getText() + "\"_mana:\"" + (Integer) manaFilter.getSelectedItem()
                                + "\"_Card:\"" + userHaveFilter.getSelectedItem().toString() + "\"");
            collectionMenu.startShowCardPanelContent(collectionMenu.getCurrentHeroSelected(),
                    colGraphControl.getCollectionCardsShow(
                            collectionMenu.getCurrentHeroSelected(), (Integer) manaFilter.getSelectedItem(),
                            searchCardName.getText(), userCards, closedCards));
        });

    }

    public void closedCardsSelect(MyCardButton button, Card card) {
        button.addActionListener(actionEvent -> {
            clientNetwork.getSender().saveToLog(LogsEnum.valueOf("collection").getEvent()[3],
                    LogsEnum.valueOf("shop").getEvent_description()[3] + card.getName() + "/" + LogsEnum.valueOf("collection").getEvent_description()[2]);
            clientNetwork.getSender().getMainMenuHandler().goShop();
            clientNetwork.getSender().getShopMenuHandler().startBuying();
            myGraphics.getUserFrame().getShopMenu().showCards(false);
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

    public void newDeckAction(JButton button) {
        button.addActionListener(actionEvent -> {
            collectionMenu.offEnabledMenu();
            JLayeredPane pane = collectionMenu.getUserFrame().getPane();
            String[] heroesName = myGraphics.getPlayerHeroesName().toArray(new String[0]);
            MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                    GraphicsDefault.message.messagePanel, pane, false, 19);
            JLabel label = ComponentCreator.getInstance().setText(
                    "<html></center><br><center>\"Create New Deck\"</center><br><center></center><br><center></center>" +
                            "<br><center></center><br><center></center></html><br><center></center></html></html>",
                    messagePanel, new MyFont(FontEnum.MESSAGE.getName(), 30)
                    , Color.black, new Bounds(0, 0, messagePanel.getWidth(), messagePanel.getHeight()));
            ComponentCreator.getInstance().setText("Enter Your Deck Name:", messagePanel,
                    new MyFont(FontEnum.MESSAGE.getName(), 20), Color.black,
                    GraphicsDefault.message.component(6));
            JTextField deckName = ComponentCreator.getInstance().setImportBox(messagePanel, 30, new Color(0, 136, 204),
                    GraphicsDefault.message.component(7));
            ComponentCreator.getInstance().setText("Select Hero:", messagePanel,
                    new MyFont(FontEnum.MESSAGE.getName(), 20), Color.black,
                    GraphicsDefault.message.component(4));
            JComboBox<String> heroDeck = ComponentCreator.getInstance().setStrComboBox(messagePanel,
                    heroesName, 3, GraphicsDefault.message.component(5), 18);
            JButton createButton = ComponentCreator.getInstance().setButton("Create", messagePanel, "buttons2.png",
                    GraphicsDefault.message.component(2), Color.white, 30, 0);
            JButton cancelButton = ComponentCreator.getInstance().setButton("Cancel", messagePanel, "buttons2.png",
                    GraphicsDefault.message.component(3), Color.white, 30, 0);
            messagePanel.setVisible(true);
            messagePanel.setEnabled(true);
            createButton.addActionListener(actionEvent2 -> {
                clientNetwork.getSender().getCollectionMenuHandler().newDeck(deckName.getText(),
                        heroDeck.getSelectedItem().toString());
            });
            cancelButton.addActionListener(actionEvent2 -> {
                clientNetwork.getSender().saveToLog(LogsEnum.valueOf("collection").getEvent()[4],
                        LogsEnum.valueOf("collection").getEvent_description()[4]);
                collectionMenu.onEnabledMenu();
            });


        });
    }

    public void playerCardSelect(MyCardButton button, Card card) {
        button.addActionListener(actionEvent -> {
            if (collectionMenu.getSelectedDeck() == null) {
                collectionMenu.offEnabledMenu();
                JButton okbutton = MessageCreator.getInstance().errorMessage(MessageEnum.valueOf("unSelectedDeck").getText(),
                        collectionMenu.getUserFrame().getPane(), GraphicsDefault.message.messagePanel, 19, 30);
                okbutton.addActionListener(actionEvent2 -> {
                    clientNetwork.getSender().saveToLog(LogsEnum.valueOf("error").getEvent()[0],
                            LogsEnum.valueOf("error").getEvent_description()[1]);
                    collectionMenu.onEnabledMenu();
                });
                return;
            }
            if (!(collectionMenu.getSelectedDeck().getHero().getHeroName().equals(card.getCardClass()) || card.getCardClass().equals("Neutral"))) {
                collectionMenu.offEnabledMenu();
                JButton okbutton = MessageCreator.getInstance().errorMessage(
                        MessageEnum.valueOf("unMatchingCardAndDeck").getText(), collectionMenu.getUserFrame().getPane(),
                        GraphicsDefault.message.messagePanel, 19, 30);
                okbutton.addActionListener(actionEvent2 -> {
                    clientNetwork.getSender().saveToLog(LogsEnum.valueOf("error").getEvent()[0],
                            LogsEnum.valueOf("error").getEvent_description()[1]);
                    collectionMenu.onEnabledMenu();
                });
                return;
            }
            if (colGraphControl.countCardInDeck(collectionMenu.getSelectedDeck(), card) >= 2) {
                collectionMenu.offEnabledMenu();
                JButton okbutton = MessageCreator.getInstance().errorMessage(
                        MessageEnum.valueOf("moreTowCardExist").getText(), collectionMenu.getUserFrame().getPane(),
                        GraphicsDefault.message.messagePanel, 19, 30);
                okbutton.addActionListener(actionEvent2 -> {
                    clientNetwork.getSender().saveToLog(LogsEnum.valueOf("error").getEvent()[0],
                            LogsEnum.valueOf("error").getEvent_description()[2]);
                    collectionMenu.onEnabledMenu();
                });
                return;
            }
            if (collectionMenu.getSelectedDeck().getCards().size() >= collectionMenu.getCollectionProtocol().getMaxDeckCardNumber()) {
                collectionMenu.offEnabledMenu();
                JButton okbutton = MessageCreator.getInstance().errorMessage(
                        MessageEnum.valueOf("fullDeckCards").getText(), collectionMenu.getUserFrame().getPane(),
                        GraphicsDefault.message.messagePanel, 19, 30);
                okbutton.addActionListener(actionEvent2 -> {
                    clientNetwork.getSender().saveToLog(LogsEnum.valueOf("error").getEvent()[0],
                            LogsEnum.valueOf("error").getEvent_description()[3]);
                    collectionMenu.onEnabledMenu();
                });
                return;
            }
            clientNetwork.getSender().getCollectionMenuHandler().playerCardSelect(collectionMenu.getSelectedDeck(), card);
        });
    }

    public void deckCardSelect(MyCardButton button, Card card) {
        button.addActionListener(actionEvent -> {
            clientNetwork.getSender().getCollectionMenuHandler().deckCardSelect(collectionMenu.getSelectedDeck(), card);
           });
    }

    public void selectDeckForGame(JButton button) {
        button.addActionListener(actionEvent -> {
            collectionMenu.offEnabledMenu();
            if (collectionMenu.getSelectedDeck().getCards().size() < collectionMenu.getCollectionProtocol().getMinDeckCardNumber()) {
                JButton okbutton = MessageCreator.getInstance().errorMessage(
                        MessageEnum.valueOf("lowDeckCards").getText(), collectionMenu.getUserFrame().getPane(),
                        GraphicsDefault.message.messagePanel, 19, 30);
                okbutton.addActionListener(actionEvent2 -> {
                    collectionMenu.onEnabledMenu();
                });

            } else {
                clientNetwork.getSender().getCollectionMenuHandler().selectDeckForGame(collectionMenu.getSelectedDeck());
            }
        });
    }

    public void deleteDeck(JButton button) {
        button.addActionListener(actionEvent -> {
            collectionMenu.offEnabledMenu();
            clientNetwork.getSender().saveToLog(LogsEnum.valueOf("collection").getEvent()[9],
                    LogsEnum.valueOf("collection").getEvent_description()[7]);
            JButton[] buttons = MessageCreator.getInstance().yesNoMessage(MessageEnum.valueOf("deleteDeck").getText()
                    , collectionMenu.getUserFrame().getPane(), 19, 30);
            buttons[0].addActionListener(actionEvent2 -> {
                clientNetwork.getSender().getCollectionMenuHandler().deleteDeck(collectionMenu.getSelectedDeck());
            });
            buttons[1].addActionListener(actionEvent2 -> {
                clientNetwork.getSender().saveToLog(LogsEnum.valueOf("collection").getEvent()[11],
                        LogsEnum.valueOf("collection").getEvent_description()[9]);
                collectionMenu.onEnabledMenu();
            });
        });
    }

    public void selectDeck(JButton button, int deckIndex) {
        button.addActionListener(actionEvent -> {
            collectionMenu.setSelectedDeckIndex(deckIndex);
            clientNetwork.getSender().saveToLog(LogsEnum.valueOf("collection").getEvent()[5],
                    LogsEnum.valueOf("collection").getEvent_description()[5] + collectionMenu.getSelectedDeck().getName());
            collectionMenu.showSelectedDeck(collectionMenu.getSelectedDeck(), 0);
        });
    }

    public void editDeck(JButton button) {
        button.addActionListener(actionEvent -> {
            collectionMenu.offEnabledMenu();
            JLayeredPane pane = collectionMenu.getUserFrame().getPane();
            String[] heroesName = myGraphics.getPlayerHeroesName().toArray(new String[0]);

            MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                    GraphicsDefault.message.messagePanel, pane, false, 19);
            JLabel label = ComponentCreator.getInstance().setText(
                    "<html></center><br><center>\"Edit Deck Characteristics\"</center><br><center></center><br><center></center>" +
                            "<br><center></center><br><center></center></html><br><center></center></html></html>",
                    messagePanel, new MyFont(FontEnum.MESSAGE.getName(), 30)
                    , Color.black, new Bounds(0, 0, messagePanel.getWidth(), messagePanel.getHeight()));
            ComponentCreator.getInstance().setText("Enter New Deck Name:", messagePanel,
                    new MyFont(FontEnum.MESSAGE.getName(), 20), Color.black,
                    GraphicsDefault.message.component(6));
            JTextField deckName = ComponentCreator.getInstance().setImportBox(messagePanel, 30, new Color(0, 136, 204),
                    GraphicsDefault.message.component(7));
            ComponentCreator.getInstance().setText("Select New Hero:", messagePanel,
                    new MyFont(FontEnum.MESSAGE.getName(), 20), Color.black,
                    GraphicsDefault.message.component(4));
            JComboBox<String> heroDeck = ComponentCreator.getInstance().setStrComboBox(messagePanel,
                    heroesName, 3, GraphicsDefault.message.component(5), 18);
            JButton editButton = ComponentCreator.getInstance().setButton("Edit", messagePanel, "buttons2.png",
                    GraphicsDefault.message.component(2), Color.white, 30, 0);
            JButton cancelButton = ComponentCreator.getInstance().setButton("Cancel", messagePanel, "buttons2.png",
                    GraphicsDefault.message.component(3), Color.white, 30, 0);
            editButton.addActionListener(actionEvent2 -> {
                clientNetwork.getSender().getCollectionMenuHandler().editDeck(collectionMenu.getSelectedDeck(),
                        deckName.getText(), heroDeck.getSelectedItem().toString());
            });
            cancelButton.addActionListener(actionEvent2 -> {
                clientNetwork.getSender().saveToLog(LogsEnum.valueOf("collection").getEvent()[12],
                        LogsEnum.valueOf("collection").getEvent_description()[4] + collectionMenu.getSelectedDeck().getName());
                collectionMenu.onEnabledMenu();
            });
            messagePanel.setVisible(true);
            messagePanel.setEnabled(true);
        });
    }

    public void editDeckSuccessful(CollectionProtocol collectionProtocol) {

        myGraphics.getUserFrame().getCollectionMenu().setCollectionProtocol(collectionProtocol);
        collectionMenu.showSelectedDeck(collectionMenu.getSelectedDeck(), collectionMenu.getCardInDeckIndex());
        collectionMenu.onEnabledMenu();
    }

    public void selectDeckForGameSuccess(CollectionProtocol collectionProtocol) {

        myGraphics.getUserFrame().getCollectionMenu().setCollectionProtocol(collectionProtocol);
        JButton okbutton = MessageCreator.getInstance().errorMessage(
                MessageEnum.valueOf("changeGameDeck").getText(), collectionMenu.getUserFrame().getPane(),
                GraphicsDefault.message.messagePanel, 19, 30);
        okbutton.addActionListener(actionEvent2 -> {
            collectionMenu.onEnabledMenu();
        });
    }

    public void newDeckSuccess(CollectionProtocol collectionProtocol) {

        myGraphics.getUserFrame().getCollectionMenu().setCollectionProtocol(collectionProtocol);
        collectionMenu.showDeckList();
        collectionMenu.onEnabledMenu();
    }

    public void transferCardDeckSuccess(CollectionProtocol collectionProtocol) {

        myGraphics.getUserFrame().getCollectionMenu().setCollectionProtocol(collectionProtocol);
        collectionMenu.startShowCardPanelContent(collectionMenu.getCurrentHeroSelected(),
                colGraphControl.getCollectionCardsShow(
                        collectionMenu.getCurrentHeroSelected(), 0, "", true, true));
        collectionMenu.showSelectedDeck(collectionMenu.getSelectedDeck(), collectionMenu.getCardInDeckIndex());
    }


    public void editDeckUnsuccessful() {
        collectionMenu.onEnabledMenu();
        collectionMenu.offEnabledMenu();
        JButton okbutton = MessageCreator.getInstance().errorMessage(
                MessageEnum.valueOf("editDeckMistake").getText(), collectionMenu.getUserFrame().getPane(), GraphicsDefault.message.messagePanel, 19, 30);
        okbutton.addActionListener(actionEvent3 -> {
            clientNetwork.getSender().saveToLog(LogsEnum.valueOf("error").getEvent()[0],
                    LogsEnum.valueOf("error").getEvent_description()[4]);
            collectionMenu.onEnabledMenu();
        });
    }

    public void backToDecksAction(JButton button) {
        button.addActionListener(actionEvent -> {
            clientNetwork.getSender().saveToLog(LogsEnum.valueOf("back").getEvent()[0],
                    LogsEnum.valueOf("back").getEvent_description()[1]);
            collectionMenu.setSelectedDeckIndex(-1);
            collectionMenu.showDeckList();
        });
    }

    public void deleteDeckSuccess(CollectionProtocol collectionProtocol) {
        myGraphics.getUserFrame().getCollectionMenu().setCollectionProtocol(collectionProtocol);
        collectionMenu.startShowCardPanelContent(collectionMenu.getCurrentHeroSelected(),
                colGraphControl.getCollectionCardsShow(
                        collectionMenu.getCurrentHeroSelected(), 0, "", true, true));
        collectionMenu.showDeckList();
        collectionMenu.onEnabledMenu();
    }

    class ColGraphControl {
        private CollectionProtocol collectionProtocol;
        private CollectionMenu collectionMenu;

        ColGraphControl(CollectionMenu collectionMenu) {
            this.collectionMenu = collectionMenu;
        }

        ArrayList<Card> getCollectionCardsShow(String type, int mana, String searchWord, boolean userCards, boolean closedCards) {
            ArrayList<Card> cards = collectionCards(userCards, closedCards);
            typeFilter(cards, type);
            manaFilter(cards, mana);
            wordFilter(cards, searchWord);
            return cards;
        }

        private void manaFilter(ArrayList<Card> cards, int mana) {
            if (mana != 0) {
                for (int i = 0; i < cards.size(); i++) {
                    if (cards.get(i).getMana() != mana) {
                        cards.remove(i);
                        i--;
                    }
                }
            }
        }

        private void typeFilter(ArrayList<Card> cards, String type) {
            for (int i = 0; i < cards.size(); i++) {
                if (!cards.get(i).getCardClass().equals(type)) {
                    cards.remove(i);
                    i--;
                }
            }
        }

        private void wordFilter(ArrayList<Card> cards, String word) {
            for (int i = 0; i < cards.size(); i++) {
                if (!cards.get(i).getName().toLowerCase().contains(word.toLowerCase())) {
                    cards.remove(i);
                    i--;
                }
            }
        }

        private ArrayList<Card> collectionCards(boolean userCards, boolean closedCards) {
            setCollectionProtocol();
            ArrayList<Card> cards = new ArrayList<>();
            ArrayList<Card> allCards = FileManagement.getInstance().getAllCardsFromFile();
            if (userCards)
                addPlayerCards(cards);
            if (closedCards) {
                boolean isDiffer = true;
                for (int i = 0; i < allCards.size(); i++) {
                    isDiffer = true;
                    for (int j = 0; j < collectionProtocol.getFreeDeck().getCards().size(); j++) {
                        if (collectionProtocol.getFreeDeck().getCards().get(j).getName().equals(allCards.get(i).getName())) {
                            isDiffer = false;
                        }
                    }
                    if (isDiffer) {
                        cards.add(allCards.get(i));
                    }
                }
            }
            return cards;
        }

        private void cardSortByName(ArrayList<Card> cards) {
            for (int i = 0; i < cards.size() - 1; i++)
                for (int j = 0; j < cards.size() - i - 1; j++)
                    if (cards.get(j).getName().compareToIgnoreCase(cards.get(j + 1).getName()) > 0)
                        Collections.swap(cards, j, j + 1);
        }

        private void addPlayerCards(ArrayList<Card> cards) {
            cardSortByName(collectionProtocol.getFreeDeck().getCards());
            int counter = 1;
            for (int i = 0; i < collectionProtocol.getFreeDeck().getCards().size() - 1; i++) {
                if (collectionProtocol.getFreeDeck().getCards().get(i).getName().equals(collectionProtocol.getFreeDeck().getCards().get(i + 1).getName())) {
                    counter++;
                } else {
                    cards.add(collectionProtocol.getFreeDeck().getCards().get(i));
                    cards.get(cards.size() - 1).setNumber(counter);
                    counter = 1;
                }
            }
            if (collectionProtocol.getFreeDeck().getCards().size() != 0) {
                cards.add(collectionProtocol.getFreeDeck().getCards().get(collectionProtocol.getFreeDeck().getCards().size() - 1));
                cards.get(cards.size() - 1).setNumber(counter);
            }
        }

        private int countCardInDeck(DeckProtocol deck, Card card) {
            int count = 0;
            for (int i = 0; i < deck.getCards().size(); i++) {
                if (card.getName().equals(deck.getCards().get(i).getName())) {
                    count++;
                }
            }
            return count;
        }

        private void setCollectionProtocol() {
            collectionProtocol = collectionMenu.getCollectionProtocol();
        }
    }
}

