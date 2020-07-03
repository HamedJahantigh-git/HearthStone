package controller.game.playingCard;

import enums.ExceptionsEnum;
import model.Game;
import model.Player;
import model.card.Card;
import model.card.Minion;

public class PlayingCardController extends GetPlayerGames {

    private PlayingMinion playingMinion;
    private PlayingSpell playingSpell;
    private PlayingWeapon playingWeapon;

    public PlayingCardController(Game game) {
        super(game);
        playingMinion = new PlayingMinion(game);
    }


    public void playMinion(Minion card) throws Exception {
        playingMinion.checkMinionCanPlay();
        playingMinion.swapHandToGround(card);
        playingMinion.handleMechanics(card);
        reduceMana(card);
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

    public void checkCardCanPlay(Card card, int playerIndex) throws Exception {
        if (game.getPlayerIndex() != playerIndex)
            throw new Exception(ExceptionsEnum.illegalTurn.getMessage());
        if (game.getPlayerGames(playerIndex).getCurrentMana() < card.getMana())
            throw new Exception(ExceptionsEnum.lowMoney.getMessage());
    }

    private void reduceMana (Card card){
        currentPlayerGame().setCurrentMana(currentPlayerGame().getCurrentMana() - card.getMana());
    }

}
