package controller.game.playingCard;

import enums.ExceptionsEnum;
import model.Game;
import model.card.Card;
import model.card.Minion;

public class PlayingMinion extends GetPlayerGames {

     PlayingMinion(Game game) {
        super(game);
    }

    void checkMinionCanPlay () throws Exception {
        if (currentPlayerGame().getGroundCard().size() > 6)
            throw new Exception(ExceptionsEnum.fullGroundDeck.getMessage());
    }

    void swapHandToGround(Minion card){
        currentPlayerGame().getGroundCard().add(card);
        currentPlayerGame().getHandCard().remove(card);
        /*for (int i = 0; i <currentPlayerGame().getHandCard().size(); i++) {
            if (card.getName().equals(currentPlayerGame().getHandCard().get(i).getName())) {
                currentPlayerGame().getGroundCard().add(currentPlayerGame().getHandCard().get(i));
                currentPlayerGame().getHandCard().remove(i);
                break;
            }
        }*/
    }

    void handleMechanics(Minion card) {

    }
}
