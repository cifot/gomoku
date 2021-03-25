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
    Weight weight = new Weight(5, 4);
    final int size = 19;
    final int winLength = 5;
    Color[][][] stoneBoards = new Color[ArrayType.values().length][][];

    public Board() {
        stoneBoards[ArrayType.Horizontals.getValue()] = new Color[size][size];
        stoneBoards[ArrayType.Vertical.getValue()] = new Color[size][size];
        stoneBoards[ArrayType.RightDiagonals.getValue()] = new Color[size * 2 - 1][];
        stoneBoards[ArrayType.LeftDiagonals.getValue()] = new Color[size * 2 - 1][];

        int tmpValue = size - 1;
        for (int i = 0; i < stoneBoards[ArrayType.LeftDiagonals.getValue()].length; i++) {
            final int diagonalSize = Math.abs(tmpValue - Math.abs(tmpValue - i)) + 1;
            stoneBoards[ArrayType.LeftDiagonals.getValue()][i] = new Color[diagonalSize];
            stoneBoards[ArrayType.RightDiagonals.getValue()][i] = new Color[diagonalSize];
        }
        for (Color[][] stoneBoard : stoneBoards) {
            for (Color[] line : stoneBoard) {
                Arrays.fill(line, null);
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void putStone(Color color, int column, int line) {
        System.out.println(color);
        System.out.println(line);
        System.out.println(column);
        System.out.printf("right size:%d pos:%d%n", stoneBoards[ArrayType.RightDiagonals.getValue()][column + line].length, Integer.min(line, size - column - 1));
        System.out.printf("left size:%d pos:%d%n", stoneBoards[ArrayType.LeftDiagonals.getValue()][size - 1 - column + line].length, Integer.min(line, column));
        if (stoneBoards[ArrayType.Vertical.getValue()][column][line] == null) {
            stoneBoards[ArrayType.Vertical.getValue()][column][line] = color;
            stoneBoards[ArrayType.Horizontals.getValue()][line][column] = color;
            stoneBoards[ArrayType.LeftDiagonals.getValue()][size - 1 - column + line][Integer.min(line, column)] = color;
            stoneBoards[ArrayType.RightDiagonals.getValue()][column + line][Integer.min(line, size - column - 1)] = color;
        } else {
            throw new BadPlaceException();
        }
    }

    public long getScore(Color color) {
        long score = 0L;
        for (Color[][] stoneBoard: stoneBoards) {
            for (Color[] line : stoneBoard) {
                int size = line.length;
                for (int j = 0; j < line.length; j++) {
                    while (line[j] != color) {
                        j++;
                    }
                    int startPos = j;
                    while (j < size && line[j] == color) {
                        j++;
                    }
                    score += weight.getWeight(j - startPos);
                }
            }
        }
        return score;
    }
}
