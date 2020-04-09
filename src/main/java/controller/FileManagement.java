package controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import defaults.FilesPath;
import model.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class FileManagement {

    public static ArrayList<String> allFileNameInPath(String path) {
        ArrayList<String> result = new ArrayList<String>();
        File f = new File(path);
        String[] name = f.list();
        for (int i = 0; i < name.length; i++)
            result.add(name[i]);
        return result;
    }

    public static void  savePlayerToFile(Player player) {
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

}
