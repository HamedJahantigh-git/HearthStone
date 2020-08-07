package controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import defaults.FilesPath;
import defaults.ModelDefault;
import enums.CardType;
import enums.HeroType;
import io.gsonfire.GsonFireBuilder;
import io.gsonfire.TypeSelector;
import model.Game;
import model.Player;
import model.card.Card;
import model.card.Minion;
import model.card.Spell;
import model.card.Weapon;
import model.hero.*;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileManagement {

    private static FileManagement instance = null;
    private ReadCardFromFile readCardFromFile;
    private CreatNewCardInFile creatNewCardInFile;
    private Copy copy;
    private PlayerFile playerFile;
    private File file;

    private FileManagement() {
        copy = new Copy();
        readCardFromFile = new ReadCardFromFile();
        creatNewCardInFile = new CreatNewCardInFile();
        playerFile = new PlayerFile();
    }

    public static FileManagement getInstance() {
        if (instance == null)
            instance = new FileManagement();
        return instance;
    }

    public ReadCardFromFile getReadCardFromFile() {
        return readCardFromFile;
    }

    public CreatNewCardInFile getCreatNewCardInFile() {
        return creatNewCardInFile;
    }

    public void creatFolder(String path) {
        new File(path).mkdir();
    }

    public PlayerFile getPlayerFile() {
        return playerFile;
    }

    public ArrayList<Card> getAllCardsFromFile() {
        ArrayList<Card> result = new ArrayList<>();
        result.addAll(ModelDefault.CardDefaults.minions);
        result.addAll(ModelDefault.CardDefaults.spells);
        result.addAll(ModelDefault.CardDefaults.weapons);
        return result;
    }

    public ArrayList<String> allFileNameInPath(String path) {
        ArrayList<String> result = new ArrayList<>();
        file = new File(path);
        String[] name = file.list();
        for (int i = 0; i < name.length; i++)
            result.add(name[i]);
        return result;
    }

    public void saveGameModelToFile(Game game) {
        Gson gson = new GsonBuilder().create();
        try {
            Writer writer = new FileWriter(
                    FilesPath.GAME_MODEL + "/battle#" + game.getID() + ".txt");
            gson.toJson(game, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveHeroModelToFile(Hero hero) {
        Gson gson = new GsonBuilder().create();
        try {
            Writer writer = new FileWriter(
                    FilesPath.HERO_DATA_PATH + "/" + hero.getHeroName() + ".txt");
            gson.toJson(hero, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readLineByLineFile(String filePath) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int Counter = 0;
        for (int i = 0; i < contentBuilder.toString().length(); i++) {
            if (contentBuilder.toString().charAt(i) == '\n') {
                result.add(contentBuilder.toString().substring(Counter, i));
                Counter = i + 1;
            }
        }
        return result;
    }

    public class Copy {

        public Hero copyHero(Hero hero) {
            Hero result = null;
            switch (hero.getHeroName()) {
                case "Mage":
                    result = new Mage();
                    break;
                case "Warlock":
                    result = new Warlock();
                    break;
                case "Priest":
                    result = new Priest();
                    break;
                case "Hunter":
                    result = new Hunter();
                    break;
                case "Rogue":
                    result = new Rogue();
                    break;
            }
            return result;
        }

        public Card copyCard(Card card) {
            Card result = null;
            switch (card.getType()) {
                case "Minion":
                    result = copyMinion((Minion) card);
                    break;
                case "Spell":
                    result = copySpell((Spell) card);
                    break;
                case "Weapon":
                    result = copyWeapon((Weapon) card);
                    break;
            }
            return result;
        }

        private Weapon copyWeapon(Weapon card) {
            return new Weapon(card.getName(), card.getCardClass(), card.getType(), card.getMana(), card.getBuyCost(),
                    card.getIncomeSell(), card.getMechanics(), card.getDescription(), card.getRarity(), card.getDurability(), card.getAttack());
        }

        private Minion copyMinion(Minion card) {
            return new Minion(card.getName(), card.getCardClass(), card.getType(), card.getMana(),
                    card.getBuyCost(), card.getIncomeSell(), card.getMechanics(), card.getDescription(), card.getRarity(), card.getHealth(),
                    card.getAttack());
        }

        private Spell copySpell(Spell card) {
            return new Spell(card.getName(), card.getCardClass(), card.getType(), card.getMana(), card.getBuyCost(),
                    card.getIncomeSell(), card.getMechanics(), card.getDescription(), card.getRarity(), card.getQuest(), card.getReward());
        }
    }

    public class CreatNewCardInFile {

        private Gson gson = new GsonBuilder().create();

        public void creatMinion(String name, String cardClass, int mana,
                                int buyCost, int incomeSell, ArrayList<String> mechanics, String description,
                                String rarity, int health, int attack) {
            Minion minion = new Minion(name, cardClass, "Minion", mana, buyCost, incomeSell, mechanics, description,
                    rarity, health, attack);
            try {
                Writer writer = new FileWriter(
                        FilesPath.MINION_DATA_PATH + "/" + name + ".txt");
                gson.toJson(minion, writer);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void creatSpell(String name, String cardClass, int mana,
                               int buyCost, int incomeSell, ArrayList<String> mechanics, String description,
                               String rarity, String quest, String reward) {
            Spell spell = new Spell(name, cardClass, "Spell", mana,
                    buyCost, incomeSell, mechanics, description, rarity, quest, reward);
            try {

                Writer writer = new FileWriter(
                        FilesPath.SPELL_DATA_PATH + "/" + name + ".txt");
                gson.toJson(spell, writer);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void creatWeapon(String name, String cardClass, int mana,
                                int buyCost, int incomeSell, ArrayList<String> mechanics, String description,
                                String rarity, int durability, int attack) {
            Weapon weapon = new Weapon(name, cardClass, "Weapon", mana,
                    buyCost, incomeSell, mechanics, description, rarity, durability, attack);
            try {
                Writer writer = new FileWriter(
                        FilesPath.WEAPON_DATA_PATH + "/" + name + ".txt");
                gson.toJson(weapon, writer);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public class ReadCardFromFile {
        Gson gson = new Gson();

        public ArrayList<Minion> readMinion() {
            ArrayList<Minion> minionsCards = new ArrayList<>();
            ArrayList<String> minionsName = allFileNameInPath(FilesPath.MINION_DATA_PATH);
            for (String s : minionsName) {
                try (Reader reader = new FileReader(FilesPath.MINION_DATA_PATH + "/" + s)) {
                    minionsCards.add(gson.fromJson(reader, Minion.class));
                } catch (Exception ignored) {
                }
            }
            return minionsCards;
        }

        public ArrayList<Spell> readSpell() {
            ArrayList<Spell> spellsCards = new ArrayList<>();
            ArrayList<String> spellsName = allFileNameInPath(FilesPath.SPELL_DATA_PATH);
            for (String s : spellsName) {
                try (Reader reader = new FileReader(FilesPath.SPELL_DATA_PATH + "/" + s)) {
                    spellsCards.add(gson.fromJson(reader, Spell.class));
                } catch (Exception ignored) {
                }
            }
            return spellsCards;
        }

        public ArrayList<Weapon> readWeapon() {
            ArrayList<Weapon> weaponsCards = new ArrayList<>();
            ArrayList<String> weaponsName = allFileNameInPath(FilesPath.WEAPON_DATA_PATH);
            for (String s : weaponsName) {
                try (Reader reader = new FileReader(FilesPath.WEAPON_DATA_PATH + "/" + s)) {
                    weaponsCards.add(gson.fromJson(reader, Weapon.class));
                } catch (Exception ignored) {
                }
            }
            return weaponsCards;
        }
    }

    public class PlayerFile {

        public PlayerFile() {
        }

        public void creatPlayerFile(Player player) {
            savePlayerToFile(player);
        }

        public void savePlayerToFile(Player player) {
            /*String path = FilesPath.playerDataPath + "/" + player.getUserName();
            try {
                Writer writer = new FileWriter(
                        path + ".txt");
                mapper.writeValue(writer, player);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            Gson gson = handleGsonProblems();
            String path = FilesPath.PLAYER_DATA_PATH + "/" + player.getUserName();
            try {
                Writer writer = new FileWriter(
                        path + ".txt");
                gson.toJson(player, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Player creatPlayerFromFile(String name) {
            /*Player player = null;
            try (Reader reader = new FileReader(
                    FilesPath.playerDataPath + "/" + name + ".txt")) {
                player = mapper.readValue(reader, Player.class);
            } catch (Exception ignored) {
            }
            return player;*/
            Player player = null;
            Gson gson = handleGsonProblems();
            try (Reader reader = new FileReader(
                    FilesPath.PLAYER_DATA_PATH + "/" + name + ".txt")) {
                player = gson.fromJson(reader, Player.class);
            } catch (Exception ignored) {
            }
            return player;
        }

        private Gson handleGsonProblems() {
            GsonBuilder gsonBuilder = handleSaveGsonPolymorphism();
            //gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
            Gson gson = handleAbstractGsonSerDeSer(gsonBuilder);
            return gson;
        }

        private GsonBuilder handleSaveGsonPolymorphism() {
            GsonFireBuilder builder = new GsonFireBuilder();
            handleSaveCardToGsonPolymorphism(builder);
            // handleSaveHeroToGsonPolymorphism(builder);
            return builder.createGsonBuilder();
        }

        private void handleSaveHeroToGsonPolymorphism(GsonFireBuilder builder) {
            builder.registerTypeSelector(Hero.class, new TypeSelector<Hero>() {
                @Override
                public Class<? extends Hero> getClassForElement(JsonElement readElement) {
                    String type = readElement.getAsJsonObject().get("type").getAsString();
                    if (type.equals(HeroType.Hunter.name())) {
                        return Hunter.class;
                    } else if (type.equals(HeroType.Warlock.name())) {
                        return Warlock.class;
                    } else if (type.equals(HeroType.Mage.name())) {
                        return Mage.class;
                    } else if (type.equals(HeroType.Rogue.name())) {
                        return Rogue.class;
                    } else if (type.equals(HeroType.Priest.name())) {
                        return Priest.class;
                    } else {
                        return null; //returning null will trigger Gson's default behavior
                    }
                }
            });
        }

        private void handleSaveCardToGsonPolymorphism(GsonFireBuilder builder) {
            builder.registerTypeSelector(Card.class, new TypeSelector<Card>() {
                @Override
                public Class<? extends Card> getClassForElement(JsonElement readElement) {
                    String type = readElement.getAsJsonObject().get("type").getAsString();
                    if (type.equals(CardType.Minion.name())) {
                        return Minion.class;
                    } else if (type.equals(CardType.Spell.name())) {
                        return Spell.class;
                    } else if (type.equals(CardType.Weapon.name())) {
                        return Weapon.class;
                    } else {
                        return null; //returning null will trigger Gson's default behavior
                    }
                }
            });
        }

        private Gson handleAbstractGsonSerDeSer(GsonBuilder gsonBuilder) {
            return gsonBuilder.registerTypeAdapter
                    (Hero.class, new HeroAbstractAdapter()).create();
        }
    }

    public Copy getCopy() {
        return copy;
    }
}
