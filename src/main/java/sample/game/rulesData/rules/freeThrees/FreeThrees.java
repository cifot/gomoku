package sample.game.rulesData.rules.freeThrees;

import sample.game.board.ArrayType;
import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.board.Place;
import sample.game.rulesData.rules.Rule;
import sample.game.rulesData.interfaces.PossiblePlaceRule;
import sample.game.rulesData.RulePattern;

public class FreeThrees extends Rule
    implements PossiblePlaceRule {

    private final Color[] colors = new Color[] {Color.WHITE, Color.BLACK};
    private final int maxFreeRaw = 2;
    private final RulePattern[][] patterns;

    public FreeThrees(RulePattern[][] patterns) {
        this.patterns = patterns;
    }

    @Override
    public void updateBoard(Board board) {
        int size = board.getSize();
        Place[][] stoneBoard = board.getStoneBoard();
        for (int column = 0; column < size; column++) {
            for (int line = 0; line < size; line++) {
                if (stoneBoard[column][line].getColor() == Color.EMPTY) {
                    for (Color color : colors) {
                        int countFreeRaws = RulePattern.countMatchBoardPattern(board, column, line, patterns[color.ordinal()]);
                        stoneBoard[column][line].getCanPlace()[color.ordinal()] = countFreeRaws < maxFreeRaw;
                    }
                }
            }
        }

    }
}
