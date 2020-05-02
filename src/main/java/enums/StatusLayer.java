package enums;

public enum StatusLayer {
    base(35),
    deckShow(36),
    deckDetail(37);

    private int layer;

    private StatusLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
