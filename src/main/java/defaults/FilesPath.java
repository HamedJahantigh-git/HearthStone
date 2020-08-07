package defaults;

public class FilesPath {

    public static final String CONFIGURATION = "src/main/resources/Configuration";
    public static final String CONFIGURATION_FILE = CONFIGURATION+"/Game Config.txt";
    public static final String GAME_DATA_PATH = "src/main/resources/Game Data";
    public static final String LOGS_PATH = GAME_DATA_PATH + "/Logs";
    public static final String PLAYER_LOGS_PATH = LOGS_PATH + "/Player Logs";
    public static final String GAME_EVENT_LOGS_PATH = LOGS_PATH + "/Game Event Logs";
    public static final String GAME_INFO = GAME_DATA_PATH + "/Game Information";
    public static final String GAME_MODEL = GAME_DATA_PATH + "/Battle Data";
    public static final String PLAYER_DATA_PATH = GAME_DATA_PATH + "/Player Data";
    public static final String HERO_DATA_PATH = GAME_DATA_PATH + "/Hero Data";
    public static final String DELETE_PLAYER_DATA_PATH = PLAYER_DATA_PATH + "/Deleted Account";
    public static final String CARD_DATA_PATH = GAME_DATA_PATH + "/Card Data";
    public static final String MINION_DATA_PATH = CARD_DATA_PATH + "/Minion";
    public static final String SPELL_DATA_PATH = CARD_DATA_PATH + "/Spell";
    public static final String WEAPON_DATA_PATH = CARD_DATA_PATH + "/Weapon";
    public static final String defaultPlayerCardsName = GAME_INFO + "/PlayerDefaultCards.txt";



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
