package network.protocol;

import model.card.Card;

import java.io.Serializable;
import java.util.ArrayList;

public class ShopProtocol implements Serializable {
    private ShopParameter shopParameter;
    private ArrayList<Card> buyCard;
    private ArrayList<Card> sellCard;
    private Card aimCard;
    private int playerMoney;

    public ShopProtocol(ShopParameter shopParameter) {
        this.shopParameter = shopParameter;
        this.buyCard = new ArrayList<>();
        this.sellCard = new ArrayList<>();
    }

    public ArrayList<Card> getBuyCard() {
        return buyCard;
    }

    public ArrayList<Card> getSellCard() {
        return sellCard;
    }

    public void setBuyCard(ArrayList<Card> buyCard) {
        this.buyCard = buyCard;
    }

    public void setSellCard(ArrayList<Card> sellCard) {
        this.sellCard = sellCard;
    }

    public ShopParameter getShopParameter() {
        return shopParameter;
    }

    public void setShopParameter(ShopParameter shopParameter) {
        this.shopParameter = shopParameter;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public void setAimCard(Card aimCard) {
        this.aimCard = aimCard;
    }

    public Card getAimCard() {
        return aimCard;
    }
}
