package controller.game.playingCard;

import controller.FileManagement;
import controller.game.GameController;
import enums.CardMechanicsEnum;
import enums.CardType;
import enums.ExceptionsEnum;
import model.Game;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;
import model.card.Weapon;
import model.hero.Hero;

import java.util.ArrayList;

public class PlayingCardController extends CardController {

    private PlayingMinion playingMinion;
    private PlayingSpell playingSpell;
    private PlayingWeapon playingWeapon;

    public PlayingCardController(GameController gameController, Game game) {
        super(gameController, game);
        playingMinion = new PlayingMinion(gameController, game);
        playingWeapon = new PlayingWeapon(gameController, game);
        playingSpell = new PlayingSpell(gameController, game);
    }

    public void groundCardSelectToAttack(Card card) {
        game.setAttackSelected(true);
        if (card.getType().equals(CardType.Minion.name()))
            game.setAttacker((Minion) card);
        else
            game.setAttacker((Weapon) card);
    }


    public void playMinion(Minion card) throws Exception {
        checkSet(card);
        playingMinion.checkMinionCanPlay();
        playingMinion.swapHandToGround(card);
        playingMinion.handleMinionMechanics(card);
        playingSpell.playSecret(card);
        reduceMana(card);
        checkHunter(card);
        playingMinion.checkQuest(card);
        checkForceAttack(card);
    }

    private void checkHunter(Minion card) {
        if(game.getPlayerGames(game.getNextPlayerIndex()).getHero().getHeroName().equals("Hunter")){
            card.minusHealth(1);
        }
    }

    public void playSpell(Spell card) throws Exception {
        playingSpell.checkSpellCanPlay();
        playingSpell.removeCard(card);
        playingSpell.handleSpellMechanics(card);
        reduceMana(card);
        playingSpell.checkQuest(card);
    }

    public void playWeapon(Weapon card) throws Exception {
        playingWeapon.checkWeaponCanPlay();
        playingWeapon.swapHandToGround(card);
        reduceMana(card);
    }

    public void checkCardCanPlay(Card card, int playerIndex) throws Exception {
        if (game.getPlayerIndex() != playerIndex)
            throw new Exception(ExceptionsEnum.illegalTurn.getMessage());
        if (game.getPlayerGames(playerIndex).getCurrentMana() < card.getMana())
            throw new Exception(ExceptionsEnum.lowMoney.getMessage());
    }

    public void checkCardCanAttack(int playerIndex, Card card) throws Exception {
        if (game.getPlayerIndex() != playerIndex)
            throw new Exception(ExceptionsEnum.illegalTurn.getMessage());
        if (game.getNewPlayedCard().contains(card))
            throw new Exception(ExceptionsEnum.newPlayCard.getMessage());
        if (game.getDoAttackInTurn().contains(card))
            throw new Exception(ExceptionsEnum.beforeAttacked.getMessage());
    }

    public void checkCardCanAttacked(int playerIndex, Card card) throws Exception {
        if (game.getPlayerIndex() == playerIndex)
            throw new Exception(ExceptionsEnum.selfMinion.getMessage());
        if (checkErrorTauntConditionAttack(playerIndex, card))
            throw new Exception(ExceptionsEnum.tauntExist.getMessage());

    }

    private boolean checkErrorTauntConditionAttack(int playerIndex, Card card) {
        if (!playingMinion.checkExistTaunt(playerIndex)) {
            return false;
        }
        if (card.getDescription().contains(CardMechanicsEnum.Taunt.name())) {
            return false;
        }
        return true;
    }

    public void minionAttacked(Minion attacked) {
        playingMinion.minionAttacked(attacked);
    }


    private void reduceMana(Card card) {
        currentPlayerGame().setCurrentMana(currentPlayerGame().getCurrentMana() - card.getMana());
    }

    public void checkHeroCanAttacked(int playerIndex, Hero hero) throws Exception {
        if (game.getPlayerIndex() == playerIndex)
            throw new Exception(ExceptionsEnum.selfHero.getMessage());
        if (playingMinion.checkExistTaunt(playerIndex))
            throw new Exception(ExceptionsEnum.tauntExist.getMessage());
    }

    public void heroAttacked(Hero Attacked) {
        game.getAttacker().toHeroAttack(Attacked);
        game.setAttackSelected(false);
        game.getDoAttackInTurn().add(game.getAttacker());
    }

    public void dealDamageTurn() {
        for (Card card : currentPlayerGame().getGroundCard()) {
            if (card.getDescription().contains("DealDamageTurn")) {
                int deal = Integer.parseInt(nextWord(card.getDescription(), "Deal"));
                for (Card opinionCard : game.getPlayerGames(game.getNextPlayerIndex()).getGroundCard()) {
                    ((Minion) opinionCard).minusHealth(deal);
                    checkDestroyedGroundCard();
                }
            }
        }
    }

    private void checkForceAttack(Minion attacked) throws Exception {
        Minion forceAttacker = game.getPlayerGames(game.getNextPlayerIndex()).getForceAttackCard();
        if (forceAttacker != null) {
            game.setAttacker(forceAttacker);
            minionAttacked(attacked);
            if(attacked.getHealth()<=0){
                currentPlayerGame().getGroundCard().remove(attacked);
            }
            if(forceAttacker.getHealth()<=0){
                game.getPlayerGames(game.getNextPlayerIndex()).getGroundCard().remove(forceAttacker);
            }
            game.getPlayerGames(game.getNextPlayerIndex()).setForceAttackCard(null);
            throw new Exception(ExceptionsEnum.forceAttack.getMessage());
        }
    }

    private void checkSet(Minion card) {
        if (currentPlayerGame().getSetEqualCard() != null) {
            card.setHealth(currentPlayerGame().getSetEqualCard().getHealth());
            currentPlayerGame().setSetEqualCard(null);
        }
    }

    void doSpellSummonMinion(Minion minion) {
        playingMinion.swapHandToGround(minion);
        playingMinion.handleMinionMechanics(minion);
        playingSpell.playSecret(minion);
    }

    public void returnHand(Minion targetedObj) {
        if (game.getPlayerGames(game.getNextPlayerIndex()).getHandCard().size() < 12) {
            game.getPlayerGames(game.getNextPlayerIndex()).getHandCard().add(targetedObj);
            game.getPlayerGames(game.getNextPlayerIndex()).getGroundCard().remove(targetedObj);
        }
    }

    public void doCopyHand(Minion targetedObj) {
        if(currentPlayerGame().getHandCard().size()<12){
           mackNullCopy(targetedObj);
        }
    }
}
