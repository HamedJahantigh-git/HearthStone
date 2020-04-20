package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import defaults.FilesPath;
import model.Player;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;
import model.card.Weapon;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

public class CardController {

    public static class CreatNewCardInFile {

        static Gson gson = new GsonBuilder().create();

        public static void creatMinion(String name, String cardClass, int mana,
                                       int buyCost, int incomeSell, ArrayList<String> mechanics, String description,
                                       String rarity, int health, int attack) {
            Minion minion = new Minion(name, cardClass, "Minion", mana, buyCost, incomeSell, mechanics, description,
                    rarity, health, attack);
            try {
                Writer writer = new FileWriter(
                        FilesPath.minionDataPath + "/" + name + ".txt");
                gson.toJson(minion, writer);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void creatSpell(String name, String cardClass, int mana,
                                      int buyCost, int incomeSell, ArrayList<String> mechanics, String description,
                                      String rarity, String quest, String reward) {
            Spell spell = new Spell(name, cardClass, "Spell", mana,
                    buyCost, incomeSell, mechanics, description, rarity, quest, reward);
            try {

                Writer writer = new FileWriter(
                        FilesPath.spellDataPath + "/" + name + ".txt");
                gson.toJson(spell, writer);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void creatWeapon(String name, String cardClass, int mana,
                                       int buyCost, int incomeSell, ArrayList<String> mechanics, String description,
                                       String rarity, int durability, int attack) {
            Weapon weapon = new Weapon(name, cardClass, "Weapon", mana,
                    buyCost, incomeSell, mechanics, description, rarity, durability, attack);
            try {
                Writer writer = new FileWriter(
                        FilesPath.weaponDataPath + "/" + name + ".txt");
                gson.toJson(weapon, writer);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static class ReadCardFromFile {
        static Gson gson = new Gson();

        static public ArrayList<Minion> readMinion() {
            ArrayList<Minion> minionsCards = new ArrayList<>();
            ArrayList<String> minionsName = FileManagement.allFileNameInPath(FilesPath.minionDataPath);
            for (String s : minionsName) {
                try (Reader reader = new FileReader(FilesPath.minionDataPath + "/" + s)) {
                    minionsCards.add(gson.fromJson(reader, Minion.class));
                } catch (Exception ignored) {
                }
            }
            return minionsCards;
        }

        static public ArrayList<Spell> readSpell() {
            ArrayList<Spell> spellsCards = new ArrayList<>();
            ArrayList<String> spellsName = FileManagement.allFileNameInPath(FilesPath.spellDataPath);
            for (String s : spellsName) {
                try (Reader reader = new FileReader(FilesPath.spellDataPath + "/" + s)) {
                    spellsCards.add(gson.fromJson(reader, Spell.class));
                } catch (Exception ignored) {
                }
            }
            return spellsCards;
        }
        static public ArrayList<Weapon> readWeapon() {
            ArrayList<Weapon> weaponsCards = new ArrayList<>();
            ArrayList<String> weaponsName = FileManagement.allFileNameInPath(FilesPath.weaponDataPath);
            for (String s : weaponsName) {
                try (Reader reader = new FileReader(FilesPath.weaponDataPath + "/" + s)) {
                    weaponsCards.add(gson.fromJson(reader, Weapon.class));
                } catch (Exception ignored) {
                }
            }
            return weaponsCards;
        }
    }

    public static String[] cardsName(ArrayList<Card> cards) {
        String[] result;
        if (cards.size() > 0) {
            result = new String[cards.size()];
            for (int i = 0; i < cards.size(); i++)
                result[i] = cards.get(i).getName();
            for (int i = 0; i < result.length - 1; i++)
                for (int j = i + 1; j < result.length; j++)
                    if (result[i].compareTo(result[j]) > 0) {
                        String temp = result[i];
                        result[i] = result[j];
                        result[j] = temp;
                    }
        } else {
            result = new String[1];
            result[0] = null;
        }
        return result;
    }

    /*public static void deleteHeroCard(String card, Player player) throws Exception {
        boolean check = true;
        for (int i = 0; i < player.getCurrentHero().getHeroCards().size(); i++) {
            if (player.getCurrentHero().getHeroCards().get(i).getName().equals(card)) {
                player.addToFreePlayerCards(player.getCurrentHero().getHeroCards().get(i));
                player.getCurrentHero().deleteHeroCards(player.getCurrentHero().getHeroCards().get(i));
                check = false;
                break;
            }
        }
        if (check) {
            throw new Exception(" not valid");
        }
    }

    public static void addHeroCard(String card, Player player) throws Exception {
        boolean check = true;
        for (int i = 0; i < player.getFreePlayerCards().size(); i++) {
            if ((player.getFreePlayerCards().get(i).getName().equals(card))
                    && (player.getFreePlayerCards().get(i).getCardClass().equals(player.getCurrentHero().getHeroName())
                    || player.getFreePlayerCards().get(i).getCardClass().equals("Neutral"))
                    && (player.getCurrentHero().getHeroCards().size() < 16)) {
                String[] heroCardsName = CardController.cardsName(player.getCurrentHero().getHeroCards());
                int counter = 0;
                for (String s : heroCardsName) {
                    if (s.compareTo(card) == 0) {
                        counter++;
                    }
                }
                if (counter < 2) {
                    player.getCurrentHero().addToHeroCards(player.getFreePlayerCards().get(i));
                    player.deleteFromFreePlayerCards(player.getFreePlayerCards().get(i));
                    check = false;
                    break;
                }
            }
        }
        if (check) {
            throw new Exception(" not valid");
        }
    }*/

}
