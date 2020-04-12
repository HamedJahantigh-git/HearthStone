package controller;

import CLI.CLIMenu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import defaults.FilesPath;
import model.Player;
import model.card.Card;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;


public class PlayerController {
    private Player player;
    Gson gson = new GsonBuilder().create();

    public Player getPlayer() {
        return player;
    }

    public void signInPlayer(String username, String password) {
        try {
            if (!FileManagement.allFileNameInPath(
                    FilesPath.playerDataPath).contains(username + ".txt")) {
                System.out.println("aaa");
                throw new Exception("not valid");
            }
            player = creatPlayerFromFile(
                    FilesPath.playerDataPath, username);
            if (!player.getPassword().equals(password)) {
                System.out.println(" - Your password is wrong.");
                System.out.println("  Please try again.");
                CLIMenu.getInstance().accountMenu();
            }
        } catch (Exception e) {
            System.out.println("- Your username is wrong!!");
            System.out.println("  Please try again.");
            CLIMenu.getInstance().accountMenu();
        }
    }

    public void signUpPlayer(String username, String password) {
        Date registerTime = new Date();
        try {
            if (checkExistUsername(username))
                throw new Exception("not valid.");
            player = new Player(username, password, registerTime,
                    numberAllPlayerSignIn());
            try {
                Writer writer = new FileWriter(
                        FilesPath.playerDataPath + "/" + username + ".txt");
                gson.toJson(player, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("- This username is repeated!!!");
            System.out.println("  Please try again.");
            System.out.println();
            CLIMenu.getInstance().accountMenu();
        }

    }

    public void deleteAccount(Player player) {
        player.setDeletePlayer(true);
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

    public Player creatPlayerFromFile(String path, String name) {
        Player player = null;
        Gson gson = new Gson();
        try (Reader reader = new FileReader(path + "/" + name + ".txt")) {
            player = gson.fromJson(reader, Player.class);
        } catch (Exception ignored) {
        }
        return player;
    }

    private boolean checkExistUsername(String username) {
        boolean answer = false;
        ArrayList<String> result;
        result = FileManagement.allFileNameInPath(FilesPath.playerDataPath);
        String compare;
        for (int i = 0; i < result.size(); i++) {
            compare = result.get(i);
            if (compare.equalsIgnoreCase(username + ".txt"))
                answer = true;
        }
        return answer;
    }

    public ArrayList<Card> playerAllCards(Player player) {
        ArrayList<Card> result = new ArrayList<>();
        result.addAll(player.getFreePlayerCards());
        for (int i = 0; i < player.getPlayerHeroes().size(); i++) {
            result.addAll(player.getPlayerHeroes().get(i).getHeroCards());
        }
        return result;
    }

    public static int numberAllPlayerSignIn() {
        int result;
        ArrayList<String> name;
        name = FileManagement.allFileNameInPath(FilesPath.playerDataPath);
        result = name.size();
        name = FileManagement.allFileNameInPath(FilesPath.deletePlayerDataPath);
        result += name.size();
        return result;
    }

}
