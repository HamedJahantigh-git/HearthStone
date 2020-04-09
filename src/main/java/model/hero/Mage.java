package model.hero;

import defaults.ModelDefault;

public class Mage extends Hero {
    {
        super.heroName = "Mage";
        super.Health = ModelDefault.heroDefaults.baseOfHeroHealth;
        super.heroCards = ModelDefault.CardDefaults.defaultAllGropingCards.defaultMageCards();
    }
}
