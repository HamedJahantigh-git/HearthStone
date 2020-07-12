package model.infoPassive;

import controller.game.GameController;
import enums.InfoPassiveEnum;
import model.Player;

public class FreePower extends InfoPassive {

    public FreePower() {
        super(InfoPassiveEnum.freePower);
    }

    @Override
    public void applyInfo(GameController gameController, int playerIndex) {
        if (gameController.getGame().getPlayerGames(playerIndex).getHero().getHeroPowerMana() > 0) {
            gameController.getGame().getPlayerGames(playerIndex).getHero()
                    .minusHeroPowerMana(1);
            gameController.getGame().getPlayerGames(playerIndex).getHero().
                    setHeroPowerCanUseInEveryTurn(2);
        }
    }
}
