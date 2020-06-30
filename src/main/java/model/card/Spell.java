package model.card;

import java.util.ArrayList;

public class Spell extends Card {
    private String quest;
    private String reward;

    public Spell(String name, String cardClass, String type, int mana,
                 int buyCost, int incomeSell, ArrayList<String> mechanics, String description,
                 String rarity, String quest, String reward) {
        super(name, cardClass, type, mana, buyCost, incomeSell, mechanics, description, rarity);
        this.quest = quest;
        this.reward = reward;
    }

    public String getQuest() {
        return quest;
    }

    public String getReward() {
        return reward;
    }
}
