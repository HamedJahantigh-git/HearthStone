
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


    {
        playerCards = new ArrayList<>();
        playerDecks = new ArrayList<>();
        deletePlayer = false;
        money = ModelDefault.PlayerDefaults.defaultMoney;
        playerHeroes = ModelDefault.PlayerDefaults.defaultPlayerHeroes;
        playerCards = ModelDefault.CardDefaults.defaultPlayerCards();
    }


    public Player(String userName, String password, Date registerTime, int id) {
        this.userName = userName;
        this.password = password;
        this.registerTime = registerTime;
        this.id = id;
    }

    public void setMoney(int money) {
        this.money = money;
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


  /* public ArrayList<Hero> getPlayerHeroes() {
       for (int i = 0; i < getPlayerHeroesNew().size(); i++)
           if (currentHero.getHeroName().equals(getPlayerHeroesNew().get(i).getHeroName()))
               playerHeroes.set(i, currentHero);
       return playerHeroes;
   }*/

   /* public ArrayList<Card> getFreePlayerCards() {
        return freePlayerCards;
    }*/

/*
    public void addToFreePlayerCards(Card card) {
        this.freePlayerCards.add(card);
    }

    public void deleteFromFreePlayerCards(Card card) {
        this.freePlayerCards.remove(card);
    }*/
}