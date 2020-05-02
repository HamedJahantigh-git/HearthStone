package controller;

import enums.ExceptionsEnum;
import enums.GameEventEnum;
import enums.LogsEnum;
import logs.GameEventLogs;
import logs.PlayerLogs;
import model.Game;
import model.Player;
import model.card.Card;

import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private Game game;
    private GameEventLogs gameEventLogs;
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
        if(checkQuestCard(game.getPlayerGames(0).getAroundCard(),game.getPlayerGames(0).getHandCard())){
            moveCardAroundToHand(2);
        }else {
            moveCardAroundToHand(3);
        }
    }

    private boolean checkQuestCard(ArrayList<Card> aroundCards, ArrayList<Card> handCards) {
        boolean result = false;
        //todo
        for (int i = 0; i < aroundCards.size(); i++) {
            if (aroundCards.get(i).getName().equals("Strength in Numbers")||aroundCards.get(i).getName().equals("Learn Draconic")){
                handCards.add(aroundCards.get(i));
                aroundCards.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    public void moveCardAroundToHand(int number) {
        if (game.getPlayerGames(0).getAroundCard().size() > 0) {
            if (number + game.getPlayerGames(0).getHandCard().size() > 12) {
                for (int i = 0; i < number; i++) {
                    rand = random.nextInt(game.getPlayerGames(0).getAroundCard().size());
                    game.getPlayerGames(0).getAroundCard().remove(rand);
                }
            } else {
                for (int i = 0; i < number; i++) {
                    rand = random.nextInt(game.getPlayerGames(0).getAroundCard().size());
                    game.getPlayerGames(0).getHandCard().add(game.getPlayerGames(0).getAroundCard().get(rand));
                    game.getPlayerGames(0).getAroundCard().remove(rand);
                }
            }
        }
    }

    public void addGameEvent(GameEventEnum gameEventEnum, String explanation, int playerID, Player player) {
        for (int i = game.getEvent().length - 1; i > 0; i--) {
            game.getEvent()[i] = game.getEvent()[i - 1];
        }
        game.getEvent()[0] = "P#" + Integer.toString(playerID + 1) + "|" + gameEventEnum.getType() + ": " + explanation;
        gameEventLogs.addToLogBody(playerID + 1, gameEventEnum.getType(), explanation);
        PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[5],
                LogsEnum.valueOf("play").getEvent_description()[6] + "battle#" + game.getID() + ".log", player);

    }

    public void endTurn() {
        game.setNumberOfHand(game.getNumberOfHand() + 1);
        if (game.getNumberOfHand() < 11)
            game.getPlayerGames(0).setRandMana(game.getNumberOfHand());
        game.getPlayerGames(0).setCurrentMana(game.getPlayerGames(0).getRandMana());
        moveCardAroundToHand(1);
    }

    public void playMinion(Card card) throws Exception {
        if (game.getPlayerGames(0).getGroundCard().size() > 6)
            throw new Exception(ExceptionsEnum.valueOf("fullGroundDeck").getMessage());
        if (game.getPlayerGames(0).getCurrentMana() < card.getMana())
            throw new Exception(ExceptionsEnum.valueOf("lowMoney").getMessage());
        for (int i = 0; i < game.getPlayerGames(0).getHandCard().size(); i++) {
            if (card.getName().equals(game.getPlayerGames(0).getHandCard().get(i).getName())) {
                game.getPlayerGames(0).getGroundCard().add(game.getPlayerGames(0).getHandCard().get(i));
                game.getPlayerGames(0).getHandCard().remove(i);
                break;
            }
        }
        game.getPlayerGames(0).setCurrentMana(game.getPlayerGames(0).getCurrentMana() - card.getMana());
    }

    public void playSpell(Card card) throws Exception {
        if (game.getPlayerGames(0).getCurrentMana() < card.getMana())
            throw new Exception(ExceptionsEnum.valueOf("lowMoney").getMessage());
        for (int i = 0; i < game.getPlayerGames(0).getHandCard().size(); i++) {
            if (card.getName().equals(game.getPlayerGames(0).getHandCard().get(i).getName())) {
                game.getPlayerGames(0).getHandCard().remove(i);
                break;
            }
        }
        game.getPlayerGames(0).setCurrentMana(game.getPlayerGames(0).getCurrentMana() - card.getMana());
    }

    public void playWeapon(Card card) throws Exception {
        if (game.getPlayerGames(0).getCurrentMana() < card.getMana())
            throw new Exception(ExceptionsEnum.valueOf("lowMoney").getMessage());
        for (int i = 0; i < game.getPlayerGames(0).getHandCard().size(); i++) {
            if (card.getName().equals(game.getPlayerGames(0).getHandCard().get(i).getName())) {
                game.getPlayerGames(0).getHandCard().remove(i);
                break;
            }
        }
        game.getPlayerGames(0).setCurrentMana(game.getPlayerGames(0).getCurrentMana() - card.getMana());
    }

}
