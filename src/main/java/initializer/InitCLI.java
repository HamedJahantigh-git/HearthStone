package initializer;


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
        File s = new File(FilesPath.gameDataPath);
        s.mkdir();
        s = new File(FilesPath.gameInfo);
        s.mkdir();
        s = new File(FilesPath.logsPath);
        s.mkdir();
        s = new File(FilesPath.playerLogsPath);
        s.mkdir();
        s = new File(FilesPath.gameEventLogsPath);
        s.mkdir();
        s = new File(FilesPath.playerDataPath);
        s.mkdir();
        s = new File(FilesPath.gameModel);
        s.mkdir();
        s = new File(FilesPath.deletePlayerDataPath);
        s.mkdir();
        s = new File(FilesPath.cardDataPath);
        s.mkdir();
        s = new File(FilesPath.minionDataPath);
        s.mkdir();
        s = new File(FilesPath.spellDataPath);
        s.mkdir();
        s = new File(FilesPath.weaponDataPath);
        s.mkdir();
    }

}
