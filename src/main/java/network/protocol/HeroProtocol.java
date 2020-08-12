package network.protocol;

import java.io.Serializable;

public class HeroProtocol implements Serializable {

    private String heroName;
    private int health;

    private int heroPowerMana;
    private int heroPowerCanUseInEveryTurn;

    private boolean isHeroPowerTargeted;

    public HeroProtocol(String heroName, int health, int heroPowerMana,
                        int heroPowerCanUseInEveryTurn, boolean isHeroPowerTargeted) {
        this.heroName = heroName;
        this.health = health;
        this.heroPowerMana = heroPowerMana;
        this.heroPowerCanUseInEveryTurn = heroPowerCanUseInEveryTurn;
        this.isHeroPowerTargeted = isHeroPowerTargeted;
    }

    public String getHeroName() {
        return heroName;
    }

    public int getHealth() {
        return health;
    }

    public int getHeroPowerMana() {
        return heroPowerMana;
    }

    public int getHeroPowerCanUseInEveryTurn() {
        return heroPowerCanUseInEveryTurn;
    }

    public boolean isHeroPowerTargeted() {
        return isHeroPowerTargeted;
    }

}
