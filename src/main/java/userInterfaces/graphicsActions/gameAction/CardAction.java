package userInterfaces.graphicsActions.gameAction;

import controller.FileManagement;
import controller.game.GameController;
import controller.game.playingCard.PlayingCardController;
import enums.GameEventEnum;
import enums.GameSoundsEnum;
import model.card.Card;
import model.card.Minion;
import userInterfaces.Sounds;
import userInterfaces.myComponent.gameComponent.CardDrawer;
import userInterfaces.userMenu.play.BaseGameThread;

public class CardAction {
    private GameController gameController;
    private BaseGameThread baseGameThread;
    private PlayingCardController cardController;
    private Sounds announcerSound;

    public CardAction(GameController gameController, BaseGameThread baseGameThread) {
        this.gameController = gameController;
        this.baseGameThread = baseGameThread;
        this.cardController = gameController.getPlayingCardController();
    }

    public void handleHandCardSelectionAction(CardDrawer cardDrawer, Card card, int playIndex) {
        cardDrawer.getButton().addActionListener(actionEvent -> {
            try {
                cardController.checkCardCanPlay(card, playIndex);
                switch (card.getType()) {
                    case "Minion":
                        minionHandCardSelect((Minion) card);
                        break;
                    case "Spell":
                        spellHandCardSelect(card);
                        break;
                    case "Weapon":
                        weaponHandCardSelect(card);
                        break;
                }
            } catch (Exception e) {
                announcerSound = new Sounds(GameSoundsEnum.cantPlay.getPath());
                announcerSound.playOne();
            }
        });
    }

    private void minionHandCardSelect(Minion card) {
        try {
            cardController.playMinion(card);
            gameController.addGameEvent(gameController.getGame().getPlayerIndex(),
                    GameEventEnum.playMinion, card.getName());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            //todo
            baseGameThread.drawAfterCardSelection();
        } catch (Exception e) {
            announcerSound = new Sounds(GameSoundsEnum.groundFully.getPath());
            announcerSound.playOne();
        }
    }

    private void spellHandCardSelect(Card card) {
        //todo
        /*
        try {
            cardController.playSpell(card);
            gameController.addGameEvent(gameController.getGame().getPlayerIndex(),
                    GameEventEnum.playMinion, card.getName());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            baseGameThread.drawGameBoard();
        } catch (Exception e) {
        }*/
    }

    private void weaponHandCardSelect(Card card) {
        //todo
        /*
        try {
            cardController.playWeapon(card);
            gameController.addGameEvent(gameController.getGame().getPlayerIndex(),
                    GameEventEnum.playMinion, card.getName());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            baseGameThread.drawGameBoard();
        } catch (Exception e) {
        }*/

    }
}
