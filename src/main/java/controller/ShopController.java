package controller;

import model.Player;
import model.card.Card;
import model.card.Minion;

public class ShopController {

    private Player player;

    public ShopController(Player player) {
        this.player = player;
    }

    public void sellCard(Card card) {
        for (int i = 0; i < player.getFreeDeck().getCards().size(); i++) {
            if (player.getFreeDeck().getCards().get(i).getName().equals(card.getName())) {
                player.getFreeDeck().getCards().get(i).setNumber(player.getFreeDeck().getCards().get(i).getNumber() - 1);
                player.getFreeDeck().getCards().remove(i);
                player.changeMoney(+card.getIncomeSell());
                break;
            }
        }
    }

    public void buyCard(Card card) {
        player.changeMoney(-card.getBuyCost());
        player.getFreeDeck().getCards().add(card);
       }

    //todo
    // creat exception in buy card
    // change logic from single town

}
