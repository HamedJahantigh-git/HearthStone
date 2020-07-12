package model.hero;

import defaults.ModelDefault;
import enums.CardType;
import enums.HeroType;
import model.Player;
import model.card.Card;

public class Rogue extends Hero {

    public Rogue() {
        super(HeroType.Rogue.name(),3, true);
    }

    @Override
    public void SpecialPower(Player.PlayerGame playerGame) {
        for (Card card : playerGame.getHandCard()) {
            if((!card.getCardClass().equals(HeroType.Rogue.name()))&&
                    (!card.getCardClass().equals(HeroType.Neutral.name()))){
                for (int i = 0; i <2 ; i++) {
                    if(card.getMana()>0)
                        card.minusMana(1);
                }
            }
        }
    }
}
