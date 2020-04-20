package initializer;


import controller.CardController;

import java.util.ArrayList;

public class InitCards {

    private static ArrayList<String> mechanics = new ArrayList<>();

    public static void initMinion() {
        mechanics.clear();
        mechanics.add("Battlecry");
        mechanics.add("Generate");
        mechanics.add("Put into battlefield");
        mechanics.add("Put into hand");
        mechanics.add("Shuffle into deck");
        mechanics.add("Targeted");
        CardController.CreatNewCardInFile.creatMinion("Sathrovarr", "Neutral", 9, 9, 8, mechanics,
                "Battlecry: Choose a friendly minion. Add a copy of it to your hand, deck and battlefield.", "Legendary", 5, 5);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Battlecry");
        mechanics.add("Copy");
        mechanics.add("Summon");
        mechanics.add("Taunt");
        mechanics.add("Mech-generating");
        CardController.CreatNewCardInFile.creatMinion("Tomb Warden", "Neutral", 8, 8, 7, mechanics,
                "Taunt\n" + "Battlecry: Summon a copy of this minion.", "Rare", 6, 3);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Summon");
        mechanics.add("Damage-related");
        mechanics.add("Mech-generating");
        mechanics.add("Taunt-generating");
        mechanics.add("Triggered effect");
        CardController.CreatNewCardInFile.creatMinion("Security Rover", "Neutral", 6, 6, 5, mechanics,
                "Whenever this minion takes damage, summon a 4/6 Mech with Taunt.", "Rare", 12, 4);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Increment attribute");
        mechanics.add("Draw-related");
        mechanics.add("Triggered effect");
        CardController.CreatNewCardInFile.creatMinion("Curio Collector", "Neutral", 5, 5, 4, mechanics,
                "Whenever you draw a card, gain +1/+1.", "Rare", 4, 4);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Set attribute");
        mechanics.add("Health-related");
        mechanics.add("Summoning-related");
        mechanics.add("Triggered effect");
        CardController.CreatNewCardInFile.creatMinion("High Priest Amet", "Priest", 4, 4, 3, mechanics,
                "Whenever you summon a minion, set its Health equal to this minion's.", "Legendary", 7, 2);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Summoning-related");
        mechanics.add("Triggered effect");
        CardController.CreatNewCardInFile.creatMinion("Swamp King Dred", "Hunter", 7, 7, 6, mechanics,
                "After your opponent plays a minion, attack it.", "Legendary", 9, 9);
        //***************************************************************************

        /*description.clear();
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

         */
    }

    public static void initSpell() {
        mechanics.clear();
        mechanics.add("Put into battlefield");
        mechanics.add("Sidequest");
        mechanics.add("Deck-related");
        mechanics.add("Mana-related");
        mechanics.add("Random");
        CardController.CreatNewCardInFile.creatSpell("Strength in Numbers", "Neutral", 1,
        1, 0, mechanics, "Sidequest: Spend 10 Mana on minions.\n"+"Reward: Summon a minion from your deck.",
                "Common", "Spend 10 Mana on minions.", "Summon a minion from your deck.");
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Summon");
        mechanics.add("Sidequest");
        mechanics.add("Dragon-generating");
        mechanics.add("Mana-related");
        mechanics.add("Spell-related");
        CardController.CreatNewCardInFile.creatSpell("Learn Draconic", "Neutral", 1,
                1, 0, mechanics, "Sidequest: Spend 8 Mana on spells.\nReward: Summon a 6/6 Dragon.",
                "Common", "Spend 8 Mana on spells.", "Summon a 6/6 Dragon.");
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Draw cards");
        CardController.CreatNewCardInFile.creatSpell("Sprint", "Neutral", 7,
                7, 6, mechanics, "Summon seven 1/1 Locusts with Rush.",
                "Rare", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Summon");
        mechanics.add("Beast-generating");
        mechanics.add("Rush-generating");
        CardController.CreatNewCardInFile.creatSpell("Swarm of Locusts", "Neutral", 6,
                6, 5, mechanics, "Draw 4 cards.",
                "Rare", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Increment attribute");
        mechanics.add("Divine Shield-granting");
        mechanics.add("Targeted");
        mechanics.add("Taunt-granting");
        CardController.CreatNewCardInFile.creatSpell("Pharaohs Blessing", "Neutral", 6,
                6, 5, mechanics, "Give a minion +4/+4, Divine Shield, and Taunt.",
                "Rare", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Discard");
        mechanics.add("Draw cards");
        mechanics.add("Spell-related");
        CardController.CreatNewCardInFile.creatSpell("Book of Specters", "Neutral", 2,
                2, 1, mechanics, "Draw 3 cards. Discard any spells drawn.",
                "Epic", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Set attribute");
        mechanics.add("Targeted");
        CardController.CreatNewCardInFile.creatSpell("Hunters Mark", "Hunter", 2,
                2, 1, mechanics, "Change a minion's Health to 1.",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Destroy");
        mechanics.add("Attack-related");
        mechanics.add("Targeted");
        CardController.CreatNewCardInFile.creatSpell("Shadow Word Death", "Priest", 2,
                2, 1, mechanics, "Destroy a minion with an Attack of 5 or more.",
                "common", null, null);
        //***************************************************************************

        /*description.clear();
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
*/

    }
    public static void initWeapon() {
        mechanics.clear();
        CardController.CreatNewCardInFile.creatWeapon("Heavy Axe", "Neutral", 1,
                1, 0, mechanics, null,
                "Common", 3, 1);
        //***************************************************************************
        mechanics.clear();
        CardController.CreatNewCardInFile.creatWeapon("Blood Fury", "Neutral", 3,
                3, 2, mechanics, null,
                "Common", 8, 3);
        //***************************************************************************
        mechanics.clear();
        CardController.CreatNewCardInFile.creatWeapon("Dragon Claw", "Neutral", 5,
                5, 4, mechanics, null,
                "Common", 2, 5);
        //***************************************************************************

    }

}
