package model.hero;

import defaults.ModelDefault;

public class Rogue extends Hero {
    {
        super.heroName = "Rogue";
        super.Health = ModelDefault.heroDefaults.baseOfHeroHealth;
        super.heroCards = ModelDefault.CardDefaults.defaultAllGropingCards.defaultRogueCards();
    }
}
