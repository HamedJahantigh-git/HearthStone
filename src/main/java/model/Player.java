
package model;


import defaults.ModelDefault;
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
    private ArrayList<Deck> playerDecks;
    private Deck gameDeck;
    private Deck freeDeck;


    {
        playerDecks = new ArrayList<>();
        deletePlayer = false;
        money = ModelDefault.PlayerDefaults.defaultMoney;
        playerHeroes = ModelDefault.PlayerDefaults.defaultPlayerHeroes;
        freeDeck = new Deck(ModelDefault.CardDefaults.defaultPlayerCards());
        gameDeck = freeDeck;
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
}