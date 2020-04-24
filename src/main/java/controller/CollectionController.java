package controller;

import model.Player;
import model.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class CollectionController {
    private Player player;

    public CollectionController(Player player) {
        this.player = player;
    }

    public ArrayList<Card> getCollectionCardsShow(String type, int mana, String searchWord, boolean userCards, boolean closedCards) {
        ArrayList<Card> cards = collectionCards(userCards, closedCards);
        typeFilter(cards, type);
        manaFilter(cards, mana);
        wordFilter(cards, searchWord);
        return cards;
    }

    private void manaFilter(ArrayList<Card> cards, int mana) {
        if (mana != 0) {
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).getMana() != mana) {
                    cards.remove(i);
                    i--;
                }
            }
        }
    }

    private void typeFilter(ArrayList<Card> cards, String type) {
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).getCardClass().equals(type)) {
                cards.remove(i);
                i--;
            }
        }
    }

    private void wordFilter(ArrayList<Card> cards, String word) {
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).getName().toLowerCase().contains(word.toLowerCase())) {
                cards.remove(i);
                i--;
            }
        }
    }

    private ArrayList<Card> collectionCards(boolean userCards, boolean closedCards) {
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> allCards = FileManagement.getInstance().getAllCardsFromFile();
        if (userCards)
            addPlayerCards(cards);
        if (closedCards) {
            boolean isDiffer = true;
            for (int i = 0; i < allCards.size(); i++) {
                isDiffer = true;
                for (int j = 0; j < player.getFreeDeck().getCards().size(); j++) {
                    if (player.getFreeDeck().getCards().get(j).getName().equals(allCards.get(i).getName())) {
                        isDiffer = false;
                    }
                }
                if (isDiffer) {
                    cards.add(allCards.get(i));
                }
            }
        }
        return cards;
    }

    private void cardSortByName(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size() - 1; i++)
            for (int j = 0; j < cards.size() - i - 1; j++)
                if (cards.get(j).getName().compareToIgnoreCase(cards.get(j + 1).getName()) > 0)
                    Collections.swap(cards, j, j + 1);
    }

    private void addPlayerCards(ArrayList<Card> cards) {
        cardSortByName(player.getFreeDeck().getCards());
        int counter = 1;
        for (int i = 0; i < player.getFreeDeck().getCards().size() - 1; i++) {
            if (player.getFreeDeck().getCards().get(i).getName().equals(player.getFreeDeck().getCards().get(i + 1).getName())) {
                counter++;
            } else {
                cards.add(player.getFreeDeck().getCards().get(i));
                cards.get(cards.size() - 1).setNumber(counter);
                counter = 1;
            }
        }
        cards.add(player.getFreeDeck().getCards().get(player.getFreeDeck().getCards().size() - 1));
        cards.get(cards.size() - 1).setNumber(counter);
    }


}
