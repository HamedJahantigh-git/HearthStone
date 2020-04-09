package model.card;

import java.util.ArrayList;

public class Weapon extends Card {
    private int durability;

    public Weapon(String name, String cardClass, String type, int mana,
                  int buyCost, int incomeSell, ArrayList<String> description,
                  String rarity, int durability) {
        super(name, cardClass, type, mana, buyCost, incomeSell, description, rarity);
        this.durability = durability;
    }

    public int getDurability() {
        return durability;
    }
}
