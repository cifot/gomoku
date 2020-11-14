package sample.game.rules;

import sample.game.board.Board;
import sample.game.board.PlaceState;
import sample.game.rules.interfaces.CheckPutOnPlace;

import java.util.List;

public class NoDoubleThrees extends Rule
    implements CheckPutOnPlace {

    @Override
    public boolean canPutOnPlace(Board board, int i, int j, PlaceState color) {
        return false;
    }
}
