package model;

import model.card.Card;
import model.hero.Hero;

import java.util.ArrayList;

public class Deck {
    private String name;
    private ArrayList<Card> cards;
    private Hero hero;
    private int minCards, maxCards;

    public Deck(String name, Hero hero) {
        this.name = name;
        this.hero = hero;
        cards = new ArrayList<>();
    }

    public Deck (ArrayList<Card> cards){
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
