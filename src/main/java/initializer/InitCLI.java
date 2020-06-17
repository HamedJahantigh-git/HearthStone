package initializer;


import controller.FileManagement;
import defaults.FilesPath;

import java.io.File;

public class InitCLI {

    public static int screenLength = 70;

    public static void headerBox(String title) {
        int length = screenLength;
        System.out.println(org.apache.commons.lang3.StringUtils.rightPad("+", length - 1, "-") + "+");
        System.out.println(org.apache.commons.lang3.StringUtils.center(org.apache.commons.lang3.StringUtils.center(
                "*" + title + "*", length - 2), length, "|"));
        System.out.println(org.apache.commons.lang3.StringUtils.rightPad("+", length - 1, "-") + "+");
        System.out.println();
    }

    public static void frameCreator(String title, String[] str) {
        int length = screenLength;
        System.out.println(org.apache.commons.lang3.StringUtils.rightPad("/", length - 1, "*") + "\\");
        System.out.println(org.apache.commons.lang3.StringUtils.center(org.apache.commons.lang3.StringUtils.center(
                "@ " + title + " @", length - 2), length, " "));
        for (int i = 0; i < str.length; i++)
            System.out.println(str[i]);
        System.out.println(org.apache.commons.lang3.StringUtils.rightPad("\\", length - 1, "*") + "/");
    }

    public static void createNeedsFolder() {
        FileManagement.getInstance().creatFolder(FilesPath.gameDataPath);
        FileManagement.getInstance().creatFolder(FilesPath.gameInfo);
        FileManagement.getInstance().creatFolder(FilesPath.logsPath);
        FileManagement.getInstance().creatFolder(FilesPath.playerLogsPath);
        FileManagement.getInstance().creatFolder(FilesPath.gameEventLogsPath);
        FileManagement.getInstance().creatFolder(FilesPath.heroDataPath);
        FileManagement.getInstance().creatFolder(FilesPath.playerDataPath);
        FileManagement.getInstance().creatFolder(FilesPath.gameModel);
        FileManagement.getInstance().creatFolder(FilesPath.deletePlayerDataPath);
        FileManagement.getInstance().creatFolder(FilesPath.cardDataPath);
        FileManagement.getInstance().creatFolder(FilesPath.minionDataPath);
        FileManagement.getInstance().creatFolder(FilesPath.spellDataPath);
        FileManagement.getInstance().creatFolder(FilesPath.weaponDataPath);
    }

}
