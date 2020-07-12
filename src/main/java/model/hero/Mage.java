package model.hero;

import defaults.ModelDefault;
import enums.CardMechanicsEnum;
import enums.CardType;
import enums.HeroType;
import model.Player;
import model.card.Card;

import java.util.ArrayList;

public class Mage extends Hero {

    private int heroPowerDamage;

    public Mage() {
        super(HeroType.Mage.name(), 2,true);
        this.heroPowerDamage = 1;
    }

    private void doCycle(ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.getType().equals(CardType.Spell.name())) {
                for (int i = 0; i < 2; i++) {
                    if (card.getMana() > 0)
                        card.minusMana(1);
                }
            }
        }
    }

    @Override
    public void SpecialPower(Player.PlayerGame playerGame) {
        doCycle(playerGame.getAroundCard());
        doCycle(playerGame.getHandCard());
    }

    public int getHeroPowerDamage() {
        return heroPowerDamage;
    }

    public void setHeroPowerDamage(int heroPowerDamage) {
        this.heroPowerDamage = heroPowerDamage;
    }
}
