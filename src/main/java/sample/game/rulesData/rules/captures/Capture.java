package sample.game.rulesData.rules.captures;

import sample.game.board.ArrayType;
import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.board.Place;
import sample.game.rulesData.Rule;
import sample.game.rulesData.RulePattern;
import sample.game.rulesData.interfaces.ActionAfterPutStone;

import java.util.Arrays;

public class Capture extends Rule
    implements ActionAfterPutStone {

    private final Color[] colors = new Color[] {Color.WHITE, Color.BLACK};
    private final Color[] invertColors = new Color[] {Color.BLACK, Color.WHITE};

    private final int countPatterns = 2;
    private final RulePattern[][] patterns = new RulePattern[colors.length][countPatterns];

    public Capture() {
        for (int i = 0; i < patterns.length; i++) {
            patterns[i][0] = new RulePattern(new Color[] {
                    colors[i],
                    invertColors[i],
                    invertColors[i],
                    colors[i]},
                    0);
            patterns[i][1] = new RulePattern(new Color[] {
                    colors[i],
                    invertColors[i],
                    invertColors[i],
                    colors[i]},
                    3);
        }
    }

    @Override
    public void actionAfterPutStone(Board board, Color color, int column, int line) {

        Place[][][] stoneBoards = board.getStoneBoards();
        int size = board.getSize();

        System.out.println(color);
        for (ArrayType arrayType : ArrayType.values()) {
            int secondIndex = arrayType.getSecondIndex(column, line, size);
            int firstIndex = arrayType.getFirstIndex(column, line, size);
            Place[][] currentBoard = stoneBoards[arrayType.ordinal()];
            Place[] boardLine = currentBoard[firstIndex];
            for (RulePattern pattern : patterns[color.ordinal()]) {
                if (pattern.checkMatchPattern(boardLine, secondIndex)) {
                    int startIndex;
                    if (pattern.getIndex() == 0) {
                        startIndex = 1;
                    } else {
                        startIndex = -2;
                    }
                    boardLine[secondIndex + startIndex].setColor(Color.EMPTY);
                    Arrays.fill(boardLine[secondIndex + startIndex].getCanPlace(), true);
                    boardLine[secondIndex + startIndex + 1].setColor(Color.EMPTY);
                    Arrays.fill(boardLine[secondIndex + startIndex + 1].getCanPlace(), true);
                }
            }
        }
    }
}
