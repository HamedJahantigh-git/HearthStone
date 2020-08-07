package userInterfaces;

import network.client.ClientNetwork;
import userInterfaces.userMenu.UserFrame;

public class MyGraphics {
    private ClientNetwork clientNetwork;

    private AccountMenu accountMenu;
    private UserFrame userFrame;

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
}
