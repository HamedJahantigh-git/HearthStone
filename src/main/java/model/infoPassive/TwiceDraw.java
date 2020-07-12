package model.infoPassive;

import controller.game.GameController;
import enums.InfoPassiveEnum;
import model.Player;

public class TwiceDraw extends InfoPassive {

    public TwiceDraw() {
        super(InfoPassiveEnum.twiceDraw);
    }

    @Override
    public void applyInfo(GameController gameController, int playerIndex) {
        gameController.getGame().getPlayerGames(playerIndex).setNumberDrawCardInTurn(2);
    }
}
