package userInterfaces.userMenu;

import controller.GameController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.GameLayer;
import model.Player;
import userInterfaces.graphicsActions.GameAction;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.GameComponent;
import userInterfaces.myComponent.MyJPanel;

import javax.swing.*;
import java.awt.*;

public class GameBoard {

    private UserMenu userMenu;
    private MyJPanel mainPanel;
    private GameController gameController;
    private GameAction action;

    public GameBoard(UserMenu userMenu, Player[] players, String battleBackground) {
        this.userMenu = userMenu;
        gameController = new GameController(players);
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/" + battleBackground + ".jpg",
                GraphicsDefault.GameBoard.mainBounds, userMenu.getPane(), false, 40);
        action = new GameAction(userMenu.getPlayerController(), gameController);
        drawGameBoard();
    }

    public UserMenu getUserMenu() {
        return userMenu;
    }

    public GameAction getAction() {
        return action;
    }

    private void drawGameBoard() {
        drawConstantElement();
        drawHandDeck();
        drawManaBar();
        drawHero();
        drawHeroPower();
    }

    public void drawConstantElement() {
        JButton menuButton = ComponentCreator.getInstance().setButton("", mainPanel, "Menu Icon.png",
                GraphicsDefault.GameBoard.menuIcon, Color.white, 35, 1);
        action.menuIconClick(menuButton, this);
        GameComponent aroundDeckIcon = new GameComponent(userMenu.getPane(), GameLayer.base.getLayer(),
                FilesPath.graphicsPath.backgroundsPath + "/Deck Icon.png",
                GraphicsDefault.GameBoard.aroundDeckIcon);
        aroundDeckIcon.moveListener(false);
        action.showAroundDeck(aroundDeckIcon.getButton(), userMenu);
        GameComponent turnButton = new GameComponent(userMenu.getPane(), GameLayer.base.getLayer(), FilesPath.graphicsPath.backgroundsPath + "/End Turn.jpg",
                GraphicsDefault.GameBoard.turnButton);
        turnButton.moveListener(false);
        action.endTurn(turnButton.getButton(), userMenu);
    }

    private void drawHandDeck() {
        int size = gameController.getGame().getPlayerGames(0).getHandCard().size();
        for (int i = 0; i < size; i++) {
            GameComponent handCard = new GameComponent(userMenu.getPane(), GameLayer.handCards.getLayer(), FilesPath.graphicsPath.gmaeCardsPath + "/" +
                    gameController.getGame().getPlayerGames(0).getHandCard().get(i).getName() + ".png",
                    GraphicsDefault.GameBoard.handDeckCard(i, size));
            handCard.moveListener(true);
            if (gameController.getGame().getPlayerGames(0).getHandCard().get(i).getType().equals("Minion")) {
                handCard.setMinionText(gameController.getGame().getPlayerGames(0).getHandCard().get(i));
            }
            if (gameController.getGame().getPlayerGames(0).getHandCard().get(i).getType().equals("Spell")) {
                handCard.setSpellText(gameController.getGame().getPlayerGames(0).getHandCard().get(i));
            }
            if (gameController.getGame().getPlayerGames(0).getHandCard().get(i).getType().equals("Weapon")) {
                handCard.setWeaponText(gameController.getGame().getPlayerGames(0).getHandCard().get(i));
            }
        }

    }

    private void drawEventBar() {

    }

    private void drawManaBar() {
        GameComponent manaBar = new GameComponent(userMenu.getPane(), GameLayer.base.getLayer(),
                FilesPath.graphicsPath.backgroundsPath + "/Mana Bar.png",
                GraphicsDefault.GameBoard.manaBar);
        manaBar.setManaText(gameController.getGame().getPlayerGames(0).getCurrentMana(),
                gameController.getGame().getPlayerGames(0).getRandMana());
    }

    private void drawHero() {
        GameComponent hero = new GameComponent(userMenu.getPane(), GameLayer.hero.getLayer(),
                FilesPath.graphicsPath.heroPath + "/" + gameController.getGame().getPlayerGames(0).getHero().getHeroName() + " Center.png",
                GraphicsDefault.GameBoard.heroBounds);
    }

    private void drawHeroPower() {

        GameComponent heroPower = new GameComponent(userMenu.getPane(), GameLayer.hero.getLayer() + 1,
                FilesPath.graphicsPath.heroPath + "/" + gameController.getGame().getPlayerGames(0).getHero().getHeroName() + " HeroPower2.png",
                GraphicsDefault.GameBoard.heroPowerBounds);
        heroPower.moveListener(false);
    }

    public void offEnabledMenu() {
        for (int i = 41; i < 49; i++) {
            for (Component component : userMenu.getPane().getComponentsInLayer(i)) {
                component.setEnabled(false);
                component.setVisible(false);
            }
        }

    }

    public void onEnabledMenu() {
        userMenu.getPane().remove(userMenu.getPane().getComponentsInLayer(GameLayer.message.getLayer())[0]);
        for (int i = 41; i < 49; i++) {
            for (Component component : userMenu.getPane().getComponentsInLayer(i)) {
                component.setEnabled(true);
                component.setVisible(true);
            }
        }
        mainPanel.paint(mainPanel.getGraphics());
    }

}
