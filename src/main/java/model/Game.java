package model;

import controller.FileManagement;
import defaults.FilesPath;

import java.util.Random;

public class Game {
    private int ID;
    private Player.PlayerGame[] playerGames;
    private int playerTurnIndex;
    private Random rand;

    public Game(Player[] players) {
        ID = (FileManagement.getInstance().allFileNameInPath(FilesPath.gameModel).size()+1);
        playerGames = new Player.PlayerGame[players.length];
        for (int i = 0; i < players.length; i++) {
            players[i].newPlayerGame();
            playerGames[i] = players[i].getPlayerGame();
            playerGames[i].startPlayerGame(players[i].getGameDeck());
        }
        rand = new Random();
        playerTurnIndex =rand.nextInt(players.length);
    }

    public int getID() {
        return ID;
    }

    public Player.PlayerGame getPlayerGames(int index) {
        return playerGames[index];
    }
}
