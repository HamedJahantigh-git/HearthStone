package model.card;

import enums.ExceptionsEnum;
import model.Attacker;
import model.hero.Hero;
import userInterfaces.myComponent.MouseManager;

import java.util.ArrayList;

public class Weapon extends Card implements Attacker {
    private int durability;
    private int attack;

    public Weapon(String name, String cardClass, String type, int mana,
                  int buyCost, int incomeSell, ArrayList<String> mechanics, String description,
                  String rarity, int durability, int attack) {
        super(name, cardClass, type, mana, buyCost, incomeSell, mechanics, description, rarity);
        this.durability = durability;
        this.attack = attack;
    }

    public void plusDurability(int number) {
        this.durability += number;
    }

    public void plusAttack(int number) {
        this.attack += number;
    }

    public int getDurability() {
        return durability;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public void toCardAttack(Card attacked, Hero attackerHero) throws Exception {
        attackerHero.setHealth(attackerHero.getHealth() - ((Minion) attacked).getAttack());
        durability--;
        ((Minion) attacked).minusHealth((attack));
        if (durability <= 0 && ((Minion) attacked).getHealth() <= 0)
            throw new Exception(ExceptionsEnum.weaponAndMinionDead.getMessage());
        if (durability <= 0)
            throw new Exception(ExceptionsEnum.destroyWeapon.getMessage());
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
        durability--;
    }
}
