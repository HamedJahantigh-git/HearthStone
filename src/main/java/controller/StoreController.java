package controller;

import defaults.ModelDefault;
import model.Player;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;

import java.util.ArrayList;

public class StoreController {

    public static class canBuy {
        public static String[] canBuyMinion(Player player) {
            String[] result = new String[CardController.ReadCardFromFile.readMinion().size()];
            for (int i = 0; i < CardController.ReadCardFromFile.readMinion().size(); i++)
                result[i] = CardController.ReadCardFromFile.readMinion().get(i).getName();
            return result;
        }

        public static String[] canBuySpell(Player player) {
            String[] result = new String[CardController.ReadCardFromFile.readSpell().size()];
            for (int i = 0; i < CardController.ReadCardFromFile.readSpell().size(); i++)
                result[i] = CardController.ReadCardFromFile.readSpell().get(i).getName();
            return result;
        }

        public static String[] canBuyWeapon(Player player) {
            //todo
            return null;
        }
    }

    public static void cardSell(String card, Player player) throws Exception {
        boolean check = true;
        for (int i = 0; i < player.getFreePlayerCards().size(); i++) {
            if (player.getFreePlayerCards().get(i).getName().equals(card)) {
                player.setMoney(player.getMoney() + player.getFreePlayerCards().get(i).getIncomeSell());
                player.getFreePlayerCards().remove(player.getFreePlayerCards().get(i));
                check = false;
                break;
            }
        }
        if (check)
            throw new Exception(" card not valid");
    }

    public static void buyCard(String card, Player player) throws Exception {
        ArrayList<Minion> minionsCards;
        boolean check = true;
        minionsCards = CardController.ReadCardFromFile.readMinion();
        for (int i = 0; i < minionsCards.size(); i++) {
            if (minionsCards.get(i).getName().equals(card)) {
                if (player.getMoney() - minionsCards.get(i).getBuyCost() < 0) {
                    break;
                }
                player.setMoney(player.getMoney() - minionsCards.get(i).getBuyCost());
                player.addToFreePlayerCards(minionsCards.get(i));
                check = false;
                break;
            }
        }
        ArrayList<Spell> spellsCards;
        spellsCards = CardController.ReadCardFromFile.readSpell();
        for (int i = 0; i < spellsCards.size(); i++) {
            if (spellsCards.get(i).getName().equals(card)) {
                if (player.getMoney() - spellsCards.get(i).getBuyCost() < 0) {
                    break;
                }
                player.setMoney(player.getMoney() - spellsCards.get(i).getBuyCost());
                player.addToFreePlayerCards(spellsCards.get(i));
                check = false;
                break;
            }
        }
        if (check)
            throw new Exception(" card not valid");
    }

}
