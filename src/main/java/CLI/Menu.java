package CLI;

import controller.CardController;
import controller.PlayerController;
import controller.StoreController;
import initializer.InitCLI;
import logs.PlayerLogs;
import model.Player;
import model.card.Card;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    static Scanner scanner = new Scanner(System.in);
    static String command;

    public static void exitGame() {
        InitCLI.headerBox("Goodbye");
        System.exit(0);
    }

    public static void helpMenu() {
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

    public static void accountMenu() {
        String username;
        String password;
        Player player;
        PlayerController playerController;
        while (true) {
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
                    playerController = new PlayerController(username, password);
                    player = playerController.signInPlayer();
                    PlayerLogs.addToLogBody("sign_in", "go_to_user_menu", player);
                    userMenu(player, playerController);
                    break;
                case "sign up":
                    System.out.println("- Enter new Username: ");
                    username = scanner.nextLine();
                    System.out.println("- Enter new Password: ");
                    password = scanner.nextLine();
                    playerController = new PlayerController(username, password);
                    player = playerController.signUpPlayer();
                    PlayerLogs.creatLogFile(player);
                    PlayerLogs.addToLogBody("sign_up", "creat_new_account", player);
                    System.out.println(" - Your account created successfully.");
                    userMenu(player, playerController);
                    break;
                case "help":
                    helpMenu();
                    break;
                case "exit game":
                    exitGame();
                    break;
                default:
                    System.out.println("- Oops!! your command is wrong.");
            }
        }
    }

    public static void userMenu(Player player, PlayerController playerController) {
        while (true) {
            playerController.savePlayerToFile(player);
            String[] description = {" - cards management", " - store",
                    " - delete account", " - sign out", " - help", " - exit game"};
            InitCLI.frameCreator(" Account Menu->" + player.getUserName(), description);
            command = scanner.nextLine();
            switch (command) {
                case "cards management":
                    PlayerLogs.addToLogBody("navigate", "cards_managment", player);
                    cardsManagementMenu(player, playerController);
                    break;
                case "store":
                    PlayerLogs.addToLogBody("navigate", "store", player);
                    storeMenu(player, playerController);
                    break;
                case "delete account":
                    String checkPassword;
                    System.out.println(" - Enter your password:");
                    checkPassword = scanner.nextLine();
                    if (!player.getPassword().equals(checkPassword)) {
                        PlayerLogs.addToLogBody("delete_account", "enter_wrong_password", player);
                        System.out.println(" - Oops!! your password is wrong.");
                        System.out.println("   Please try again.");
                    } else {
                        playerController.savePlayerToFile(player);
                        PlayerLogs.addToLogBody("delete_account", "account_deleted_successful", player);
                        playerController.deleteAccount(player);
                        System.out.println(" - Account deleted successful");
                        return;
                    }
                    break;
                case "sign out":
                    PlayerLogs.addToLogBody("sign_out", "user_exit", player);
                    playerController.savePlayerToFile(player);
                    return;
                case "help":
                    PlayerLogs.addToLogBody("navigate", "help", player);
                    helpMenu();
                    break;
                case "exit game":
                    PlayerLogs.addToLogBody("sign_out", "user_exit_game:user_menu", player);
                    playerController.savePlayerToFile(player);
                    exitGame();
                    break;
                default:
                    PlayerLogs.addToLogBody("wrong_command", "user_menu:" + command, player);
                    System.out.println("- Oops!! your command is wrong.");
            }
        }
    }

    public static void cardsManagementMenu(Player player, PlayerController playerController) {
        while (true) {
            playerController.savePlayerToFile(player);
            String[] description = {" - my heroes", " - current hero", " - change current hero",
                    " - show all cards", " - my hero cards", " - card can add hero", " - add hero card",
                    " - delete hero card", " - back", " - help", " - exit game"};
            InitCLI.frameCreator(player.getUserName() + "->Cards Management", description);
            String cardName;
            command = scanner.nextLine();
            switch (command) {
                case "my heroes":
                    System.out.print(" - Your heroes are: ");
                    for (int i = 0; i < player.getPlayerHeroes().size(); i++) {
                        System.out.print("'" + player.getPlayerHeroes().get(i).getHeroName() + "' ");
                    }
                    PlayerLogs.addToLogBody("list", "heroes:all", player);
                    System.out.println();
                    break;
                case "current hero":
                    System.out.println(" - Your current hero is: '" + player.getCurrentHero().getHeroName() + "'");
                    PlayerLogs.addToLogBody("list",
                            "current_hero:" + player.getCurrentHero().getHeroName(), player);
                    break;
                case "change current hero":
                    System.out.println(" - Your current hero is: '" + player.getCurrentHero().getHeroName() + "'");
                    System.out.print(" - Your heroes can select: ");
                    for (int i = 0; i < player.getPlayerHeroes().size(); i++) {
                        System.out.print("'" + player.getPlayerHeroes().get(i).getHeroName() + "' ");
                    }
                    System.out.println();
                    boolean check = true;
                    while (check) {
                        String newHero = scanner.nextLine();
                        for (int i = 0; i < player.getPlayerHeroes().size(); i++) {
                            if (player.getPlayerHeroes().get(i).getHeroName().equals(newHero)) {
                                player.setCurrentHero(player.getPlayerHeroes().get(i));
                                System.out.println(" - your current player change successful.");
                                PlayerLogs.addToLogBody("change", "current_hero_to:"
                                        + player.getCurrentHero().getHeroName(), player);
                                check = false;
                            }
                        }
                        if (check) {
                            PlayerLogs.addToLogBody("wrong_command", "change_current_hero:" + newHero, player);
                            System.out.println("- Oops!! your command is wrong.");
                            System.out.println("   Please try again.");
                        }
                    }
                    break;
                case "show all cards":
                    ArrayList<Card> allCards;
                    System.out.println(" - All cards of you are: ");
                    allCards = playerController.playerAllCards(player);

                    for (Card allCard : allCards) System.out.println("'" + allCard.getName() + "' ");

                    PlayerLogs.addToLogBody("list", "player_cards:all", player);
                    break;
                case "my hero cards":
                    String[] heroCardsName = CardController.cardsName(player.getCurrentHero().getHeroCards());
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
                    PlayerLogs.addToLogBody("list", "player_hero_cards:all", player);
                    break;
                case "card can add hero":
                    System.out.println(" - Cards that can be add to current hero: ");
                    for (int i = 0; i < player.getFreePlayerCards().size(); i++) {
                        if ((player.getFreePlayerCards().get(i).getCardClass().equals(player.getCurrentHero().getHeroName())
                                || player.getFreePlayerCards().get(i).getCardClass().equals("Neutral"))
                                && (player.getCurrentHero().getHeroCards().size() < 16)) {
                            String[] heroCardsNames = CardController.cardsName(player.getCurrentHero().getHeroCards());
                            int counter2 = 0;
                            for (String cardsName : heroCardsNames) {
                                if (cardsName.compareTo(player.getFreePlayerCards().get(i).getName()) == 0) {
                                    counter2++;
                                }
                            }
                            if (counter2 < 2) {
                                System.out.println("\t '" + player.getFreePlayerCards().get(i).getName() + "' ");
                            }
                        }
                    }
                    PlayerLogs.addToLogBody("list", "card_can_add_hero:all", player);
                    break;
                case "add hero card":
                    System.out.println(" - Enter yor card name: ");
                    cardName = scanner.nextLine();
                    try {
                        CardController.addHeroCard(cardName, player);
                        System.out.println(" - Your card add to current hero cards successful.");
                        PlayerLogs.addToLogBody("add", "hero_card:" + cardName, player);
                    } catch (Exception e) {
                        PlayerLogs.addToLogBody("wrong_command", "add_hero_card:" + cardName + ":"
                                + player.getCurrentHero().getHeroName(), player);
                        System.out.println(" - this name not valid. (maybe more 2 same card add to hero cards");
                        System.out.println("   or card class not valid.)");
                    }
                    break;
                case "delete hero card":
                    System.out.println(" - Enter yor card name: ");
                    cardName = scanner.nextLine();
                    try {
                        CardController.deleteHeroCard(cardName, player);
                        System.out.println(" - Your hero card deleted successful.");
                        PlayerLogs.addToLogBody("delete", "hero_card:" + cardName, player);
                    } catch (Exception e) {
                        PlayerLogs.addToLogBody("wrong_command", "delete_hero_card:" + cardName, player);
                        System.out.println(" - this name not valid.");
                    }
                    break;

                case "back":
                    PlayerLogs.addToLogBody("navigate", "user_menu", player);
                    return;
                case "help":
                    PlayerLogs.addToLogBody("navigate", "help", player);
                    helpMenu();
                    break;
                case "exit game":
                    PlayerLogs.addToLogBody("sign_out", "user_exit_game:card_managment", player);
                    playerController.savePlayerToFile(player);
                    exitGame();
                    break;
                default:
                    System.out.println("- Oops!! your command is wrong.");
                    PlayerLogs.addToLogBody("wrong_command", "cards_management:" + command, player);
            }
        }
    }

    public static void storeMenu(Player player, PlayerController playerController) {
        String cardName;
        while (true) {
            playerController.savePlayerToFile(player);
            String[] description = {" - can buy", " - can sell", " - card sale", " - buy card"
                    , " - wallet", " - back", " - help", " - exit game"};
            InitCLI.frameCreator(player.getUserName() + "->Store Menu", description);
            command = scanner.nextLine();
            switch (command) {
                case "can buy":
                    String[] s;
                    System.out.println(" - Spells that can be bought: ");
                    s = StoreController.canBuy.canBuySpell(player);
                    for (String value : s) System.out.println("\t '" + value + "' ");
                    System.out.println(" - Minions that can be bought: ");
                    s = StoreController.canBuy.canBuyMinion(player);
                    for (String value : s) System.out.println("\t '" + value + "' ");
                    PlayerLogs.addToLogBody("list", "can_buy_card:all", player);
                    break;
                case "can sell":
                    System.out.println(" - Your cards can be sold: ");
                    String str;
                    str = "";
                    for (int i = 0; i < player.getFreePlayerCards().size(); i++) {
                        str = str.concat("'" + player.getFreePlayerCards().get(i).getName() + "'");
                        System.out.println("\t '" + player.getFreePlayerCards().get(i).getName() + "' ");
                    }
                    PlayerLogs.addToLogBody("list", "can_sell_card:[" + str + "]", player);
                    break;
                case "card sale":
                    System.out.println(" - Enter yor card name: ");
                    cardName = scanner.nextLine();
                    try {
                        StoreController.cardSell(cardName, player);
                        System.out.println(" - Sales completed successfully. ");
                        PlayerLogs.addToLogBody("sell", "card:" + cardName, player);
                    } catch (Exception e) {
                        PlayerLogs.addToLogBody("wrong_command", "sell_card:" + command, player);
                        System.out.println(" - Your card can't be sold or name is wrong.");
                    }
                    break;
                case "buy card":
                    System.out.println(" - Enter yor card name: ");
                    cardName = scanner.nextLine();
                    try {
                        StoreController.buyCard(cardName, player);
                        System.out.println(" - Purchase successful. ");
                        PlayerLogs.addToLogBody("buy", "card:" + cardName, player);
                    } catch (Exception e) {
                        PlayerLogs.addToLogBody("wrong_command", "buy_card:" + command, player);
                        System.out.println(" - card can't be bought (your card name is wrong or your money is't enough.)");
                    }
                    break;
                case "wallet":
                    System.out.println(" - Your asset: " + player.getMoney() + " $");
                    PlayerLogs.addToLogBody("list", "money:" + player.getMoney(), player);
                    break;
                case "back":
                    PlayerLogs.addToLogBody("navigate", "user_menu", player);
                    return;
                case "help":
                    PlayerLogs.addToLogBody("navigate", "help", player);
                    helpMenu();
                    break;
                case "exit game":
                    PlayerLogs.addToLogBody("sign_out", "user_exit_game:store", player);
                    playerController.savePlayerToFile(player);
                    exitGame();
                    break;
                default:
                    System.out.println("- Oops!! your command is wrong.");
                    PlayerLogs.addToLogBody("wrong_command", "store:" + command, player);
            }
        }
    }

}