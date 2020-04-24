package CLI;

public class CLIMenu {
/*
    private static final CLIMenu instance = new CLIMenu();
    private Scanner scanner = new Scanner(System.in);
    private String command;

    private CLIMenu() {
        InitCLI.headerBox("Welcome To HearthStone Game");
    }

    public static CLIMenu getInstance() {
        return instance;
    }

    private void exitGame() {
        InitCLI.headerBox("Goodbye");
        System.exit(0);
    }

    private void helpMenu() {
        String[] description = {
                " - In each menu the commands are followed by a '-' sign.",
                "   Write the command exactly to execute.",
                " - programmer Email: n.hamedjahantigh@yahoo.com",
                " ",
                " - For back write 'back'."};
        InitCLI.frameCreator("Help Menu", description);
        do {
            command = scanner.nextLine();
            if (!command.equals("back"))
                System.out.println(" - Oops!! your command is wrong.");
        } while (!command.equals("back"));
    }

    public void accountMenu() {
        String username;
        String password;
        boolean whileCondition = true;
        PlayerController playerController;
        while (whileCondition) {
            String[] description = {" - sign in", " - sign up",
                    " - help", " - exit game"};
            InitCLI.frameCreator("Account Menu", description);
            command = scanner.nextLine();
            switch (command) {
                case "sign in":
                    System.out.println("- Enter Username: ");
                    username = scanner.nextLine();
                    System.out.println("- Enter Password: ");
                    password = scanner.nextLine();
                    try {
                        playerController = new PlayerController();
                        playerController.signInPlayer(username, password);
                        PlayerLogs.addToLogBody("sign_in", "go_to_user_menu", playerController.getPlayer());
                        userMenu(playerController);
                        break;
                    } catch (Exception e) {
                        if (e.getMessage().equals(ExceptionsEnum.valueOf("userNoExist").getMessage()))
                            System.out.println(" - User Name not Exist");
                        if (e.getMessage().equals(ExceptionsEnum.valueOf("wrongPassword").getMessage()))
                            System.out.println(" - Your password is wrong.");
                        System.out.println("  Please try again.");
                        break;
                    }
                case "sign up":
                    System.out.println("- Enter new Username: ");
                    username = scanner.nextLine();
                    System.out.println("- Enter new Password: ");
                    password = scanner.nextLine();
                    try {
                        playerController = new PlayerController();
                        playerController.signUpPlayer(username, password);
                        PlayerLogs.creatLogFile(playerController.getPlayer());
                        PlayerLogs.addToLogBody("sign_up", "creat_new_account", playerController.getPlayer());
                        System.out.println(" - Your account created successfully.");
                        userMenu(playerController);
                        break;
                    } catch (Exception e) {
                        System.out.println("- This username is repeated!!!");
                        System.out.println("  Please try again.");
                        System.out.println();
                        break;
                    }
                case "help":
                    helpMenu();
                    break;
                case "exit game":
                    whileCondition = false;
                    exitGame();
                    break;
                default:
                    System.out.println("- Oops!! your command is wrong.");
            }
        }
    }

    private void userMenu(PlayerController playerController) {
        while (true) {
            FileManagement.savePlayerToFile(playerController.getPlayer());
            String[] description = {" - cards management", " - store",
                    " - delete account", " - sign out", " - help", " - exit game"};
            InitCLI.frameCreator(" Account Menu->" + playerController.getPlayer().getUserName(), description);
            command = scanner.nextLine();
            switch (command) {
                case "cards management":
                    PlayerLogs.addToLogBody("navigate", "cards_managment", playerController.getPlayer());
                    cardsManagementMenu(playerController);
                    break;
                case "store":
                    PlayerLogs.addToLogBody("navigate", "store", playerController.getPlayer());
                    storeMenu(playerController);
                    break;
                case "delete account":
                    String checkPassword;
                    System.out.println(" - Enter your password:");
                    checkPassword = scanner.nextLine();
                    if (!playerController.getPlayer().getPassword().equals(checkPassword)) {
                        PlayerLogs.addToLogBody("delete_account", "enter_wrong_password",
                                playerController.getPlayer());
                        System.out.println(" - Oops!! your password is wrong.");
                        System.out.println("   Please try again.");
                    } else {
                        FileManagement.savePlayerToFile(playerController.getPlayer());
                        PlayerLogs.addToLogBody("delete_account", "account_deleted_successful",
                                playerController.getPlayer());
                        playerController.deleteAccount(playerController.getPlayer());
                        System.out.println(" - Account deleted successful");
                        return;
                    }
                    break;
                case "sign out":
                    PlayerLogs.addToLogBody("sign_out", "user_exit", playerController.getPlayer());
                    FileManagement.savePlayerToFile(playerController.getPlayer());
                    return;
                case "help":
                    PlayerLogs.addToLogBody("navigate", "help", playerController.getPlayer());
                    helpMenu();
                    break;
                case "exit game":
                    PlayerLogs.addToLogBody("sign_out", "user_exit_game:user_menu",
                            playerController.getPlayer());
                    FileManagement.savePlayerToFile(playerController.getPlayer());
                    exitGame();
                    break;
                default:
                    PlayerLogs.addToLogBody("wrong_command", "user_menu:" + command,
                            playerController.getPlayer());
                    System.out.println("- Oops!! your command is wrong.");
            }
        }
    }

    private void cardsManagementMenu(PlayerController playerController) {
        while (true) {
            FileManagement.savePlayerToFile(playerController.getPlayer());
            String[] description = {" - my heroes", " - current hero", " - change current hero",
                    " - show all cards", " - my hero cards", " - card can add hero", " - add hero card",
                    " - delete hero card", " - back", " - help", " - exit game"};
            InitCLI.frameCreator(playerController.getPlayer().getUserName() + "->Cards Management", description);
            String cardName;
            command = scanner.nextLine();
            switch (command) {
                case "my heroes":
                    System.out.print(" - Your heroes are: ");
                    for (int i = 0; i < playerController.getPlayer().getPlayerHeroes().size(); i++) {
                        System.out.print("'" + playerController.getPlayer().getPlayerHeroes().get(i).getHeroName() + "' ");
                    }
                    PlayerLogs.addToLogBody("list", "heroes:all", playerController.getPlayer());
                    System.out.println();
                    break;
                case "current hero":
                    System.out.println(" - Your current hero is: '" +
                            playerController.getPlayer().getCurrentHero().getHeroName() + "'");
                    PlayerLogs.addToLogBody("list",
                            "current_hero:" + playerController.getPlayer().getCurrentHero().getHeroName(),
                            playerController.getPlayer());
                    break;
                case "change current hero":
                    System.out.println(" - Your current hero is: '" +
                            playerController.getPlayer().getCurrentHero().getHeroName() + "'");
                    System.out.print(" - Your heroes can select: ");
                    for (int i = 0; i < playerController.getPlayer().getPlayerHeroes().size(); i++) {
                        System.out.print("'" + playerController.getPlayer().getPlayerHeroes().get(i).getHeroName() + "' ");
                    }
                    System.out.println();
                    boolean check = true;
                    while (check) {
                        String newHero = scanner.nextLine();
                        for (int i = 0; i < playerController.getPlayer().getPlayerHeroes().size(); i++) {
                            if (playerController.getPlayer().getPlayerHeroes().get(i).getHeroName().equals(newHero)) {
                                playerController.getPlayer().setCurrentHero(playerController.getPlayer().getPlayerHeroes().get(i));
                                System.out.println(" - your current player change successful.");
                                PlayerLogs.addToLogBody("change", "current_hero_to:"
                                                + playerController.getPlayer().getCurrentHero().getHeroName(),
                                        playerController.getPlayer());
                                check = false;
                            }
                        }
                        if (check) {
                            PlayerLogs.addToLogBody("wrong_command", "change_current_hero:" +
                                    newHero, playerController.getPlayer());
                            System.out.println("- Oops!! your command is wrong.");
                            System.out.println("   Please try again.");
                        }
                    }
                    break;
                case "show all cards":
                    ArrayList<Card> allCards;
                    System.out.println(" - All cards of you are: ");
                    allCards = playerController.playerAllCards(playerController.getPlayer());

                    for (Card allCard : allCards) System.out.println("'" + allCard.getName() + "' ");

                    PlayerLogs.addToLogBody("list", "player_cards:all", playerController.getPlayer());
                    break;
                case "my hero cards":
                    String[] heroCardsName = CardController.cardsName(
                            playerController.getPlayer().getCurrentHero().getHeroCards());
                    int counter = 1;
                    for (int i = 0; i < heroCardsName.length - 1; i++) {
                        if (heroCardsName[i].compareTo(heroCardsName[i + 1]) == 0) {
                            counter++;
                        } else {
                            System.out.println("'" + heroCardsName[i] + "' : " + counter);
                            counter = 1;
                        }
                    }
                    if (heroCardsName[heroCardsName.length - 1] != null) {
                        System.out.println("'" + heroCardsName[heroCardsName.length - 1] + "' : " + counter);
                    } else {
                        System.out.println(" - any cards found.");
                    }
                    PlayerLogs.addToLogBody("list", "player_hero_cards:all",
                            playerController.getPlayer());
                    break;
                case "card can add hero":
                    System.out.println(" - Cards that can be add to current hero: ");
                    for (int i = 0; i < playerController.getPlayer().getFreePlayerCards().size(); i++) {
                        if ((playerController.getPlayer().getFreePlayerCards().get(i).getCardClass().equals(
                                playerController.getPlayer().getCurrentHero().getHeroName())
                                || playerController.getPlayer().getFreePlayerCards().get(i).getCardClass().equals("Neutral"))
                                && (playerController.getPlayer().getCurrentHero().getHeroCards().size() < 16)) {
                            String[] heroCardsNames = CardController.cardsName(
                                    playerController.getPlayer().getCurrentHero().getHeroCards());
                            int counter2 = 0;
                            for (String cardsName : heroCardsNames) {
                                if (cardsName.compareTo(
                                        playerController.getPlayer().getFreePlayerCards().get(i).getName()) == 0) {
                                    counter2++;
                                }
                            }
                            if (counter2 < 2) {
                                System.out.println("\t '" +
                                        playerController.getPlayer().getFreePlayerCards().get(i).getName() + "' ");
                            }
                        }
                    }
                    PlayerLogs.addToLogBody("list", "card_can_add_hero:all",
                            playerController.getPlayer());
                    break;
                case "add hero card":
                    System.out.println(" - Enter yor card name: ");
                    cardName = scanner.nextLine();
                    try {
                        CardController.addHeroCard(cardName, playerController.getPlayer());
                        System.out.println(" - Your card add to current hero cards successful.");
                        PlayerLogs.addToLogBody("add", "hero_card:" + cardName,
                                playerController.getPlayer());
                    } catch (Exception e) {
                        PlayerLogs.addToLogBody("wrong_command", "add_hero_card:" + cardName + ":"
                                + playerController.getPlayer().getCurrentHero().getHeroName(), playerController.getPlayer());
                        System.out.println(" - this name not valid. (maybe more 2 same card add to hero cards");
                        System.out.println("   or card class not valid.)");
                    }
                    break;
                case "delete hero card":
                    System.out.println(" - Enter yor card name: ");
                    cardName = scanner.nextLine();
                    try {
                        CardController.deleteHeroCard(cardName, playerController.getPlayer());
                        System.out.println(" - Your hero card deleted successful.");
                        PlayerLogs.addToLogBody("delete", "hero_card:" + cardName,
                                playerController.getPlayer());
                    } catch (Exception e) {
                        PlayerLogs.addToLogBody("wrong_command", "delete_hero_card:" + cardName,
                                playerController.getPlayer());
                        System.out.println(" - this name not valid.");
                    }
                    break;

                case "back":
                    PlayerLogs.addToLogBody("navigate", "user_menu", playerController.getPlayer());
                    return;
                case "help":
                    PlayerLogs.addToLogBody("navigate", "help", playerController.getPlayer());
                    helpMenu();
                    break;
                case "exit game":
                    PlayerLogs.addToLogBody("sign_out", "user_exit_game:card_managment",
                            playerController.getPlayer());
                    FileManagement.savePlayerToFile(playerController.getPlayer());
                    exitGame();
                    break;
                default:
                    System.out.println("- Oops!! your command is wrong.");
                    PlayerLogs.addToLogBody("wrong_command", "cards_management:" + command,
                            playerController.getPlayer());
            }
        }
    }

    private void storeMenu(PlayerController playerController) {
        String cardName;
        while (true) {
            FileManagement.savePlayerToFile(playerController.getPlayer());
            String[] description = {" - can buy", " - can sell", " - card sale", " - buy card"
                    , " - wallet", " - back", " - help", " - exit game"};
            InitCLI.frameCreator(playerController.getPlayer().getUserName() + "->Store Menu", description);
            command = scanner.nextLine();
            switch (command) {
                case "can buy":
                    String[] s;
                    System.out.println(" - Spells that can be bought: ");
                    s = StoreController.canBuy.canBuySpell(playerController.getPlayer());
                    for (String value : s) System.out.println("\t '" + value + "' ");
                    System.out.println(" - Minions that can be bought: ");
                    s = StoreController.canBuy.canBuyMinion(playerController.getPlayer());
                    for (String value : s) System.out.println("\t '" + value + "' ");
                    PlayerLogs.addToLogBody("list", "can_buy_card:all", playerController.getPlayer());
                    break;
                case "can sell":
                    System.out.println(" - Your cards can be sold: ");
                    String str;
                    str = "";
                    for (int i = 0; i < playerController.getPlayer().getFreePlayerCards().size(); i++) {
                        str = str.concat("'" + playerController.getPlayer().getFreePlayerCards().get(i).getName() + "'");
                        System.out.println("\t '" + playerController.getPlayer().getFreePlayerCards().get(i).getName() + "' ");
                    }
                    PlayerLogs.addToLogBody("list", "can_sell_card:[" + str + "]",
                            playerController.getPlayer());
                    break;
                case "card sale":
                    System.out.println(" - Enter yor card name: ");
                    cardName = scanner.nextLine();
                    try {
                        StoreController.cardSell(cardName, playerController.getPlayer());
                        System.out.println(" - Sales completed successfully. ");
                        PlayerLogs.addToLogBody("sell", "card:" + cardName, playerController.getPlayer());
                    } catch (Exception e) {
                        PlayerLogs.addToLogBody("wrong_command", "sell_card:" + command,
                                playerController.getPlayer());
                        System.out.println(" - Your card can't be sold or name is wrong.");
                    }
                    break;
                case "buy card":
                    System.out.println(" - Enter yor card name: ");
                    cardName = scanner.nextLine();
                    try {
                        StoreController.buyCard(cardName, playerController.getPlayer());
                        System.out.println(" - Purchase successful. ");
                        PlayerLogs.addToLogBody("buy", "card:" + cardName, playerController.getPlayer());
                    } catch (Exception e) {
                        PlayerLogs.addToLogBody("wrong_command", "buy_card:" +
                                command, playerController.getPlayer());
                        System.out.println(" - card can't be bought (your card name is wrong or your money is't enough.)");
                    }
                    break;
                case "wallet":
                    System.out.println(" - Your asset: " + playerController.getPlayer().getMoney() + " $");
                    PlayerLogs.addToLogBody("list", "money:" +
                            playerController.getPlayer().getMoney(), playerController.getPlayer());
                    break;
                case "back":
                    PlayerLogs.addToLogBody("navigate", "user_menu", playerController.getPlayer());
                    return;
                case "help":
                    PlayerLogs.addToLogBody("navigate", "help", playerController.getPlayer());
                    helpMenu();
                    break;
                case "exit game":
                    PlayerLogs.addToLogBody("sign_out", "user_exit_game:store",
                            playerController.getPlayer());
                    FileManagement.savePlayerToFile(playerController.getPlayer());
                    exitGame();
                    break;
                default:
                    System.out.println("- Oops!! your command is wrong.");
                    PlayerLogs.addToLogBody("wrong_command", "store:" +
                            command, playerController.getPlayer());
            }
        }
    }
*/
}
