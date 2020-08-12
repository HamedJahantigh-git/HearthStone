package network.server;


import controller.FileManagement;
import controller.PlayerController;
import enums.ExceptionsEnum;
import enums.LogsEnum;
import logs.PlayerLogs;
import model.MyThread;
import model.card.Card;
import network.protocol.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Transceiver extends MyThread {

    private ObjectOutputStream mineOutputStream;
    private ObjectInputStream inputStream;
    private NetworkProtocol networkProtocol;

    private PlayerController playerController;

    private AccountHandler accountHandler;
    private MainMenuHandler mainMenuHandler;
    private ShopMenuHandler shopMenuHandler;
    private CollectionMenuHandler collectionMenuHandler;

    public Transceiver(PlayerController playerController) {
        this.playerController = playerController;

        this.accountHandler = new AccountHandler();
        this.mainMenuHandler = new MainMenuHandler();
        this.shopMenuHandler = new ShopMenuHandler();
        this.collectionMenuHandler = new CollectionMenuHandler();

        this.inputStream = playerController.getInputStream();
        this.mineOutputStream = playerController.getOutputStream();
    }

    @Override
    public void run() {
        accountHandler.ConnectToServer();
        receive();
    }

    private void receive() {
        while (true) {
            try {
                networkProtocol = (NetworkProtocol) inputStream.readObject();
                handleReceiveProtocol();
            } catch (IOException | ClassNotFoundException e) {
                break;
            }
        }
    }

    private void send() {
        try {
            mineOutputStream.reset();
            mineOutputStream.writeObject(networkProtocol);
            mineOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleReceiveProtocol() {
        switch (networkProtocol.getProtocolType()) {
            case EXIT_GAME:
                exitGame();
                break;
            case SAVE_TO_LOG:
                saveToLog();
                break;
            case SIGN_IN:
                accountHandler.SignIn();
                break;
            case SIGN_UP:
                accountHandler.SignUp();
                break;
            case DELETE_ACCOUNT:
                mainMenuHandler.deleteAccount();
                break;
            case BACK_TO_MAIN_MENU:
                mainMenuHandler.backToMainMenu();
                break;
            case SHOP:
                shopMenuHandler.handelReceiveShopProtocol();
                break;
            case COLLECTION:
                collectionMenuHandler.handelReceiveCollectionProtocol();
        }
    }

    private void saveToLog() {
        String event = networkProtocol.getParameter().get(ParameterTyp.EVENT);
        String eventDescription = networkProtocol.getParameter().get(ParameterTyp.EVENT_DESCRIPTION);
        PlayerLogs.addToLogBody(event, eventDescription, playerController.getPlayer());
    }

    private void exitGame() {
        if (playerController.getPlayer() != null) {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[2],
                    LogsEnum.valueOf("sign").getEvent_description()[3], playerController.getPlayer());
            FileManagement.getInstance().getPlayerFile().savePlayerToFile(playerController.getPlayer());
        }
    }

    class AccountHandler {

        private void ConnectToServer() {
            networkProtocol = new NetworkProtocol(null, ProtocolType.CONNECT_TO_SERVER);
            send();
        }

        private void SignIn() {
            try {
                String username = networkProtocol.getParameter().get(ParameterTyp.USERNAME);
                String password = networkProtocol.getParameter().get(ParameterTyp.PASSWORD);
                playerController.signInPlayer(username, password);
                PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[1],
                        LogsEnum.valueOf("sign").getEvent_description()[1], playerController.getPlayer());
                PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[1],
                        LogsEnum.valueOf("sign").getEvent_description()[1], playerController.getPlayer());
                playerController.getPlayer().setAuthToken();
                String playerAuthToken = playerController.getPlayer().getAuthToken();
                ServerModel.getInstance().addOnlinePlayer(playerAuthToken, playerController);
                networkProtocol = new NetworkProtocol(playerAuthToken, ProtocolType.SIGN_IN_SUCCESS);
                networkProtocol.signSuccess(playerController.getPlayer().getPlayerHeroes());
            } catch (Exception e) {
                if (e.getMessage().equals(ExceptionsEnum.valueOf("wrongPassword").getMessage())) {
                    networkProtocol = new NetworkProtocol(networkProtocol.getAuthToken(), ProtocolType.ERROR);
                    networkProtocol.error(ErrorNetworkEnum.WRONG_PASSWORD);
                }
                if (e.getMessage().equals(ExceptionsEnum.valueOf("userNoExist").getMessage())) {
                    networkProtocol = new NetworkProtocol(networkProtocol.getAuthToken(), ProtocolType.ERROR);
                    networkProtocol.error(ErrorNetworkEnum.NOT_VALID_USERNAME);
                }
            } finally {
                send();
            }

        }

        private void SignUp() {
            try {
                String username = networkProtocol.getParameter().get(ParameterTyp.USERNAME);
                String password = networkProtocol.getParameter().get(ParameterTyp.PASSWORD);
                playerController.signUpPlayer(username, password);
                PlayerLogs.creatLogFile(playerController.getPlayer());
                PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[0],
                        LogsEnum.valueOf("sign").getEvent_description()[0], playerController.getPlayer());
                networkProtocol = new NetworkProtocol(networkProtocol.getAuthToken(), ProtocolType.SIGN_IN_SUCCESS);
                networkProtocol.signSuccess(playerController.getPlayer().getPlayerHeroes());
            } catch (Exception e) {
                if (e.getMessage().equals(ExceptionsEnum.valueOf("emptyImport").getMessage())) {
                    networkProtocol = new NetworkProtocol(networkProtocol.getAuthToken(), ProtocolType.ERROR);
                    networkProtocol.error(ErrorNetworkEnum.EMPTY_FIElD);
                }
                if (e.getMessage().equals(ExceptionsEnum.valueOf("userRepeated").getMessage())) {
                    networkProtocol = new NetworkProtocol(networkProtocol.getAuthToken(), ProtocolType.ERROR);
                    networkProtocol.error(ErrorNetworkEnum.EXIST_USERNAME);
                }
            } finally {
                send();
            }

        }
    }

    class MainMenuHandler {
        private void deleteAccount() {
            try {
                String password = networkProtocol.getParameter().get(ParameterTyp.PASSWORD);
                playerController.deleteAccount(password);
                networkProtocol = new NetworkProtocol(networkProtocol.getAuthToken(), ProtocolType.DELETE_ACCOUNT_SUCCESS);
            } catch (Exception e) {
                if (e.getMessage().equals(ExceptionsEnum.wrongPassword.getMessage())) {
                    networkProtocol = new NetworkProtocol(networkProtocol.getAuthToken(), ProtocolType.ERROR);
                    networkProtocol.error(ErrorNetworkEnum.WRONG_PASSWORD_DELETE_ACCOUNT);
                } else
                    e.printStackTrace();
            } finally {
                send();
            }
        }


        private void backToMainMenu() {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("back").getEvent()[0],
                    LogsEnum.valueOf("back").getEvent_description()[0], playerController.getPlayer());
        }
    }

    class ShopMenuHandler {

        public void handelReceiveShopProtocol() {
            ShopParameter shopParameter = networkProtocol.getShopProtocol().getShopParameter();
            switch (shopParameter) {
                case GO_SHOP:
                    goShop();
                    break;
                case START_BUYING:
                    startBuying();
                    break;
                case START_SELLING:
                    startSelling();
                    break;
                case SELL_CARD:
                    sellCard();
                    break;
                case BUY_CARD:
                    buyCard();
                    break;

            }
        }

        private void sellCard() {
            Card card = networkProtocol.getShopProtocol().getAimCard();
            PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[5],
                    LogsEnum.valueOf("shop").getEvent_description()[5] + card.getName(), playerController.getPlayer());
            playerController.getShopController().sellCard(card);
            //todo
            // change single town
            FileManagement.getInstance().getPlayerFile().savePlayerToFile(playerController.getPlayer());
            networkProtocol = new NetworkProtocol(networkProtocol.getAuthToken(), ProtocolType.SHOP);
            networkProtocol.shop(ShopParameter.SELL_SUCCESSFUL);
            playerController.creatShopProtocol(networkProtocol);
            send();
        }

        private void buyCard() {
            Card card = networkProtocol.getShopProtocol().getAimCard();
            PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[5],
                    LogsEnum.valueOf("shop").getEvent_description()[6] + card.getName(), playerController.getPlayer());
            playerController.getShopController().buyCard(card);
            //todo
            // change single town
            FileManagement.getInstance().getPlayerFile().savePlayerToFile(playerController.getPlayer());
            networkProtocol = new NetworkProtocol(networkProtocol.getAuthToken(), ProtocolType.SHOP);
            networkProtocol.shop(ShopParameter.BUY_SUCCESSFUL);
            playerController.creatShopProtocol(networkProtocol);
            send();
        }

        private void startSelling() {
            //todo
            // change single town
            PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[1],
                    LogsEnum.valueOf("shop").getEvent_description()[1], playerController.getPlayer());
        }

        private void startBuying() {
            PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[2],
                    LogsEnum.valueOf("shop").getEvent_description()[2], playerController.getPlayer());
        }

        private void goShop() {
            networkProtocol = new NetworkProtocol(
                    networkProtocol.getAuthToken(), ProtocolType.SHOP);
            networkProtocol.shop(ShopParameter.START_SHOP);
            playerController.creatShopProtocol(networkProtocol);
            PlayerLogs.addToLogBody(LogsEnum.valueOf("shop").getEvent()[0],
                    LogsEnum.valueOf("shop").getEvent_description()[0], playerController.getPlayer());
            send();
        }
    }

    class CollectionMenuHandler {
        private void handelReceiveCollectionProtocol() {
            CollectionParameter collectionParameter = networkProtocol.
                    getCollectionProtocol().getCollectionParameter();
            switch (collectionParameter) {
                case GO_COLLECTION:
                    goCollection();
                    break;
                case NEW_DECK:
                    newDeck();
                    break;
                case CARD_TO_DECK:
                    cardToDeck();
                    break;
                case CARD_FROM_DECK:
                    cardFromDeck();
                    break;
                case SELECT_DECK_GAME:
                    selectDeckForGame();
                    break;
                case DELETE_DECK:
                    deleteDeck();
                    break;
                case EDIT_DECK:
                    editDeck();
                    break;
            }
        }

        private void editDeck() {
            try {
                String selectedDeckName = networkProtocol.getParameter().get(ParameterTyp.SELECTED_DECK);
                String newDeckName = networkProtocol.getParameter().get(ParameterTyp.DECK_NAME);
                String newHeroName = networkProtocol.getParameter().get(ParameterTyp.HERO_NAME);
                playerController.getCollectionController().editDeckCharacteristics(
                        selectedDeckName, newDeckName, newHeroName);
                PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[12],
                        LogsEnum.valueOf("collection").getEvent_description()[10] + newDeckName + "/" + newHeroName,
                        playerController.getPlayer());
                FileManagement.getInstance().getPlayerFile().savePlayerToFile(playerController.getPlayer());
                networkProtocol = new NetworkProtocol(
                        networkProtocol.getAuthToken(), ProtocolType.COLLECTION);
                playerController.creatCollectionProtocol(networkProtocol, CollectionParameter.EDIT_DECK_SUCCESS);
                send();
            } catch (Exception e) {
                networkProtocol = new NetworkProtocol(
                        networkProtocol.getAuthToken(), ProtocolType.COLLECTION);
                networkProtocol.collection(CollectionParameter.EDIT_DECK_UNSUCCESSFUL);
                send();
            }

        }

        private void deleteDeck() {
            String deckName = networkProtocol.getParameter().get(ParameterTyp.SELECTED_DECK);
            playerController.getCollectionController().deleteDeck(deckName);
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[10],
                    LogsEnum.valueOf("collection").getEvent_description()[8] + deckName,
                    playerController.getPlayer());
            FileManagement.getInstance().getPlayerFile().savePlayerToFile(playerController.getPlayer());
            networkProtocol = new NetworkProtocol(
                    networkProtocol.getAuthToken(), ProtocolType.COLLECTION);
            playerController.creatCollectionProtocol(networkProtocol, CollectionParameter.DELETE_DECK_SUCCESS);
            send();
        }

        private void selectDeckForGame() {
            String deckName = networkProtocol.getParameter().get(ParameterTyp.SELECTED_DECK);
            playerController.getCollectionController().setGameDeck(deckName);
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[8],
                    LogsEnum.valueOf("collection").getEvent_description()[6] + deckName,
                    playerController.getPlayer());
            FileManagement.getInstance().getPlayerFile().savePlayerToFile(playerController.getPlayer());
            networkProtocol = new NetworkProtocol(
                    networkProtocol.getAuthToken(), ProtocolType.COLLECTION);
            playerController.creatCollectionProtocol(networkProtocol, CollectionParameter.SELECT_DECK_GAME_SUCCESS);
            send();
        }

        private void cardToDeck() {
            try {
                String deckName = networkProtocol.getParameter().get(ParameterTyp.SELECTED_DECK);
                String cardName = networkProtocol.getParameter().get(ParameterTyp.CARD_NAME);
                playerController.getCollectionController().moveCardFromFreeToDeck(
                        deckName, cardName);
                PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[6],
                        cardName + "_add_to:" + deckName, playerController.getPlayer());
                FileManagement.getInstance().getPlayerFile().savePlayerToFile(playerController.getPlayer());
                networkProtocol = new NetworkProtocol(
                        networkProtocol.getAuthToken(), ProtocolType.COLLECTION);
                playerController.creatCollectionProtocol(networkProtocol, CollectionParameter.TRANSFER_CARD_DECK_SUCCESS);
                send();
            } catch (Exception e) {
//                if (e.getMessage().equals(ExceptionsEnum.valueOf("fullDeckCards").getMessage())){
//
//                }
//                if (e.getMessage().equals(ExceptionsEnum.valueOf("fullDeckCards").getMessage())){
//
//                }
//                if (e.getMessage().equals(ExceptionsEnum.valueOf("unMatchingCardAndDeck").getMessage())){
//
//                }
//                if (e.getMessage().equals(ExceptionsEnum.valueOf("moreTowCardExist").getMessage())){
//
//                }
            }
        }

        private void cardFromDeck() {
            String deckName = networkProtocol.getParameter().get(ParameterTyp.SELECTED_DECK);
            String cardName = networkProtocol.getParameter().get(ParameterTyp.CARD_NAME);
            playerController.getCollectionController().moveCardFromDeckToFree(deckName, cardName);
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[7],
                    cardName + "_removed_from" + deckName, playerController.getPlayer());
            FileManagement.getInstance().getPlayerFile().savePlayerToFile(playerController.getPlayer());
            networkProtocol = new NetworkProtocol(
                    networkProtocol.getAuthToken(), ProtocolType.COLLECTION);
            playerController.creatCollectionProtocol(networkProtocol, CollectionParameter.TRANSFER_CARD_DECK_SUCCESS);
            send();
        }

        private void newDeck() {
            String deckName = networkProtocol.getParameter().get(ParameterTyp.DECK_NAME);
            String heroName = networkProtocol.getParameter().get(ParameterTyp.HERO_NAME);
            playerController.getCollectionController().createNewDeck(deckName, heroName);
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[4],
                    LogsEnum.valueOf("collection").getEvent_description()[3] + "_" + deckName + ":" + heroName, playerController.getPlayer());
            FileManagement.getInstance().getPlayerFile().savePlayerToFile(playerController.getPlayer());
            networkProtocol = new NetworkProtocol(
                    networkProtocol.getAuthToken(), ProtocolType.COLLECTION);
            playerController.creatCollectionProtocol(networkProtocol, CollectionParameter.NEW_DECK_SUCCESS);
            send();
        }

        private void goCollection() {
            networkProtocol = new NetworkProtocol(
                    networkProtocol.getAuthToken(), ProtocolType.COLLECTION);
            playerController.creatCollectionProtocol(networkProtocol, CollectionParameter.START_COLLECTION);
            PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[0],
                    LogsEnum.valueOf("collection").getEvent_description()[0], playerController.getPlayer());
            send();
        }

    }


}
