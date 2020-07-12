package controller.game.playingCard;

import controller.game.GameController;
import enums.ExceptionsEnum;
import model.Game;
import model.card.Minion;
import model.card.Weapon;

public class PlayingWeapon extends CardController {

    protected PlayingWeapon(GameController gameController, Game game) {
        super(gameController, game);
    }

    void checkWeaponCanPlay() throws Exception {
        if(false) {
            throw new Exception(ExceptionsEnum.fullGroundDeck.getMessage());
        }
    }

    void swapHandToGround(Weapon card) {
        currentPlayerGame().setWeapon(card);
        currentPlayerGame().getHandCard().remove(card);
        game.getNewPlayedCard().add(card);
    }
}
