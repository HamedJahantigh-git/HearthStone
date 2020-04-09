package initializer;


import controller.CardController;

import java.util.ArrayList;

public class InitCards {

    private static ArrayList<String> description = new ArrayList<>();

    public static void initMinion() {
        description.clear();
        description.add("At the end of your turn, deal 1 damage to all other minions");
        CardController.CreatNewCardInFile.creatMinion("Dreadscale", "Warlock", 3,
                3, 2, description, "", 2, 4);
        //***************************************************************************
        description.clear();
        description.add("Taunt");
        description.add("Battlecry");
        description.add("Battlecry: Deal 2 damage to your hero.");
        CardController.CreatNewCardInFile.creatMinion("Vulgar Homnuculus", "Warlock", 2,
                2, 1, description, "Common", 4, 2);
        //***************************************************************************
        description.clear();
        description.add("Your weapons cost (2) less");
        CardController.CreatNewCardInFile.creatMinion("Blackwater Pirate", "Neutral", 4,
                4, 3, description, "Rare", 5, 2);
        //***************************************************************************
        description.clear();
        description.add("Inspire");
        description.add("Inspire: Return this minion to your hand.");
        CardController.CreatNewCardInFile.creatMinion("Coliseum Manager", "Neutral", 3,
                3, 2, description, "Rare", 5, 2);
        //***************************************************************************
        description.clear();
        description.add("Battlecry");
        description.add("Battlecry: Give +1 Attack for each card in your opponent's hand.");
        CardController.CreatNewCardInFile.creatMinion("Fire Hawk", "Neutral", 3,
                3, 2, description, "Common", 3, 1);
        //***************************************************************************
        description.clear();
        description.add("At the start of your turn, gain +1 Health");
        CardController.CreatNewCardInFile.creatMinion("Hot Air Balloon", "Neutral", 1,
                1, 0, description, "Common", 2, 1);
        //***************************************************************************
        description.clear();
        description.add("Battlecry");
        description.add("Battlecry: Deal 3 damage to the enemy hero.");
        CardController.CreatNewCardInFile.creatMinion("Nightblade", "Neutral", 5,
                5, 4, description, "Free", 4, 4);
        //***************************************************************************
        description.clear();
        description.add("");
        CardController.CreatNewCardInFile.creatMinion("Puddlestomper", "Neutral", 2,
                2, 1, description, "Common", 2, 3);
        //***************************************************************************
        description.clear();
        description.add("Taunt");
        CardController.CreatNewCardInFile.creatMinion("Senjin Shieldmasta", "Neutral", 4,
                4, 3, description, "Free", 5, 3);
        //***************************************************************************
        description.clear();
        description.add("Reborn");
        description.add("Has +2 Attack while damaged.");
        CardController.CreatNewCardInFile.creatMinion("Temple Berserker", "Neutral", 2,
                2, 1, description, "Common", 2, 1);
        //***************************************************************************
        description.clear();
        description.add("Taunt");
        description.add("Deathrattle: Deal 3 damage to al minions");
        CardController.CreatNewCardInFile.creatMinion("Tunnel Blaster", "Neutral", 7,
                7, 6, description, "Common", 7, 3);
        //***************************************************************************
        description.clear();
        description.add("Your minions cost (3) more.");
        CardController.CreatNewCardInFile.creatMinion("Venture Co. Mercenary", "Neutral", 5,
                5, 4, description, "Common", 6, 7);
        //***************************************************************************
        description.clear();
        description.add("Charge");
        CardController.CreatNewCardInFile.creatMinion("Wolfrider", "Neutral", 3,
                3, 2, description, "Free", 1, 3);
        //***************************************************************************
    }

    public static void initSpell() {
        description.clear();
        description.add("Discover");
        description.add("Discover a weapon from any class. Add it to your Adventure Deck with +2/+2");
        CardController.CreatNewCardInFile.creatSpell("Friendly Smith", "Rogue", 4,
                4, 3, description, "");
        //***************************************************************************
        description.clear();
        description.add("Return an enemy minion to your opponent's hand.");
        CardController.CreatNewCardInFile.creatSpell("Sap", "Rogue", 2,
                2, 1, description, "Free");
        //***************************************************************************
        description.clear();
        description.add("Transform a minion into a 1/1 sheep");
        CardController.CreatNewCardInFile.creatSpell("Polymorph", "Mage", 4,
                4, 3, description, "");
        //***************************************************************************
        description.clear();
        description.add("Secret");
        description.add("Secret: After your opponent plays a minion, add two copies of it to your hand.");
        CardController.CreatNewCardInFile.creatSpell("Frozen Clone", "Mage", 3,
                3, 2, description, "common");
        //***************************************************************************
        description.clear();
        description.add("Twinspell");
        description.add("Give your minion +1/+1");
        CardController.CreatNewCardInFile.creatSpell("Blessing of the Ancients", "Neutral", 3,
                3, 2, description, "Common");
        //***************************************************************************
        description.clear();
        description.add("The next spell you cast this turn costs Health instead of Mana");
        CardController.CreatNewCardInFile.creatSpell("Bloodbloom", "Neutral", 2,
                2, 1, description, "Epic");
        //***************************************************************************
        description.clear();
        description.add("Twinspell");
        description.add("Give a Beast +2/+2");
        CardController.CreatNewCardInFile.creatSpell("Fresh Scent", "Neutral", 2,
                2, 1, description, "Common");
        //***************************************************************************
        description.clear();
        description.add("Draw a minion and a spell from your deck. Swap their Costs.");
        CardController.CreatNewCardInFile.creatSpell("Prismatic Lens", "Neutral", 4,
                4, 3, description, "Epic");
        //***************************************************************************
        description.clear();
        description.add("Spell Damage");
        description.add("Summon two random 2-Cost minions (improved by Spell Damage).");
        CardController.CreatNewCardInFile.creatSpell("Unexpected Results", "Neutral", 3,
                3, 2, description, "Epic");
        //***************************************************************************


    }

}
