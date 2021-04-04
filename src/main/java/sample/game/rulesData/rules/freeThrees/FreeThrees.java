package sample.game.rulesData.rules.freeThrees;

import sample.game.board.ArrayType;
import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.board.Place;
import sample.game.rulesData.Rule;
import sample.game.rulesData.interfaces.PossiblePlaceRule;

public class FreeThrees extends Rule
    implements PossiblePlaceRule {

    private final Color[] colors = new Color[] {Color.WHITE, Color.BLACK};
    private final int countPatterns = 9;
    private final int maxFreeRaw = 2;
    private final FreeThreesPattern[][] patterns = new FreeThreesPattern[colors.length][countPatterns];

    public FreeThrees() {
        for (var color : colors) {
            patterns[color.ordinal()][0] = new FreeThreesPattern(new Color[] {
                    Color.EMPTY,
                    Color.EMPTY,
                    Color.EMPTY,
                    color,
                    color,
                    Color.EMPTY},
                    1);
            patterns[color.ordinal()][1] = new FreeThreesPattern(new Color[] {
                    Color.EMPTY,
                    color,
                    color,
                    Color.EMPTY,
                    Color.EMPTY,
                    Color.EMPTY},
                    4);
            patterns[color.ordinal()][2] = new FreeThreesPattern(new Color[] {
                    Color.EMPTY,
                    color,
                    Color.EMPTY,
                    color,
                    Color.EMPTY,
                    Color.EMPTY},
                    4);
            patterns[color.ordinal()][3] = new FreeThreesPattern(new Color[] {
                    Color.EMPTY,
                    Color.EMPTY,
                    color,
                    Color.EMPTY,
                    color,
                    Color.EMPTY},
                    1);
            patterns[color.ordinal()][4] = new FreeThreesPattern(new Color[] {
                    Color.EMPTY,
                    color,
                    Color.EMPTY,
                    Color.EMPTY,
                    color,
                    Color.EMPTY},
                    2);
            patterns[color.ordinal()][5] = new FreeThreesPattern(new Color[] {
                    Color.EMPTY,
                    color,
                    Color.EMPTY,
                    Color.EMPTY,
                    color,
                    Color.EMPTY},
                    3);
            patterns[color.ordinal()][6] = new FreeThreesPattern(new Color[] {
                    Color.EMPTY,
                    Color.EMPTY,
                    color,
                    color,
                    Color.EMPTY},
                    1);
            patterns[color.ordinal()][7] = new FreeThreesPattern(new Color[] {
                    Color.EMPTY,
                    color,
                    color,
                    Color.EMPTY,
                    Color.EMPTY},
                    3);
            patterns[color.ordinal()][8] = new FreeThreesPattern(new Color[] {
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
                            for (FreeThreesPattern pattern : patterns[color.ordinal()]) {
                                int newSecondIndex = secondIndex - pattern.getIndex();
                                Color[] patternLine = pattern.getPattern();
                                if (newSecondIndex >= 0 && newSecondIndex + patternLine.length <= boardLine.length) {
                                    boolean match = true;
                                    for (int i = 0; i < patternLine.length && match; i++) {
                                        match = match && (boardLine[newSecondIndex + i].getColor() == patternLine[i]);
                                    }
                                    if (match) {
                                        countFreeRaws++;
                                    }
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
