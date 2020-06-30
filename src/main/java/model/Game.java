package model;

import controller.FileManagement;
import defaults.FilesPath;
import defaults.ModelDefault;

import java.util.Random;

public class Game {
    private int ID;
    private Player[] players;
    private int playerIndex, numberOfHand;
    private String[] event;
    private Random rand;

    public Game(Player[] players) {
        ID = (FileManagement.getInstance().allFileNameInPath(FilesPath.gameModel).size() + 1);
        this.players = players;
        for (int i = 0; i < players.length; i++) {
            this.players[i].newPlayerGame();
        }
        rand = new Random();
        playerIndex = 0;
        numberOfHand = 1;
        event = new String[ModelDefault.gameDefaults.EVENT_NUMBER];
        for (int i = 0; i < event.length; i++) {
            event[i] = "";
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
        return players[index].getPlayerGame();
    }

    public Player getPlayers(int index) {
        return players[index];
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
}
