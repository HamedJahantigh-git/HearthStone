package controller;

import com.google.gson.Gson;
import defaults.FilesPath;
import defaults.ModelDefault;
import enums.ExceptionsEnum;
import model.Deck;
import model.Player;
import model.hero.Hero;
import network.protocol.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;


public class PlayerController {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private Player player;
    private CollectionController collectionController;
    private StatusController statusController;
    private ShopController shopController;

    public PlayerController(Socket socket) {
        try {
            this.socket = socket;
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            player = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public ShopController getShopController() {
        return shopController;
    }

    public CollectionController getCollectionController() {
        return collectionController;
    }

    public StatusController getStatusController() {
        return statusController;
    }

    public void signInPlayer(String username, String password) throws Exception {
        if (!FileManagement.getInstance().allFileNameInPath(
                FilesPath.PLAYER_DATA_PATH).contains(username + ".txt")) {
            throw new Exception(ExceptionsEnum.valueOf("userNoExist").getMessage());
        }

        player = FileManagement.getInstance().getPlayerFile().creatPlayerFromFile(username);
        collectionController = new CollectionController(player);
        statusController = new StatusController(player.getPlayerDecks());
        shopController = new ShopController(player);
        if (!player.getPassword().equals(password)) {
            throw new Exception(ExceptionsEnum.valueOf("wrongPassword").getMessage());
        }

    }

    public void signUpPlayer(String username, String password) throws Exception {
        Date registerTime = new Date();
        if (username.equals("") || password.equals(""))
            throw new Exception(ExceptionsEnum.valueOf("emptyImport").getMessage());
        if (checkExistUsername(username))
            throw new Exception(ExceptionsEnum.valueOf("userRepeated").getMessage());
        player = new Player(username, password, registerTime,
                numberAllPlayerSignIn());
        collectionController = new CollectionController(player);
        statusController = new StatusController(player.getPlayerDecks());
        shopController = new ShopController(player);
        FileManagement.getInstance().getPlayerFile().creatPlayerFile(player);
    }

    public void deleteAccount(String password) throws Exception {
        Gson gson = new Gson();
        player.setDeletePlayer(true);
        if (!password.equals(player.getPassword()))
            throw new Exception(ExceptionsEnum.wrongPassword.getMessage());
        try {
            Writer writer = new FileWriter(
                    FilesPath.DELETE_PLAYER_DATA_PATH + "/" + player.getUserName() +
                            "-" + String.valueOf(player.getId()) + ".txt");
            gson.toJson(player, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(FilesPath.PLAYER_DATA_PATH + "/" + player.getUserName() + ".txt");
        file.delete();
    }

    private boolean checkExistUsername(String username) {
        boolean answer = false;
        ArrayList<String> result;
        result = FileManagement.getInstance().allFileNameInPath(FilesPath.PLAYER_DATA_PATH);
        String compare;
        for (int i = 0; i < result.size(); i++) {
            compare = result.get(i);
            if (compare.equalsIgnoreCase(username + ".txt"))
                answer = true;
        }
        return answer;
    }

    public static int numberAllPlayerSignIn() {
        int result;
        ArrayList<String> name;
        name = FileManagement.getInstance().allFileNameInPath(FilesPath.PLAYER_DATA_PATH);
        result = name.size();
        name = FileManagement.getInstance().allFileNameInPath(FilesPath.DELETE_PLAYER_DATA_PATH);
        result += name.size();
        return result;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void creatShopProtocol(NetworkProtocol networkProtocol) {
        ShopProtocol shopProtocol = networkProtocol.getShopProtocol();
        shopProtocol.setBuyCard(FileManagement.getInstance().getAllCardsFromFile());
        shopProtocol.setSellCard(player.getFreeDeck().getCards());
        shopProtocol.setPlayerMoney(player.getMoney());
        networkProtocol.setShopProtocol(shopProtocol);
    }

    public void creatCollectionProtocol(NetworkProtocol networkProtocol,
                                        CollectionParameter collectionParameter) {
        ArrayList<DeckProtocol> playerDecksProtocol = new ArrayList<>();
        for (Deck deck : player.getPlayerDecks()) {
            playerDecksProtocol.add(deckProtocolBuilder(deck));
        }
        DeckProtocol freeDeckProtocol = deckProtocolBuilder(player.getFreeDeck());
        DeckProtocol gameDeck = deckProtocolBuilder(player.getGameDeck());
        networkProtocol.collectionState(collectionParameter, playerDecksProtocol,
                freeDeckProtocol, gameDeck, ModelDefault.deckDefaults.maxNumberCards,
                ModelDefault.deckDefaults.minNumberCards);
    }

    private DeckProtocol deckProtocolBuilder(Deck deck) {
        Hero hero = deck.getHero();
        HeroProtocol heroProtocol = new HeroProtocol(hero.getHeroName(), hero.getHealth(), hero.getHeroPowerMana(),
                hero.getHeroPowerCanUseInEveryTurn(), hero.isHeroPowerTargeted());
        return new DeckProtocol(deck.getName(), deck.getCards(),
                heroProtocol, deck.getVictoryNumber(), deck.getGameNumber());
    }

}
