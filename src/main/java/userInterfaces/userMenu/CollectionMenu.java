package userInterfaces.userMenu;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import model.Deck;
import model.card.Card;
import userInterfaces.graphicsActions.CollectionMenuAction;
import userInterfaces.myComponent.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CollectionMenu {
    private MyJPanel mainPanel;
    private MyJPanel deckPanel;
    private MyJPanel cardPanel;
    private CollectionMenuAction action;
    private UserMenu userMenu;

    private Deck selectedDeck;
    private String currentHeroSelected;
    private int cardPageIndex, decksPageIndex, cardInDeckIndex;


    private ArrayList<MyCardButton> heroesButton;

    public CollectionMenu(UserMenu userMenu) {
        this.userMenu = userMenu;
        action = new CollectionMenuAction(userMenu.getPlayerController(), this);
        heroesButton = new ArrayList<>();
        currentHeroSelected = null;
        selectedDeck = null;
        initMainPanel();
        initDeckPanel();
        initCardPanel();
    }

    public int getCardInDeckIndex() {
        return cardInDeckIndex;
    }

    public UserMenu getUserMenu() {
        return userMenu;
    }

    public CollectionMenuAction getAction() {
        return action;
    }

    public Deck getSelectedDeck() {
        return selectedDeck;
    }

    public void setSelectedDeck(Deck selectedDeck) {
        this.selectedDeck = selectedDeck;
    }

    public String getCurrentHeroSelected() {
        return currentHeroSelected;
    }

    public void setCurrentHeroSelected(String currentHeroSelected) {
        this.currentHeroSelected = currentHeroSelected;
    }

    public void initMainPanel() {
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Main Collection.jpg",
                GraphicsDefault.UserMenu.mainBounds, userMenu.getPane(), false, 10);
        JButton exitGame = ComponentCreator.getInstance().setButton("Exit Game", mainPanel, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() * 5 / 24 + 5,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth() / 9,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 14), Color.white, 30, 0);
        action.exitGame(exitGame);
        JButton back = ComponentCreator.getInstance().setButton("Back", mainPanel, "buttons1.png",
                new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                        GraphicsDefault.UserMenu.mainBounds.getWidth() / 12,
                        GraphicsDefault.UserMenu.mainBounds.getHeight() / 14), Color.white, 30, 0);
        action.backToUserMenu(back,userMenu);
        ComponentCreator.getInstance().setText("Search By Name: ", mainPanel,
                "FORTE", 20, Color.black,
                GraphicsDefault.Collection.searchSection(1));
        JTextField searchCardName = ComponentCreator.getInstance().setImportBox(mainPanel, 30, new Color(0, 136, 204),
                GraphicsDefault.Collection.searchSection(2));
        ComponentCreator.getInstance().setText("Mana:", mainPanel,
                "FORTE", 20, Color.black,
                GraphicsDefault.Collection.searchSection(3));
        JComboBox<Integer> manaFilter = ComponentCreator.getInstance().setIntComboBox(mainPanel, 0, 15, 4,
                GraphicsDefault.Collection.searchSection(4));
        ComponentCreator.getInstance().setText("Type Cards:", mainPanel,
                "FORTE", 20, Color.black,
                GraphicsDefault.Collection.searchSection(5));
        JComboBox<String> userHaveFilter = ComponentCreator.getInstance().setStrComboBox(mainPanel,
                new String[]{"All Cards", "User Cards", "Close Cards"}, 3, GraphicsDefault.Collection.searchSection(6));
        JButton filterButton = ComponentCreator.getInstance().setButton("Filter", mainPanel, "buttons1.png",
                GraphicsDefault.Collection.searchSection(7), Color.white, 25, 0);
        action.filterAction(filterButton, searchCardName, manaFilter, userHaveFilter);
        //init Upper button for hero
        heroesButton.add(new MyCardButton("Neutral", mainPanel, FilesPath.graphicsPath.collectionPath + "/neutralCollection1.png",
                GraphicsDefault.Collection.heroesButtonBounds(1)));
        heroesButton.get(0).moveListener();
        String name;
        for (int i = 0; i < action.getPlayerController().getPlayer().getPlayerHeroes().size(); i++) {
            name = action.getPlayerController().getPlayer().getPlayerHeroes().get(i).getHeroName();
            heroesButton.add(new MyCardButton(name, mainPanel, FilesPath.graphicsPath.collectionPath + "/" +
                    name + "Collection1.png", GraphicsDefault.Collection.heroesButtonBounds(i + 2)));
            heroesButton.get(i + 1).moveListener();
        }
        action.showHeroCards(heroesButton);
    }

    private void initDeckPanel() {
        deckPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/deckCollection2.png",
                GraphicsDefault.Collection.rightPanel, userMenu.getPane(), false, 12);
        decksPageIndex = 0;
        showDeckList();
    }

    private void initCardPanel() {
        cardPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/CardCollection.png",
                GraphicsDefault.Collection.cardPanel, userMenu.getPane(), false, 11);
    }

    public void startShowCardPanelContent(String type, ArrayList<Card> cards) {
        cardPageIndex = 0;
        ShowCardPanelContent(type, cards);
    }

    private void ShowCardPanelContent(String type, ArrayList<Card> cards) {
        cardPanel.removeAll();
        ComponentCreator.getInstance().setText("\"" + type + "\"", cardPanel,
                "FORTE", 40, Color.black,
                GraphicsDefault.Collection.cardsSection(0, 0));
        JButton nextPage = ComponentCreator.getInstance().setButton("", cardPanel, "Right Arrow.png",
                GraphicsDefault.Collection.cardsSection(0, 1), Color.white, 30, 3);
        JButton backPage = ComponentCreator.getInstance().setButton("", cardPanel, "Left Arrow.png",
                GraphicsDefault.Collection.cardsSection(0, 2), Color.white, 30, 2);
        nextPage.addActionListener(actionEvent -> {
            if (cards.size() > cardPageIndex + 8)
                cardPageIndex += 8;
            ShowCardPanelContent(type, cards);
        });
        backPage.addActionListener(actionEvent -> {
            if (0 <= cardPageIndex - 8)
                cardPageIndex -= 8;
            ShowCardPanelContent(type, cards);
        });
        showCards(cards, cardPageIndex);

    }

    private void showCards(ArrayList<Card> cards, int pageIndex) {
        for (int i = pageIndex; i < Math.min((pageIndex + 8), cards.size()); i++) {
            MyCardButton card = new MyCardButton(cardPanel, FilesPath.graphicsPath.cardsPath + "/"
                    + cards.get(i).getName() + ".png", GraphicsDefault.Collection.cardsSection(i, 5));
            card.moveListener();
            if (cards.get(i).getNumber() == 0) {
                ComponentCreator.getInstance().setText("Closed",
                        cardPanel, "FORTE", 30, Color.red, GraphicsDefault.Collection.cardsSection(i, 4));
                action.closedCardsSelect(card, cards.get(i));
            } else {
                ComponentCreator.getInstance().setText("Number: " + cards.get(i).getNumber(),
                        cardPanel, "FORTE", 20, Color.black, GraphicsDefault.Collection.cardsSection(i, 4));
                action.playerCardSelect(card, cards.get(i));
            }
        }
        cardPanel.paint(cardPanel.getGraphics());
    }

    public void showDeckList() {
        deckPanel.removeAll();
        ArrayList<Deck> playerDecks = action.getPlayerController().getPlayer().getPlayerDecks();
        ComponentCreator.getInstance().setText("My Decks", deckPanel,
                "FORTE", 35, Color.black,
                GraphicsDefault.Collection.deckSection(1,  1));
        ComponentCreator.getInstance().setText("Game Deck: " + action.getPlayerController().getPlayer().getGameDeck().getName()
                , deckPanel, "FORTE", 20, Color.black,
                GraphicsDefault.Collection.deckSection(0,  8));
        JButton newDeck = ComponentCreator.getInstance().setButton("New Deck", deckPanel, "buttons1.png",
                GraphicsDefault.Collection.deckSection(1,  2), Color.white, 30, 0);
        action.newDeckAction(newDeck);
        JButton nextPage = ComponentCreator.getInstance().setButton("", deckPanel, "Right Arrow.png",
                GraphicsDefault.Collection.deckSection(0, 4), Color.white, 30, 3);
        JButton backPage = ComponentCreator.getInstance().setButton("", deckPanel, "Left Arrow.png",
                GraphicsDefault.Collection.deckSection(0,  5), Color.white, 30, 2);
        nextPage.addActionListener(actionEvent -> {
            if (playerDecks.size() > decksPageIndex + 5)
                decksPageIndex += 5;
            showDeckList();
        });
        backPage.addActionListener(actionEvent -> {
            if (0 <= decksPageIndex - 5)
                decksPageIndex -= 5;
            showDeckList();
        });
        for (int i = decksPageIndex; i < Math.min((decksPageIndex + 5), playerDecks.size()); i++) {
            MyCardButton card = new MyCardButton(deckPanel, FilesPath.graphicsPath.collectionPath + "/"
                    + playerDecks.get(i).getHero().getHeroName() + " Deck.jpg", GraphicsDefault.Collection.deckSection(i - decksPageIndex,  6));
            ComponentCreator.getInstance().setText(playerDecks.get(i).getName(), deckPanel,
                    "FORTE", 25, Color.blue,
                    GraphicsDefault.Collection.deckSection(i - decksPageIndex,  7));
            card.moveListener();
            action.selectDeck(card, playerDecks.get(i));
        }
        deckPanel.paint(deckPanel.getGraphics());
    }

    public void showSelectedDeck(Deck deck, int cardInDeckIndex) {
        this.cardInDeckIndex = cardInDeckIndex;
        deckPanel.removeAll();
        ComponentCreator.getInstance().setText(deck.getName(), deckPanel,
                "FORTE", 35, Color.black,
                GraphicsDefault.Collection.deckSection(1,  1));
         new MyCardButton(deckPanel, FilesPath.graphicsPath.collectionPath + "/"
                +deck.getHero().getHeroName() + " Deck.jpg", GraphicsDefault.Collection.deckSection(0,  6));
        JButton backToDecks = ComponentCreator.getInstance().setButton("Back To Decks", deckPanel, "buttons1.png",
                GraphicsDefault.Collection.deckSection(1,  2), Color.white, 25, 0);
        action.backToDecksAction(backToDecks);
        JButton deleteDeck = ComponentCreator.getInstance().setButton("Delete Deck", deckPanel, "buttons1.png",
                GraphicsDefault.Collection.deckSection(0, 3), Color.white, 15, 0);
        action.deleteDeck(deleteDeck);
        JButton editDeck = ComponentCreator.getInstance().setButton("Edit Deck", deckPanel, "buttons1.png",
                GraphicsDefault.Collection.deckSection(1,  3), Color.white, 15, 0);
        action.editDeck(editDeck);
        JButton selectDeckForGame = ComponentCreator.getInstance().setButton("Game Deck", deckPanel, "buttons1.png",
                GraphicsDefault.Collection.deckSection(2,  3), Color.white, 15, 0);
        action.selectDeckForGame(selectDeckForGame);
        JButton nextPage = ComponentCreator.getInstance().setButton("", deckPanel, "Right Arrow.png",
                GraphicsDefault.Collection.deckSection(0,  4), Color.white, 30, 3);
        JButton backPage = ComponentCreator.getInstance().setButton("", deckPanel, "Left Arrow.png",
                GraphicsDefault.Collection.deckSection(0, 5), Color.white, 30, 2);
        nextPage.addActionListener(actionEvent -> {
            if (deck.getCards().size() > this.cardInDeckIndex + 6)
                this.cardInDeckIndex += 6;
            showSelectedDeck(deck, this.cardInDeckIndex);
        });
        backPage.addActionListener(actionEvent -> {
            if (0 <= this.cardInDeckIndex - 6)
                this.cardInDeckIndex -= 6;
            showSelectedDeck(deck, this.cardInDeckIndex);
        });
        for (int i = this.cardInDeckIndex; i < Math.min((this.cardInDeckIndex + 6), deck.getCards().size()); i++) {
           MyCardButton card = new MyCardButton(deckPanel, FilesPath.graphicsPath.cardsPath + "/"
                    + deck.getCards().get(i).getName() + ".png", GraphicsDefault.Collection.deckSection(i, 9));
          card.moveListener();
           action.deckCardSelect(card, deck.getCards().get(i));
        }
        deckPanel.paint(deckPanel.getGraphics());
    }

    public void offEnabledMenu() {
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(false);
        }
        for (Component component : cardPanel.getComponents()) {
            component.setEnabled(false);
        }
        for (Component component : deckPanel.getComponents()) {
            component.setEnabled(false);
        }
    }

    public void onEnabledMenu() {
        userMenu.getPane().remove(userMenu.getPane().getComponentsInLayer(19)[0]);
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(true);
        }
        for (Component component : cardPanel.getComponents()) {
            component.setEnabled(true);
        }
        for (Component component : deckPanel.getComponents()) {
            component.setEnabled(true);
        }
        mainPanel.paint(mainPanel.getGraphics());
        cardPanel.paint(cardPanel.getGraphics());
        deckPanel.paint(deckPanel.getGraphics());
    }
}
