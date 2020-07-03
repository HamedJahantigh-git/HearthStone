package controller.game.playingCard;

import model.Game;
import model.Player;

public class GetPlayerGames {

    protected Game game;

    protected GetPlayerGames(Game game) {
        this.game = game;
    }

    protected Player.PlayerGame currentPlayerGame (){
        return game.getPlayerGames(game.getPlayerIndex());
    }
}
