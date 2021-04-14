package sample.game.rulesData.interfaces;

import sample.game.board.Board;
import sample.game.board.Color;

import java.util.List;

public interface WinRule {
    boolean isWin(Board board, Color color);
    long getScore(Board board, Color color);
    boolean hasChance(Board board, Color color, List<WinRule> winRules);
}
