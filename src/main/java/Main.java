import CLI.CLIMenu;

public class Main {
    public static void main(String[] args) {
        /*InitCLI.createNeedsFolder();
        InitCards.initSpell();
        InitCards.initMinion();
        AccountFrame accountFrame = new AccountFrame();*/
       CLIMenu.getInstance().accountMenu();
    }
}
