package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.ExceptionsEnum;
import model.Player;
import model.card.Card;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;


public class PlayerController {
    private Player player;
    private CollectionController collectionController;
    private StatusController statusController;

    public PlayerController() {
    }

    public Player getPlayer() {
        return player;
    }

    public CollectionController getCollectionController() {
        return collectionController;
    }

    public StatusController getStatusController() {
        return statusController;
    }

    public void signInPlayer(String username, String password) throws Exception {
        if (!FileManagement.getInstance().allFileNameInPath(
                FilesPath.playerDataPath).contains(username+".txt")) {
            throw new Exception(ExceptionsEnum.valueOf("userNoExist").getMessage());
        }

        player = FileManagement.getInstance().getPlayerFile().creatPlayerFromFile(username);
        collectionController = new CollectionController(player);
        statusController = new StatusController(player.getPlayerDecks());
        if (!player.getPassword().equals(password)) {
            throw new Exception(ExceptionsEnum.valueOf("wrongPassword").getMessage());
        }

    }

    public void signUpPlayer(String username, String password) throws Exception {
        Date registerTime = new Date();
        if (username.equals("")||password.equals(""))
            throw new Exception(ExceptionsEnum.valueOf("emptyImport").getMessage());
        if (checkExistUsername(username))
            throw new Exception(ExceptionsEnum.valueOf("userRepeated").getMessage());
        player = new Player(username, password, registerTime,
                numberAllPlayerSignIn());
        collectionController = new CollectionController(player);
        statusController = new StatusController(player.getPlayerDecks());
        FileManagement.getInstance().getPlayerFile().creatPlayerFile(player);
    }

    public void deleteAccount(String password) throws Exception {
        Gson gson = new Gson();
        player.setDeletePlayer(true);
        if(!password.equals(player.getPassword()))
            throw new Exception(ExceptionsEnum.wrongPassword.getMessage());
        try {
            Writer writer = new FileWriter(
                    FilesPath.deletePlayerDataPath + "/" + player.getUserName() +
                            "-" + String.valueOf(player.getId()) + ".txt");
            gson.toJson(player, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(FilesPath.playerDataPath + "/" + player.getUserName() + ".txt");
        file.delete();
    }

    private boolean checkExistUsername(String username) {
        boolean answer = false;
        ArrayList<String> result;
        result = FileManagement.getInstance().allFileNameInPath(FilesPath.playerDataPath);
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
        name = FileManagement.getInstance().allFileNameInPath(FilesPath.playerDataPath);
        result = name.size();
        name = FileManagement.getInstance().allFileNameInPath(FilesPath.deletePlayerDataPath);
        result += name.size();
        return result;
    }

}
