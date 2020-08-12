package controller;

import defaults.ModelDefault;
import enums.ExceptionsEnum;
import model.Deck;
import model.Player;
import model.card.Card;
import model.hero.Hero;
import network.protocol.DeckProtocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class CollectionController {
    private Player player;

    public CollectionController(Player player) {
        this.player = player;
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

    public void setGameDeck (String deckName){
        player.setGameDeck(deckBuildFromName(deckName));
    }

    public void moveCardFromFreeToDeck(String deckName, String cardName) throws Exception {
        Deck deck = deckBuildFromName(deckName);
        Card card = cardBuildFromName(cardName);

//      if (deck.getCards().size() >= ModelDefault.deckDefaults.maxNumberCards)
//            throw new Exception(ExceptionsEnum.valueOf("fullDeckCards").getMessage());
//        if (!(deck.getHero().getHeroName().equals(card.getCardClass()) || card.getCardClass().equals("Neutral")))
//            throw new Exception(ExceptionsEnum.valueOf("unMatchingCardAndDeck").getMessage());
//        if (countCardInDeck(deck, card) >= 2)
//            throw new Exception(ExceptionsEnum.valueOf("moreTowCardExist").getMessage());
        for (int i = 0; i < player.getFreeDeck().getCards().size(); i++) {
            if (card.getName().equals(player.getFreeDeck().getCards().get(i).getName())) {
                deck.addCard(card);
                player.getFreeDeck().getCards().get(i).setNumber(player.getFreeDeck().getCards().get(i).getNumber() - 1);
                player.getFreeDeck().getCards().remove(player.getFreeDeck().getCards().get(i));
                break;
            }
        }
    }

    private Card cardBuildFromName(String cardName) {
        ArrayList<Card> allCard = new ArrayList<>();
        for (Deck deck: player.getPlayerDecks()) {
            allCard.addAll(deck.getCards());
        }
        allCard.addAll(player.getFreeDeck().getCards());
        for (Card card:allCard) {
            if(cardName.equals(card.getName()))
                return card;
        }
        return null;
    }

    public void moveCardFromDeckToFree(String deckName, String cardName) {
        Deck deck = deckBuildFromName(deckName);
        Card card = cardBuildFromName(cardName);
        for (int i = 0; i < deck.getCards().size(); i++) {
            if (card.getName().equals(deck.getCards().get(i).getName())) {
                player.getFreeDeck().getCards().add(card);
                deck.getCards().remove(i);
                break;
            }
        }
    }

    private Deck deckBuildFromName(String deckName){
        for (Deck deck:player.getPlayerDecks()) {
            if(deckName.equals(deck.getName()))
                return deck;
        }
        return null;
    }

    public void deleteDeck(String deckName) {
        Deck deck = deckBuildFromName(deckName);
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

    public void editDeckCharacteristics(String selectedDeckName, String newName, String newHeroName) throws Exception {
        Deck deck = deckBuildFromName(selectedDeckName);
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

}
