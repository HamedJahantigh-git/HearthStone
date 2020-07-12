package model.infoPassive;

import controller.game.GameController;
import enums.InfoPassiveEnum;
import model.Player;

public class ManaJump extends InfoPassive {

    public ManaJump() {
        super(InfoPassiveEnum.manaJump);
    }

    @Override
    public void applyInfo(GameController gameController, int playerIndex) {
        gameController.getGame().getPlayerGames(playerIndex).setRandMana(
                gameController.getGame().getPlayerGames(playerIndex).getRandMana()+1);
        gameController.getGame().getPlayerGames(playerIndex).setCurrentMana(
                gameController.getGame().getPlayerGames(playerIndex).getCurrentMana()+1);
    }
}
