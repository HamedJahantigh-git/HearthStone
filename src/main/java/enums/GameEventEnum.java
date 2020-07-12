package enums;

public enum GameEventEnum {

    playMinion("play_minion"), playWeapon("play_weapon"), playSpell("play_spell"),
    playHeroPower("play_hero_power"),
    endTurn("end_turn"),
    selectInfoPassive("select_info_passive"),
    changeStartCard("change_start_card"),
    stopGame("stop_game"),
    attack("attack"),
    finishedGame("finished_game");

    private String type;

    private GameEventEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
