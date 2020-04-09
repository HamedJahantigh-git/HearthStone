package model;

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
    private Hero currentHero;
    private Date registerTime;
    private boolean deletePlayer;
    ArrayList<Hero> playerHeroes = new ArrayList<Hero>();
    ArrayList<Card> freePlayerCards = new ArrayList<>();


    {
        deletePlayer = false;
        money = ModelDefault.PlayerDefaults.defaultMoney;
        currentHero = ModelDefault.PlayerDefaults.defaultHero;
        playerHeroes = ModelDefault.PlayerDefaults.defaultPlayerHeroes;
        freePlayerCards = ModelDefault.CardDefaults.defaultAllGropingCards.defaultFreePlayerCards();
    }

    public Player(String userName, String password, Date registerTime, int id) {
        this.userName = userName;
        this.password = password;
        this.registerTime = registerTime;
        this.id = id;
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

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setDeletePlayer(boolean deletePlayer) {
        this.deletePlayer = deletePlayer;
    }

    public boolean isDeletePlayer() {
        return deletePlayer;
    }

    public int getId() {
        return id;
    }

    public Hero getCurrentHero() {
        return currentHero;
    }

    public ArrayList<Hero> getPlayerHeroesNew(){
        return playerHeroes;
    }

    public ArrayList<Hero> getPlayerHeroes() {
        for (int i = 0; i < getPlayerHeroesNew().size(); i++)
            if (currentHero.getHeroName().equals(getPlayerHeroesNew().get(i).getHeroName()))
                playerHeroes.set(i, currentHero);
        return playerHeroes;
    }

    public ArrayList<Card> getFreePlayerCards() {
        return freePlayerCards;
    }

    public void setCurrentHero(Hero currentHero) {
        this.currentHero = currentHero;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addToFreePlayerCards(Card card) {
        this.freePlayerCards.add(card);
    }

    public void deleteFromFreePlayerCards(Card card) {
        this.freePlayerCards.remove(card);
    }
}
