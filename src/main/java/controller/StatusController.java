package controller;

import defaults.ModelDefault;
import model.Deck;
import model.card.Card;

import java.util.ArrayList;
import java.util.Collections;

public class StatusController {
    private ArrayList<Deck> playerDecks;

    public StatusController(ArrayList<Deck> playerDecks) {
        this.playerDecks = playerDecks;
    }

    public ArrayList<Deck> topDeck() {
        ArrayList<Deck> topDecks = new ArrayList<>();
        sortDecks();
        for (int i = 0; i < Math.min(ModelDefault.deckDefaults.topDeckNumber, playerDecks.size()); i++) {
            topDecks.add(playerDecks.get(i));
        }
        return topDecks;
    }

    private void sortDecks() {
        for (int i = 0; i < playerDecks.size() - 1; i++) {
            for (int j = 0; j < playerDecks.size() - i - 1; j++) {
                if (playerDecks.get(j).getVictoryDensity() < playerDecks.get(j + 1).getVictoryDensity())
                    Collections.swap(playerDecks, j, j + 1);
                if (playerDecks.get(j).getVictoryDensity() == playerDecks.get(j + 1).getVictoryDensity()) {
                    if (playerDecks.get(j).getVictoryNumber() < playerDecks.get(j + 1).getVictoryNumber()) {
                        Collections.swap(playerDecks, j, j + 1);
                    }
                    if (playerDecks.get(j).getVictoryNumber() == playerDecks.get(j + 1).getVictoryNumber()) {
                        if (playerDecks.get(j).getGameNumber() < playerDecks.get(j + 1).getGameNumber()) {
                            Collections.swap(playerDecks, j, j + 1);
                        }
                        if (playerDecks.get(j).getGameNumber() == playerDecks.get(j + 1).getGameNumber()) {
                            if (playerDecks.get(j).aveCardsMana() < playerDecks.get(j + 1).aveCardsMana()) {
                                Collections.swap(playerDecks, j, j + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public String sortDeckCard(Deck deck) {
        for (int i = 0; i < deck.getCards().size() - 1; i++) {
            for (int j = 0; j < deck.getCards().size() - i - 1; j++) {
                if (deck.getCards().get(j).getNumberUsage() < deck.getCards().get(j + 1).getNumberUsage()) {
                    Collections.swap(playerDecks, j, j + 1);
                }
                if (deck.getCards().get(j).getNumberUsage() == deck.getCards().get(j + 1).getNumberUsage()) {
                    if (deck.getCards().get(j).getRarityLevel() < deck.getCards().get(j + 1).getRarityLevel()) {
                        Collections.swap(playerDecks, j, j + 1);
                    }
                    if (deck.getCards().get(j).getRarityLevel() == deck.getCards().get(j + 1).getRarityLevel()) {
                        if (deck.getCards().get(j).getMana() < deck.getCards().get(j + 1).getMana()) {
                            Collections.swap(playerDecks, j, j + 1);
                        }
                        if (deck.getCards().get(j + 1).getType().equals("Minion")) {
                            Collections.swap(playerDecks, j, j + 1);
                        }
                    }
                }
            }
        }
        if (deck.getCards().size() == 0) {
            return "Deck has'nt Eny Card";
        }
        return deck.getCards().get(0).getName();
    }

}