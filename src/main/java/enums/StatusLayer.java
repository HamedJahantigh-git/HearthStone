package enums;

public enum StatusLayer {
    mainPanel(35),
    deckShow(36),
    deckDetail(37),
    endLayer(40);

    private int layer;

    private StatusLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
