package userInterfaces.userMenu.play;

import controller.FileManagement;
import controller.game.GameController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.MineGameLayer;
import model.MyThread;
import model.Player;
import model.card.Card;
import model.hero.Hero;
import userInterfaces.graphicsActions.gameAction.GameAction;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.myComponent.gameComponent.*;
import userInterfaces.userMenu.UserFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BaseGameThread extends MyThread {

    private UserFrame userFrame;
    private JPanel mainPanel;

    private MineGameBoard mineGameBoard;

    private Player minePlayer;
    private GameController gameController;
    private ClockThread clockThread;
    private GameAction action;

    private EventDrawer eventBar;
    private ManaDrawer manaBars[];
    private HeroHeroPowerDrawer heroDrawers[];
    private HeroHeroPowerDrawer heroPowerDrawers[];

    public BaseGameThread(UserFrame userFrame, MineGameBoard mineGameBoard, Player minePlayer,
                          GameAction action, GameController gameController) {
        this.userFrame = userFrame;
        this.mineGameBoard = mineGameBoard;
        this.gameController = gameController;
        this.minePlayer = minePlayer;
        this.action = action;
        this.action.setBaseGameThread(this);
        this.mainPanel = mineGameBoard.getMainPanel();
    }

    @Override
    public void run() {
        selectCompetitor();
    }

    public void startGame() {
        gameController.startGame();
        clockThread = new ClockThread(this);
        reDrawAllGameBoard();
    }

    public Player getMinePlayer() {
        return minePlayer;
    }

    public UserFrame getUserFrame() {
        return userFrame;
    }

    private void selectCompetitor() {
        mineGameBoard.onEnabledMenu();
        MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.GameBoard.selectCompetitorBound(0), userFrame.getPane(), true, MineGameLayer.message.getLayer());
        ComponentCreator.getInstance().setText("Competitor Selection", messagePanel, "FORTE", 30, Color.black,
                GraphicsDefault.GameBoard.selectCompetitorBound(3));
        JComboBox combo = ComponentCreator.getInstance().setStrComboBox(messagePanel,
                competitorList(), 4,
                GraphicsDefault.GameBoard.selectCompetitorBound(2), 25);
        JButton selectButton = ComponentCreator.getInstance().setButton("Select", messagePanel, "buttons1.png",
                GraphicsDefault.GameBoard.selectCompetitorBound(1), Color.white, 30, 0);
        action.selectCompetitorAction(selectButton, combo, gameController);
        messagePanel.setVisible(true);
        messagePanel.setEnabled(true);
    }

    private String[] competitorList() {
        ArrayList<String> list = FileManagement.getInstance().allFileNameInPath(FilesPath.playerDataPath);
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.substring(s.length() - 4).equals(".txt")) {
                String temporary = s.substring(0, s.length() - 4);
                list.set(i, temporary);
            } else {
                list.remove(s);
                i--;
            }
        }
        list.remove(minePlayer.getUserName());
        return list.toArray(new String[0]);
    }

    public void reDrawAllGameBoard() {
        mineGameBoard.cleanGameBoard();
        drawMenuIcon();
        drawEventBar();
        drawManaBar();
        drawTurnIcon();
        drawAroundIcon();
        drawHandDeck();
        drawGroundDeck();
        drawHero();
        drawHeroPower();
        mainPanel.validate();
        mainPanel.repaint();
    }

    public void drawAfterEndTurn(){
        mineGameBoard.cleanLayer(MineGameLayer.handCards.getLayer());
        ManaDrawer.updateAllManaBars(manaBars);
        eventBar.setEventText();
        drawHandDeck();
    }

    public void drawAfterCardSelection(){
        mineGameBoard.cleanLayer(MineGameLayer.handCards.getLayer());
        mineGameBoard.cleanLayer(MineGameLayer.groundCards.getLayer());
        ManaDrawer.updateAllManaBars(manaBars);
        eventBar.setEventText();
        drawHandDeck();
        drawGroundDeck();
    }

    private void drawMenuIcon() {
        JButton menuButton = ComponentCreator.getInstance().setButton("", mainPanel, "Menu Icon.png",
                GraphicsDefault.GameBoard.menuIcon, Color.white, 35, 1);
        action.menuIconClick(menuButton, userFrame);
    }

    private void drawEventBar() {
        eventBar = new EventDrawer(
                FilesPath.graphicsPath.backgroundsPath + "/Event Background.png",
                GraphicsDefault.GameBoard.eventBounds(1), mainPanel,userFrame.getPane(),MineGameLayer.event.getLayer(),
                gameController.getGame());
        eventBar.setEventText();
    }

    private void drawTurnIcon() {
        PanelComponentDrawer turnButton = new PanelComponentDrawer(FilesPath.graphicsPath.backgroundsPath + "/End Turn.jpg",
                GraphicsDefault.GameBoard.turnButton, mainPanel, mainPanel);
        turnButton.selectable();
        action.endTurn(turnButton.getButton());
    }

    private void drawAroundIcon() {
        for (int i = 0; i < 2; i++) {
            PanelComponentDrawer aroundDeckIcon = new PanelComponentDrawer(
                    FilesPath.graphicsPath.backgroundsPath + "/Deck Icon.png",
                    GraphicsDefault.GameBoard.aroundDeckCardBound(i, 0, 0), mainPanel, mainPanel);
            aroundDeckIcon.selectable();
            action.showAroundDeck(aroundDeckIcon.getButton(), i);
        }

    }

    private void drawHero() {
        heroDrawers = new HeroHeroPowerDrawer[2];
        for (int i = 0; i < 2; i++) {
            Hero hero = gameController.getGame().getPlayerGames(i).getHero();
            HeroHeroPowerDrawer heroPaint = new HeroHeroPowerDrawer(hero, FilesPath.graphicsPath.heroPath + "/" + hero.getHeroName() + " Center.png",
                    GraphicsDefault.GameBoard.heroBounds(i, 1), mainPanel, 30, userFrame.getPane(),
                    MineGameLayer.hero.getLayer());
            heroPaint.selectable();
            heroPaint.setHeroHealth();
        }
    }

    private void drawHeroPower() {
        heroPowerDrawers = new HeroHeroPowerDrawer[2];
        for (int i = 0; i < 2; i++) {
            Hero hero = gameController.getGame().getPlayerGames(i).getHero();
            HeroHeroPowerDrawer heroPower = new HeroHeroPowerDrawer(hero, FilesPath.graphicsPath.heroPath + "/" + hero.getHeroName() + " HeroPower2.png",
                    GraphicsDefault.GameBoard.heroPowerBounds(i, 1), mainPanel,
                    25, userFrame.getPane(), MineGameLayer.hero.getLayer());
            heroPower.selectable();
            heroPower.setHeroPowerMana();
            //action.heroPowerSelect(heroPower.getButton());
        }
    }

    private void drawManaBar() {
        manaBars = new ManaDrawer[2];
        for (int i = 0; i < 2; i++) {
            manaBars[i] = new ManaDrawer(FilesPath.graphicsPath.backgroundsPath + "/Mana Bar.png",
                    GraphicsDefault.GameBoard.manaBarBounds(i), mainPanel,userFrame.getPane(),MineGameLayer.mana.getLayer()
            , gameController.getGame().getPlayerGames(i));
            manaBars[i].setManaBarText();
        }
    }

    private void drawHandDeck() {
        for (int i = 0; i < 2; i++) {
            int size = gameController.getGame().getPlayerGames(i).getHandCard().size();
            for (int j = 0; j < size; j++) {
                Card card = gameController.getGame().getPlayerGames(i).getHandCard().get(j);
                CardDrawer cardPaint = new CardDrawer(card,
                        GraphicsDefault.GameBoard.handDeckCard(i, j, size),  mainPanel, 23,
                        userFrame.getPane(), MineGameLayer.handCards.getLayer());
                cardPaint.rightClickMagnifier(i == 1);
                action.handCardSelectAction(cardPaint, card, i);
            }
        }
    }

    private void drawGroundDeck() {
        ArrayList<Card> cards;
        for (int i = 0; i <2 ; i++) {
            cards = gameController.getGame().getPlayerGames(i).getGroundCard();
            for (int j = 0; j < cards.size(); j++) {
                Card card = cards.get(j);
                CardDrawer cardPaint = new CardDrawer(card,GraphicsDefault.GameBoard.groundDeckCard(i,j, cards.size()),
                        mainPanel, 15, userFrame.getPane(), MineGameLayer.groundCards.getLayer());
                cardPaint.rightClickMagnifier(i == 1);
            }
        }

    }

    JPanel getMainPanel() {
        return mainPanel;
    }

    GameController getGameController() {
        return gameController;
    }

    public ClockThread getClockThread() {
        return clockThread;
    }

    public void rePaintClock() {
        clockThread = new ClockThread(this);
    }
}
