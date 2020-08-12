package network.protocol;

import java.io.Serializable;
import java.util.ArrayList;

public class CollectionProtocol implements Serializable {
    private CollectionParameter collectionParameter;

    private ArrayList<DeckProtocol> playerDecks;
    private DeckProtocol freeDeck;
    private DeckProtocol gameDeck;
    private DeckProtocol selectedDeck;
    int maxDeckCardNumber;
    int minDeckCardNumber;

    public CollectionProtocol(CollectionParameter collectionParameter,
                              ArrayList<DeckProtocol> playerDecks, DeckProtocol freeDeck,
                              DeckProtocol gameDeck,
                              int maxDeckCardNumber, int minDeckCardNumber) {
        this.collectionParameter = collectionParameter;
        this.playerDecks = playerDecks;
        this.freeDeck = freeDeck;
        this.gameDeck = gameDeck;
        this.maxDeckCardNumber = maxDeckCardNumber;
        this.minDeckCardNumber = minDeckCardNumber;
    }

    public CollectionProtocol(CollectionParameter collectionParameter) {
        this.collectionParameter = collectionParameter;
        this.playerDecks = new ArrayList<>();
        this.freeDeck = null;
    }

    public CollectionParameter getCollectionParameter() {
        return collectionParameter;
    }

    public ArrayList<DeckProtocol> getPlayerDecks() {
        return playerDecks;
    }

    public void setPlayerDecks(ArrayList<DeckProtocol> playerDecks) {
        this.playerDecks = playerDecks;
    }

    public DeckProtocol getFreeDeck() {
        return freeDeck;
    }

    public DeckProtocol getGameDeck() {
        return gameDeck;
    }

    public int getMaxDeckCardNumber() {
        return maxDeckCardNumber;
    }

    public int getMinDeckCardNumber() {
        return minDeckCardNumber;
    }
    
}
