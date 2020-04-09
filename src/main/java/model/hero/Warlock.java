package model.hero;

import defaults.ModelDefault;

public class Warlock extends Hero {
    {
        super.heroName = "Warlock";
        super.Health = ModelDefault.heroDefaults.baseOfHeroHealth + 5;
        super.heroCards = ModelDefault.CardDefaults.defaultAllGropingCards.defaultWarlockCards();
    }
}
