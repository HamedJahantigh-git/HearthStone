package enums;

public enum LogsEnum {
    sign(new String[]{"sign_up", "sign_in", "sign_out"},
            new String[]{"creat_new_account", "go_to_userMenu", "user_exit", "click_exit_game"}),
    setting(new String[]{"click_play"},
            new String[]{"navigate_setting"}),
    play(new String[]{"click_play","click_Menu_Icon","click_back","click_main_menu","click_around_deck","game_action","click_back","click_play_mine"},
            new String[]{"select_game_method","navigate_collection/empty_game_deck","open_Menu","continue_game","Stop_game",
                    "show_around_deck","detail_gameEventLogs:","start_mine_game"}),
    collection(new String[]{"click_collection", "click_hero_cards", "filter", "buy_closed_card", "click_new_deck",
    "select_deck","add_card_deck","remove_deck_card","click_game_deck","click_delete_deck","click_yes","click_no","click_edit_deck"},
            new String[]{"navigate_collection", "_cards", "navigate_shop","creat_successful","cancel","deck:","select:",
            "show_message","delete:","cancel_delete_deck","edit_to:"}),
    shop(new String[]{"click_shop", "click_sell", "click_buy", "click_card", "click_no", "click_yes"},
            new String[]{"navigate_shop", "show_cards_sell", "show_cards_buy", "select:", "cancel_sell", "sell:","buy:"}),
    status(new String[]{"click_status","select_deck"},
            new String[]{"navigate_status"}),
    back(new String[]{"click_back"},
            new String[]{"navigate_user_menu","show_all_decks"}),
    error(new String[]{"error"},
            new String[]{"not_enough_money","deckHero_unMatching_cardClass","add_more_2card_deck","fulled_deck_cards",
            "current_hero_exist_in_deck"});

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
