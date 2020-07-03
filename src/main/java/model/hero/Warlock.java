package model.hero;

import defaults.ModelDefault;
import enums.HeroType;

public class Warlock extends Hero {

    public Warlock() {
        super(HeroType.Warlock.name());
    }

    @Override
    public void SpecialPower() {
    }
}
