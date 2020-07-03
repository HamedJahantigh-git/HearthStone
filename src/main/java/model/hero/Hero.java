package model.hero;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import defaults.ModelDefault;
import enums.HeroType;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Hunter.class, name = "Hunter"),
        @JsonSubTypes.Type(value = Priest.class, name = "Priest"),
        @JsonSubTypes.Type(value = Rogue.class, name = "Rogue"),
        @JsonSubTypes.Type(value = Mage.class, name = "Mage"),
        @JsonSubTypes.Type(value = Warlock.class, name = "Warlock"),
})

 public class Hero {

    protected String heroName;
    protected int Health;

    protected Hero(String heroName){
        this.heroName = heroName;
        this.Health = ModelDefault.heroDefaults.baseOfHeroHealth;
    }

    public String getHeroName() {
        return heroName;
    }

    public int getHealth() {
        return Health;
    }

   public void SpecialPower (){};
}
