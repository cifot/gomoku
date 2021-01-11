package sample.game.board;

public enum PlaceState {
    EMPTY, WHITE, BLACK;

    public String getStyle() {
        String style;
        switch (this) {
            case BLACK -> style = "    -fx-content-display: graphic-only;\n" +
                    "    -fx-graphic-text-gap: 0.0;\n" +
                    "    -fx-min-height: 10.0;\n" +
                    "    -fx-pref-height: 32.0;\n" +
                    "    -fx-min-width: 10.0;\n" +
                    "    -fx-pref-width: 32.0;\n" +
                    "    -fx-background-color: #000000;\n" +
                    "    -fx-background-radius: 50";

            case WHITE -> style = "    -fx-content-display: graphic-only;\n" +
                    "    -fx-graphic-text-gap: 0.0;\n" +
                    "    -fx-min-height: 10.0;\n" +
                    "    -fx-pref-height: 32.0;\n" +
                    "    -fx-min-width: 10.0;\n" +
                    "    -fx-pref-width: 32.0;\n" +
                    "    -fx-background-color: #ffffff;\n" +
                    "    -fx-background-radius: 50";

            case EMPTY -> style = "    -fx-content-display: graphic-only;\n" +
                    "    -fx-graphic-text-gap: 0.0;\n" +
                    "    -fx-min-height: 10.0;\n" +
                    "    -fx-pref-height: 18.0;\n" +
                    "    -fx-min-width: 10.0;\n" +
                    "    -fx-pref-width: 18.0;\n" +
                    "    -fx-background-color: #c0c0c0;\n" +
                    "    -fx-background-radius: 50";
            default -> throw new RuntimeException("Invalid style");
        }
        return style;
    }
}
