package controller;

import defaults.ModelDefault;
import enums.ExceptionsEnum;
import model.Deck;
import model.Player;
import model.card.Card;
import model.hero.Hero;

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
        if (player.getFreeDeck().getCards().size() != 0) {
            cards.add(player.getFreeDeck().getCards().get(player.getFreeDeck().getCards().size() - 1));
            cards.get(cards.size() - 1).setNumber(counter);
        }
    }

    public void createNewDeck(String deckName, String heroName) {
        Deck deck;
        for (Hero hero : player.getPlayerHeroes()) {
            if (hero.getHeroName().equals(heroName)) {
                deck = new Deck(deckName, hero);
                player.getPlayerDecks().add(deck);
                break;
            }
        }
    }

    public void setGameDeck (Deck deck) throws Exception {
        if(deck.getCards().size()<ModelDefault.deckDefaults.minNumberCards)
            throw new Exception(ExceptionsEnum.valueOf("minDeckCard").getMessage());
        player.setGameDeck(deck);
    }

    public void moveCardFromFreeToDeck(Deck deck, Card card) throws Exception {
        if (deck == null)
            throw new Exception(ExceptionsEnum.valueOf("unSelectedDeck").getMessage());
        if (deck.getCards().size() >= ModelDefault.deckDefaults.maxNumberCards)
            throw new Exception(ExceptionsEnum.valueOf("fullDeckCards").getMessage());
        if (!(deck.getHero().getHeroName().equals(card.getCardClass()) || card.getCardClass().equals("Neutral")))
            throw new Exception(ExceptionsEnum.valueOf("unMatchingCardAndDeck").getMessage());
        if (countCardInDeck(deck, card) >= 2)
            throw new Exception(ExceptionsEnum.valueOf("moreTowCardExist").getMessage());
        for (int i = 0; i < player.getFreeDeck().getCards().size(); i++) {
            if (card.getName().equals(player.getFreeDeck().getCards().get(i).getName())) {
                deck.addCard(card);
                player.getFreeDeck().getCards().get(i).setNumber(player.getFreeDeck().getCards().get(i).getNumber() - 1);
                player.getFreeDeck().getCards().remove(player.getFreeDeck().getCards().get(i));
                break;
            }
        }
    }

    public void moveCardFromDeckToFree(Deck deck, Card card) {
        for (int i = 0; i < deck.getCards().size(); i++) {
            if (card.getName().equals(deck.getCards().get(i).getName())) {
                player.getFreeDeck().getCards().add(card);
                deck.getCards().remove(i);
                break;
            }
        }
    }

    public void deleteDeck(Deck deck) {
        player.getFreeDeck().getCards().addAll(deck.getCards());
        deck.getCards().removeAll(deck.getCards());
        player.setGameDeck(player.getFreeDeck());
        for (int i = 0; i < player.getPlayerDecks().size(); i++) {
            if (player.getPlayerDecks().get(i).getName().equals(deck.getName())) {
                player.getPlayerDecks().remove(i);
                break;
            }
        }
    }

    public void editDeckCharacteristics(Deck deck, String newName, String newHeroName) throws Exception {
        if (checkDeckHeroCard(deck.getHero().getHeroName(), deck)&&!newHeroName.equals(deck.getHero().getHeroName()))
            throw new Exception(ExceptionsEnum.valueOf("unEditableDeck").getMessage());
        deck.setName(newName);
        for (Hero hero : player.getPlayerHeroes()) {
            if (hero.getHeroName().equals(newHeroName)) {
                deck.setHero(hero);
                break;
            }
        }
    }

    private boolean checkDeckHeroCard(String heroName, Deck deck) {
        boolean result = false;
        for (Card card : deck.getCards()) {
            if (card.getCardClass().equals(heroName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private int countCardInDeck(Deck deck, Card card) {
        int count = 0;
        for (int i = 0; i < deck.getCards().size(); i++) {
            if (card.getName().equals(deck.getCards().get(i).getName())) {
                count++;
            }
        }
        return count;
    }

}
