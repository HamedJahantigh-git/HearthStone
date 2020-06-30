package model.hero;
import defaults.ModelDefault;

import java.io.Serializable;

 public abstract class Hero {

    protected String heroName;
    protected int Health;

    protected Hero(String heroName){
        this.heroName = heroName;
        this.Health = ModelDefault.heroDefaults.baseOfHeroHealth;
    }

    public String getHeroName() {
        return heroName;
    }

    public int getHealth() {
        return Health;
    }

     abstract public void SpecialPower ();
}
