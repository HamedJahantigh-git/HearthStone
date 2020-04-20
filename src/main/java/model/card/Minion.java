package model.card;

import java.util.ArrayList;

public class Minion extends Card {

    private int health;
    private int attack;

    public Minion(String name, String cardClass, String type, int mana,
                  int buyCost, int incomeSell, ArrayList<String> mechanics, String description,
                  String rarity, int health, int attack) {
        super(name, cardClass, type, mana, buyCost, incomeSell, mechanics, description, rarity);
        this.health = health;
        this.attack = attack;
    }


    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }
}
