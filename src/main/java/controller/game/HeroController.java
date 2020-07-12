package controller.game;

import enums.TargeterType;
import model.Game;
import model.Player;
import model.card.Minion;
import model.hero.Warlock;

import java.util.Random;

public class HeroController {

    private GameController gameController;
    private Game game;

    private Random random;

    public HeroController(GameController gameController) {
        random = new Random();
        this.gameController = gameController;
        this.game = gameController.getGame();
    }

    protected void handleSpecialPower() {
        for (int i = 0; i < 2; i++) {
            game.getPlayers(i).getPlayerGame().getHero().SpecialPower(game.getPlayerGames(i));
        }
    }

    public void handelHeroPower() {
        Player.PlayerGame currentPlayerGame = game.getPlayerGames(game.getPlayerIndex());
        switch (currentPlayerGame.getHero().getHeroName()) {
            case "Mage":
                handleMageHeroPower();
                break;
            case "Rogue":
                handleRogueHeroPower();
                break;
            case "Warlock":
                handleWarlockHeroPower();
                break;
            case "Hunter":
                break;
            case "Priest":
                handlePriestHeroPower();
                break;
        }
        currentPlayerGame.setHeroPowerUsedInTurn(currentPlayerGame.getHeroPowerUsedInTurn() + 1);
    }

    private void handleWarlockHeroPower() {
        Player.PlayerGame currentPlayerGame = game.getPlayerGames(game.getPlayerIndex());
        if(currentPlayerGame.getGroundCard().size()>0){
            int rand = random.nextInt(2);
            if(rand ==1){
                rand = random.nextInt(currentPlayerGame.getGroundCard().size());
                ((Minion)currentPlayerGame.getGroundCard().get(rand)).minusHealth(
                        -1*((Warlock)currentPlayerGame.getHero()).getHeroPowerEffect());
                ((Minion)currentPlayerGame.getGroundCard().get(rand)).plusAttack(
                        ((Warlock)currentPlayerGame.getHero()).getHeroPowerEffect());
            }else {
                gameController.moveCardAroundToHand(1);
            }
        }else {
            gameController.moveCardAroundToHand(1);
        }
        currentPlayerGame.getHero().minusHealth(2* ((Warlock)currentPlayerGame.getHero()).getHeroPowerEffect());
        reduceMana();
    }

    private void handleRogueHeroPower() {
        if (game.getPlayerGames(game.getPlayerIndex()).getHandCard().size() < 12
        &&game.getPlayerGames(game.getNextPlayerIndex()).getHandCard().size()>0) {
            game.getPlayerGames(game.getPlayerIndex()).getHandCard().add(
                    game.getPlayerGames(game.getNextPlayerIndex()).getHandCard().get(0));
            game.getPlayerGames(game.getNextPlayerIndex()).getHandCard().remove(0);
            game.getPlayerGames(game.getPlayerIndex()).getHero().SpecialPower(game.getPlayerGames(game.getPlayerIndex()));
            reduceMana();
        }
    }

    private void handlePriestHeroPower() {
        gameController.getTargetManager().setTargeter(TargeterType.HeroPower, game.getPlayerGames(game.getPlayerIndex()).getHero(), null,false);
        reduceMana();
    }

    private void handleMageHeroPower() {
        gameController.getTargetManager().setTargeter(TargeterType.HeroPower, game.getPlayerGames(game.getPlayerIndex()).getHero(), null,true);
        reduceMana();
    }

    private void reduceMana() {
        game.getPlayerGames(game.getPlayerIndex()).setCurrentMana(game.getPlayerGames(game.getPlayerIndex()).getCurrentMana()
                - game.getPlayerGames(game.getPlayerIndex()).getHero().getHeroPowerMana());
    }


}
