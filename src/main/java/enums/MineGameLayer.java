package enums;

public enum MineGameLayer {

    mainPanel(40),
    event(41),
    clock(48),
    mana(44),
    hero(42),
    groundCards(43),
    handCards(45),
    message(49),
    messageContent(50),
    endLayer(60);

    private int layer;

    private MineGameLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
