package model.card;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.hero.*;

import java.util.ArrayList;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Minion.class, name = "Minion"),
        @JsonSubTypes.Type(value = Spell.class, name = "Spell"),
        @JsonSubTypes.Type(value = Weapon.class, name = "Weapon"),
})

public class Card {

    private int mana;
    private String name;
    private String rarity;
    private String cardClass;
    private String type;
    private int buyCost;
    private int incomeSell;
    private ArrayList<String> mechanics;
    private String description;
    private int number;
    private int numberUsage;

    public Card() {
    }

    public Card(String name, String cardClass, String type, int mana,
                int buyCost, int incomeSell, ArrayList<String> mechanics, String description,
                String rarity) {
        this.mana = mana;
        this.name = name;
        this.cardClass = cardClass;
        this.type = type;
        this.buyCost = buyCost;
        this.incomeSell = incomeSell;
        this.mechanics = mechanics;
        this.description = description;
        this.rarity = rarity;
        this.number = 0;
        this.numberUsage = 0;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
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

    public ArrayList<String> getMechanics() {
        return mechanics;
    }

    public String getDescription() {
        return description;
    }

    public int getBuyCost() {
        return buyCost;
    }

    public int getIncomeSell() {
        return incomeSell;
    }

    public int getNumberUsage() {
        return numberUsage;
    }

    public void reduceMana(int differ) {
        this.mana -= differ;
    }

    public int getRarityLevel() {
        if (rarity.equals("Common"))
            return 1;
        if (rarity.equals("Rare"))
            return 2;
        if (rarity.equals("Epic"))
            return 3;
        if (rarity.equals("Legendary"))
            return 4;
        return 0;
    }
}
