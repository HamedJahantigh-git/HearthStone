package userInterfaces.userMenu;

import controller.GameController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.GameLayer;
import enums.InfoPassiveEnum;
import model.InfoPassive;
import model.Player;
import userInterfaces.Sounds;
import userInterfaces.graphicsActions.GameAction;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.GameComponent;
import userInterfaces.myComponent.MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameBoard {

    private UserMenu userMenu;
    private MyJPanel mainPanel;
    private GameController gameController;
    private GameAction action;
    private Sounds mainGameSounds;

    public GameBoard(UserMenu userMenu, Player[] players, String battleBackground) {
        this.userMenu = userMenu;
        mainGameSounds = new Sounds("GameMenu.wav");
        mainGameSounds.playLoop();
        gameController = new GameController(players);
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/" + battleBackground + ".jpg",
                GraphicsDefault.GameBoard.mainBounds, userMenu.getPane(), false, GameLayer.mainPanel.getLayer());
        action = new GameAction(userMenu.getPlayerController(), gameController, this);
        drawGameBoard();
    }

    public Sounds getMainGameSounds() {
        return mainGameSounds;
    }

    public UserMenu getUserMenu() {
        return userMenu;
    }

    public GameAction getAction() {
        return action;
    }

    public void drawGameBoard() {
        cleanGameBoard();
        drawConstantElement();
        drawEventBar();
        drawHandDeck();
        drawGroundDeck();
        drawManaBar();
        drawHero();
        drawHeroPower();
    }

    public void drawInfoPassiveMessage() {
        offEnabledMenu();
        MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.GameBoard.infoPassivePanelBounds, userMenu.getPane(), false, GameLayer.message.getLayer());
        ComponentCreator.getInstance().setText("Info Passive", messagePanel, "FORTE", 40, Color.black,
                GraphicsDefault.GameBoard.infoPassiveBounds(2, 1));
        ArrayList<InfoPassiveEnum> randomInfo = InfoPassive.creatRandomInfoPassive(3);
        for (int i = 0; i < randomInfo.size(); i++) {
            JButton info = ComponentCreator.getInstance().setButton(randomInfo.get(i).getTitle(), messagePanel,
                    "Info Passive Button.png",
                    GraphicsDefault.GameBoard.infoPassiveBounds(1, i + 1), Color.white, 35, 1);
            action.infoButtonAction(info, randomInfo.get(i));
        }
        messagePanel.setVisible(true);
        messagePanel.setEnabled(true);
    }

    private void drawConstantElement() {
        JButton menuButton = ComponentCreator.getInstance().setButton("", mainPanel, "Menu Icon.png",
                GraphicsDefault.GameBoard.menuIcon, Color.white, 35, 1);
        action.menuIconClick(menuButton, userMenu);
        GameComponent aroundDeckIcon = new GameComponent(userMenu.getPane(), GameLayer.base.getLayer(),
                FilesPath.graphicsPath.backgroundsPath + "/Deck Icon.png",
                GraphicsDefault.GameBoard.aroundDeckIcon);
        aroundDeckIcon.moveListener(false);
        action.showAroundDeck(aroundDeckIcon.getButton());
        GameComponent turnButton = new GameComponent(userMenu.getPane(), GameLayer.base.getLayer(), FilesPath.graphicsPath.backgroundsPath + "/End Turn.jpg",
                GraphicsDefault.GameBoard.turnButton);
        turnButton.moveListener(false);
        action.endTurn(turnButton.getButton());
    }

    private void drawGroundDeck() {
        int size = gameController.getGame().getPlayerGames(0).getGroundCard().size();
        for (int i = 0; i < size; i++) {
            GameComponent groundCard = new GameComponent(userMenu.getPane(), GameLayer.groundCards.getLayer(),
                    FilesPath.graphicsPath.gmaeCardsPath + "/" +
                            gameController.getGame().getPlayerGames(0).getGroundCard().get(i).getName() + ".png",
                    GraphicsDefault.GameBoard.groundDeckCard(i, size));
            groundCard.moveListener(false);
        }
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
                action.minionCardSelect(handCard.getButton(), gameController.getGame().getPlayerGames(0).getHandCard().get(i));
            }
            if (gameController.getGame().getPlayerGames(0).getHandCard().get(i).getType().equals("Spell")) {
                handCard.setSpellText(gameController.getGame().getPlayerGames(0).getHandCard().get(i));
                action.spellCardSelect(handCard.getButton(), gameController.getGame().getPlayerGames(0).getHandCard().get(i));
            }
            if (gameController.getGame().getPlayerGames(0).getHandCard().get(i).getType().equals("Weapon")) {
                handCard.setWeaponText(gameController.getGame().getPlayerGames(0).getHandCard().get(i));
                action.weaponCardSelect(handCard.getButton(), gameController.getGame().getPlayerGames(0).getHandCard().get(i));
            }
        }

    }

    private void drawEventBar() {
        GameComponent eventBar = new GameComponent(userMenu.getPane(), GameLayer.event.getLayer(),
                FilesPath.graphicsPath.backgroundsPath + "/Event Background.png",
                GraphicsDefault.GameBoard.eventBounds);
        eventBar.setEventText(gameController.getGame().getEvent());
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
        action.heroPowerSelect(heroPower.getButton());
    }

    public void offEnabledMenu() {
        for (int i = 41; i < 49; i++) {
            for (Component component : userMenu.getPane().getComponentsInLayer(i)) {
                component.setEnabled(false);
                component.setVisible(false);
            }
        }
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(false);
            component.setVisible(false);
        }

    }

    public void onEnabledMenu() {
        for (Component c : userMenu.getPane().getComponentsInLayer(GameLayer.message.getLayer())) {
            userMenu.getPane().remove(c);
        }
        for (Component c : userMenu.getPane().getComponentsInLayer(GameLayer.messageContent.getLayer())) {
            userMenu.getPane().remove(c);
        }
        for (int i = 41; i < 49; i++) {
            for (Component component : userMenu.getPane().getComponentsInLayer(i)) {
                component.setEnabled(true);
                component.setVisible(true);
            }
        }
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(true);
            component.setVisible(true);
        }
        mainPanel.paint(mainPanel.getGraphics());
    }

    public void cleanGameBoard() {
        for (int i = 41; i < 49; i++) {
            for (Component component : userMenu.getPane().getComponentsInLayer(i)) {
                userMenu.getPane().remove(component);
            }
        }
        for (Component component : mainPanel.getComponents()) {
            mainPanel.remove(component);
        }
    }

}
