package sample.game.rulesData.interfaces;

import sample.game.board.Board;
import sample.game.board.Color;

public interface ActionAfterPutStone {
    void actionAfterPutStone(Board board, Color color, int column, int line);
}
