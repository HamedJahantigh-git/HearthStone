package controller;

import logs.GameEventLogs;
import model.Game;
import model.Player;

import java.util.Random;

public class GameController {
    private Game game;
    private GameEventLogs gameEventLogs;
    private String[] event;
    private Random random;
    int rand;

    public GameController(Player[] players) {
        this.game = new Game(players);
        this.random = new Random();
        FileManagement.getInstance().saveGameModelToFile(game);
        gameEventLogs = new GameEventLogs(game);
        starGame();
    }

    public Game getGame() {
        return game;
    }

    public void starGame() {
        moveCardAroundToHand(3);
    }

    public void moveCardAroundToHand(int number) {
        for (int i = 0; i < number; i++) {
            rand = random.nextInt(game.getPlayerGames(0).getAroundCard().size());
            game.getPlayerGames(0).getHandCard().add(game.getPlayerGames(0).getAroundCard().get(rand));
            game.getPlayerGames(0).getAroundCard().remove(rand);
        }
    }
}
