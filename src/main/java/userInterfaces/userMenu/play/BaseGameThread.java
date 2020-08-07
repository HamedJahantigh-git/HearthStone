package userInterfaces.userMenu.play;

import controller.FileManagement;
import controller.game.GameController;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.*;
import model.MyThread;
import model.Player;
import model.Quest;
import model.card.Card;
import model.hero.Hero;
import userInterfaces.Sounds;
import userInterfaces.graphicsActions.gameAction.GameAction;
import userInterfaces.myComponent.*;
import userInterfaces.myComponent.gameComponent.*;
import userInterfaces.userMenu.UserFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.TreeMap;

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
    private CardDrawer weaponDrawers[];
    private NavigableMap<Integer, CardDrawer> groundCardDrawers;
    private NavigableMap<Integer, CardDrawer> handCardDrawers;

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
        ComponentCreator.getInstance().setText("Competitor Selection", messagePanel, new MyFont(FontEnum.LABEl.getName(),30), Color.black,
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
        ArrayList<String> list = FileManagement.getInstance().allFileNameInPath(FilesPath.PLAYER_DATA_PATH);
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.endsWith(".txt")) {
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

    private void checkGraphicFinish() {
        try {
            gameController.checkGameFinished();
        } catch (Exception e) {
            drawFinishPage();
        }
    }

    private void drawFinishPage() {
        mineGameBoard.offEnabledMenu();
        clockThread.pausing();
        new Sounds(GameSoundsEnum.finishedGame.getPath()).playOne();
        MyJPanel messagePanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Message1.png",
                GraphicsDefault.GameBoard.menuMessage, userFrame.getPane(), false, MineGameLayer.message.getLayer());
        String text =  "<html><center>Game Finished</center>" +
                "<br><center>Winier #"+(gameController.getGame().getWinnerPlayerIndex()+1)+"</center></html>";
        ComponentCreator.getInstance().setText(text,
                messagePanel, new MyFont(FontEnum.LABEl.getName(),45)
                , Color.black, new Bounds(0, 0, GraphicsDefault.GameBoard.menuMessage.getWidth(), GraphicsDefault.GameBoard.menuMessage.getHeight()));
        JButton finishedButton = ComponentCreator.getInstance().setButton("Menu", messagePanel, "buttons3.png",
                GraphicsDefault.GameBoard.menuButtons(4), Color.white, 25, 1);
        finishedButton.addActionListener(actionEvent2 -> {
            MouseManager.getInstance().defaultCursorImage(userFrame.getUserFrame());
           gameController.addGameEvent(gameController.getGame().getPlayerIndex(), GameEventEnum.finishedGame, "Winner: #"+
                   (gameController.getGame().getWinnerPlayerIndex()+1));
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            mineGameBoard.endGame();
        });
        messagePanel.setVisible(true);
        messagePanel.setEnabled(true);
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
        drawWeapon();
        mainPanel.validate();
        mainPanel.repaint();
    }

    public void drawAfterEndTurn() {
        mineGameBoard.cleanLayer(MineGameLayer.handCards.getLayer());
        mineGameBoard.cleanLayer(MineGameLayer.groundCards.getLayer());
        ManaDrawer.updateAllManaBars(manaBars);
        eventBar.setEventText();
        drawQuestCard();
        drawGroundDeck();
        drawHandDeck();
        checkGraphicFinish();
    }

    public void drawAfterCardSelection() {
        mineGameBoard.cleanLayer(MineGameLayer.handCards.getLayer());
        mineGameBoard.cleanLayer(MineGameLayer.groundCards.getLayer());
        mineGameBoard.cleanLayer(MineGameLayer.weapon.getLayer());
        ManaDrawer.updateAllManaBars(manaBars);
        heroDrawers[0].setHeroHealth();
        heroDrawers[1].setHeroHealth();
        eventBar.setEventText();
        drawQuestCard();
        drawHandDeck();
        drawGroundDeck();
        drawWeapon();
        checkGraphicFinish();
    }

    public void drawAfterAttackAndTarget() {
        ManaDrawer.updateAllManaBars(manaBars);
        mineGameBoard.cleanLayer(MineGameLayer.handCards.getLayer());
        mineGameBoard.cleanLayer(MineGameLayer.groundCards.getLayer());
        mineGameBoard.cleanLayer(MineGameLayer.weapon.getLayer());
        heroDrawers[0].setHeroHealth();
        heroDrawers[1].setHeroHealth();
        eventBar.setEventText();
        drawQuestCard();
        drawWeapon();
        drawHandDeck();
        drawGroundDeck();
        checkGraphicFinish();
    }

    private void drawMenuIcon() {
        JButton menuButton = ComponentCreator.getInstance().setButton("", mainPanel, "Menu Icon.png",
                GraphicsDefault.GameBoard.menuIcon, Color.white, 35, 1);
        action.menuIconClick(menuButton, userFrame);
    }

    private void drawEventBar() {
        eventBar = new EventDrawer(
                FilesPath.graphicsPath.backgroundsPath + "/Event Background.png",
                GraphicsDefault.GameBoard.eventBounds(1), mainPanel, userFrame.getPane(), MineGameLayer.event.getLayer(),
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
            action.heroSelection(heroPaint, hero, i);
            heroDrawers[i] = heroPaint;
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
            action.heroPowerSelect(heroPower.getButton(), hero, i);
        }
    }

    private void drawManaBar() {
        manaBars = new ManaDrawer[2];
        for (int i = 0; i < 2; i++) {
            manaBars[i] = new ManaDrawer(FilesPath.graphicsPath.backgroundsPath + "/Mana Bar.png",
                    GraphicsDefault.GameBoard.manaBarBounds(i), mainPanel, userFrame.getPane(), MineGameLayer.mana.getLayer()
                    , gameController.getGame().getPlayerGames(i));
            manaBars[i].setManaBarText();
        }
    }

    private void drawHandDeck() {
        handCardDrawers = new TreeMap<>();
        ArrayList<Card> cards;
        for (int i = 0; i < 2; i++) {
            cards = gameController.getGame().getPlayerGames(i).getHandCard();
            for (int j = 0; j < cards.size(); j++) {
                Card card = cards.get(j);
                CardDrawer cardPaint = new CardDrawer(card,
                        GraphicsDefault.GameBoard.handDeckCard(i, j, cards.size()), mainPanel, 23,
                        userFrame.getPane(), MineGameLayer.handCards.getLayer());
                cardPaint.rightClickMagnifier(i == 1);
                action.handCardSelectAction(cardPaint, card, i);
                handCardDrawers.put(i, cardPaint);
            }
        }
    }

    private void drawGroundDeck() {
        groundCardDrawers = new TreeMap<>();
        ArrayList<Card> cards;
        for (int i = 0; i < 2; i++) {
            cards = gameController.getGame().getPlayerGames(i).getGroundCard();
            for (int j = 0; j < cards.size(); j++) {
                Card card = cards.get(j);
                CardDrawer cardPaint = new CardDrawer(card, GraphicsDefault.GameBoard.groundDeckCard(i, j, cards.size()),
                        mainPanel, 15, userFrame.getPane(), MineGameLayer.groundCards.getLayer());
                cardPaint.rightClickMagnifier(i == 1);
                action.groundCardSelectAction(cardPaint, card, i);
                groundCardDrawers.put(i, cardPaint);
            }
        }

    }

    private void drawWeapon() {
        weaponDrawers = new CardDrawer[2];
        Card weapon;

        for (int i = 0; i < 2; i++) {
            weapon = gameController.getGame().getPlayerGames(i).getWeapon();
            if (weapon != null) {
                CardDrawer weaponPaint = new CardDrawer(weapon, GraphicsDefault.GameBoard.weaponGroundBounds(i),
                        mainPanel, 17, userFrame.getPane(), MineGameLayer.weapon.getLayer());
                weaponPaint.selectable();
                action.groundWeaponSelectAction(weaponPaint, weapon, i);
                weaponDrawers[i] = weaponPaint;
            }
        }

    }

    private void drawQuestCard() {
        mineGameBoard.cleanLayer(MineGameLayer.quest.getLayer());
        for (int i = 0; i < 2; i++) {
            Quest quest = gameController.getGame().getPlayerGames(i).getQuest();
            if (quest.isOn()) {
                QuestDrawer questPaint = new QuestDrawer(quest, GraphicsDefault.GameBoard.questBounds(i, 0),
                        20, mainPanel, userFrame.getPane(), MineGameLayer.quest.getLayer());
            }
        }
    }

    public JPanel getMainPanel() {
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
