package sample.game.rulesData.rules.freeThrees;

import sample.game.board.ArrayType;
import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.board.Place;
import sample.game.rulesData.Rule;
import sample.game.rulesData.interfaces.PossiblePlaceRule;
import sample.game.rulesData.RulePattern;

public class FreeThrees extends Rule
    implements PossiblePlaceRule {

    private final Color[] colors = new Color[] {Color.WHITE, Color.BLACK};
    private final int countPatterns = 9;
    private final int maxFreeRaw = 2;
    private final RulePattern[][] patterns = new RulePattern[colors.length][countPatterns];

    public FreeThrees() {
        for (var color : colors) {
            patterns[color.ordinal()][0] = new RulePattern(new Color[] {
                    Color.EMPTY,
                    Color.EMPTY,
                    Color.EMPTY,
                    color,
                    color,
                    Color.EMPTY},
                    1);
            patterns[color.ordinal()][1] = new RulePattern(new Color[] {
                    Color.EMPTY,
                    color,
                    color,
                    Color.EMPTY,
                    Color.EMPTY,
                    Color.EMPTY},
                    4);
            patterns[color.ordinal()][2] = new RulePattern(new Color[] {
                    Color.EMPTY,
                    color,
                    Color.EMPTY,
                    color,
                    Color.EMPTY,
                    Color.EMPTY},
                    4);
            patterns[color.ordinal()][3] = new RulePattern(new Color[] {
                    Color.EMPTY,
                    Color.EMPTY,
                    color,
                    Color.EMPTY,
                    color,
                    Color.EMPTY},
                    1);
            patterns[color.ordinal()][4] = new RulePattern(new Color[] {
                    Color.EMPTY,
                    color,
                    Color.EMPTY,
                    Color.EMPTY,
                    color,
                    Color.EMPTY},
                    2);
            patterns[color.ordinal()][5] = new RulePattern(new Color[] {
                    Color.EMPTY,
                    color,
                    Color.EMPTY,
                    Color.EMPTY,
                    color,
                    Color.EMPTY},
                    3);
            patterns[color.ordinal()][6] = new RulePattern(new Color[] {
                    Color.EMPTY,
                    Color.EMPTY,
                    color,
                    color,
                    Color.EMPTY},
                    1);
            patterns[color.ordinal()][7] = new RulePattern(new Color[] {
                    Color.EMPTY,
                    color,
                    color,
                    Color.EMPTY,
                    Color.EMPTY},
                    3);
            patterns[color.ordinal()][8] = new RulePattern(new Color[] {
                    Color.EMPTY,
                    color,
                    Color.EMPTY,
                    color,
                    Color.EMPTY},
                    2);
        }
    }

    @Override
    public void updateBoard(Board board) {
        int size = board.getSize();
        Place[][][] stoneBoards = board.getStoneBoards();
        Place[][] stoneBoard = board.getStoneBoard();

        for (int column = 0; column < size; column++) {
            for (int line = 0; line < size; line++) {
                if (stoneBoard[column][line].getColor() == Color.EMPTY) {
                    for (Color color : colors) {
                        int countFreeRaws = 0;
                        for (ArrayType arrayType : ArrayType.values()) {
                            int secondIndex = arrayType.getSecondIndex(column, line, size);
                            Place[] boardLine = stoneBoards[arrayType.ordinal()][arrayType.getFirstIndex(column, line, size)];
                            for (RulePattern pattern : patterns[color.ordinal()]) {
                                if (pattern.checkMatchPattern(boardLine, secondIndex)) {
                                    countFreeRaws++;
                                }
                            }
                        }
                        stoneBoard[column][line].getCanPlace()[color.ordinal()] = countFreeRaws < maxFreeRaw;
                    }
                }
            }
        }

    }
}
