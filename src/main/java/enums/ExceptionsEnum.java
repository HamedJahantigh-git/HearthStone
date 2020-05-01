package enums;

public enum ExceptionsEnum {
    userNoExist ("User Name not Exist"),
    userRepeated ("Repeated Username."),
    wrongPassword ("Wrong Password"),
    emptyImport("Empty Import"),
    unMatchingCardAndDeck("Deck Hero And Card Class UnMatching"),
    unSelectedDeck("unSelected Deck"),
    moreTowCardExist("there 2 Same Card in Selected Deck"),
    fullDeckCards("Fulled Deck Cards"),
    minDeckCard("Small Deck for Game"),
    unEditableDeck("Current Hero Card Exist in Deck");

    private String message;

    private ExceptionsEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
