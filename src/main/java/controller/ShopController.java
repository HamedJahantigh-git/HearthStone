package controller;

import model.Player;
import model.card.Card;

public class ShopController {

    private static ShopController instance = new ShopController();

    private ShopController() {
    }

    public static ShopController getInstance() {
        return instance;
    }

    public void sellCard(Player player, Card card) {
        for (int i = 0; i < player.getFreeDeck().getCards().size(); i++) {
            if (player.getFreeDeck().getCards().get(i).getName().equals(card.getName())) {
                player.getFreeDeck().getCards().get(i).setNumber(player.getFreeDeck().getCards().get(i).getNumber()-1);
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
