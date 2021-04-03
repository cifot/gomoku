package sample.game.board;

import lombok.Getter;
import sample.game.exception.BadPlaceException;

import java.util.Arrays;

/*
Отсчет координат происходит с верхнего-левого угла
Отсчет левых диагоналей происходит с верхнего-правого угла и идет вниз
Отсчет правых диагоналей происходит с верхнего-левого угла и идет вниз
 */
@Getter
public class Board {
    Weight weight = new Weight(5, 3);
    final int size;
    final int winLength = 5;
    PlaceState[][][] stoneBoards = new PlaceState[ArrayType.values().length][][];

    public Board(int size) {
        this.size = size;

        for (var type: ArrayType.values()) {
            stoneBoards[type.getValue()] = new PlaceState[type.getFirstSize(size)][];
            for (int firstIndex = 0; firstIndex < stoneBoards[type.getValue()].length; firstIndex++) {
                stoneBoards[type.getValue()][firstIndex] = new PlaceState[type.getSecondSize(firstIndex, size)];
                Arrays.fill(stoneBoards[type.getValue()][firstIndex], PlaceState.AVAILABLE);
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void putStone(PlaceState color, int column, int line) {
        if (stoneBoards[ArrayType.Vertical.getValue()][column][line] == PlaceState.AVAILABLE) {
            for (var type : ArrayType.values()) {
                stoneBoards[type.getValue()][type.getFirstIndex(column, line, size)][type.getSecondIndex(column, line, size)] = color;
            }
        } else {
            throw new BadPlaceException();
        }
    }

    public long getScore(PlaceState color) {
        long score = 0L;
        for (PlaceState[][] stoneBoard: stoneBoards) {
            for (PlaceState[] line : stoneBoard) {
                for (int j = 0; j < line.length; j++) {
                    while (j < line.length && line[j] != color) {
                        j++;
                    }
                    int startPos = j;
                    while (j < line.length && line[j] == color) {
                        j++;
                    }
                    score += weight.getWeight(j - startPos);
                }
            }
        }
        return score;
    }
}
