package sample.game.board;

public enum PlaceState {
    WHITE("-fx-background-color: #FFFFFF;" +
            "-fx-border-color: #707070;"),
    BLACK("-fx-background-color: #333333;" +
            "-fx-border-color: #222222;"),
    AVAILABLE_ONLY_WHITE("-fx-border-width: 0;" +
            "-fx-pref-width: 18.0;" +
            "-fx-pref-height: 18.0;" +
            "-fx-background-color: #c0c0c0;"),
    AVAILABLE_ONLY_BLACK("-fx-border-width: 0;" +
            "-fx-pref-width: 18.0;" +
            "-fx-pref-height: 18.0;" +
            "-fx-background-color: #c0c0c0;"),
    AVAILABLE("-fx-border-width: 0;" +
            "-fx-pref-width: 18.0;" +
            "-fx-pref-height: 18.0;" +
            "-fx-background-color: #c0c0c0;");

    private final String style;

    PlaceState(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}
