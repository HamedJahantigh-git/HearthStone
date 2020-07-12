package controller.game.playingCard;

import controller.FileManagement;
import controller.game.GameController;
import enums.CardMechanicsEnum;
import model.Game;
import model.Player;
import model.card.Card;
import model.card.Minion;

import java.util.ArrayList;

public class CardController {

    protected GameController gameController;
    protected Game game;

    protected CardController(GameController gameController, Game game) {
        this.gameController = gameController;
        this.game = game;
    }

    protected Player.PlayerGame currentPlayerGame() {
        return game.getPlayerGames(game.getPlayerIndex());
    }

    protected ArrayList<CardMechanicsEnum> returnCardMechanics(Card card) {
        ArrayList<CardMechanicsEnum> result = new ArrayList<>();
        for (CardMechanicsEnum mechanic : CardMechanicsEnum.values()) {
            if (card.getMechanics().contains(mechanic.name()))
                result.add(mechanic);
        }
        return result;
    }

    public String nextWord(String message, String word) {
        String[] strArr = message.split(" ");
        for (int i = 0; i <strArr.length ; i++) {
            if(strArr[i].equals(word)){
                return strArr[i+1];
            }
        }
        return null;
    }

protected Card mackNullCopy(Card card){
    Card copy = FileManagement.getInstance().getCopy().copyCard(card);
    copy.setDescription(" ");
    ArrayList<String> mec = new ArrayList<>();
    mec.add("Empty");
    copy.setMechanics(mec);
    currentPlayerGame().getGroundCard().add(copy);
    return copy;
}

    protected void checkDestroyedGroundCard() {
        for (int i = 0; i < 2; i++) {
            for (Card card:game.getPlayerGames(i).getGroundCard()) {
                if(((Minion) card).getHealth() <= 0){
                    if (card.getDescription().contains("Reborn")){
                        int attack = Integer.parseInt(nextWord(card.getDescription(),"Attack"));
                        ((Minion) card).plusAttack(attack);
                        ((Minion) card).setHealth(1);
                        card.setDescription("Do");
                    }
                    else {
                        game.getPlayerGames(i).getGroundCard().remove(card);
                    }
                }
            }

        }
    }

    public int[] extractionNumber (String subStr){
        int[] ans = new int[2];
        if(subStr.length()==3){
            ans[0] = Integer.parseInt(subStr.substring(0,1));
            ans[1] = Integer.parseInt(subStr.substring(2));
        }
        return ans;
    }
}
