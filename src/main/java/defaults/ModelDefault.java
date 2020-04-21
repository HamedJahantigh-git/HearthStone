package defaults;

import controller.CardController;
import controller.FileManagement;
import model.Player;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;
import model.card.Weapon;
import model.hero.*;
import sun.jvm.hotspot.opto.MachIfNode;

import java.util.ArrayList;


public class ModelDefault {

    static public class PlayerDefaults {
        public static final int defaultMoney = 20;
        public static final Hero defaultHero = new Mage();
        public static ArrayList<Hero> defaultPlayerHeroes = new ArrayList<>();

        static {
            Mage mage = new Mage();
            Rogue rogue = new Rogue();
            Warlock warlock = new Warlock();
            Hunter hunter = new Hunter();
            Priest priest = new Priest();
            defaultPlayerHeroes.add(mage);
            defaultPlayerHeroes.add(rogue);
            defaultPlayerHeroes.add(warlock);
            defaultPlayerHeroes.add(hunter);
            defaultPlayerHeroes.add(priest);
        }
    }

    static public class CardDefaults {
        public static ArrayList<Minion> minions = CardController.ReadCardFromFile.readMinion();
        public static ArrayList<Spell> spells = CardController.ReadCardFromFile.readSpell();
        public static ArrayList<Weapon> weapons = CardController.ReadCardFromFile.readWeapon();

        public static ArrayList<Card> defaultPlayerCards() {
            ArrayList<String> cardsName = FileManagement.readLineByLineFile(FilesPath.defaultPlayerCardsName);
            ArrayList<Card> result = new ArrayList<>();
            if (cardsName.get(0).equals("All")) {
                result = CardController.getInstance().getAllCards();
            } else {
                for (int j = 0; j < cardsName.size(); j++) {
                    for (int i = 0; i < minions.size(); i++) {
                        if ((minions.get(i).getName().equals(cardsName.get(j)))) {
                            result.add(minions.get(i));
                        }
                    }
                    for (int i = 0; i < spells.size(); i++) {
                        if ((spells.get(i).getName().equals(cardsName.get(j)))) {
                            result.add(spells.get(i));
                        }
                    }
                    for (int i = 0; i < weapons.size(); i++) {
                        if ((weapons.get(i).getName().equals(cardsName.get(j)))) {
                            result.add(weapons.get(i));
                        }
                    }
                }
            }
            return result;
        }


    }

    static public class heroDefaults {
        public static final int baseOfHeroHealth = 30;
    }

    static public class deckDefaults {
        public static final int maxNumberCards = 15;
        public static final int minNumberCards = 5;
    }

}
