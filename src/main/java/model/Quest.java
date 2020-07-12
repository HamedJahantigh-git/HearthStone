package model;

import enums.QuestType;
import model.card.Spell;

public class Quest {
    private Spell questCard;
    private QuestType questType;
    private int manaDoing;
    private int manaHaveTo;
    private boolean isOn;

    public Quest() {
        questCard = null;
        isOn = false;
    }

    public void setQuest(Spell questCard, int manaHaveTo) {
        this.questCard = questCard;
        this.manaHaveTo = manaHaveTo;
        this.isOn = true;
        setQuestType();
    }

    private void setQuestType() {
        if (questCard.getDescription().contains("Spells"))
            questType = QuestType.Spells;
        if (questCard.getDescription().contains("Minions"))
            questType = QuestType.Minions;
    }

    public boolean isOn() {
        return isOn;
    }

    public QuestType getQuestType() {
        return questType;
    }

    public void plusMana(int plus) {
        this.manaDoing += plus;
    }

    public boolean checkDoing() {
        if (manaDoing >= manaHaveTo) {
            isOn = false;
            return true;
        }
        return false;
    }

    public Spell getQuestCard() {
        return questCard;
    }

    public int getManaDoing() {
        return manaDoing;
    }

    public int getManaHaveTo() {
        return manaHaveTo;
    }
}
