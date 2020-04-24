package controller;

import defaults.ModelDefault;
import enums.ExceptionsEnum;
import model.Player;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;

import java.util.ArrayList;

public class StoreController {

    private static StoreController instance = new StoreController();

    private StoreController() {
    }

    public static StoreController getInstance() {
        return instance;
    }

    public void sellCard(Player player, Card card) {
        for (int i = 0; i < player.getFreeDeck().getCards().size(); i++) {
            if (player.getFreeDeck().getCards().get(i).getName().equals(card.getName())) {
                player.getFreeDeck().getCards().remove(i);
                player.changeMoney(+card.getIncomeSell());
                break;
            }
        }
    }

    public void buyCard(Player player, Card card) {
        player.changeMoney(-card.getBuyCost());
        player.getFreeDeck().getCards().add(card);
    }

    //todo
    // creat exception in buy card

}
