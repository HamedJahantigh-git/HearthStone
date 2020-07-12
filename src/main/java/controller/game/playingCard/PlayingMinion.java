package controller.game.playingCard;

import controller.FileManagement;
import controller.game.GameController;
import enums.*;
import model.Game;
import model.Quest;
import model.card.Card;
import model.card.Minion;

import java.util.ArrayList;

public class PlayingMinion extends CardController {

    PlayingMinion(GameController gameController, Game game) {
        super(gameController, game);
    }

    void checkMinionCanPlay() throws Exception {
        if (currentPlayerGame().getGroundCard().size() > 6)
            throw new Exception(ExceptionsEnum.fullGroundDeck.getMessage());
    }

    void swapHandToGround(Minion card) {
        currentPlayerGame().getGroundCard().add(card);
        currentPlayerGame().getHandCard().remove(card);
        game.getNewPlayedCard().add(card);
    }

    void minionAttacked(Minion attacked) {
        try {
            game.getAttacker().toCardAttack(attacked, currentPlayerGame().getHero());
        } catch (Exception e) {
            if (e.getMessage().equals(ExceptionsEnum.allDead.getMessage())) {
                game.getPlayerGames((game.getPlayerIndex() + 1) % 2).getGroundCard().remove(attacked);
                currentPlayerGame().getGroundCard().remove(game.getAttacker());
            }
            if (e.getMessage().equals(ExceptionsEnum.attackedDead.getMessage())) {
                game.getPlayerGames((game.getPlayerIndex() + 1) % 2).getGroundCard().remove(attacked);
            }
            if (e.getMessage().equals(ExceptionsEnum.rebornAttacker.getMessage())) {
                ((Minion)game.getAttacker()).setHealth(1);
                ((Minion)game.getAttacker()).setAttack(2);
                ((Minion)game.getAttacker()).setDescription("  ");
                currentPlayerGame().getGroundCard().remove(game.getAttacker());
            }
            if (e.getMessage().equals(ExceptionsEnum.rebornAttacked.getMessage())) {
               attacked.setHealth(1);
               attacked.setAttack(2);
                ((Minion)game.getAttacker()).setDescription("  ");
            }
            if (e.getMessage().equals(ExceptionsEnum.attackerDead.getMessage())) {
                currentPlayerGame().getGroundCard().remove(game.getAttacker());
            }
            if (e.getMessage().equals(ExceptionsEnum.weaponAndMinionDead.getMessage())) {
                currentPlayerGame().setWeapon(null);
                game.getPlayerGames((game.getPlayerIndex() + 1) % 2).getGroundCard().remove(attacked);
            }
            if (e.getMessage().equals(ExceptionsEnum.destroyWeapon.getMessage())) {
                currentPlayerGame().setWeapon(null);
            }
        } finally {
            game.setAttackSelected(false);
            game.getDoAttackInTurn().add(game.getAttacker());
        }

    }

    boolean checkExistTaunt(int playerIndex) {
        boolean result = false;
        for (Card card : game.getPlayerGames(playerIndex).getGroundCard()) {
            if (returnCardMechanics(card).contains(CardMechanicsEnum.Taunt)) {
                result = true;
            }
        }
        return result;
    }

    void handleMinionMechanics(Minion card) {
        ArrayList<CardMechanicsEnum> cardMechanics = returnCardMechanics(card);
        while (cardMechanics.size() != 0) {
            switch (cardMechanics.get(0)) {
                case Targeted:
                    targeted(card);
                    break;
                case Charge:
                    charge(card);
                    break;
                case DealDamage:
                    dealDamage(card);
                    break;
                case ModifyCost:
                    modifyCost(card);
                    break;
                case Inspire:
                    inspire(card);
                    break;
                case Increment:
                    increment(card.getDescription());
                    break;
                case Gain:
                    gain(card);
                    break;
                case ForceAttack:
                    forceAttack(card);
                    break;
                case Set:
                    set(card);
                    break;
                case CopyBattle:
                    copyBattle(card);
                    break;
            }
            cardMechanics.remove(0);
        }
    }

    private void targeted(Minion card) {
        gameController.getTargetManager().setTargeter(TargeterType.Minion, card, returnCardMechanics(card),
                card.getDescription().contains("Opponent"));
    }

    private void copyBattle(Minion card) {
        if (currentPlayerGame().getGroundCard().size() < 7){
           game.getNewPlayedCard().add(mackNullCopy(card));
        }
    }

    private void set(Minion card) {
        currentPlayerGame().setSetEqualCard(card);
    }

    private void gain(Minion card) {
        if (card.getDescription().contains("Attack")) {
            int plus = Integer.parseInt(nextWord(card.getDescription(), "Attack"));
            for (int i = 0; i < game.getPlayerGames(game.getNextPlayerIndex()).getHandCard().size(); i++) {
                card.plusAttack(plus);
            }
        }
        if (card.getDescription().contains("Draw")) {
            currentPlayerGame().setGainDrawCard(card);
        }
    }

    private void increment(String description) {
        switch (nextWord(description, "Increment")) {
            case "MinionsCost":
                int cost = Integer.parseInt(nextWord(description, "MinionsCost"));
                for (Card card : currentPlayerGame().getHandCard()) {
                    if (card.getType().equals(CardType.Minion.name()))
                        card.minusMana(-1 * cost);
                }
        }
    }

    private void dealDamage(Card mecCard) {
        int deal = Integer.parseInt(nextWord(mecCard.getDescription(), "Deal"));
        if (mecCard.getDescription().contains("Minions")) {
            for (Card card : game.getPlayerGames(game.getNextPlayerIndex()).getGroundCard()) {
                ((Minion) card).minusHealth(deal);
            }
            checkDestroyedGroundCard();
        }
        if (mecCard.getDescription().contains("Hero")) {
            if (mecCard.getDescription().contains("your"))
                currentPlayerGame().getHero().minusHealth(deal);
            if (mecCard.getDescription().contains("enemy")) {
                game.getPlayerGames(game.getNextPlayerIndex()).getHero().minusHealth(deal);
            }
        }

    }

    private void charge(Minion card) {
        game.getNewPlayedCard().remove(card);
    }

    private void modifyCost(Card mecCard) {
        int deal = Integer.parseInt(nextWord(mecCard.getDescription(), "cost"));
        for (Card card : currentPlayerGame().getHandCard()) {
            if (card.getType().equals(CardType.Weapon.name())) {
                for (int i = 0; i < deal; i++) {
                    card.minusMana(1);
                    if (card.getMana() == 0)
                        break;
                }
            }
        }

    }

    private void forceAttack(Minion card) {
        currentPlayerGame().setForceAttackCard(card);
    }

    private void inspire(Minion card) {
        Card copy = FileManagement.getInstance().getCopy().copyCard(card);
        copy.setDescription(" ");
        ArrayList<String> mec = new ArrayList<>();
        mec.add("Empty");
        copy.setMechanics(mec);
        currentPlayerGame().getHandCard().add(copy);
    }

    public void checkQuest(Minion card) throws Exception {
        Quest quest = currentPlayerGame().getQuest();
        if(quest.isOn() && quest.getQuestType() == QuestType.Minions){
            quest.plusMana(card.getMana());
            if(quest.checkDoing()){
                if(currentPlayerGame().getGroundCard().size()<7){
                    for (Card aroundCard:currentPlayerGame().getAroundCard()) {
                        if(aroundCard.getType().equals(CardType.Minion.name())){
                            currentPlayerGame().getGroundCard().add(aroundCard);
                            game.getNewPlayedCard().add(aroundCard);
                            currentPlayerGame().getHandCard().remove(aroundCard);
                            break;
                        }
                    }
                }
            }
        }
    }
}
