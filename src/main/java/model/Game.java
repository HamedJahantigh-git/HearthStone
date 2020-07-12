package model;

import controller.FileManagement;
import defaults.FilesPath;
import defaults.ModelDefault;
import model.card.Card;
import model.card.Minion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {
    private int ID;
    private Player[] players;
    private int playerIndex, numberOfHand;
    private boolean isAttackSelected;
    private boolean isFinish;
    private Long startPlayerTime;
    private int winnerPlayerIndex;

    private Attacker attacker;
    private ArrayList<Attacker> doAttackInTurn;
    private ArrayList<Card> newPlayedCard;
    private int isSecretPlayerIndex;

    private String[] event;
    private Random rand;

    public Game(Player[] players) {
        isFinish = false;
        isAttackSelected = false;
        doAttackInTurn = new ArrayList<>();
        newPlayedCard = new ArrayList<>();
        ID = (FileManagement.getInstance().allFileNameInPath(FilesPath.gameModel).size() + 1);
        this.players = players;
        for (int i = 0; i < players.length; i++) {
            this.players[i].newPlayerGame();
        }
        rand = new Random();
        playerIndex = 0;
        numberOfHand = 1;
        isSecretPlayerIndex = -1;
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

    public int getNextPlayerIndex(){
        return (playerIndex+1)%2;
    }

    public void switchPlayerIndex() {
        setStartPlayerTime(System.currentTimeMillis());
        playerIndex = getNextPlayerIndex();
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

    public boolean isAttackSelected() {
        return isAttackSelected;
    }

    public void setAttackSelected(boolean attackSelected) {
        isAttackSelected = attackSelected;
    }

    public Attacker getAttacker() {
        return attacker;
    }

    public ArrayList<Card> getNewPlayedCard() {
        return newPlayedCard;
    }

    public ArrayList<Attacker> getDoAttackInTurn() {
        return doAttackInTurn;
    }

    public void setAttacker(Attacker attacker) {
        this.attacker = attacker;
    }

    public int getIsSecretPlayerIndex() {
        return isSecretPlayerIndex;
    }

    public void setIsSecretPlayerIndex(int isSecretPlayerIndex) {
        this.isSecretPlayerIndex = isSecretPlayerIndex;
    }

    public int getWinnerPlayerIndex() {
        return winnerPlayerIndex;
    }

    public void setWinnerPlayerIndex(int winnerPlayerIndex) {
        this.winnerPlayerIndex = winnerPlayerIndex;
    }
}
