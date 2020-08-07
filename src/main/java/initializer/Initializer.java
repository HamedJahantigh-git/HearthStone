package initializer;

import controller.FileManagement;
import defaults.FilesPath;

public class Initializer {

    private static Initializer instance = null;

    private Initializer() {

    }

    public static Initializer getInstance() {
        if (instance == null)
            instance = new Initializer();
        return instance;
    }

    public void initializeClient() {
        buildClientFolders();
    }

    public void initializeServer() {
        buildServerFolders();
        InitHero.saveHeroModels();
        InitCards.initSpell();
        InitCards.initMinion();
        InitCards.initWeapon();
    }

    private void buildClientFolders() {

    }

    private void buildServerFolders() {
        FileManagement.getInstance().creatFolder(FilesPath.CONFIGURATION);
        FileManagement.getInstance().creatFolder(FilesPath.GAME_DATA_PATH);
        FileManagement.getInstance().creatFolder(FilesPath.GAME_INFO);
        FileManagement.getInstance().creatFolder(FilesPath.LOGS_PATH);
        FileManagement.getInstance().creatFolder(FilesPath.PLAYER_LOGS_PATH);
        FileManagement.getInstance().creatFolder(FilesPath.GAME_EVENT_LOGS_PATH);
        FileManagement.getInstance().creatFolder(FilesPath.HERO_DATA_PATH);
        FileManagement.getInstance().creatFolder(FilesPath.PLAYER_DATA_PATH);
        FileManagement.getInstance().creatFolder(FilesPath.GAME_MODEL);
        FileManagement.getInstance().creatFolder(FilesPath.DELETE_PLAYER_DATA_PATH);
        FileManagement.getInstance().creatFolder(FilesPath.CARD_DATA_PATH);
        FileManagement.getInstance().creatFolder(FilesPath.MINION_DATA_PATH);
        FileManagement.getInstance().creatFolder(FilesPath.SPELL_DATA_PATH);
        FileManagement.getInstance().creatFolder(FilesPath.WEAPON_DATA_PATH);
    }

}
