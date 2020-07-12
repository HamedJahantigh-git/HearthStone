package model.hero;

import defaults.ModelDefault;
import enums.CardMechanicsEnum;
import enums.HeroType;
import model.Player;
import model.card.Card;

import java.util.ArrayList;


public class Hunter extends Hero {

    public Hunter() {
        super(HeroType.Hunter.name(),0,false);
    }

    private void doCycle(ArrayList<Card> cards){
        for (Card card : cards) {
            card.getMechanics().add(CardMechanicsEnum.Charge.name());
        }
    }

    @Override
    public void SpecialPower(Player.PlayerGame playerGame) {
        doCycle(playerGame.getAroundCard());
        doCycle(playerGame.getHandCard());
    }
}
