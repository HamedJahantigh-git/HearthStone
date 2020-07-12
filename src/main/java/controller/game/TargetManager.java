package controller.game;

import controller.game.playingCard.PlayingCardController;
import enums.*;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;
import model.hero.Hero;
import model.hero.Mage;
import model.hero.Priest;

import java.util.ArrayList;

public class TargetManager {
    private PlayingCardController playingCardController;
    private boolean isTargeter;
    private Object targeterObj;
    private Object targetedObj;
    private TargeterType targeterType;
    private TargetedType targetedType;
    private CardMechanicsEnum targetMechanics;
    private boolean isOpponentTarget;

    public TargetManager(PlayingCardController playingCardController) {
        isTargeter = false;
        this.playingCardController = playingCardController;
    }

    public void setTargeter(TargeterType targeterType, Object targeterObj, ArrayList<CardMechanicsEnum> cardMechanics,
                            boolean isOpponentTarget) {
        isTargeter = true;
        this.targeterType = targeterType;
        this.targeterObj = targeterObj;
        this.isOpponentTarget = isOpponentTarget;
        if (cardMechanics != null) {
            for (int i = 0; i < cardMechanics.size(); i++) {
                if (cardMechanics.get(i) == CardMechanicsEnum.Targeted)
                    this.targetMechanics = (cardMechanics.get(i + 1));
            }
        }
    }

    public void doTargetAction(TargetedType targetedType, Object targetedObj,
                               int requestPlayerIndex, int currentPlayerIndex) throws Exception {
        this.targetedType = targetedType;
        this.targetedObj = targetedObj;
        if (isOpponentTarget && requestPlayerIndex == currentPlayerIndex)
            throw new Exception(ExceptionsEnum.targetedNotValid.getMessage());
        if ((!isOpponentTarget) && requestPlayerIndex != currentPlayerIndex)
            throw new Exception(ExceptionsEnum.targetedNotValid.getMessage());
        switch (targeterType) {
            case Spell:
                handleSpellMechanics((Spell) targeterObj);
                break;
            case Minion:
                handleMinionMechanics((Minion) targeterObj);
                break;
            case HeroPower:
                handleHeroPowerMechanics((Hero) targeterObj);
                break;
        }
        isTargeter = false;

    }

    private void handleHeroPowerMechanics(Hero targeterObj) throws Exception {
        switch (targeterObj.getHeroName()) {
            case "Mage":
                mageHeroPower();
                break;
            case "Priest":
                priestHeroPower();
                break;
        }
    }

    private void priestHeroPower() throws Exception {
        if (targetedType.equals(TargetedType.Minion)) {
            ((Minion) targetedObj).minusHealth(-1 * ((Priest) targeterObj).getHeroPowerRestore());
        } else if (targetedType.equals(TargetedType.Hero)) {
            ((Hero) targetedObj).minusHealth(-1 * ((Priest) targeterObj).getHeroPowerRestore());
        } else {
            throw new Exception(ExceptionsEnum.targetedNotValid.getMessage());
        }
    }

    private void mageHeroPower() throws Exception {
        if (targetedType.equals(TargetedType.Minion)) {
            ((Minion) targetedObj).minusHealth(((Mage) targeterObj).getHeroPowerDamage());
        } else if (targetedType.equals(TargetedType.Hero)) {
            ((Hero) targetedObj).minusHealth(((Mage) targeterObj).getHeroPowerDamage());
        } else {
            throw new Exception(ExceptionsEnum.targetedNotValid.getMessage());
        }
    }

    private void handleMinionMechanics(Minion targeterObj) throws Exception {
        if (!targetedType.equals(TargetedType.Minion))
            throw new Exception(ExceptionsEnum.targetedNotValid.getMessage());
        switch (targetMechanics) {
            case CopyHand:
                CopyHand();
                break;
        }
    }

    private void CopyHand() {
        playingCardController.doCopyHand((Minion) targetedObj);
    }

    private void handleSpellMechanics(Spell targeter) throws Exception {
        if (!targetedType.equals(TargetedType.Minion))
            throw new Exception(ExceptionsEnum.targetedNotValid.getMessage());
        switch (targetMechanics) {
            case DealDamage:
                dealDamage();
                break;
            case TransForm:
                transForm();
                break;
            case Return:
                returnHand();
                break;
            case MakeDivineShieldAndTaunt:
                makeDivineShieldAndTaunt();
                break;
        }

    }

    private void makeDivineShieldAndTaunt() {
        int[] element = playingCardController.extractionNumber(playingCardController.nextWord(((Card) targeterObj).getDescription(), "Increment"));
        ((Minion) targetedObj).plusAttack(element[0]);
        ((Minion) targetedObj).minusHealth(-1 * element[1]);
        ((Minion) targetedObj).setDescription(((Minion) targetedObj).getDescription().concat(" - Taunt - DivineShield"));
    }

    private void returnHand() {
        playingCardController.returnHand(((Minion) targetedObj));
    }

    public boolean isTargeter() {
        return isTargeter;
    }

    public void setTargeter(boolean targeter) {
        isTargeter = targeter;
    }

    private void dealDamage() {
        int deal = Integer.parseInt(playingCardController.nextWord(((Card) targeterObj).getDescription(), "Deal"));
        ((Minion) targetedObj).minusHealth(deal);
    }

    private void transForm() {
        int[] element = playingCardController.extractionNumber(playingCardController.nextWord(
                ((Card) targeterObj).getDescription(), "Minion"));

        ((Minion) targetedObj).setHealth(element[1]);
        if (element[0] != 0)
            ((Minion) targetedObj).setAttack(element[0]);
    }

    public boolean isOpponent() {
        if (targeterType.equals(TargeterType.Spell) || targeterType.equals(TargeterType.Minion)) {
            return ((Card) targeterObj).getDescription().contains("Opponent");
        }
        return false;
    }
}
