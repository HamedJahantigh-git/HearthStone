package model.hero;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import defaults.ModelDefault;
import model.Player;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Hunter.class, name = "Hunter"),
        @JsonSubTypes.Type(value = Priest.class, name = "Priest"),
        @JsonSubTypes.Type(value = Rogue.class, name = "Rogue"),
        @JsonSubTypes.Type(value = Mage.class, name = "Mage"),
        @JsonSubTypes.Type(value = Warlock.class, name = "Warlock"),
})

public class Hero {

    protected String heroName;
    protected int health;

    protected int heroPowerMana;
    protected int heroPowerCanUseInEveryTurn;

    protected boolean isHeroPowerTargeted;

    public Hero(){};

    protected Hero(String heroName, int heroPowerMana, boolean isHeroPowerTargeted) {
        this.heroName = heroName;
        this.isHeroPowerTargeted = isHeroPowerTargeted;
        this.heroPowerMana = heroPowerMana;
        this.heroPowerCanUseInEveryTurn = 1;
        this.health = ModelDefault.heroDefaults.baseOfHeroHealth;
    }

    public String getHeroName() {
        return heroName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void minusHealth(int reduce) {
        health -= reduce;
    }

    public void SpecialPower(Player.PlayerGame playerGame) {
    }

    public boolean isHeroPowerTargeted() {
        return isHeroPowerTargeted;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public int getHeroPowerMana() {
        return heroPowerMana;
    }

    public void setHeroPowerCanUseInEveryTurn(int heroPowerCanUseInEveryTurn) {
        this.heroPowerCanUseInEveryTurn = heroPowerCanUseInEveryTurn;
    }

    public void minusHeroPowerMana(int reduce) {
        this.heroPowerMana -= reduce;
    }

    public int getHeroPowerCanUseInEveryTurn() {
        return heroPowerCanUseInEveryTurn;
    }
}
