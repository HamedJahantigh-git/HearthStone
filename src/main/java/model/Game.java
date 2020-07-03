package model;

import controller.FileManagement;
import defaults.FilesPath;
import defaults.ModelDefault;
import userInterfaces.myComponent.Bounds;

import java.util.Random;

public class Game {
    private int ID;
    private Player[] players;
    private int playerIndex, numberOfHand;
    private boolean isFinish;
    private Long startPlayerTime;

    private String[] event;
    private Random rand;

    public Game(Player[] players) {
        isFinish = false;
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



    public void switchPlayerIndex() {
        setStartPlayerTime(System.currentTimeMillis());
        playerIndex = (playerIndex+1)%2;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setStartPlayerTime(Long startPlayerTime) {
        this.startPlayerTime = startPlayerTime;
    }

    public int getDifferPlayerSecondsTime(){
        return (int)(Math.ceil((System.currentTimeMillis()-startPlayerTime)/1000));
    }

    public Long getDifferPlayerMilliTime(){
        return System.currentTimeMillis()-startPlayerTime;
    }


    public void setFinish() {
        isFinish = true;
    }
}
