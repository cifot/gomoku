package sample.game.rulesData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import sample.game.board.ArrayType;
import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.board.Place;

import java.util.Arrays;

@Getter
public class RulePattern {
    private final Color[] pattern;
    private final int index;

    public RulePattern(@JsonProperty("pattern") Color[] pattern, @JsonProperty("index") int index) {
        this.pattern = pattern;
        this.index = index;
    }

    public boolean checkMatchPattern(Place[] row, int rowIndex) {
        int newStartRawIndex = rowIndex - index;
        if (newStartRawIndex >= 0 && newStartRawIndex + pattern.length <= row.length) {
            boolean match = true;
            for (int i = 0; i < pattern.length && match; i++) {
                match = match && (row[newStartRawIndex + i].getColor() == pattern[i]);
            }
            return match;
        } else {
            return false;
        }
    }
    public static boolean checkMatchBoardPattern(Board board, int column, int line, RulePattern[] patterns) {
        int size = board.getSize();
        Place[][][] stoneBoards = board.getStoneBoards();
        for (ArrayType arrayType : ArrayType.values()) {
            int secondIndex = arrayType.getSecondIndex(column, line, size);
            int firstIndex = arrayType.getFirstIndex(column, line, size);
            Place[][] currentBoard = stoneBoards[arrayType.ordinal()];
            Place[] boardLine = currentBoard[firstIndex];
            for (RulePattern pattern : patterns) {
                if (pattern.checkMatchPattern(boardLine, secondIndex)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int countMatchBoardPattern(Board board, int column, int line, RulePattern[] patterns) {
        int size = board.getSize();
        int count = 0;
        Place[][][] stoneBoards = board.getStoneBoards();
        for (ArrayType arrayType : ArrayType.values()) {
            int secondIndex = arrayType.getSecondIndex(column, line, size);
            int firstIndex = arrayType.getFirstIndex(column, line, size);
            Place[][] currentBoard = stoneBoards[arrayType.ordinal()];
            Place[] boardLine = currentBoard[firstIndex];
            for (RulePattern pattern : patterns) {
                if (pattern.checkMatchPattern(boardLine, secondIndex)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "RulePattern{" +
                "pattern=" + Arrays.toString(pattern) +
                ", index=" + index +
                '}';
    }
}
