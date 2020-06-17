package enums;

public enum MainLayer {
    mainPanel(0),
    message(9),
    endLayer(10);

    private int layer;

    private MainLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
