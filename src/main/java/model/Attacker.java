package model;

import model.card.Card;
import model.hero.Hero;

public interface Attacker {

    abstract public void toCardAttack(Card attacked, Hero attackerHero) throws Exception;

    abstract public void toHeroAttack(Hero attacked);
}
