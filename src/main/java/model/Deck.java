package model;

import model.card.Card;
import model.hero.Hero;

import java.util.ArrayList;

public class Deck {
    private String name;
    private ArrayList<Card> cards;
    private Hero hero;
    private int victoryNumber;
    private int gameNumber;
    public Deck(String name, Hero hero) {
        this.name = name;
        this.hero = hero;
        cards = new ArrayList<>();
        victoryNumber = 0;
        gameNumber = 0;
    }

    public Deck (ArrayList<Card> cards){
        //use for create free deck player
        this.name = "No Selected";
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public Hero getHero() {
        return hero;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void addCard (Card card){
        this.cards.add(card);
    }
}
