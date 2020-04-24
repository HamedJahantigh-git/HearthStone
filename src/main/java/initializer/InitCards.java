package initializer;


import controller.FileManagement;

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
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Sathrovarr", "Neutral", 9, 9, 8, mechanics,
                "Battlecry: Choose a friendly minion. Add a copy of it to your hand, deck and battlefield.", "Legendary", 5, 5);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Battlecry");
        mechanics.add("Copy");
        mechanics.add("Summon");
        mechanics.add("Taunt");
        mechanics.add("Mech-generating");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Tomb Warden", "Neutral", 8, 8, 7, mechanics,
                "Taunt\n" + "Battlecry: Summon a copy of this minion.", "Rare", 6, 3);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Summon");
        mechanics.add("Damage-related");
        mechanics.add("Mech-generating");
        mechanics.add("Taunt-generating");
        mechanics.add("Triggered effect");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Security Rover", "Neutral", 6, 6, 5, mechanics,
                "Whenever this minion takes damage, summon a 4/6 Mech with Taunt.", "Rare", 12, 4);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Increment attribute");
        mechanics.add("Draw-related");
        mechanics.add("Triggered effect");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Curio Collector", "Neutral", 5, 5, 4, mechanics,
                "Whenever you draw a card, gain +1/+1.", "Rare", 4, 4);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Set attribute");
        mechanics.add("Health-related");
        mechanics.add("Summoning-related");
        mechanics.add("Triggered effect");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("High Priest Amet", "Priest", 4, 4, 3, mechanics,
                "Whenever you summon a minion, set its Health equal to this minion's.", "Legendary", 7, 2);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Summoning-related");
        mechanics.add("Triggered effect");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Swamp King Dred", "Hunter", 7, 7, 6, mechanics,
                "After your opponent plays a minion, attack it.", "Legendary", 9, 9);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Deal damage");
        mechanics.add("Area of effect");
        mechanics.add("Triggered effect");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Dreadscale", "Warlock", 3, 3, 2, mechanics,
                "At the end of your turn, deal 1 damage to all other minions.", "Legendary", 2, 4);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Battlecry");
        mechanics.add("Deal damage");
        mechanics.add("Taunt");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Vulgar Homunculus", "Warlock", 2, 2, 1, mechanics,
                "Taunt\n" + "Battlecry: Deal 2 damage to your hero.", "Common", 4, 2);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Increment attribute");
        mechanics.add("Modify cost");
        mechanics.add("Ongoing effect");
        mechanics.add("Weapon-related");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Blackwater Pirate", "Neutral", 4, 4, 3, mechanics,
                "Your weapons cost (2) less.", "Rare", 5, 2);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Inspire");
        mechanics.add("Return to hand");
        mechanics.add("Hero Power-related");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Coliseum Manager", "Neutral", 3, 3, 2, mechanics,
                "Inspire: Return this minion to your hand.", "Rare", 5, 2);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Battlecry");
        mechanics.add("Increment attribute");
        mechanics.add("Hand-related");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Fire Hawk", "Neutral", 3, 3, 2, mechanics,
                "Battlecry: Gain +1 Attack for each card in your opponent's hand.", "Common", 3, 1);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Increment attribute");
        mechanics.add("Triggered effect");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Hot Air Balloon", "Neutral", 1, 1, 0, mechanics,
                "At the start of your turn, gain +1 Health.", "Common", 2, 1);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Battlecry");
        mechanics.add("Deal damage");
        mechanics.add("Raymond Swanland");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Nightblade", "Neutral", 5, 5, 4, mechanics,
                "Battlecry: Deal 3 damage to the enemy hero.", "Common", 4, 4);
        //***************************************************************************
        mechanics.clear();
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Puddlestomper", "Neutral", 2, 2, 1, mechanics,
                null, "Common", 2, 3);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Taunt");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Senjin Shieldmasta", "Neutral", 4, 4, 3, mechanics,
                "Taunt", "Common", 5, 3);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Enrage");
        mechanics.add("Increment attribute");
        mechanics.add("Reborn");
        mechanics.add("Damaged-related");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Temple Berserker", "Neutral", 2, 2, 1, mechanics,
                "Reborn\n" + "Has +2 Attack while damaged.", "Common", 2, 1);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Deal damage");
        mechanics.add("Deathrattle");
        mechanics.add("Taunt");
        mechanics.add("Area of effect");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Tunnel Blaster", "Neutral", 7, 7, 6, mechanics,
                "Taunt\n" + "Deathrattle: Deal 3 damage to all minions.", "Common", 7, 3);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Increment attribute");
        mechanics.add("Modify cost");
        mechanics.add("Ongoing effect");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Venture Co. Mercenary", "Neutral", 5, 5, 4, mechanics,
                "Your minions cost (3) more.", "Common", 6, 7);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Charge");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Wolfrider", "Neutral", 3, 3, 2, mechanics,
                "Charge", "Common", 1, 3);
        //***************************************************************************
    }

    public static void initSpell() {
        mechanics.clear();
        mechanics.add("Put into battlefield");
        mechanics.add("Sidequest");
        mechanics.add("Deck-related");
        mechanics.add("Mana-related");
        mechanics.add("Random");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Strength in Numbers", "Neutral", 1,
                1, 0, mechanics, "Sidequest: Spend 10 Mana on minions.\n" + "Reward: Summon a minion from your deck.",
                "Common", "Spend 10 Mana on minions.", "Summon a minion from your deck.");
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Summon");
        mechanics.add("Sidequest");
        mechanics.add("Dragon-generating");
        mechanics.add("Mana-related");
        mechanics.add("Spell-related");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Learn Draconic", "Neutral", 1,
                1, 0, mechanics, "Sidequest: Spend 8 Mana on spells.\nReward: Summon a 6/6 Dragon.",
                "Common", "Spend 8 Mana on spells.", "Summon a 6/6 Dragon.");
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Draw cards");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Sprint", "Neutral", 7,
                7, 6, mechanics, "Summon seven 1/1 Locusts with Rush.",
                "Rare", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Summon");
        mechanics.add("Beast-generating");
        mechanics.add("Rush-generating");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Swarm of Locusts", "Neutral", 6,
                6, 5, mechanics, "Draw 4 cards.",
                "Rare", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Increment attribute");
        mechanics.add("Divine Shield-granting");
        mechanics.add("Targeted");
        mechanics.add("Taunt-granting");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Pharaohs Blessing", "Neutral", 6,
                6, 5, mechanics, "Give a minion +4/+4, Divine Shield, and Taunt.",
                "Rare", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Discard");
        mechanics.add("Draw cards");
        mechanics.add("Spell-related");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Book of Specters", "Neutral", 2,
                2, 1, mechanics, "Draw 3 cards. Discard any spells drawn.",
                "Epic", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Set attribute");
        mechanics.add("Targeted");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Hunters Mark", "Hunter", 2,
                2, 1, mechanics, "Change a minion's Health to 1.",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Destroy");
        mechanics.add("Attack-related");
        mechanics.add("Targeted");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Shadow Word Death", "Priest", 2,
                2, 1, mechanics, "Destroy a minion with an Attack of 5 or more.",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Discover");
        mechanics.add("Generate");
        mechanics.add("Random");
        mechanics.add("Weapon-related");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Friendly Smith", "Rogue", 1,
                1, 0, mechanics, "Discover a weapon from any class. Add it to your Adventure Deck with +2/+2.",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Return to hand");
        mechanics.add("Targeted");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Sap", "Rogue", 2,
                2, 1, mechanics, "Return an enemy minion to your opponent's hand.",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Transform");
        mechanics.add("Beast-generating");
        mechanics.add("Targeted");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Polymorph", "Mage", 4,
                4, 3, mechanics, "Transform a minion into a 1/1 Sheep.",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Copy");
        mechanics.add("Generate");
        mechanics.add("Secret");
        mechanics.add("Summoning-related");
        mechanics.add("Triggered effect");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Frozen Clone", "Mage", 3,
                3, 2, mechanics, "Secret: After your opponent plays a minion, put 2 copies of it into your hand.",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Generate");
        mechanics.add("Increment attribute");
        mechanics.add("Twinspell");
        mechanics.add("Area of effect");
        mechanics.add("Spell-generating");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Blessing of the Ancients", "Neutral", 3,
                3, 2, mechanics, "Twinspell\n" + "Give your minions +1/+1.",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Deal damage");
        mechanics.add("Modify cost");
        mechanics.add("Cost-related");
        mechanics.add("Health-related");
        mechanics.add("Spell-related");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Bloodbloom", "Neutral", 2,
                2, 1, mechanics, "The next spell you cast this turn costs Health instead of Mana.",
                "Epic", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Generate");
        mechanics.add("Increment attribute");
        mechanics.add("Twinspell");
        mechanics.add("Beast-related");
        mechanics.add("Spell-generating");
        mechanics.add("Targeted");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Fresh Scent", "Neutral", 2,
                2, 1, mechanics, "Twinspell\n" + "Give a Beast +2/+2.",
                "Common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Modify cost");
        mechanics.add("Put into hand");
        mechanics.add("Spell-related");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Prismatic Lens", "Neutral", 4,
                4, 3, mechanics, "Draw a minion and a spell from your deck. Swap their Costs.",
                "Epic", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Summon");
        mechanics.add("Cost-related");
        mechanics.add("Random");
        mechanics.add("Spell Damage-related");
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Unexpected Results", "Neutral", 4,
                4, 3, mechanics, "Summon two random 2-Cost minions (improved by Spell Damage).",
                "Epic", null, null);
        //***************************************************************************

    }

    public static void initWeapon() {
        mechanics.clear();
        FileManagement.getInstance().getCreatNewCardInFile().creatWeapon("Heavy Axe", "Neutral", 1,
                1, 0, mechanics, null,
                "Common", 3, 1);
        //***************************************************************************
        mechanics.clear();
        FileManagement.getInstance().getCreatNewCardInFile().creatWeapon("Blood Fury", "Neutral", 3,
                3, 2, mechanics, null,
                "Common", 8, 3);
        //***************************************************************************
        mechanics.clear();
        FileManagement.getInstance().getCreatNewCardInFile().creatWeapon("Dragon Claw", "Neutral", 5,
                5, 4, mechanics, null,
                "Common", 2, 5);
        //***************************************************************************

    }

}
