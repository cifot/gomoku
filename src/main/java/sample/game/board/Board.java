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
        stoneBoards[ArrayType.Horizontals.getValue()] = new PlaceState[size][size];
        stoneBoards[ArrayType.Vertical.getValue()] = new PlaceState[size][size];
        stoneBoards[ArrayType.RightDiagonals.getValue()] = new PlaceState[size * 2 - 1][];
        stoneBoards[ArrayType.LeftDiagonals.getValue()] = new PlaceState[size * 2 - 1][];

        int tmpValue = size - 1;
        for (int i = 0; i < stoneBoards[ArrayType.LeftDiagonals.getValue()].length; i++) {
            final int diagonalSize = Math.abs(tmpValue - Math.abs(tmpValue - i)) + 1;
            stoneBoards[ArrayType.LeftDiagonals.getValue()][i] = new PlaceState[diagonalSize];
            stoneBoards[ArrayType.RightDiagonals.getValue()][i] = new PlaceState[diagonalSize];
        }
        for (PlaceState[][] stoneBoard : stoneBoards) {
            for (PlaceState[] line : stoneBoard) {
                Arrays.fill(line, null);
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void putStone(PlaceState color, int column, int line) {
        if (stoneBoards[ArrayType.Vertical.getValue()][column][line] == null) {
            stoneBoards[ArrayType.Vertical.getValue()][column][line] = color;
            stoneBoards[ArrayType.Horizontals.getValue()][line][column] = color;
            stoneBoards[ArrayType.LeftDiagonals.getValue()][size - 1 - column + line][Integer.min(line, column)] = color;
            stoneBoards[ArrayType.RightDiagonals.getValue()][column + line][Integer.min(line, size - column - 1)] = color;
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
