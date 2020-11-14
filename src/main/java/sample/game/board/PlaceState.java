package sample.game.board;

public enum PlaceState {
    EMPTY("    -fx-content-display: graphic-only;\n" +
            "    -fx-graphic-text-gap: 0.0;\n" +
            "    -fx-min-height: 10.0;\n" +
            "    -fx-pref-height: 18.0;\n" +
            "    -fx-min-width: 10.0;\n" +
            "    -fx-pref-width: 18.0;\n" +
            "    -fx-background-color: #c0c0c0;\n" +
            "    -fx-background-radius: 50"),
    WHITE("    -fx-content-display: graphic-only;\n" +
            "    -fx-graphic-text-gap: 0.0;\n" +
            "    -fx-min-height: 10.0;\n" +
            "    -fx-pref-height: 32.0;\n" +
            "    -fx-min-width: 10.0;\n" +
            "    -fx-pref-width: 32.0;\n" +
            "    -fx-background-color: #ffffff;\n" +
            "    -fx-background-radius: 50"),
    BLACK("    -fx-content-display: graphic-only;\n" +
            "    -fx-graphic-text-gap: 0.0;\n" +
            "    -fx-min-height: 10.0;\n" +
            "    -fx-pref-height: 32.0;\n" +
            "    -fx-min-width: 10.0;\n" +
            "    -fx-pref-width: 32.0;\n" +
            "    -fx-background-color: #000000;\n" +
            "    -fx-background-radius: 50");

    private String style;
    PlaceState(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}
