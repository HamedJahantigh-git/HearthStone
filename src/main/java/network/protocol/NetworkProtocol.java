package network.protocol;

import model.hero.Hero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NetworkProtocol implements Serializable {

    private String authToken;
    private ProtocolType protocolType;
    private Map<ParameterTyp, String> parameter;
    private ShopProtocol shopProtocol;
    private CollectionProtocol collectionProtocol;
    private ArrayList<String> playerHeroesName;

    public NetworkProtocol(String authToken, ProtocolType protocolType) {
        this.protocolType = protocolType;
        this.parameter = new HashMap<>();
        this.authToken = authToken;
        this.shopProtocol = null;
        this.collectionProtocol = null;
        this.playerHeroesName = null;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public void error(ErrorNetworkEnum errorNetworkEnum) {
        parameter.put(ParameterTyp.ERROR, errorNetworkEnum.name());
    }

    public void signInUp(String username, String password) {
        parameter.put(ParameterTyp.USERNAME, username);
        parameter.put(ParameterTyp.PASSWORD, password);
    }

    public void shop(ShopParameter shopParameter) {
        shopProtocol = new ShopProtocol(shopParameter);
    }

    public void collection(CollectionParameter collectionParameter) {
        collectionProtocol = new CollectionProtocol(collectionParameter);
    }

    public void collectionState(CollectionParameter collectionParameter,
                                ArrayList<DeckProtocol> playerDecks, DeckProtocol freeDeck, DeckProtocol gameDeck,
                                int maxDeckCardNumber, int minDeckCardNumber) {
        collectionProtocol = new CollectionProtocol(collectionParameter,
                playerDecks, freeDeck, gameDeck, maxDeckCardNumber, minDeckCardNumber);
    }

    public String getAuthToken() {
        return authToken;
    }

    public Map<ParameterTyp, String> getParameter() {
        return parameter;
    }

    public void deleteAccount(String password) {
        parameter.put(ParameterTyp.PASSWORD, password);
    }

    public CollectionProtocol getCollectionProtocol() {
        return collectionProtocol;
    }

    public void setCollectionProtocol(CollectionProtocol collectionProtocol) {
        this.collectionProtocol = collectionProtocol;
    }

    public void setLog(String event, String eventDescription) {
        parameter.put(ParameterTyp.EVENT, event);
        parameter.put(ParameterTyp.EVENT_DESCRIPTION, eventDescription);
    }

    public ShopProtocol getShopProtocol() {
        return shopProtocol;
    }

    public void setShopProtocol(ShopProtocol shopProtocol) {
        this.shopProtocol = shopProtocol;
    }

    public void signSuccess(ArrayList<Hero> playerHeroes) {
        this.playerHeroesName = new ArrayList<>();
        for (Hero hero : playerHeroes) {
            playerHeroesName.add(hero.getHeroName());
        }
    }

    public ArrayList<String> getPlayerHeroesName() {
        return playerHeroesName;
    }

}
