package userInterfaces.userMenu.play;

import controller.FileManagement;
import controller.gameController.GameController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.MineGameLayer;
import enums.InfoPassiveEnum;
import model.InfoPassive;
import model.Player;
import userInterfaces.Sounds;
import userInterfaces.graphicsActions.GameAction;
import userInterfaces.myComponent.ComponentCreator;
import userInterfaces.myComponent.GameComponent;
import userInterfaces.myComponent.MyJPanel;
import userInterfaces.userMenu.UserFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BaseGameThread extends Thread {

    private UserFrame userFrame;
    private MyJPanel mainPanel;
    private Player minePlayer;
    private GameController gameController;
    private PlayerGraphicThread playerGraphicThread;
    private GameAction action;
    private Sounds mainGameSounds;

    public BaseGameThread(UserFrame userFrame, Player minePlayer, String battleBackground, PlayerGraphicThread playerGraphicThread) {
        this.userFrame = userFrame;
        gameController = new GameController();
        mainGameSounds = new Sounds("GameMenu.wav");
        mainGameSounds.playLoop();
        this.minePlayer = minePlayer;
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/" + battleBackground + ".jpg",
                GraphicsDefault.GameBoard.mainBounds, userFrame.getPane(), false, MineGameLayer.mainPanel.getLayer());
        this.playerGraphicThread = playerGraphicThread;
        playerGraphicThread.setGraphicThread(gameController, this);
        action = new GameAction(this, playerGraphicThread);
        start();
    }

    @Override
    public void run() {
        super.run();
    }

    public Sounds getMainGameSounds() {
        return mainGameSounds;
    }

    public Player getMinePlayer() {
        return minePlayer;
    }

    public UserFrame getUserFrame() {
        return userFrame;
    }

    public GameAction getAction() {
        return action;
    }

    public void selectCompetitor() {
        System.out.println("start select competitor");
        offEnabledMenu();
        MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.GameBoard.selectCompetitorBound(0), userFrame.getPane(), false, MineGameLayer.message.getLayer());
        ComponentCreator.getInstance().setText("Competitor Selection", messagePanel, "FORTE", 30, Color.black,
                GraphicsDefault.GameBoard.selectCompetitorBound(3));
        JComboBox combo = ComponentCreator.getInstance().setStrComboBox(messagePanel,
                competitorList(), 4,
                GraphicsDefault.GameBoard.selectCompetitorBound(2), 25);
        JButton selectButton = ComponentCreator.getInstance().setButton("Select", messagePanel, "buttons1.png",
                GraphicsDefault.GameBoard.selectCompetitorBound(1), Color.white, 30, 0);
        action.selectCompetitorAction(selectButton, combo.getSelectedItem().toString(), gameController);
        messagePanel.setVisible(true);
        messagePanel.setEnabled(true);
        startGame();
    }

    private void startGame() {
        playerGraphicThread.handleInfoPassive();
        //graphicThread.handleDrawGameBoard();
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

    void drawInfoPassiveMessage() {
        offEnabledMenu();
        MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.GameBoard.infoPassivePanelBounds, userFrame.getPane(), false, MineGameLayer.message.getLayer());
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
        action.menuIconClick(menuButton, userFrame);
        GameComponent aroundDeckIcon = new GameComponent(userFrame.getPane(), MineGameLayer.base.getLayer(),
                FilesPath.graphicsPath.backgroundsPath + "/Deck Icon.png",
                GraphicsDefault.GameBoard.aroundDeckIcon);
        aroundDeckIcon.moveListener(false);
        action.showAroundDeck(aroundDeckIcon.getButton());
        GameComponent turnButton = new GameComponent(userFrame.getPane(), MineGameLayer.base.getLayer(), FilesPath.graphicsPath.backgroundsPath + "/End Turn.jpg",
                GraphicsDefault.GameBoard.turnButton);
        turnButton.moveListener(false);
        action.endTurn(turnButton.getButton());
    }

    private void drawGroundDeck() {
        int size = gameController.getGame().getPlayerGames(0).getGroundCard().size();
        for (int i = 0; i < size; i++) {
            GameComponent groundCard = new GameComponent(userFrame.getPane(), MineGameLayer.groundCards.getLayer(),
                    FilesPath.graphicsPath.gmaeCardsPath + "/" +
                            gameController.getGame().getPlayerGames(0).getGroundCard().get(i).getName() + ".png",
                    GraphicsDefault.GameBoard.groundDeckCard(i, size));
            groundCard.moveListener(false);
        }
    }

    private void drawHandDeck() {
        int size = gameController.getGame().getPlayerGames(0).getHandCard().size();
        for (int i = 0; i < size; i++) {
            GameComponent handCard = new GameComponent(userFrame.getPane(), MineGameLayer.handCards.getLayer(), FilesPath.graphicsPath.gmaeCardsPath + "/" +
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
        GameComponent eventBar = new GameComponent(userFrame.getPane(), MineGameLayer.event.getLayer(),
                FilesPath.graphicsPath.backgroundsPath + "/Event Background.png",
                GraphicsDefault.GameBoard.eventBounds);
        eventBar.setEventText(gameController.getGame().getEvent());
    }

    private void drawManaBar() {
        GameComponent manaBar = new GameComponent(userFrame.getPane(), MineGameLayer.base.getLayer(),
                FilesPath.graphicsPath.backgroundsPath + "/Mana Bar.png",
                GraphicsDefault.GameBoard.manaBar);
        manaBar.setManaText(gameController.getGame().getPlayerGames(0).getCurrentMana(),
                gameController.getGame().getPlayerGames(0).getRandMana());
    }

    private void drawHero() {
        GameComponent hero = new GameComponent(userFrame.getPane(), MineGameLayer.hero.getLayer(),
                FilesPath.graphicsPath.heroPath + "/" + gameController.getGame().getPlayerGames(0).getHero().getHeroName() + " Center.png",
                GraphicsDefault.GameBoard.heroBounds);
    }

    private void drawHeroPower() {
        GameComponent heroPower = new GameComponent(userFrame.getPane(), MineGameLayer.hero.getLayer() + 1,
                FilesPath.graphicsPath.heroPath + "/" + gameController.getGame().getPlayerGames(0).getHero().getHeroName() + " HeroPower2.png",
                GraphicsDefault.GameBoard.heroPowerBounds);
        heroPower.moveListener(false);
        action.heroPowerSelect(heroPower.getButton());
    }

    public void offEnabledMenu() {
        for (int i = 41; i < 49; i++) {
            for (Component component : userFrame.getPane().getComponentsInLayer(i)) {
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
        for (Component c : userFrame.getPane().getComponentsInLayer(MineGameLayer.message.getLayer())) {
            userFrame.getPane().remove(c);
        }
        for (Component c : userFrame.getPane().getComponentsInLayer(MineGameLayer.messageContent.getLayer())) {
            userFrame.getPane().remove(c);
        }
        for (int i = 41; i < 49; i++) {
            for (Component component : userFrame.getPane().getComponentsInLayer(i)) {
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
            for (Component component : userFrame.getPane().getComponentsInLayer(i)) {
                userFrame.getPane().remove(component);
            }
        }
        for (Component component : mainPanel.getComponents()) {
            mainPanel.remove(component);
        }
    }

}
