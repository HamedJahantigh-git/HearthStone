package controller.game;

import enums.InfoPassiveEnum;
import model.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class InfoPassiveController {

    private GameController gameController;

    public InfoPassiveController(GameController gameController) {
        this.gameController = gameController;
    }

    public ArrayList<InfoPassiveEnum> creatRandomInfoPassive(int number) {
        Random random = new Random();
        int randomNumber;
        ArrayList<InfoPassiveEnum> result = new ArrayList<>(Arrays.asList(InfoPassiveEnum.values()));
        number = result.size() - number;
        for (int i = 0; i < number; i++) {
            randomNumber = random.nextInt(result.size());
            result.remove(randomNumber);
        }
        return result;
    }

    protected void handleInfoPassive() {
        for (int i = 0; i < 2; i++) {
            gameController.getGame().getPlayers(i).getPlayerGame().getInfoPassive().applyInfo(gameController, i);
        }
    }
}
