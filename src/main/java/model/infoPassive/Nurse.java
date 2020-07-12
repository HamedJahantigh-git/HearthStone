package model.infoPassive;

import controller.game.GameController;
import enums.InfoPassiveEnum;
import model.Player;

public class Nurse extends InfoPassive {


    public Nurse() {
        super(InfoPassiveEnum.nurse);
    }

    @Override
    public void applyInfo(GameController gameController, int playerIndex) {

    }
}
