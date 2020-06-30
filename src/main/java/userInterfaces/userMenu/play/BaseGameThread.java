package userInterfaces.userMenu.play;

import controller.FileManagement;
import controller.gameController.GameController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.CardType;
import enums.MineGameLayer;
import model.Player;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;
import model.card.Weapon;
import model.hero.Hero;
import userInterfaces.graphicsActions.GameAction;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.myComponent.gameComponent.CardDrawer;
import userInterfaces.myComponent.gameComponent.HeroHeroPowerDrawer;
import userInterfaces.myComponent.gameComponent.PanelComponentDrawer;
import userInterfaces.userMenu.UserFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BaseGameThread extends Thread {

    private UserFrame userFrame;
    private JPanel mainPanel;

    private MineGameBoard mineGameBoard;

    private Player minePlayer;
    private GameController gameController;
    private GameAction action;

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
        drawGameBoard();
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
        return list.toArray(new String[0]);
    }

    public void drawGameBoard() {
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

    private void drawMenuIcon() {
        JButton menuButton = ComponentCreator.getInstance().setButton("", mainPanel, "Menu Icon.png",
                GraphicsDefault.GameBoard.menuIcon, Color.white, 35, 1);
        action.menuIconClick(menuButton, userFrame);
    }

    private void drawEventBar() {
        PanelComponentDrawer eventBar = new PanelComponentDrawer(
                FilesPath.graphicsPath.backgroundsPath + "/Event Background.png",
                GraphicsDefault.GameBoard.eventBounds(1), mainPanel, mainPanel);
        eventBar.setEventText(gameController.getGame().getEvent());
    }

    private void drawTurnIcon() {
        PanelComponentDrawer turnButton = new PanelComponentDrawer(FilesPath.graphicsPath.backgroundsPath + "/End Turn.jpg",
                GraphicsDefault.GameBoard.turnButton, mainPanel, mainPanel);
        turnButton.selectable();
        // action.endTurn(turnButton.getButton());
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

    private void drawHandDeck() {
        for (int i = 0; i < 2; i++) {
            int size = gameController.getGame().getPlayerGames(i).getHandCard().size();
            for (int j = 0; j < size; j++) {
                Card card = gameController.getGame().getPlayerGames(i).getHandCard().get(j);
                CardDrawer cardPaint = new CardDrawer(card,
                        GraphicsDefault.GameBoard.handDeckCard(i, j, size), mainPanel, mainPanel, 23,
                        userFrame.getPane(), MineGameLayer.handCards.getLayer());
                cardPaint.crossMagnifier(i == 1);
            }
        }
    }

    private void drawHero() {
        for (int i = 0; i < 2; i++) {
            Hero hero = gameController.getGame().getPlayerGames(i).getHero();
            HeroHeroPowerDrawer heroPaint = new HeroHeroPowerDrawer(hero, FilesPath.graphicsPath.heroPath + "/" + hero.getHeroName() + " Center.png",
                    GraphicsDefault.GameBoard.heroBounds(i, 1), mainPanel, mainPanel, 30, userFrame.getPane(),
                    MineGameLayer.hero.getLayer());
            heroPaint.selectable();
            heroPaint.setHeroHealth();
        }
    }

    private void drawHeroPower() {
        for (int i = 0; i < 2; i++) {
            Hero hero = gameController.getGame().getPlayerGames(i).getHero();
            HeroHeroPowerDrawer heroPower = new HeroHeroPowerDrawer(hero, FilesPath.graphicsPath.heroPath + "/" + hero.getHeroName() + " HeroPower2.png",
                    GraphicsDefault.GameBoard.heroPowerBounds(i, 1), mainPanel, mainPanel,
                    25, userFrame.getPane(), MineGameLayer.hero.getLayer());
            heroPower.selectable();
            heroPower.setHeroPowerMana();
            //action.heroPowerSelect(heroPower.getButton());
        }
    }

    private void drawManaBar() {
        for (int i = 0; i < 2; i++) {
            PanelComponentDrawer manaBar = new PanelComponentDrawer(FilesPath.graphicsPath.backgroundsPath + "/Mana Bar.png",
                    GraphicsDefault.GameBoard.manaBarBounds(i), mainPanel, mainPanel);
            Player.PlayerGame playerGame = gameController.getGame().getPlayerGames(i);
            manaBar.setManaBarText(playerGame.getCurrentMana(), playerGame.getRandMana());
        }
    }

    private void drawGroundDeck() {
        /*int size = gameController.getGame().getPlayerGames(0).getGroundCard().size();
        for (int i = 0; i < size; i++) {
            GameComponent groundCard = new GameComponent(userFrame.getPane(), MineGameLayer.groundCards.getLayer(),
                    FilesPath.graphicsPath.gmaeCardsPath + "/" +
                            gameController.getGame().getPlayerGames(0).getGroundCard().get(i).getName() + ".png",
                    GraphicsDefault.GameBoard.groundDeckCard(i, size));
            groundCard.moveListener(false);
        }*/
    }

}
