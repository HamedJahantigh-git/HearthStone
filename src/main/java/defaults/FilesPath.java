package defaults;

public class FilesPath {

    public static final String gameDataPath = "Game Data";
    public static final String logsPath = gameDataPath + "/Logs";
    public static final String playerLogsPath = logsPath + "/Player Logs";
    public static final String gameInfo = gameDataPath + "/Game Information";
    public static final String playerDataPath = gameDataPath + "/Player Data";
    public static final String deletePlayerDataPath = playerDataPath + "/Deleted Account";
    public static final String cardDataPath = gameDataPath + "/Card Data";
    public static final String minionDataPath = cardDataPath + "/Minion";
    public static final String spellDataPath = cardDataPath + "/Spell";
    public static final String weaponDataPath = cardDataPath + "/Weapon";

    public static class graphicsPath {
        public static final String gameFilesPath = "Game Files";
        public static final String fontsPath = gameFilesPath + "/Fonts";
        public static final String backgroundsPath = gameFilesPath + "/Backgrounds";
    }

}
