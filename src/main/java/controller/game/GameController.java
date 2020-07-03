package controller.game;

import controller.FileManagement;
import controller.game.playingCard.PlayingCardController;
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
    private HeroController heroController;
    private InfoPassiveController infoPassiveController;
    private PlayingCardController playingCardController;
    private GameEventLogs gameEventLogs;
    private Random random;
    private int rand;

    public GameController() {
        this.random = new Random();
    }

    public void startGame() {
        infoPassiveController.handleInfoPassive();
        heroController.handleSpecialPower();
        System.out.println(game.getPlayerIndex());
        game.setStartPlayerTime(System.currentTimeMillis());
    }

    // Same second Constructor
    public void setPlayers(Player[] players) {
        this.game = new Game(players);
        FileManagement.getInstance().saveGameModelToFile(game);
        gameEventLogs = new GameEventLogs(game);
        heroController = new HeroController(game);
        infoPassiveController = new InfoPassiveController(game);
        playingCardController = new PlayingCardController(game);
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

    public void moveStartPlayerCardToHand() {
        int questCardNumber = moveQuestCard(game.getPlayerGames(game.getPlayerIndex()).getAroundCard()
                , game.getPlayerGames(game.getPlayerIndex()).getHandCard());
        moveCardAroundToHand(ModelDefault.gameDefaults.MAX_START_PLAYER_CARDS - questCardNumber);
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
        if (game.getPlayerIndex() == 1) {
            game.setNumberOfHand(game.getNumberOfHand() + 1);
        }
        for (int i = 0; i < 2; i++) {
            if (game.getNumberOfHand() < 11)
                game.getPlayerGames(i).setRandMana(game.getNumberOfHand());
            game.getPlayerGames(i).setCurrentMana(game.getPlayerGames(i).getRandMana());
        }
        game.switchPlayerIndex();
        moveCardAroundToHand(1);
    }

    public PlayingCardController getPlayingCardController() {
        return playingCardController;
    }

    public HeroController getHeroController() {
        return heroController;
    }

    public InfoPassiveController getInfoPassiveController() {
        return infoPassiveController;
    }
}
