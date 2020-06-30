package model.hero;

import defaults.ModelDefault;
import enums.HeroType;

public class Mage extends Hero {

    public Mage() {
        super(HeroType.Mage.name());
    }

    public String test = "test";

    @Override
    public void SpecialPower() {
        System.out.println("Mage");
    }
}
