package network.client;


import network.protocol.ErrorNetworkEnum;
import network.protocol.NetworkProtocol;
import network.protocol.ParameterTyp;
import network.protocol.ShopParameter;
import userInterfaces.MyGraphics;
import userInterfaces.graphicsActions.AccountMenuAction;
import userInterfaces.graphicsActions.MainMenuAction;
import userInterfaces.graphicsActions.ShopMenuAction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver {

    private MyGraphics myGraphics;

    private NetworkProtocol networkProtocol;
    private ObjectInputStream inputStream;

    private AccountHandler accountHandler;
    private MainMenuHandler mainMenuHandler;
    private ShopMenuHandler shopMenuHandler;

    private AccountMenuAction accountMenuAction;
    private MainMenuAction mainMenuAction;
    private ShopMenuAction shopMenuAction;

    public Receiver(MyGraphics myGraphics, Socket socket) throws IOException {
        this.myGraphics = myGraphics;
        this.inputStream = new ObjectInputStream(socket.getInputStream());

        this.accountHandler = new AccountHandler();
        this.mainMenuHandler = new MainMenuHandler();
        this.shopMenuHandler = new ShopMenuHandler();

        this.accountMenuAction = myGraphics.getAccountMenu().getAccountMenuAction();
    }

    public void start() {
        while (true) {
            try {
                networkProtocol = (NetworkProtocol) inputStream.readObject();
                handleReceiveProtocol();
            } catch (IOException | ClassNotFoundException e) {

            }
        }
    }

    private void handleReceiveProtocol() {
        switch (networkProtocol.getProtocolType()) {
            case ERROR:
                handleErrorReceived();
                break;
            case CONNECT_TO_SERVER:
                accountHandler.connectToServer();
                break;
            case SIGN_IN_SUCCESS:
                accountHandler.signSuccess();
                break;
            case DELETE_ACCOUNT_SUCCESS:
                mainMenuHandler.deleteAccountSuccessful();
                break;
            case SHOP:
                shopMenuHandler.handelShopReceive();
                break;
        }
    }

    private void handleErrorReceived() {
        ErrorNetworkEnum errorNetworkEnum = ErrorNetworkEnum.valueOf(
                networkProtocol.getParameter().get(ParameterTyp.ERROR));
        switch (errorNetworkEnum) {
            case EXIST_USERNAME:
                accountHandler.existUsername();
                break;
            case EMPTY_FIElD:
                accountHandler.emptyField();
                break;
            case NOT_VALID_USERNAME:
                accountHandler.notValidUsername();
                break;
            case WRONG_PASSWORD:
                accountHandler.wrongPassword();
                break;
            case WRONG_PASSWORD_DELETE_ACCOUNT:
                mainMenuHandler.wrongPasswordDeleteAccount();
                break;

        }
    }

    class AccountHandler {
        private void connectToServer() {
            accountMenuAction.connectSuccessful();
        }

        private void signSuccess() {
            myGraphics.getClientNetwork().setAuthToken(networkProtocol.getAuthToken());
            accountMenuAction.signSuccess();
            mainMenuAction = myGraphics.getUserFrame().getMainMenu().getAction();
        }

        private void existUsername() {
            accountMenuAction.repeatedUsername();
        }

        private void emptyField() {
            accountMenuAction.emptyImport();
        }

        private void notValidUsername() {
            accountMenuAction.userNoExist();
        }

        private void wrongPassword() {
            accountMenuAction.wrongPassword();
        }
    }

    class MainMenuHandler{
        public void wrongPasswordDeleteAccount(){
            mainMenuAction.deleteAccountUnsuccessful();
        }

        public void deleteAccountSuccessful(){
            mainMenuAction.deleteAccountSuccessful();
        }
    }

    class ShopMenuHandler{
        private void handelShopReceive(){
            ShopParameter shopParameter = networkProtocol.getShopProtocol().getShopParameter();
            switch (shopParameter){
                case START_SHOP:
                    startShopMenu();
                    break;
                case SELL_SUCCESSFUL:
                    sellSuccessful();
                    break;
                case BUY_SUCCESSFUL:
                    buySuccessful();
                    break;
            }
        }

        private void sellSuccessful() {
            shopMenuAction.sellSuccessful(networkProtocol.getShopProtocol());
        }

        private void buySuccessful(){
            shopMenuAction.buySuccessful(networkProtocol.getShopProtocol());
        }

        private void startShopMenu(){
            mainMenuAction.goShopSuccessFul(networkProtocol.getShopProtocol());
            shopMenuAction = myGraphics.getUserFrame().getShopMenu().getAction();
        }
    }

}
