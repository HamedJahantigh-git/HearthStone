package controller;


import java.io.File;
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

}
