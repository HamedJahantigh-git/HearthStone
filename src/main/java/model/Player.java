
package model;


import com.google.gson.Gson;
import controller.FileManagement;
import defaults.ModelDefault;
import enums.InfoPassiveEnum;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;
import model.card.Weapon;
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
    private int gameDeckIndex;
    private Deck freeDeck;
    private PlayerGame playerGame;


    {
        playerDecks = new ArrayList<>();
        deletePlayer = false;
        money = ModelDefault.PlayerDefaults.defaultMoney;
        playerHeroes = ModelDefault.PlayerDefaults.defaultPlayerHeroes;
        freeDeck = new Deck(ModelDefault.CardDefaults.defaultPlayerCards());
        gameDeckIndex = -1;
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
        playerGame.startPlayerGame(getGameDeck());
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

    public void setGameDeck(Deck deck) {
        if(deck==freeDeck)
            gameDeckIndex = -1;
        else{
            for (int i = 0; i <playerDecks.size() ; i++) {
                if(playerDecks.get(i)==deck){
                    gameDeckIndex = i;
                    return;
                }
            }
        }
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
        if (gameDeckIndex == -1)
            return freeDeck;
        else
            return playerDecks.get(gameDeckIndex);
    }

    public PlayerGame getPlayerGame() {
        return playerGame;
    }

    public class PlayerGame {
        private ArrayList<Card> groundCard;
        private ArrayList<Card> usedCard;
        private ArrayList<Card> handCard;
        private ArrayList<Card> aroundCard;
        private Weapon weapon;
        private int randMana, currentMana;
        private Hero hero;
        private InfoPassive infoPassive;
        private Map<Integer, Boolean> selectedStartCard;

        private Minion forceAttackCard;
        private Minion setEqualCard;
        private Minion gainDrawCard;
        private Quest quest;

        private int heroPowerUsedInTurn;
        private int numberDrawCardInTurn;

        public PlayerGame() {
            this.groundCard = new ArrayList<>();
            this.usedCard = new ArrayList<>();
            this.handCard = new ArrayList<>();
            this.aroundCard = new ArrayList<>();
            quest = new Quest();
            this.randMana = 1;
            numberDrawCardInTurn = 1;
            this.currentMana = 1;
            heroPowerUsedInTurn = 0;
            this.setEqualCard = null;
            this.forceAttackCard = null;
            this.weapon = null;
            this.gainDrawCard = null;
            newSelectedStartCard();
        }

        public void startPlayerGame(Deck deck) {
            for (Card card:deck.getCards()) {
                aroundCard.add(FileManagement.getInstance().getCopy().copyCard(card));
            }
            hero = FileManagement.getInstance().getCopy().copyHero(deck.getHero());
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

        public int getHeroPowerUsedInTurn() {
            return heroPowerUsedInTurn;
        }

        public void setHeroPowerUsedInTurn(int heroPowerUsedInTurn) {
            this.heroPowerUsedInTurn = heroPowerUsedInTurn;
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

        public Weapon getWeapon() {
            return weapon;
        }

        public void setWeapon(Weapon weapon) {
            this.weapon = weapon;
        }

        public Minion getForceAttackCard() {
            return forceAttackCard;
        }

        public void setForceAttackCard(Minion forceAttackCard) {
            this.forceAttackCard = forceAttackCard;
        }

        public Minion getSetEqualCard() {
            return setEqualCard;
        }

        public void setSetEqualCard(Minion setEqualCard) {
            this.setEqualCard = setEqualCard;
        }

        public Minion getGainDrawCard() {
            return gainDrawCard;
        }

        public void setGainDrawCard(Minion gainDrawCard) {
            this.gainDrawCard = gainDrawCard;
        }

        public int getNumberDrawCardInTurn() {
            return numberDrawCardInTurn;
        }

        public void setNumberDrawCardInTurn(int numberDrawCardInTurn) {
            this.numberDrawCardInTurn = numberDrawCardInTurn;
        }

        public Quest getQuest() {
            return quest;
        }
    }
}