package model;

import model.card.Card;
import model.hero.Hero;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<>();
    private Hero hero;
    private int minCards, maxCards;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Hero getHero() {
        return hero;
    }

    public int getMinCards() {
        return minCards;
    }

    public int getMaxCards() {
        return maxCards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setMinCards(int minCards) {
        this.minCards = minCards;
    }

    public void setMaxCards(int maxCards) {
        this.maxCards = maxCards;
    }
}
