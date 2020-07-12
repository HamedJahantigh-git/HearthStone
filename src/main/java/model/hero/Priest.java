package model.hero;

import defaults.ModelDefault;
import enums.HeroType;
import model.Player;

public class Priest extends Hero {

    private int heroPowerRestore;

    public Priest() {
        super(HeroType.Priest.name(), 2,true);
        heroPowerRestore = 4;
    }

    @Override
    public void SpecialPower(Player.PlayerGame playerGame) {

    }

    public int getHeroPowerRestore() {
        return heroPowerRestore;
    }

    public void setHeroPowerRestore(int heroPowerRestore) {
        this.heroPowerRestore = heroPowerRestore;
    }
}
