package sample.game.rulesData.rules.captures;

import lombok.AllArgsConstructor;
import sample.game.board.ArrayType;
import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.board.Place;
import sample.game.rulesData.rules.Rule;
import sample.game.rulesData.RulePattern;
import sample.game.rulesData.interfaces.ActionAfterPutStoneRule;
import sample.game.rulesData.interfaces.WinRule;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class Capture extends Rule
    implements ActionAfterPutStoneRule, WinRule {

    private final int[] countCaptureStones = new int[Color.values().length - 1];
    private final int winCount;
    private final int sizeCapture;
    private final boolean hasEndGameCapture;
    private final long multiplier;
    private final RulePattern[][] patterns;

    @Override
    public void actionAfterPutStone(Board board, Color color, int column, int line) {

        Place[][][] stoneBoards = board.getStoneBoards();
        int size = board.getSize();

        for (ArrayType arrayType : ArrayType.values()) {
            int secondIndex = arrayType.getSecondIndex(column, line, size);
            int firstIndex = arrayType.getFirstIndex(column, line, size);
            Place[][] currentBoard = stoneBoards[arrayType.ordinal()];
            Place[] boardLine = currentBoard[firstIndex];
            for (RulePattern pattern : patterns[color.ordinal()]) {
                if (pattern.checkMatchPattern(boardLine, secondIndex)) {
                    int startIndex;
                    countCaptureStones[color.ordinal()] += sizeCapture;
                    if (pattern.getIndex() == 0) {
                        startIndex = secondIndex + 1;
                    } else {
                        startIndex = secondIndex - sizeCapture;
                    }
                    for (int i = 0; i < sizeCapture; i++) {
                        boardLine[startIndex + i].setColor(Color.EMPTY);
                        Arrays.fill(boardLine[startIndex + i].getCanPlace(), true);
                    }
                }
            }
        }
    }

    @Override
    public boolean isWin(Board board, Color color) {
        return getScore(board, color) >= winCount;
    }

    @Override
    public long getScore(Board board, Color color) {
        return  countCaptureStones[color.ordinal()];
    }

    @Override
    public long getMiniMaxScore(Board board, Color color) {
        return countCaptureStones[color.ordinal()] * multiplier;
    }

    @Override
    public boolean hasChance(Board board, Color color, List<WinRule> winRules) {
        if (!this.hasEndGameCapture)
            return false;
        Place[][] stoneBoard = board.getStoneBoard();
        int size = board.getSize();
        for (int column = 0; column < size; column++) {
            for (int line = 0; line < size; line++) {
                if (stoneBoard[column][line].getCanPlace()[color.ordinal()]) {
                    Board newBoard  = board.clone();
                    newBoard.putStone(color, column, line);
                    if (RulePattern.checkMatchBoardPattern(newBoard, column, line, patterns[color.ordinal()])) {
                        return true;
                    }
                    if (winRules.stream().anyMatch(winRule -> winRule.isWin(newBoard, color))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
