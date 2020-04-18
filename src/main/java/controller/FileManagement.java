package controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import defaults.FilesPath;
import model.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileManagement {

    public static ArrayList<String> allFileNameInPath(String path) {
        ArrayList<String> result = new ArrayList<>();
        File f = new File(path);
        String[] name = f.list();
        for (int i = 0; i < name.length; i++)
            result.add(name[i]);
        return result;
    }

    public static void savePlayerToFile(Player player) {
        Gson gson = new GsonBuilder().create();
        try {
            Writer writer = new FileWriter(
                    FilesPath.playerDataPath + "/" + player.getUserName() + ".txt");
            gson.toJson(player, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readLineByLineFile(String filePath) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int Counter = 0;
        for (int i = 0; i < contentBuilder.toString().length(); i++) {
            if (contentBuilder.toString().charAt(i) == '\n') {
                result.add(contentBuilder.toString().substring(Counter, i));
                Counter = i + 1;
            }
        }
        return result;
    }

}
