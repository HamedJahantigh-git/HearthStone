package controller.game.playingCard;

import controller.FileManagement;
import controller.game.GameController;
import enums.*;
import model.Game;
import model.Quest;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;
import model.card.Weapon;

import java.util.ArrayList;

public class PlayingSpell extends CardController {

    protected PlayingSpell(GameController gameController, Game game) {
        super(gameController, game);
    }


    public void checkSpellCanPlay() throws Exception {
        if (false) {
            throw new Exception(ExceptionsEnum.fullGroundDeck.getMessage());
        }
    }

    public void handleSpellMechanics(Spell card) {
        ArrayList<CardMechanicsEnum> cardMechanics = returnCardMechanics(card);
        while (cardMechanics.size() != 0) {
            switch (cardMechanics.get(0)) {
                case Targeted:
                    targeted(card);
                    break;
                case Swarm:
                    swarm();
                    break;
                case Increment:
                    increment(card);
                    break;
                case Discard:
                    discard(card);
                    break;
                case Discover:
                    discover(card, cardMechanics);
                    break;
                case Secret:
                    secret();
                    break;
                case PutHand:
                    putHand();
                    break;
                case Summon:
                    summon(card);
                    break;
                case Destroy:
                    destroy(card);
                    break;
                case Quest:
                    quest(card);
                    break;
            }
            cardMechanics.remove(0);
        }
    }

    private void swarm() {
        ArrayList<Minion> minions = FileManagement.getInstance().getReadCardFromFile().readMinion();
        Minion locust = null;
        for (Minion minionCard : minions) {
            if (minionCard.getName().equals("Locust")) {
                locust = minionCard;
                break;
            }
        }
        int number = 7 - currentPlayerGame().getGroundCard().size();
        for (int i = 0; i < number; i++) {
            currentPlayerGame().getGroundCard().add(FileManagement.getInstance().getCopy().copyCard(locust));
        }
    }

    private void quest(Spell card) {
        int manaHaveTo = Integer.parseInt(nextWord(card.getDescription(), "Spend"));
        currentPlayerGame().getQuest().setQuest(card, manaHaveTo);
    }

    private void targeted(Spell card) {
        gameController.getTargetManager().setTargeter(TargeterType.Spell, card, returnCardMechanics(card),
                card.getDescription().contains("Opponent"));
    }

    private void destroy(Spell card) {
        int value = Integer.parseInt(nextWord(card.getDescription(), "Attack"));
        for (Card groundCard : game.getPlayerGames(game.getNextPlayerIndex()).getGroundCard()) {
            if (((Minion) groundCard).getAttack() >= value) {
                game.getPlayerGames(game.getNextPlayerIndex()).getGroundCard().remove(groundCard);
                break;
            }
        }
    }

    private void summon(Spell card) {
        int value = Integer.parseInt(nextWord(card.getDescription(), "Summon"));
        String type = nextWord(card.getDescription(), nextWord(card.getDescription(), "Summon"));
        if (card.getDescription().contains("CostRelated")) {
            int cost = Integer.parseInt(nextWord(card.getDescription(), "CostRelated"));
            for (int i = 0; i < value; i++) {
                if (!gameController.checkGroundFull(game.getPlayerIndex())) {
                    for (int j = 0; j < currentPlayerGame().getHandCard().size(); j++) {
                        if (currentPlayerGame().getHandCard().get(j).getType().equals(type) && currentPlayerGame().getHandCard().get(j).getMana() == cost) {
                            gameController.getPlayingCardController().doSpellSummonMinion((Minion) currentPlayerGame().getHandCard().get(j));
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < value; i++) {
                if (!gameController.checkGroundFull(game.getPlayerIndex())) {
                    for (int j = 0; j < currentPlayerGame().getHandCard().size(); j++) {
                        if (currentPlayerGame().getHandCard().get(j).getType().equals(type)) {
                            gameController.getPlayingCardController().doSpellSummonMinion((Minion) currentPlayerGame().getHandCard().get(j));
                        }
                    }
                }
            }
        }
    }

    private void putHand() {
        int a = 1, b = 1;
        for (int i = 0; i < currentPlayerGame().getAroundCard().size(); i++) {
            if (currentPlayerGame().getHandCard().size() < 12) {
                if (currentPlayerGame().getAroundCard().get(i).getType().equals("Minion") && a != 0) {
                    currentPlayerGame().getHandCard().add(currentPlayerGame().getAroundCard().get(i));
                    currentPlayerGame().getAroundCard().remove(currentPlayerGame().getAroundCard().get(i));
                    i--;
                    a--;
                    continue;
                }
                if (currentPlayerGame().getAroundCard().get(i).getType().equals("Spell") && b != 0) {
                    currentPlayerGame().getHandCard().add(currentPlayerGame().getAroundCard().get(i));
                    currentPlayerGame().getAroundCard().remove(currentPlayerGame().getAroundCard().get(i));
                    i--;
                    b--;
                }
            }
        }
    }

    private void discover(Spell card, ArrayList<CardMechanicsEnum> cardMechanics) {
        String number = nextWord(card.getDescription(), "Discover");
        String type = nextWord(card.getDescription(), number);

        switch (type) {
            case "Card":
                gameController.moveCardAroundToHand(Integer.parseInt(number));
                break;
            case "Spell":
                gameController.moveSpellAroundToHand(Integer.parseInt(number));
                break;
            default:
                int num = Integer.parseInt(number);
                for (int i = 0; i < currentPlayerGame().getAroundCard().size(); i++) {
                    if (currentPlayerGame().getAroundCard().get(i).getType().equals(type) && num != 0) {
                        if (card.getDescription().contains("Increment")) {
                            ((Weapon) currentPlayerGame().getAroundCard().get(i)).plusAttack(2);
                            ((Weapon) currentPlayerGame().getAroundCard().get(i)).plusDurability(2);
                        }
                        if (currentPlayerGame().getHandCard().size() < 12) {
                            currentPlayerGame().getHandCard().add(currentPlayerGame().getAroundCard().get(i));
                        }
                        currentPlayerGame().getAroundCard().remove(currentPlayerGame().getAroundCard().get(i));

                        i--;
                        num--;
                    }
                }
        }
    }

    private void increment(Card card) {
        if (nextWord(card.getDescription(), "Increment").equals("Minions")) {
            int[] element = extractionNumber(nextWord(card.getDescription(), "Minions"));
            for (Card groundCard : currentPlayerGame().getGroundCard()) {
                ((Minion) groundCard).plusAttack(element[0]);
                ((Minion) groundCard).minusHealth(-1 * element[1]);
            }
        }
        if (card.getType().equals(CardType.Weapon.name())) {
            int[] element = extractionNumber(nextWord(card.getDescription(), "Increment"));
            ((Weapon) card).plusAttack(element[0]);
            ((Weapon) card).plusDurability(element[1]);
        }
    }

    private void discard(Spell card) {
        currentPlayerGame().getHandCard().removeIf(handCard -> handCard.getType().equals(CardType.Spell.name()) && handCard != card);
    }

    private void secret() {
        game.setIsSecretPlayerIndex(game.getPlayerIndex());
    }

    void playSecret(Minion minion) {
        if (game.getIsSecretPlayerIndex() == game.getNextPlayerIndex()) {
            for (int i = 0; i < 2; i++) {
                game.getPlayerGames(game.getNextPlayerIndex()).getHandCard().add(
                        FileManagement.getInstance().getCopy().copyCard(minion));
            }
            game.setIsSecretPlayerIndex(-1);
        }
    }

    public void checkQuest(Spell card) {
        Quest quest = currentPlayerGame().getQuest();
        if (quest.isOn() && quest.getQuestType() == QuestType.Spells) {
            quest.plusMana(card.getMana());
            if (quest.checkDoing()) {
                if (currentPlayerGame().getGroundCard().size() < 7) {
                    ArrayList<Minion> minions = FileManagement.getInstance().getReadCardFromFile().readMinion();
                    for (Card minionCard : minions) {
                        if (minionCard.getName().equals("Draconic Emissary")) {
                            currentPlayerGame().getGroundCard().add(minionCard);
                            game.getNewPlayedCard().add(minionCard);
                            break;
                        }
                    }

                }
            }
        }
    }

    public void removeCard(Spell card) {
        currentPlayerGame().getHandCard().remove(card);
    }
}
