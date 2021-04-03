package sample.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sample.game.board.Board;
import sample.game.board.PlaceState;


@AllArgsConstructor
@Getter
public class Game {
    private final Board board;
    private final GameMode gameMode;
    private PlaceState currentColor;

    public PlaceState changeColor() {
        currentColor = currentColor == PlaceState.BLACK ? PlaceState.WHITE : PlaceState.BLACK;
        return currentColor;
    }
}
