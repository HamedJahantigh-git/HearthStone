package enums;

public enum GameLayer {

    base(41),
    hero(42),
    handCards(45),
    message(49);

    private int layer;

    private GameLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
