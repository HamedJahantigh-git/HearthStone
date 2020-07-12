package controller.game;

import controller.FileManagement;
import controller.game.playingCard.PlayingCardController;
import defaults.ModelDefault;
import enums.*;
import logs.GameEventLogs;
import logs.PlayerLogs;
import model.Game;
import model.Player;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;
import model.card.Weapon;

import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private Game game;
    private HeroController heroController;
    private InfoPassiveController infoPassiveController;
    private PlayingCardController playingCardController;
    private TargetManager targetManager;
    private GameEventLogs gameEventLogs;
    private Random random;
    private int rand;

    public GameController() {
        this.random = new Random();
    }

    public void startGame() {
        infoPassiveController.handleInfoPassive();
        heroController.handleSpecialPower();
        game.setStartPlayerTime(System.currentTimeMillis());
    }

    // Same second Constructor
    public void setPlayers(Player[] players) {
        this.game = new Game(players);
        FileManagement.getInstance().saveGameModelToFile(game);
        gameEventLogs = new GameEventLogs(game);
        heroController = new HeroController(this);
        infoPassiveController = new InfoPassiveController(this);
        playingCardController = new PlayingCardController(this, game);
        targetManager = new TargetManager(playingCardController);
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

    public void checkGameFinished() throws Exception {
        for (int i = 0; i < 2; i++) {
            if (game.getPlayerGames(i).getHero().getHealth() <= 0) {
                finishGame((i + 1) % 2);
            }
        }
        int counter = 0;
        for (int i = 0; i < 2; i++) {
            if (game.getPlayerGames(i).getHandCard().size() == 0
                    && game.getPlayerGames(i).getGroundCard().size() == 0
                    && game.getPlayerGames(i).getAroundCard().size() == 0)
                counter++;
        }
        if (counter == 2) {
            finishGame(-1);
        }
    }

    private void finishGame(int winnerIndex) throws Exception {
        game.setFinish();
        game.setWinnerPlayerIndex(winnerIndex);
        throw new Exception(ExceptionsEnum.gameFinished.getMessage());
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
        if (game.getPlayerGames(index).getAroundCard().size() > number - 1) {
            if (game.getPlayerGames(index).getGainDrawCard() != null) {
                game.getPlayerGames(index).getGainDrawCard().minusHealth(-1);
                game.getPlayerGames(index).getGainDrawCard().plusAttack(1);
                game.getPlayerGames(index).setGainDrawCard(null);
            }
            for (int i = 0; i < number; i++) {
                rand = random.nextInt(game.getPlayerGames(index).getAroundCard().size());
                if (game.getPlayerGames(index).getHandCard().size() < 12) {
                    game.getPlayerGames(index).getHandCard().add(game.getPlayerGames(index).getAroundCard().get(rand));
                }
                game.getPlayerGames(index).getAroundCard().remove(rand);
            }
        }
    }

    public void moveSpellAroundToHand(int number) {
        int a = number;
        for (int i = 0; i < game.getPlayerGames(game.getPlayerIndex()).getAroundCard().size(); i++) {
            Card card = game.getPlayerGames(game.getPlayerIndex()).getAroundCard().get(i);
            if (a > 0 && card.getType().equals(CardType.Spell.name())) {
                if (game.getPlayerGames(game.getPlayerIndex()).getHandCard().size() < 12) {
                    game.getPlayerGames(game.getPlayerIndex()).getHandCard().add(card);
                }
                game.getPlayerGames(game.getPlayerIndex()).getAroundCard().remove(card);
                a--;
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
                if (game.getPlayerGames(i).getInfoPassive().getType() == InfoPassiveEnum.manaJump)
                    game.getPlayerGames(i).setRandMana(game.getNumberOfHand()+1);
                else
                    game.getPlayerGames(i).setRandMana(game.getNumberOfHand());
            game.getPlayerGames(i).setCurrentMana(game.getPlayerGames(i).getRandMana());
        }
        playingCardController.dealDamageTurn();
        game.getPlayerGames(game.getPlayerIndex()).setHeroPowerUsedInTurn(0);
        game.switchPlayerIndex();
        game.getNewPlayedCard().clear();
        game.getDoAttackInTurn().clear();
        moveCardAroundToHand(game.getPlayerGames(game.getPlayerIndex()).getNumberDrawCardInTurn());
    }

    public void heroPowerSelection(int playerIndex) throws Exception {
        if (game.getPlayerIndex() != playerIndex) {
            throw new Exception(ExceptionsEnum.illegalTurn.getMessage());
        }
        if (game.getPlayerGames(game.getPlayerIndex()).getHero().getHeroPowerMana() >
                game.getPlayerGames(game.getPlayerIndex()).getCurrentMana()) {
            throw new Exception(ExceptionsEnum.lowMoney.getMessage());
        }
        if (game.getPlayerGames(game.getPlayerIndex()).getHero().getHeroPowerCanUseInEveryTurn() <=
                game.getPlayerGames(game.getPlayerIndex()).getHeroPowerUsedInTurn()){
            throw new Exception(ExceptionsEnum.doMoreHeroPower.getMessage());
        }
        heroController.handelHeroPower();
    }

    public boolean checkGroundFull(int playerIndex) {
        if (game.getPlayerGames(playerIndex).getGroundCard().size() >= 7)
            return true;
        return false;
    }

    public PlayingCardController getPlayingCardController() {
        return playingCardController;
    }

    public InfoPassiveController getInfoPassiveController() {
        return infoPassiveController;
    }

    public TargetManager getTargetManager() {
        return targetManager;
    }

}
