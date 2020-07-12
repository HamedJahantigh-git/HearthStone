package enums;

import defaults.FilesPath;

public enum GameSoundsEnum {
    cantPlay("ICannotPlayThat.wav"),
    cantDoThat("ICannotDoThat.wav"),
    TimeAlarm("TimeAlarm.wav"),
    notValidTarget("ThatsnotAValidTarget.wav"),
    attackWithTaunt("IMustAttackMinionATuant.wav"),
    attack("Attack.wav"),
    alreadyAttacked("ThatMinionAlreadyAttacked.wav"),
    doQuest("QuestDoing.wav"),
    targetDo("TargetDo.wav"),
    finishedGame("FinishedGame.wav"),
    mistake("Mistake.wav");

    private String path;
    private String basePath = FilesPath.graphicsPath.soundsPath+"/";

    private GameSoundsEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
