package network.protocol;

import model.card.Card;

import java.io.Serializable;
import java.util.ArrayList;

public class DeckProtocol implements Serializable {
    private String name;
    private ArrayList<Card> cards;
    private HeroProtocol hero;
    private int victoryNumber;
    private int gameNumber;

    public DeckProtocol(String name, ArrayList<Card> cards,
                        HeroProtocol hero, int victoryNumber, int gameNumber) {
        this.name = name;
        this.cards = cards;
        this.hero = hero;
        this.victoryNumber = victoryNumber;
        this.gameNumber = gameNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public HeroProtocol getHero() {
        return hero;
    }

    public void setHero(HeroProtocol hero) {
        this.hero = hero;
    }

    public int getVictoryNumber() {
        return victoryNumber;
    }

    public void setVictoryNumber(int victoryNumber) {
        this.victoryNumber = victoryNumber;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }
}
