package initializer;


import controller.FileManagement;
import enums.CardMechanicsEnum;

import java.util.ArrayList;

public class InitCards {

    private static ArrayList<String> mechanics = new ArrayList<>();

    public static void initMinion() {
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Targeted.name());
        mechanics.add(CardMechanicsEnum.CopyHand.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Sathrovarr", "Neutral", 9, 9, 8, mechanics,
                "Targeted Mine - Choose a friendly minion. Add a copy of it to your hand, deck and battlefield.", "Legendary", 5, 5);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.CopyBattle.name());
        mechanics.add(CardMechanicsEnum.Taunt.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Tomb Warden", "Neutral", 8, 8, 7, mechanics,
                "Taunt " + "Battlecry - Summon a copy of this minion.", "Rare", 6, 3);
        //***************************************************************************
        mechanics.clear();
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Draconic Emissary", "Neutral", 6, 6, 5, mechanics,
                " null ", "Rare", 6, 6);
        //***************************************************************************

        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Taunt.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Guard Bot", "Neutral", 2, 2, 1, mechanics,
                "Taunt ", "Rare", 3, 2);
        //***************************************************************************
        mechanics.clear();
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Security Rover", "Neutral", 6, 6, 5, mechanics,
                " null ", "Rare", 6, 2);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Gain.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Curio Collector", "Neutral", 5, 5, 4, mechanics,
                "Whenever you Draw a card, Gain 1/1 ", "Rare", 4, 4);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Set.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("High Priest Amet", "Priest", 4, 4, 3, mechanics,
                "Whenever you summon a minion, set its Health equal to this minion's.", "Legendary", 7, 2);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.ForceAttack.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Swamp King Dred", "Hunter", 7, 7, 6, mechanics,
                "After your opponent plays a minion, attack it.", "Legendary", 9, 9);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.DealDamageTurn.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Dreadscale", "Warlock", 3, 3, 2, mechanics,
                "DealDamageTurn - Deal 1 damage to Minions", "Legendary", 2, 4);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Battlecry.name());
        mechanics.add(CardMechanicsEnum.DealDamage.name());
        mechanics.add(CardMechanicsEnum.Taunt.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Vulgar Homunculus", "Warlock", 2, 2, 1, mechanics,
                "Taunt - Battlecry - Deal 2 damage to your Hero", "Common", 4, 2);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.ModifyCost.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Blackwater Pirate", "Neutral", 4, 4, 3, mechanics,
                "Your weapons cost 2 less.", "Rare", 5, 2);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Inspire.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Coliseum Manager", "Neutral", 3, 3, 2, mechanics,
                "Inspire this minion to your hand.", "Rare", 5, 2);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Gain.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Fire Hawk", "Neutral", 3, 3, 2, mechanics,
                "Battlecry - Gain Attack 1 for each card in your opponent's hand.", "Common", 3, 1);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Rush.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Locust", "Neutral", 1, 1, 0, mechanics,
                "Rush", "Common", 1, 1);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.DealDamage.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Nightblade", "Neutral", 5, 5, 4, mechanics,
                "Battlecry - Deal 3 damage to the enemy Hero", "Common", 4, 4);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Empty");
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Puddlestomper", "Neutral", 2, 2, 1, mechanics,
                " ", "Common", 2, 3);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Taunt.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Senjin Shieldmasta", "Neutral", 4, 4, 3, mechanics,
                "Taunt", "Common", 5, 3);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Reborn.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Temple Berserker", "Neutral", 2, 2, 1, mechanics,
                "Reborn Attack 2", "Common", 2, 1);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.DealDamage.name());
        mechanics.add(CardMechanicsEnum.Taunt.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Tunnel Blaster", "Neutral", 7, 7, 6, mechanics,
                "Taunt - Deal 3 Minions", "Common", 7, 3);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Increment.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Venture Co. Mercenary", "Neutral", 5, 5, 4, mechanics,
                "Increment MinionsCost 3", "Common", 6, 7);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Charge.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatMinion("Wolfrider", "Neutral", 3, 3, 2, mechanics,
                "Charge", "Common", 1, 3);
        //***************************************************************************
    }

    public static void initSpell() {
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Quest.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Strength in Numbers", "Neutral", 1,
                1, 0, mechanics, "Sidequest: Spend 10 Mana on Minions " + "Reward: Summon a minion from your deck.",
                "Common", "Spend 10 Mana Minions ", "Summon Minion Deck");
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Quest.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Learn Draconic", "Neutral", 1,
                1, 0, mechanics, "Sidequest: Spend 8 Mana on Spells Reward: Summon a 6/6 Dragon.",
                "Common", "Spend 8 Mana Spells ", "Summon 6/6 Dragon.");
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Discover.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Sprint", "Neutral", 7,
                7, 6, mechanics, "Discover 4 Card",
                "Rare", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Swarm.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Swarm of Locusts", "Neutral", 6,
                6, 5, mechanics, "Summon 1 Minion",
                "Rare", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add("Increment attribute");
        mechanics.add(CardMechanicsEnum.Targeted.name());
        mechanics.add(CardMechanicsEnum.MakeDivineShieldAndTaunt.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Pharaohs Blessing", "Neutral", 6,
                6, 5, mechanics, "Targeted Mine - Increment 4/4 Minion - Taunt - DivineShield",
                "Rare", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Discard.name());
        mechanics.add(CardMechanicsEnum.Discover.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Book of Specters", "Neutral", 2,
                2, 1, mechanics, "Discover 3 Card - Discard All Spell",
                "Epic", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Targeted.name());
        mechanics.add(CardMechanicsEnum.TransForm.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Hunters Mark", "Hunter", 2,
                2, 1, mechanics, "Targeted Opponent - TransForm Minion 0/1",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Destroy.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Shadow Word Death", "Priest", 2,
                2, 1, mechanics, "Destroy Minion Attack 5 more",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Discover.name());
        mechanics.add(CardMechanicsEnum.Increment.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Friendly Smith", "Rogue", 1,
                1, 0, mechanics, "Discover 1 Weapon - Increment 2/2",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Targeted.name());
        mechanics.add(CardMechanicsEnum.Return.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Sap", "Rogue", 2,
                2, 1, mechanics, "Targeted Opponent - Return Minion Opponent Hand",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Targeted.name());
        mechanics.add(CardMechanicsEnum.TransForm.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Polymorph", "Mage", 4,
                4, 3, mechanics, "Targeted Opponent - Transform Minion 1/1",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Secret.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Frozen Clone", "Mage", 3,
                3, 2, mechanics, "Secret Minion Opponent - CopyHand 2",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Increment.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Blessing of the Ancients", "Neutral", 3,
                3, 2, mechanics, "Increment Minions 1/1",
                "common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Discover.name());
        mechanics.add(CardMechanicsEnum.Targeted.name());
        mechanics.add(CardMechanicsEnum.DealDamage.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Marked Shot", "Neutral", 4,
                2, 1, mechanics, "Targeted Opponent - Deal 4 Minion - Discover 1 Spell",
                "Common", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.PutHand.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Prismatic Lens", "Neutral", 4,
                4, 3, mechanics, "PutHand Minion Spell",
                "Epic", null, null);
        //***************************************************************************
        mechanics.clear();
        mechanics.add(CardMechanicsEnum.Summon.name());
        FileManagement.getInstance().getCreatNewCardInFile().creatSpell("Unexpected Results", "Neutral", 4,
                4, 3, mechanics, "Summon 2 Minion - CostRelated 2",
                "Epic", null, null);
        //***************************************************************************

    }

    public static void initWeapon() {
        mechanics.clear();
        FileManagement.getInstance().getCreatNewCardInFile().creatWeapon("Heavy Axe", "Neutral", 1,
                1, 0, mechanics, " ",
                "Common", 3, 1);
        //***************************************************************************
        mechanics.clear();
        FileManagement.getInstance().getCreatNewCardInFile().creatWeapon("Blood Fury", "Neutral", 3,
                3, 2, mechanics, " ",
                "Common", 8, 3);
        //***************************************************************************
        mechanics.clear();
        FileManagement.getInstance().getCreatNewCardInFile().creatWeapon("Dragon Claw", "Neutral", 5,
                5, 4, mechanics, " ",
                "Common", 2, 5);
        //***************************************************************************

    }

}
