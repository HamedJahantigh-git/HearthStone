package model.hero;

import defaults.ModelDefault;
import enums.HeroType;
import model.Player;

public class Warlock extends Hero {

    private int heroPowerEffect;
    public Warlock() {
        super(HeroType.Warlock.name(),2, false);
        heroPowerEffect = 1;
    }

    @Override
    public void SpecialPower(Player.PlayerGame playerGame) {

        playerGame.getHero().setHealth(playerGame.getHero().getHealth() + 5);
    }

    public int getHeroPowerEffect() {
        return heroPowerEffect;
    }

    public void setHeroPowerEffect(int heroPowerEffect) {
        this.heroPowerEffect = heroPowerEffect;
    }
}
