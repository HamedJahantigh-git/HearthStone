package enums;

public enum CollectionLayer {
    mainPanel(10),
    endLayer(20);

    private int layer;

    private CollectionLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
