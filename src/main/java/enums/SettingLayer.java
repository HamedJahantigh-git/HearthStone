package enums;

public enum SettingLayer {
    mainPanel(30)
    ;

    private int layer;

    private SettingLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
