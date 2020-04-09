package controller;


import CLI.CLI;
import CLI.Menu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import defaults.FilesPath;
import logs.PlayerLogs;
import model.Player;
import model.card.Card;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PlayerController {
    private String username;
    private String password;
    Gson gson = new GsonBuilder().create();

    public PlayerController(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Player signInPlayer() {
        Player player = null;
        try {
            if (!FileManagement.allFileNameInPath(
                    FilesPath.playerDataPath).contains(username + ".txt")) {
                throw new Exception("not valid");
            }
            player = creatPlayerFromFile(
                    FilesPath.playerDataPath, username);
            if (!player.getPassword().equals(password)) {
                System.out.println(" - Your password is wrong.");
                System.out.println("  Please try again.");
                Menu.accountMenu();
            }
        } catch (Exception e) {
            System.out.println("- Your username is wrong!!");
            System.out.println("  Please try again.");
            Menu.accountMenu();
        }
        return player;
    }

    public Player signUpPlayer() {
        Date registerTime = new Date();

        Player player = new Player(this.username, this.password, registerTime,
                CLI.numberAllPlayerSignIn());
        try {
            if (PlayerController.checkExistUsername(player.getUserName()))
                throw new Exception("not valid.");
            try {
                Writer writer = new FileWriter(
                        FilesPath.playerDataPath + "/" + this.username + ".txt");
                gson.toJson(player, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("- This username is repeated!!!");
            System.out.println("  Please try again.");
            System.out.println();
            Menu.accountMenu();
        }
        return player;
    }

    public void savePlayerToFile(Player player) {
        try {
            Writer writer = new FileWriter(
                    FilesPath.playerDataPath + "/" + player.getUserName() + ".txt");
            gson.toJson(player, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        } catch (Exception e) {
        }
        return player;
    }

    private static boolean checkExistUsername(String username) {
        boolean answer = false;
        ArrayList<String> result = new ArrayList<String>();
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

}
