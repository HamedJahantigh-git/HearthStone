package enums;

public enum MineGameLayer {

    mainPanel(40),
    event(41),
    clock(48),
    quest(44),
    mana(45),
    hero(42),
    groundCards(46),
    weapon(43),
    handCards(47),
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
