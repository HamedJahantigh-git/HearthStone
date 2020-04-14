package enums;

public enum LogsEnum {
    sign(new String[]{"sign_up", "sign_in", "sign_out"},
            new String[]{"creat_new_account", "go_to_userMenu", "user_exit", "exit_game"});

    private String[] event;
    private String[] event_description;

    private LogsEnum(String[] event, String[] event_description) {
        this.event = event;
        this.event_description = event_description;
    }

    public String[] getEvent() {
        return event;
    }

    public String[] getEvent_description() {
        return event_description;
    }
}
