package defaults;

import controller.CardController;
import controller.FileManagement;
import model.Player;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;
import model.hero.Hero;
import model.hero.Mage;
import model.hero.Rogue;
import model.hero.Warlock;
import sun.jvm.hotspot.opto.MachIfNode;

import java.util.ArrayList;


public class ModelDefault {

    static public class PlayerDefaults {
        public static final int defaultMoney = 20;
        public static final Hero defaultHero = new Mage();
        public static ArrayList<Hero> defaultPlayerHeroes = new ArrayList<Hero>();

        static {
            Mage mage = new Mage();
            Rogue rogue = new Rogue();
            Warlock warlock = new Warlock();
            defaultPlayerHeroes.add(mage);
            defaultPlayerHeroes.add(rogue);
            defaultPlayerHeroes.add(warlock);
        }
    }

    static public class CardDefaults {
        public static class defaultAllGropingCards {
            static ArrayList<Minion> minions = CardController.ReadCardFromFile.readMinion();
            static ArrayList<Spell> spells = CardController.ReadCardFromFile.readSpell();

            public static ArrayList<Card> defaultMageCards() {
                ArrayList<Card> result = new ArrayList<>();
                for (int i = 0; i < spells.size(); i++) {
                    if ((spells.get(i).getName().equals("Bloodbloom")) ||
                            (spells.get(i).getName().equals("Polymorph"))) {
                        result.add(spells.get(i));
                    }
                }
                for (int i = 0; i < minions.size(); i++) {
                    if ((minions.get(i).getName().equals("Blackwater Pirate"))) {
                        result.add(minions.get(i));
                    }
                }
                return result;
            }

            public static ArrayList<Card> defaultWarlockCards() {
                ArrayList<Card> result = new ArrayList<>();
                for (int i = 0; i < spells.size(); i++) {
                    if ((spells.get(i).getName().equals("Blessing of the Ancients"))) {
                        result.add(spells.get(i));
                    }
                }
                for (int i = 0; i < minions.size(); i++) {
                    if ((minions.get(i).getName().equals("Dreadscale")) ||
                            (minions.get(i).getName().equals("Coliseum Manager"))) {
                        result.add(minions.get(i));
                    }
                }
                return result;
            }

            public static ArrayList<Card> defaultRogueCards() {
                ArrayList<Card> result = new ArrayList<>();
                for (int i = 0; i < spells.size(); i++) {
                    if ((spells.get(i).getName().equals("Friendly Smith"))) {
                        result.add(spells.get(i));
                    }
                }
                for (int i = 0; i < minions.size(); i++) {
                    if ((minions.get(i).getName().equals("Fire Hawk")) ||
                            (minions.get(i).getName().equals("Tunnel Blaster"))) {
                        result.add(minions.get(i));
                    }
                }
                return result;
            }

            public static ArrayList<Card> defaultFreePlayerCards() {
                ArrayList<Card> result = new ArrayList<>();
                for (int i = 0; i < minions.size(); i++) {
                    if ((minions.get(i).getName().equals("Nightblade"))) {
                        result.add(minions.get(i));
                    }
                }
                return result;
            }
        }
    }

    static public class heroDefaults {
        public static final int maxNumberHeroCard = 15;
        public static final int maxNumberSameHeroCard = 2;
        public static final int baseOfHeroHealth = 30;
    }

}
