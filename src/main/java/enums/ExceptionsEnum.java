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
    fullGroundDeck("Fulled Ground Capacity"),
    minDeckCard("Small Deck for Game"),
    unEditableDeck("Current Hero Card Exist in Deck"),
    lowMoney("low Money for Play Card"),
    changeOneStartCard("This StartCard Changes"),
    illegalTurn("Illegal Turn"),
    selfMinion("Self Minion"),
    selfHero("Self Hero"),
    tauntExist("Taunt Exist in Ground"),
    newPlayCard("New Play Card Cant Attack Immediately"),
    beforeAttacked("In This Turn Attacked"),
    attackerDead("Attacker Dead"),
    attackedDead("Attacked Dead"),
    allDead("Attacker And Attacked Dead"),
    weaponAndMinionDead("Weapon And MinionDead"),
    destroyWeapon("Destroy Weapon"),
    forceAttack("Force Attack"),
    targetedNotValid("Targeted Not Valid"),
    rebornAttacker("Reborn Attacker"),
    rebornAttacked("Reborn Attacked"),
    doMoreHeroPower("Do More Hero Power"),
    doQuest("Do Quest"),
    gameFinished("Game Finished");

    private String message;

    private ExceptionsEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
