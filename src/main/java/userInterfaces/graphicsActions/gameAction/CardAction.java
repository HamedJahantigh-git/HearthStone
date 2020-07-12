package userInterfaces.graphicsActions.gameAction;

import controller.FileManagement;
import controller.game.GameController;
import controller.game.playingCard.PlayingCardController;
import enums.*;
import model.Game;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;
import model.card.Weapon;
import model.hero.Hero;
import userInterfaces.Sounds;
import userInterfaces.myComponent.MouseManager;
import userInterfaces.myComponent.gameComponent.CardDrawer;
import userInterfaces.myComponent.gameComponent.HeroHeroPowerDrawer;
import userInterfaces.userMenu.play.BaseGameThread;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardAction {
    private GameController gameController;
    private Game game;
    private BaseGameThread baseGameThread;
    private PlayingCardController cardController;
    private Sounds announcerSound;

    public CardAction(GameController gameController, BaseGameThread baseGameThread) {
        this.gameController = gameController;
        this.game = gameController.getGame();
        this.baseGameThread = baseGameThread;
        this.cardController = gameController.getPlayingCardController();
    }

    public void handleHandCardSelectionAction(CardDrawer cardDrawer, Card card, int playIndex) {
        cardDrawer.getButton().addActionListener(actionEvent -> {
            if (game.isAttackSelected() || gameController.getTargetManager().isTargeter()) {
                new Sounds(GameSoundsEnum.notValidTarget.getPath()).playOne();
            } else
                try {
                    cardController.checkCardCanPlay(card, playIndex);
                    switch (card.getType()) {
                        case "Minion":
                            minionHandCardSelectAction((Minion) card);
                            break;
                        case "Spell":
                            spellHandCardSelectAction(card);
                            break;
                        case "Weapon":
                            weaponHandCardSelectAction(card);
                            break;
                    }
                } catch (Exception e) {
                    announcerSound = new Sounds(GameSoundsEnum.cantPlay.getPath());
                    announcerSound.playOne();
                }
        });
    }

    private void minionHandCardSelectAction(Minion card) {
        try {
            cardController.playMinion(card);
            actionForStartTargeter();
            gameController.addGameEvent(gameController.getGame().getPlayerIndex(),
                    GameEventEnum.playMinion, card.getName());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            baseGameThread.drawAfterCardSelection();
        } catch (Exception e) {
            if (e.getMessage().equals(ExceptionsEnum.forceAttack.getMessage())) {
                new Sounds(GameSoundsEnum.attack.getPath()).playOne();
                actionForStartTargeter();
                gameController.addGameEvent(gameController.getGame().getPlayerIndex(),
                        GameEventEnum.playMinion, card.getName());
                FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
                baseGameThread.drawAfterCardSelection();
                actionAfterAttack();
            }
            if (e.getMessage().equals(ExceptionsEnum.fullGroundDeck.getMessage())) {
                new Sounds(GameSoundsEnum.cantDoThat.getPath()).playOne();
            }
        }
    }

    private void spellHandCardSelectAction(Card card) {
        try {
            cardController.playSpell((Spell) card);
            actionForStartTargeter();
            gameController.addGameEvent(gameController.getGame().getPlayerIndex(),
                    GameEventEnum.playSpell, card.getName());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            baseGameThread.drawAfterCardSelection();
        } catch (Exception e) {
                new Sounds(GameSoundsEnum.cantDoThat.getPath()).playOne();
        }
    }

    private void weaponHandCardSelectAction(Card card) {
        try {
            cardController.playWeapon((Weapon) card);
            gameController.addGameEvent(gameController.getGame().getPlayerIndex(),
                    GameEventEnum.playWeapon, card.getName());
            FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
            baseGameThread.drawAfterCardSelection();
        } catch (Exception e) {
            new Sounds(GameSoundsEnum.cantDoThat.getPath()).playOne();
        }
    }

    public void handleGroundSelectionAction(CardDrawer cardDrawer, Card card, int playIndex) {
        cardDrawer.getButton().addActionListener(actionEvent -> {
            if (gameController.getGame().isAttackSelected()) {
                try {
                    cardController.checkCardCanAttacked(playIndex, card);
                    cardController.minionAttacked((Minion) card);
                    actionAfterAttack();
                } catch (Exception e) {
                    if (e.getMessage().equals(ExceptionsEnum.tauntExist.getMessage()))
                        new Sounds(GameSoundsEnum.attackWithTaunt.getPath()).playOne();
                    else
                        new Sounds(GameSoundsEnum.notValidTarget.getPath()).playOne();
                }
            } else if (gameController.getTargetManager().isTargeter()) {
                try {
                    gameController.getTargetManager().doTargetAction(TargetedType.Minion, card, playIndex,
                            game.getPlayerIndex());
                    actionAfterTargeter();
                } catch (Exception e) {
                    new Sounds(GameSoundsEnum.notValidTarget.getPath()).playOne();
                }
            } else {
                try {
                    cardController.checkCardCanAttack(playIndex, card);
                    groundCardSelectToAttackAction(card);
                } catch (Exception e) {
                    if (e.getMessage().equals(ExceptionsEnum.beforeAttacked.getMessage())) {
                        new Sounds(GameSoundsEnum.alreadyAttacked.getPath()).playOne();
                    } else {
                        new Sounds(GameSoundsEnum.cantPlay.getPath()).playOne();
                    }
                }
            }
        });
    }

    private void actionAfterTargeter() {
        MouseManager.getInstance().defaultCursorImage(baseGameThread.getUserFrame().getUserFrame());
        baseGameThread.drawAfterAttackAndTarget();
        new Sounds(GameSoundsEnum.targetDo.getPath()).playOne();
        FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
    }

    private void groundCardSelectToAttackAction(Card card) {
        MouseManager.getInstance().ChangeCursorImage(baseGameThread.getUserFrame().getUserFrame(),
                "GunCursor.png");
        cardController.groundCardSelectToAttack(card);
        FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
        baseGameThread.getMainPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    gameController.getGame().setAttackSelected(false);
                    MouseManager.getInstance().defaultCursorImage(baseGameThread.getUserFrame().getUserFrame());
                }
            }
        });
    }

    public void handleHeroAttackedAction(HeroHeroPowerDrawer heroPaint, Hero hero, int playIndex) {
        heroPaint.getButton().addActionListener(actionEvent -> {
            if (gameController.getTargetManager().isTargeter()) {
                try {
                    gameController.getTargetManager().doTargetAction(TargetedType.Hero, hero,
                            playIndex, game.getPlayerIndex());
                    actionAfterTargeter();
                } catch (Exception e) {
                    new Sounds(GameSoundsEnum.notValidTarget.getPath()).playOne();
                }
            }
            if (gameController.getGame().isAttackSelected()) {
                try {
                    cardController.checkHeroCanAttacked(playIndex, hero);
                    cardController.heroAttacked(hero);
                    actionAfterAttack();
                } catch (Exception e) {
                    if (e.getMessage().equals(ExceptionsEnum.tauntExist.getMessage())) {
                        announcerSound = new Sounds(GameSoundsEnum.attackWithTaunt.getPath());
                    } else {
                        announcerSound = new Sounds(GameSoundsEnum.notValidTarget.getPath());
                    }
                    announcerSound.playOne();
                }
            }
        });
    }

     void actionForStartTargeter() {
        if (gameController.getTargetManager().isTargeter()) {
            MouseManager.getInstance().ChangeCursorImage(baseGameThread.getUserFrame().getUserFrame(),
                    "GunCursor.png");
            baseGameThread.getMainPanel().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        gameController.getTargetManager().setTargeter(false);
                        MouseManager.getInstance().defaultCursorImage(baseGameThread.getUserFrame().getUserFrame());
                    }
                }
            });
        }

    }

    private void actionAfterAttack() {
        MouseManager.getInstance().defaultCursorImage(baseGameThread.getUserFrame().getUserFrame());
        baseGameThread.drawAfterAttackAndTarget();
        new Sounds(GameSoundsEnum.attack.getPath()).playOne();
        FileManagement.getInstance().saveGameModelToFile(gameController.getGame());
        gameController.addGameEvent(gameController.getGame().getPlayerIndex(),
                GameEventEnum.attack, ((Card) gameController.getGame().getAttacker()).getName());
    }

    public void handleGroundWeaponSelectionAction(CardDrawer weaponPaint, Card weapon, int playIndex) {
        weaponPaint.getButton().addActionListener(actionEvent -> {
            if (game.isAttackSelected() || gameController.getTargetManager().isTargeter()) {
                announcerSound = new Sounds(GameSoundsEnum.notValidTarget.getPath());
            } else {
                try {
                    cardController.checkCardCanAttack(playIndex, weapon);
                    groundCardSelectToAttackAction(weapon);
                } catch (Exception e) {
                    announcerSound = new Sounds(GameSoundsEnum.cantPlay.getPath());
                    announcerSound.playOne();
                }
            }
        });
    }

}
