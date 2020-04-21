package enums;

public enum LogsEnum {
    sign(new String[]{"sign_up", "sign_in", "sign_out"},
            new String[]{"creat_new_account", "go_to_userMenu", "user_exit", "click_exit_game"}),
    collection(new String[]{"click_collection","click_hero_cards"},
            new String[]{"navigate_collection","_cards"}),
    shop(new String[]{"click_shop","click_sell","click_buy","click_card","click_no","click_yes"},
            new String[]{"navigate_shop","show_cards_sell","show_cards_buy","select_", "cancel_sell","sell:"}),
    back(new String[]{"click_back"},
            new String[]{"navigate_user_menu"}),
    error(new String[]{"error"},
            new String[]{"not_enough_money"});

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
