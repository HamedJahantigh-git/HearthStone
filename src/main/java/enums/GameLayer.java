package enums;

public enum GameLayer {

    mainPanel(40),
    base(41),
    event(41),
    hero(42),
    groundCards(43),
    handCards(45),
    message(49),
    messageContent(50);

    private int layer;

    private GameLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
