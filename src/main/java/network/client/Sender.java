package network.client;


import model.card.Card;
import network.protocol.NetworkProtocol;
import network.protocol.ProtocolType;
import network.protocol.ShopParameter;
import network.protocol.ShopProtocol;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sender {

    private ClientNetwork clientNetwork;
    private ObjectOutputStream outputStream;
    private NetworkProtocol networkProtocol;

    private AccountHandler accountHandler;
    private MainMenuHandler mainMenuHandler;
    private ShopMenuHandler shopMenuHandler;
    private CollectionMenuHandler collectionMenuHandler;

    public Sender(ClientNetwork clientNetwork, Socket socket) throws IOException {
        this.clientNetwork = clientNetwork;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());

        this.accountHandler = new AccountHandler();
        this.mainMenuHandler = new MainMenuHandler();
        this.shopMenuHandler = new ShopMenuHandler();
        this.collectionMenuHandler = new CollectionMenuHandler();
    }

    private void send() {
        try {
            outputStream.writeObject(networkProtocol);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AccountHandler getAccountHandler() {
        return accountHandler;
    }

    public MainMenuHandler getMainMenuHandler() {
        return mainMenuHandler;
    }

    public ShopMenuHandler getShopMenuHandler() {
        return shopMenuHandler;
    }

    public CollectionMenuHandler getCollectionMenuHandler() {
        return collectionMenuHandler;
    }

    public void exitGame() {
        networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.EXIT_GAME);
        send();
    }

    public class AccountHandler {

        public void signIn(String username, String password) {
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.SIGN_IN);
            networkProtocol.signInUp(username, password);
            send();
        }

        public void signUp(String username, String password) {
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.SIGN_UP);
            networkProtocol.signInUp(username, password);
            send();
        }
    }

    public class MainMenuHandler {
        public void deleteAccount(String password){
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.DELETE_ACCOUNT);
            networkProtocol.deleteAccount(password);
            send();
        }

        public void goShop() {
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(),ProtocolType.SHOP);
            networkProtocol.shop(ShopParameter.GO_SHOP);
            send();
        }

        public void backToMainMenu() {
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.BACK_TO_MAIN_MENU);
            send();
        }
    }

    public class ShopMenuHandler{

        public void startBuying(){
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.SHOP);
            networkProtocol.shop(ShopParameter.START_BUYING);
            send();
        }

        public void startSelling() {
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.SHOP);
            networkProtocol.shop(ShopParameter.START_SELLING);
            send();
        }

        public void sellCard(Card card) {
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.SHOP);
            networkProtocol.shop(ShopParameter.SELL_CARD);
            networkProtocol.getShopProtocol().setAimCard(card);
            send();
        }

        public void buyCard(Card card){
            networkProtocol = new NetworkProtocol(clientNetwork.getAuthToken(), ProtocolType.SHOP);
            networkProtocol.shop(ShopParameter.BUY_CARD);
            networkProtocol.getShopProtocol().setAimCard(card);
            send();
        }
    }

    public class CollectionMenuHandler{

    }


}
