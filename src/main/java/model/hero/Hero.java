package model.hero;

import defaults.ModelDefault;
import model.card.Card;

import java.util.ArrayList;

public class Hero {

    protected String heroName;
    protected int Health;
    private int maxNumberHeroCard;
    private int maxNumberSameHeroCard;
    protected ArrayList<Card> heroCards = new ArrayList<>();

    {
        maxNumberHeroCard = ModelDefault.heroDefaults.maxNumberHeroCard;
        maxNumberSameHeroCard = ModelDefault.heroDefaults.maxNumberSameHeroCard;
    }

    public String getHeroName() {
        return heroName;
    }

    public int getHealth() {
        return Health;
    }

    public int getMaxNumberHeroCard() {
        return maxNumberHeroCard;
    }

    public int getMaxNumberSameHeroCard() {
        return maxNumberSameHeroCard;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public ArrayList<Card> getHeroCards() {
        return heroCards;
    }

    public void addToHeroCards(Card card) {
        this.heroCards.add(card);
    }

    public void deleteHeroCards(Card card) {
        this.heroCards.remove(card);
    }
}
