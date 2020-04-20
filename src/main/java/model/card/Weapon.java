package model.card;

import java.util.ArrayList;

public class Weapon extends Card {
    private int durability;
    private int attack;

    public Weapon(String name, String cardClass, String type, int mana,
                  int buyCost, int incomeSell, ArrayList<String> mechanics, String description,
                  String rarity, int durability, int attack) {
        super(name, cardClass, type, mana, buyCost, incomeSell, mechanics, description, rarity);
        this.durability = durability;
        this.attack = attack;
    }

}
