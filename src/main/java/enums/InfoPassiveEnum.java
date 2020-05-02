package enums;

public enum InfoPassiveEnum {
    offCards("Off Cards",""),
    nurse("Nurse",""),
    freePower("Free Power",""),
    manaJump("Mana",""),
    twiceDraw("Twice Draw","");

    private String title;
    private String explanation;

    private InfoPassiveEnum(String title,String explanation) {
        this.title = title;
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getTitle() {
        return title;
    }
}
