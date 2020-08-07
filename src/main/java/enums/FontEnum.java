package enums;

public enum FontEnum {
    PASSWORD_FIELD("times new roman"),
    BUTTON("BelweBdBTBold"),
    COMBO_BOX("BelweBdBTBold"),
    IMPORT_BOX("times new roman"),
    CARD("BelweBdBTBold"),
    LABEl("FORTE"),
    TIMER("BelweBdBTBold"),
    MANA("BelweBdBTBold"),
    MESSAGE("FORTE"),
    TITLE("BelweBdBTBold");

    private String name;

    private FontEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
