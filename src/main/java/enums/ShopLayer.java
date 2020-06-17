package enums;

public enum ShopLayer {
    mainPanel(20),
    endLayer(30);

    private int layer;

    private ShopLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
