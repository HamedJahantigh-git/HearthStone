package enums;

public enum LogsEnum {
    sign(new String[]{"sign_up", "sign_in", "sign_out"},
            new String[]{"creat_new_account", "go_to_userMenu", "user_exit", "click_exit_game"}),
    collection(new String[]{"click_collection", "click_hero_cards", "filter", "buy_closed_card", "click_new_deck",
    "select_deck","add_card_deck","remove_deck_card","click_game_deck","click_delete_deck","click_yes","click_no","click_edit_deck"},
            new String[]{"navigate_collection", "_cards", "navigate_shop","creat_successful","cancel","deck:","select:",
            "show_message","delete:","cancel_delete_deck","edit_to:"}),
    shop(new String[]{"click_shop", "click_sell", "click_buy", "click_card", "click_no", "click_yes"},
            new String[]{"navigate_shop", "show_cards_sell", "show_cards_buy", "select:", "cancel_sell", "sell:","buy:"}),
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
