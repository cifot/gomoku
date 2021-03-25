package sample.game.board;

public enum Color {
    WHITE("-fx-background-color: #FFFFFF;" +
            "-fx-border-color: #707070;"),
    BLACK("-fx-background-color: #333333;" +
            "-fx-border-color: #222222;");

    private final String style;

    Color(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}
