package model.card;

import java.util.ArrayList;

public class Card {

    private int mana;
    private String name;
    private String rarity;
    private String cardClass;
    private String type;
    private int buyCost;
    private int incomeSell;

    private ArrayList<String> description;

    public Card(String name, String cardClass, String type, int mana,
                int buyCost, int incomeSell, ArrayList<String> description,
                String rarity) {
        this.mana = mana;
        this.name = name;
        this.cardClass = cardClass;
        this.type = type;
        this.buyCost = buyCost;
        this.incomeSell = incomeSell;
        this.description = description;
        this.rarity = rarity;
    }


    public int getMana() {
        return mana;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getCardClass() {
        return cardClass;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public int getBuyCost() {
        return buyCost;
    }

    public int getIncomeSell() {
        return incomeSell;
    }


}
