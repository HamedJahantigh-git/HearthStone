
package model;


import com.google.gson.annotations.Expose;
import defaults.ModelDefault;
import enums.InfoPassiveEnum;
import model.card.Card;
import model.hero.Hero;
import model.infoPassive.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private String userName;
    private String password;
    private int id;
    private int money;
    private Date registerTime;
    private boolean deletePlayer;
    private ArrayList<Hero> playerHeroes;
    private ArrayList<Deck> playerDecks;
    private Deck gameDeck;
    private Deck freeDeck;
    private PlayerGame playerGame;


    {
        playerDecks = new ArrayList<>();
        deletePlayer = false;
        money = ModelDefault.PlayerDefaults.defaultMoney;
        playerHeroes = ModelDefault.PlayerDefaults.defaultPlayerHeroes;
        freeDeck = new Deck(ModelDefault.CardDefaults.defaultPlayerCards());
        gameDeck = freeDeck;
        newPlayerGame();
    }


    public Deck getFreeDeck() {
        return freeDeck;
    }

    public Player(String userName, String password, Date registerTime, int id) {
        this.userName = userName;
        this.password = password;
        this.registerTime = registerTime;
        this.id = id;
    }

    public void changeMoney(int differ) {
        this.money += differ;
    }

    public void newPlayerGame() {
        playerGame = new PlayerGame();
        playerGame.startPlayerGame(gameDeck);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getMoney() {
        return money;
    }

    public void setDeletePlayer(boolean deletePlayer) {
        this.deletePlayer = deletePlayer;
    }

    public void setGameDeck(Deck gameDeck) {
        this.gameDeck = gameDeck;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Hero> getPlayerHeroes() {
        return playerHeroes;
    }

    public ArrayList<Deck> getPlayerDecks() {
        return playerDecks;
    }

    public Deck getGameDeck() {
        return gameDeck;
    }

    public PlayerGame getPlayerGame() {
        return playerGame;
    }


    public class PlayerGame {
        private ArrayList<Card> groundCard;
        private ArrayList<Card> usedCard;
        private ArrayList<Card> handCard;
        private ArrayList<Card> aroundCard;
        private int randMana, currentMana;
        private Hero hero;
        private InfoPassive infoPassive;
        private Map<Integer, Boolean> selectedStartCard;

        public PlayerGame() {
            this.groundCard = new ArrayList<>();
            this.usedCard = new ArrayList<>();
            this.handCard = new ArrayList<>();
            this.aroundCard = new ArrayList<>();
            this.randMana = 1;
            this.currentMana = 1;
            newSelectedStartCard();
        }

        public void startPlayerGame(Deck deck) {
            aroundCard.addAll(deck.getCards());
            this.hero = deck.getHero();
        }

        public void newSelectedStartCard() {
            selectedStartCard = new HashMap<>();
            for (int i = 0; i < ModelDefault.gameDefaults.MAX_START_PLAYER_CARDS; i++)
                selectedStartCard.put(i, false);

        }

        public void setTrueSelectedStartCard(int key) {
            selectedStartCard.replace(key, true);
        }

        public void setInfoPassive(InfoPassiveEnum infoPassiveType, Player player) {
            switch (infoPassiveType) {
                case nurse:
                    this.infoPassive = new Nurse();
                    break;
                case manaJump:
                    this.infoPassive = new ManaJump();
                    break;
                case offCards:
                    this.infoPassive = new OffCards();
                    break;
                case freePower:
                    this.infoPassive = new FreePower();
                    break;
                case twiceDraw:
                    this.infoPassive = new TwiceDraw();
                    break;
            }
        }

        public void setCurrentMana(int currentMana) {
            this.currentMana = currentMana;
        }

        public void setRandMana(int randMana) {
            this.randMana = randMana;
        }

        public Map<Integer, Boolean> getSelectedStartCard() {
            return selectedStartCard;
        }

        public int getCurrentMana() {
            return currentMana;
        }

        public Hero getHero() {
            return hero;
        }

        public InfoPassive getInfoPassive() {
            return infoPassive;
        }

        public ArrayList<Card> getGroundCard() {
            return groundCard;
        }

        public ArrayList<Card> getUsedCard() {
            return usedCard;
        }

        public ArrayList<Card> getHandCard() {
            return handCard;
        }

        public ArrayList<Card> getAroundCard() {
            return aroundCard;
        }

        public int getRandMana() {
            return randMana;
        }
    }
}