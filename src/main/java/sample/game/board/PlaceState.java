package sample.game.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlaceState {
    WHITE("WHITE",
            "-fx-background-color: #FFFFFF;" +
            "-fx-border-color: #707070;"),
    BLACK("BLACK",
            "-fx-background-color: #333333;" +
            "-fx-border-color: #222222;"),
    AVAILABLE_ONLY_WHITE("EMPTY",
            "-fx-border-width: 0;" +
            "-fx-pref-width: 18.0;" +
            "-fx-pref-height: 18.0;" +
            "-fx-background-color: #c0c0c0;"),
    AVAILABLE_ONLY_BLACK("EMPTY",
            "-fx-border-width: 0;" +
            "-fx-pref-width: 18.0;" +
            "-fx-pref-height: 18.0;" +
            "-fx-background-color: #c0c0c0;"),
    AVAILABLE("EMPTY",
            "-fx-border-width: 0;" +
            "-fx-pref-width: 18.0;" +
            "-fx-pref-height: 18.0;" +
            "-fx-background-color: #c0c0c0;");

    private final String name;
    private final String style;
}
