package controller.gameController;

import controller.FileManagement;
import defaults.ModelDefault;
import enums.ExceptionsEnum;
import enums.GameEventEnum;
import enums.LogsEnum;
import logs.GameEventLogs;
import logs.PlayerLogs;
import model.Game;
import model.Player;
import model.card.Card;
import model.card.Spell;

import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private Game game;
    private GameTimerThread gameTimerThread;
    private GameEventLogs gameEventLogs;
    private Random random;
    int rand;

    public GameController() {
        this.random = new Random();
        gameTimerThread = new GameTimerThread();
    }

    public void startGame() {
        handleInfoPassive();
        handleSpecialPower();
    }

    private void handleInfoPassive() {

    }

    private void handleSpecialPower() {
        for (int i = 0; i <2 ; i++) {
            game.getPlayers(i).getPlayerGame().getHero().SpecialPower();
        }
    }

    public void setPlayers(Player[] players) {
        this.game = new Game(players);
        FileManagement.getInstance().saveGameModelToFile(game);
        gameEventLogs = new GameEventLogs(game);
        PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[5],
                LogsEnum.valueOf("play").getEvent_description()[6] +
                        "battle#" + game.getID() + ".log", players[0]);
        PlayerLogs.addToLogBody(LogsEnum.valueOf("play").getEvent()[5],
                LogsEnum.valueOf("play").getEvent_description()[6] +
                        "battle#" + game.getID() + ".log", players[1]);
    }

    public Game getGame() {
        return game;
    }

    public GameTimerThread getGameTimerThread() {
        return gameTimerThread;
    }

    public void moveStartPlayerCardToHand() {
        int questCardNumber = moveQuestCard(game.getPlayerGames(game.getPlayerIndex()).getAroundCard()
                , game.getPlayerGames(game.getPlayerIndex()).getHandCard());
        moveCardAroundToHand(ModelDefault.gameDefaults.MAX_START_PLAYER_CARDS - questCardNumber);
    }

    public void switchPlayerIndex() {
        game.setPlayerIndex((game.getPlayerIndex() + 1) % 2);
    }

    private int moveQuestCard(ArrayList<Card> aroundCards, ArrayList<Card> handCards) {
        int result = 0;
        for (int i = 0; i < aroundCards.size(); i++) {
            if (aroundCards.get(i).getType().equals("Spell") &&
                    ((Spell) aroundCards.get(i)).getQuest() != null) {
                handCards.add(aroundCards.get(i));
                aroundCards.remove(i);
                i--;
                result++;
            }
        }
        return result;
    }

    public void moveCardAroundToHand(int number) {
        int index = game.getPlayerIndex();
        if (game.getPlayerGames(index).getAroundCard().size() > 0) {
            if (number + game.getPlayerGames(index).getHandCard().size() > 12) {
                for (int i = 0; i < number; i++) {
                    rand = random.nextInt(game.getPlayerGames(index).getAroundCard().size());
                    game.getPlayerGames(index).getAroundCard().remove(rand);
                }
            } else {
                for (int i = 0; i < number; i++) {
                    rand = random.nextInt(game.getPlayerGames(index).getAroundCard().size());
                    game.getPlayerGames(index).getHandCard().add(game.getPlayerGames(index).getAroundCard().get(rand));
                    game.getPlayerGames(index).getAroundCard().remove(rand);
                }
            }
        }
    }

    public void changeStartCard(int playerIndex, int cardIndex) throws Exception {
        if (game.getPlayerGames(playerIndex).getSelectedStartCard().get(cardIndex))
            throw new Exception(ExceptionsEnum.changeOneStartCard.getMessage());
        Card temp = game.getPlayerGames(playerIndex).getHandCard().get(cardIndex);
        rand = random.nextInt(game.getPlayerGames(playerIndex).getAroundCard().size());
        game.getPlayerGames(playerIndex).getHandCard().set(
                cardIndex, game.getPlayerGames(playerIndex).getAroundCard().get(rand));
        game.getPlayerGames(playerIndex).getAroundCard().remove(rand);
        game.getPlayerGames(playerIndex).getAroundCard().add(temp);
        game.getPlayerGames(playerIndex).setTrueSelectedStartCard(cardIndex);
        addGameEvent(game.getPlayerIndex(), GameEventEnum.changeStartCard,
                game.getPlayerGames(playerIndex).getAroundCard().get(rand).getName());
    }

    public void addGameEvent(int playerIndex, GameEventEnum gameEventEnum, String explanation) {
        int playerID = game.getPlayerIndex() + 1;
        for (int i = game.getEvent().length - 1; i > 0; i--) {
            game.getEvent()[i] = game.getEvent()[i - 1];
        }
        game.getEvent()[0] = "P#" + playerID + "|" + gameEventEnum.getType() + ": " + explanation;
        gameEventLogs.addToLogBody(playerID, gameEventEnum.getType(), explanation);
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
