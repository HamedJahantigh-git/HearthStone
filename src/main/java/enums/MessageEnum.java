package enums;

import defaults.ModelDefault;

public enum MessageEnum {
    emptyImport("Empty Import","<html><center>Please Fill Empty Fields.</center><br><center></center><br><center></center></html>"),
    successSignUp("Success Sign Up", "<html>Your Account has been Successfully Created.</html>"),
    repeatedUsername("Repeated Username", "<html><center>Repeated Username.</center>" +
            "<br><center>Please Enter other Username.</center><br><center></center><br><center></center></html>"),
    userNoExist("User Name not Exist", "<html><center>Username is Wrong.</center><br><center></center><br><center></center></html>"),
    wrongPassword("Wrong Password", "<html><center>Password is Wrong.</center><br><center></center><br><center></center></html>"),
    unMatchingCardAndDeck("unMatching Card Class And Deck Hero","<html><center>Can't add this Card to Selected Deck." +
            "</center><br><center>Deck Hero and Card Class haven't Matching</center><br><center></center><br><center></center></html>"),
    unSelectedDeck("Current Deck is Empty","<html><center>No Select Deck for Add card to it.</center><br><center>please Select onec in Right Panel</center>" +
            "<br><center></center><br><center></center></html>"),
    moreTowCardExist("More two same Cards add to Deck","<html><center>There are Two Same Card in This Deck.</center>" +
            "<br><center>Can't add other.</center><br><center></center><br><center></center></html>"),
    fullDeckCards("Fulled Deck Cards","<html><center>There are 15 Card in This Deck.</center>" +
            "<br><center>Capacity of Deck Cards is Full.</center><br><center></center><br><center></center></html>"),
    changeGameDeck("Select Game Deck","<html><center>Change Game Deck Successfully.</center>" +
            "<br><center></center><br><center></center></html>"),
    deleteDeck("Delete Selected Deck","<html><center>are You Sure for Deleting this Deck?</center>" +
            "<br><center></center><br><center></center></html>"),
    editDeckMistake("Edit Deck Mistake","<html><center>there is Card of Current Hero Deck in This Deck</center>" +
            "<br><center>Can't Apply this Change for Deck.</center><br><center></center></html>"),
    emptyGameDeck("Empty Game Deck","<html><center>You don't Select any Deck for Game.</center>" +
            "<br><center>Please Chose one.</center><br><center></center></html>"),
    gameMenu("Menu in Game", "<html><center>Menu</center>" +
            "<br><center></center><br><center></center><br><center></center><br><center></center><br><center></center></html>"),
    playMenu("Play Menu", "<html><center>Menu</center>" +
            "<br><center></center><br><center></center><br><center></center><br><center></center><br><center></center></html>"),
    lowDeckCards("Low Deck Cards for Game","<html><center>This Deck have Low Card for Game.</center>" +
            "<br><center>You need at least "+ ModelDefault.deckDefaults.minNumberCards +" Cards for Game.</center><br><center></center></html>");

    private String title, text;

    private MessageEnum(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
