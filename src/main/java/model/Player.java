
package model;


import controller.FileManagement;
import defaults.FilesPath;
import defaults.ModelDefault;
import model.card.Card;
import model.hero.Hero;

import java.util.ArrayList;
import java.util.Date;

public class Player {
    private String userName;
    private String password;
    private int id;
    private int money;
    private Date registerTime;
    private boolean deletePlayer;
    private ArrayList<Hero> playerHeroes;
    private ArrayList<Card> playerCards;
    private ArrayList<Deck> playerDecks;
    private Deck currentDeck;
    private Deck freeDeck;


    {
        playerCards = new ArrayList<>();
        playerDecks = new ArrayList<>();
        deletePlayer = false;
        currentDeck = null;
        money = ModelDefault.PlayerDefaults.defaultMoney;
        playerHeroes = ModelDefault.PlayerDefaults.defaultPlayerHeroes;
        playerCards = ModelDefault.CardDefaults.defaultPlayerCards();
        freeDeck = new Deck(playerCards);
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

    public int getId() {
        return id;
    }

    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }
}