package model.hero;

import defaults.ModelDefault;
import model.card.Card;

import java.util.ArrayList;

public abstract class Hero {

    protected String heroName;
    protected int Health;

    public String getHeroName() {
        return heroName;
    }

    abstract public void specialPower ();

    abstract public void heroPower();

}
