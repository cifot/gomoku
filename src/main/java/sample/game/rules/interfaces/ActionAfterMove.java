package sample.game.rules.interfaces;

import sample.game.board.Board;
import sample.game.board.PlaceState;

public interface ActionAfterMove {
    void actionAfterMove(Board board, int i, int j, PlaceState color);
}
