package sample.game.rules;

import sample.game.board.Board;
import sample.game.board.PlaceState;
import sample.game.rules.interfaces.ActionAfterMove;
import sample.game.rules.interfaces.CheckGameEnd;

import java.util.List;

public class Capture implements
        ActionAfterMove,
        CheckGameEnd {

    private int whiteCapture = 0;
    private int blackCapture = 0;

    @Override
    public boolean isGameEnd(Board board) {
        return false;
    }

    @Override
    public void actionAfterMove(Board board, int i, int j, PlaceState color) {

    }
}
