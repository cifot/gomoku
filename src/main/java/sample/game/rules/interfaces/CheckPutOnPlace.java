package sample.game.rules.interfaces;

import sample.game.board.Board;
import sample.game.board.PlaceState;

public interface CheckPutOnPlace {
    boolean canPutOnPlace(Board board, int i, int j, PlaceState color);

}
