package sample.game.rules;

import sample.game.board.Board;
import sample.game.board.PlaceState;
import sample.game.rules.interfaces.ActionAfterMove;
import sample.game.rules.interfaces.CheckGameEnd;
import sample.game.rules.interfaces.CheckPutOnPlace;

public class MainRule extends Rule
        implements
        CheckPutOnPlace,
        CheckGameEnd {
    @Override
    public boolean canPutOnPlace(Board board, int i, int j, PlaceState color) {
        return board.getBoard().get(i).get(j).equals(PlaceState.EMPTY);
    }

    @Override
    public boolean isGameEnd(Board board) {
        return board.getWhiteSequences().stream().anyMatch(sequence -> sequence.getSize() >= board.getWinSize())
                || board.getBlackSequences().stream().anyMatch(sequence -> sequence.getSize() >= board.getWinSize());
    }
}
