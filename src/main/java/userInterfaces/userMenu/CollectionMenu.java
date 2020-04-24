package userInterfaces.userMenu;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import model.card.Card;
import userInterfaces.graphicsActions.CollectionMenuAction;
import userInterfaces.myComponent.Bounds;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyCardButton;
import userInterfaces.myComponent.MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CollectionMenu {
    private MyJPanel mainPanel;
    private MyJPanel deckPanel;
    private MyJPanel cardPanel;
    private CollectionMenuAction action;
    private UserMenu userMenu;

    private JTextField searchCardName;
    private JComboBox<Integer> manaFilter;
    private JComboBox<String> userHaveFilter;
    private String currentHeroSelected;
    private int pageIndex;

    private ArrayList<MyCardButton> heroesButton;

    public CollectionMenu(UserMenu userMenu) {
        this.userMenu = userMenu;
        action = new CollectionMenuAction(userMenu.getPlayerController());
        heroesButton = new ArrayList<>();
        currentHeroSelected = null;
        initMainPanel();
        //initDeckPanel();
        initCardPanel();
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
        action.backToUserMenu(back, userMenu);
        ComponentCreator.getInstance().setText("Search By Name: ", mainPanel,
                "FORTE", 20, Color.black,
                GraphicsDefault.Collection.searchSection(1));
        searchCardName = ComponentCreator.getInstance().setImportBox(mainPanel, 30, new Color(0, 136, 204),
                GraphicsDefault.Collection.searchSection(2));
        ComponentCreator.getInstance().setText("Mana:", mainPanel,
                "FORTE", 20, Color.black,
                GraphicsDefault.Collection.searchSection(3));
        manaFilter = ComponentCreator.getInstance().setIntComboBox(mainPanel, 0, 15, 4,
                GraphicsDefault.Collection.searchSection(4));
        ComponentCreator.getInstance().setText("Type Cards:", mainPanel,
                "FORTE", 20, Color.black,
                GraphicsDefault.Collection.searchSection(5));
        userHaveFilter = ComponentCreator.getInstance().setStrComboBox(mainPanel,
                new String[]{"All Cards", "User Cards", "Close Cards"}, 3, GraphicsDefault.Collection.searchSection(6));
        JButton filterButton = ComponentCreator.getInstance().setButton("Filter", mainPanel, "buttons1.png",
                GraphicsDefault.Collection.searchSection(7), Color.white, 25, 0);
        action.filterAction(filterButton, this, searchCardName, manaFilter, userHaveFilter);
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
        action.showHeroCards(heroesButton, this);
    }

    private void initDeckPanel() {
        deckPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/deckCollection2.png",
                GraphicsDefault.Collection.rightPanel, userMenu.getPane(), false, 12);
        JButton newDeck = ComponentCreator.getInstance().setButton("New Deck", deckPanel, "buttons1.png",
                GraphicsDefault.Collection.deckSection(0, 0, 1), Color.white, 30, 0);
        JButton test = ComponentCreator.getInstance().setButton("New Deck", deckPanel, "buttons1.png",
                GraphicsDefault.Collection.deckSection(1, 0, 1), Color.white, 30, 0);
        JButton test3 = ComponentCreator.getInstance().setButton("New Deck", deckPanel, "buttons1.png",
                GraphicsDefault.Collection.deckSection(2, 0, 1), Color.white, 30, 0);
        JButton test2 = ComponentCreator.getInstance().setButton("New Deck", deckPanel, "buttons1.png",
                GraphicsDefault.Collection.deckSection(3, 0, 1), Color.white, 30, 0);
        JButton test1 = ComponentCreator.getInstance().setButton("New Deck", deckPanel, "buttons1.png",
                GraphicsDefault.Collection.deckSection(4, 0, 1), Color.white, 30, 0);


    }

    private void initCardPanel() {
        cardPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/CardCollection.png",
                GraphicsDefault.Collection.cardPanel, userMenu.getPane(), false, 11);
        ArrayList<Card> cards = new ArrayList<>();
        startShowCardPanelContent("Pleas Select Hero To Show Cards", cards);
    }

    public void startShowCardPanelContent(String type, ArrayList<Card> cards) {
        pageIndex = 0;
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
            if (cards.size() > pageIndex + 8)
                pageIndex += 8;
            ShowCardPanelContent(type, cards);
        });
        backPage.addActionListener(actionEvent -> {
            if (0 <= pageIndex - 8)
                pageIndex -= 8;
            ShowCardPanelContent(type, cards);
        });
        showCards(cards, pageIndex);

    }

    private void showCards(ArrayList<Card> cards, int pageIndex) {
        for (int i = pageIndex; i < Math.min((pageIndex + 8), cards.size()); i++) {
            MyCardButton card = new MyCardButton(cardPanel, FilesPath.graphicsPath.cardsPath + "/"
                    + cards.get(i).getName() + ".png", GraphicsDefault.Collection.cardsSection(i, 5));
            card.moveListener();
            if (cards.get(i).getNumber()==0){
                ComponentCreator.getInstance().setText("Closed",
                        cardPanel, "FORTE", 30, Color.red,  GraphicsDefault.Collection.cardsSection(i, 3));
            }else {
                ComponentCreator.getInstance().setText("Number: "+cards.get(i).getNumber(),
                        cardPanel, "FORTE", 20, Color.black,  GraphicsDefault.Collection.cardsSection(i, 4));

            }
            //action.sellCardAction(card, cards.get(i), this);
        }
        cardPanel.paint(cardPanel.getGraphics());
    }


}
