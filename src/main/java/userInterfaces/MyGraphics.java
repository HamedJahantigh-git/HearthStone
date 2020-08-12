package userInterfaces;

import model.hero.Hero;
import network.client.ClientNetwork;
import userInterfaces.userMenu.UserFrame;

import java.util.ArrayList;

public class MyGraphics {
    private ClientNetwork clientNetwork;

    private AccountMenu accountMenu;
    private UserFrame userFrame;

    private ArrayList<String> playerHeroesName;

    public MyGraphics() {
    }

    public void startAccountMenu() {
        accountMenu = new AccountMenu(this);
        accountMenu.startClientNetwork();
    }

    public AccountMenu getAccountMenu() {
        return accountMenu;
    }

    public ClientNetwork getClientNetwork() {
        return clientNetwork;
    }

    public void setUserFrame(UserFrame userFrame) {
        this.userFrame = userFrame;
    }

    public void setClientNetwork(ClientNetwork clientNetwork) {
        this.clientNetwork = clientNetwork;
    }

    public UserFrame getUserFrame() {
        return userFrame;
    }

    public ArrayList<String> getPlayerHeroesName() {
        return playerHeroesName;
    }

    public void setPlayerHeroesName(ArrayList<String> playerHeroesName) {
        this.playerHeroesName = playerHeroesName;
    }
}
