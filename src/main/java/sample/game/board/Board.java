package sample.game.board;

import lombok.Getter;

/**
 * <p>
 * Отсчет координат происходит с верхнего-левого угла
 * </p>
 *
 * <p>
 * Отсчет левых диагоналей происходит с верхнего-правого угла и идет вниз
 * Отсчет правых диагоналей происходит с верхнего-левого угла и идет вниз
 * </p>
 *
 * <p>
 * Рассмотрим на примере доски 3 на 3:
 * </p>
 *
 * |  210 <br>
 * |  \\\ <br>
 * | 3\\\ <br>
 * | 4\\\ <br>
 *
 * <p>
 * левая диагональ с индексом 0 имеет длину 1 и представляет из себя правую вверхнюю клетку с [2, 0]
 * лувая диагональ с индексом 1 имеет длину 2 и представляет из себя 2 клетки с координатами  [1,0] и [2, 1] и т.д.
 * </p>
 *
 * |  012 <br>
 * |  /// <br>
 * |  ///3 <br>
 * |  ///4 <br>
 *
 * <p>
 * правая диагональ с индексом 0 имеет длину 1 и представляет из себя левую вверхнюю клетку с [0, 0]
 * </p>
 * <p>
 * правая диагональ с индексом 1 имеет длину 2 и представляет из себя 2 клетки с координатами  [1,0] и [0, 1] и т.д.
 * </p>
 * <p>
 * Нумерация строк и колонок происходит тривиально.
 * </p>
 */
@Getter
public class Board {
    Weight weight = new Weight(5, 3);
    final int size;
    final int winLength = 5;
    Place[][][] stoneBoards = new Place[ArrayType.values().length][][];
    Place[][] stoneBoard;


    public Board(int size) {

        this.size = size;
        stoneBoard = new Place[size][size];
        for (Place[] line : stoneBoard) {
            for (int i = 0; i < line.length; i++) {
                line[i] = new Place();
            }
        }
        for (var type: ArrayType.values()) {
            stoneBoards[type.ordinal()] = new Place[type.getFirstSize(size)][];
            Place[][] currentBoard = stoneBoards[type.ordinal()];
            for (int firstIndex = 0; firstIndex < currentBoard.length; firstIndex++) {
                currentBoard[firstIndex] = new Place[type.getSecondSize(firstIndex, size)];
            }
            for (int column = 0; column < size; column++) {
                for (int line = 0; line < size; line++) {
                    int firstIndex = type.getFirstIndex(column, line, size);
                    int secondIndex = type.getSecondIndex(column, line, size);
                    currentBoard[firstIndex][secondIndex] = stoneBoard[column][line];
                }
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void putStone(Color color, int column, int line) {
        stoneBoard[column][line].putStone(color);
    }

    public long getScore(Color color) {
        long score = 0L;
        for (Place[][] stoneBoard: stoneBoards) {
            for (Place[] line : stoneBoard) {
                for (int j = 0; j < line.length; j++) {
                    while (j < line.length && line[j].getColor() == color) {
                        j++;
                    }
                    int startPos = j;
                    while (j < line.length && line[j].getColor() == color) {
                        j++;
                    }
                    score += weight.getWeight(j - startPos);
                }
            }
        }
        System.out.println(score);
        return score;
    }
}
