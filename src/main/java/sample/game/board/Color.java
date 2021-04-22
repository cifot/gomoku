package sample.game.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Color {
    WHITE("-fx-background-color: #FFFFFF;" +
            "-fx-border-color: #707070;"),
    BLACK("-fx-background-color: #333333;" +
            "-fx-border-color: #222222;"),
    EMPTY("-fx-border-width: 0;" +
            "-fx-pref-width: 18.0;" +
            "-fx-pref-height: 18.0;" +
            "-fx-background-color: #c0c0c0;");

    public  Color getEnemyColor() {
        return this == Color.BLACK ? Color.WHITE : Color.BLACK;
    }
    private final String style;
}
