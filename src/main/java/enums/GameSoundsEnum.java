package enums;

import defaults.FilesPath;

public enum GameSoundsEnum {
    cantPlay("ICannotPlayThat.wav"),
    groundFully("ICannotDoThat.wav"),
    TimeAlarm("TimeAlarm.wav");


    private String path;
    private String basePath = FilesPath.graphicsPath.soundsPath+"/";

    private GameSoundsEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
