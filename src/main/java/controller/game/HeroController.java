package controller.game;

import model.Game;

public class HeroController {

    private Game game;

    public HeroController(Game game) {
        this.game = game;
    }

    protected void handleSpecialPower() {
        for (int i = 0; i <2 ; i++) {
            game.getPlayers(i).getPlayerGame().getHero().SpecialPower();
        }
    }
}
