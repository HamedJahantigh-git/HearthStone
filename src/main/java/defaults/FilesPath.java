package defaults;

public class FilesPath {

    public static final String gameDataPath = "src/main/resources/Game Data";
    public static final String logsPath = gameDataPath + "/Logs";
    public static final String playerLogsPath = logsPath + "/Player Logs";
    public static final String gameEventLogsPath = logsPath + "/Game Event Logs";
    public static final String gameInfo = gameDataPath + "/Game Information";
    public static final String gameModel = gameDataPath + "/Battle Data";
    public static final String playerDataPath = gameDataPath + "/Player Data";
    public static final String heroDataPath = gameDataPath + "/Hero Data";
    public static final String deletePlayerDataPath = playerDataPath + "/Deleted Account";
    public static final String cardDataPath = gameDataPath + "/Card Data";
    public static final String minionDataPath = cardDataPath + "/Minion";
    public static final String spellDataPath = cardDataPath + "/Spell";
    public static final String weaponDataPath = cardDataPath + "/Weapon";
    public static final String defaultPlayerCardsName = gameInfo + "/PlayerDefaultCards.txt";


    public static class graphicsPath {
        public static final String gameFilesPath = "src/main/resources/Game Files";
        public static final String fontsPath = gameFilesPath + "/Fonts";
        public static final String backgroundsPath = gameFilesPath + "/Backgrounds";
        public static final String soundsPath = gameFilesPath + "/Sounds";
        public static final String imagesPath = gameFilesPath + "/Images";
        public static final String heroPath = imagesPath+"/Hero";
        public static final String collectionPath = imagesPath+"/Collection";
        public static final String cardsPath = imagesPath + "/Cards";
        public static final String gameCardsPath = imagesPath+"/Game Cards";
    }

}
