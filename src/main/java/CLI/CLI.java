package CLI;

import controller.FileManagement;

import defaults.FilesPath;
import initializer.InitCLI;
import initializer.InitCards;


import java.util.ArrayList;


public class CLI {

    public static void startCLI() {
        InitCLI.createNeedsFolder();
        InitCards.initSpell();
        InitCards.initMinion();
        InitCLI.headerBox("Welcome To HearthStone Game");
        Menu.accountMenu();
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
