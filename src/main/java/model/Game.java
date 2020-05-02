package model;

import controller.FileManagement;
import defaults.FilesPath;
import defaults.ModelDefault;

import java.util.Random;

public class Game {
    private int ID;
    private Player.PlayerGame[] playerGames;
    private int playerTurnIndex, numberOfHand;
    private String[] event;
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
        numberOfHand=1;
        event = new String[ModelDefault.gameDefaults.eventNumber];
        for (int i = 0; i <event.length ; i++) {
            event[i]="";
        }
    }

    public int getID() {
        return ID;
    }

    public String[] getEvent() {
        return event;
    }

    public int getNumberOfHand() {
        return numberOfHand;
    }

    public void setNumberOfHand(int numberOfHand) {
        this.numberOfHand = numberOfHand;
    }

    public Player.PlayerGame getPlayerGames(int index) {
        return playerGames[index];
    }
}
