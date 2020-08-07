package model.card;

import enums.ExceptionsEnum;
import model.Attacker;
import model.hero.Hero;

import java.io.Serializable;
import java.util.ArrayList;

public class Minion extends Card implements Attacker, Serializable {

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

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void plusAttack(int number) {
        this.attack += number;
    }

    public void minusHealth(int reduce) {
        this.health -= reduce;
    }

    @Override
    public void toCardAttack(Card attacked, Hero attackerHero) throws Exception {
        health -= ((Minion) attacked).getAttack();
        ((Minion) attacked).minusHealth(attack);
        if (health <= 0 && ((Minion) attacked).getHealth() <= 0)
            throw new Exception(ExceptionsEnum.allDead.getMessage());
        if (health <= 0) {
            if (getDescription().contains("Reborn"))
                throw new Exception(ExceptionsEnum.rebornAttacker.getMessage());
            else
                throw new Exception(ExceptionsEnum.attackerDead.getMessage());
        }
        if (((Minion) attacked).getHealth() <= 0) {
            if (((Minion) attacked).getDescription().contains("Reborn"))
                throw new Exception(ExceptionsEnum.rebornAttacked.getMessage());
            else
                throw new Exception(ExceptionsEnum.attackedDead.getMessage());
        }

    }

    @Override
    public void toHeroAttack(Hero attacked) {
        attacked.minusHealth(attack);
    }


}
