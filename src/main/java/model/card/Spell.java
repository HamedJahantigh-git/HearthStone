package model.card;

import java.util.ArrayList;

public class Spell extends Card {
    public Spell(String name, String cardClass, String type, int mana,
                 int buyCost, int incomeSell, ArrayList<String> description,
                 String rarity) {
        super(name, cardClass, type, mana, buyCost, incomeSell, description, rarity);
    }

    //todo
    //Quest and reward


}
