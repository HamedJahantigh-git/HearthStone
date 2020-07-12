package model.infoPassive;

import controller.game.GameController;
import enums.InfoPassiveEnum;
import model.Player;
import model.card.Card;

import java.util.ArrayList;

public class OffCards extends InfoPassive {

    public OffCards() {
        super(InfoPassiveEnum.offCards);
    }

    @Override
    public void applyInfo(GameController gameController, int playerIndex) {
        for (Card card: gameController.getGame().getPlayerGames(playerIndex).getHandCard()) {
            card.minusMana(1);
        }
        for (Card card: gameController.getGame().getPlayerGames(playerIndex).getAroundCard()) {
            card.minusMana(1);
        }
    }
}
