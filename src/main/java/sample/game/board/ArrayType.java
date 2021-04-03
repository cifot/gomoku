package sample.game.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sample.game.exception.GomokuException;

@AllArgsConstructor
@Getter
public enum ArrayType {
    LeftDiagonals(0), RightDiagonals(1), Horizontals(2), Vertical(3);

    private final int value;

    public int getFirstIndex(int column, int line, int size) {
        switch (this) {
            case LeftDiagonals -> {return size - 1 - column + line;}
            case RightDiagonals -> {return column + line;}
            case Horizontals -> {return line;}
            case Vertical -> {return column;}
        }
        throw new GomokuException("WTF");
    }

    public int getSecondIndex(int column, int line, int size) {
        switch (this) {
            case LeftDiagonals -> {return Integer.min(line, column);}
            case RightDiagonals -> {return Integer.min(line, size - column - 1);}
            case Horizontals -> {return column;}
            case Vertical -> {return line;}
        }
        throw new GomokuException("WTF");
    }

    public int getFirstSize(int size) {
        switch (this) {
            case LeftDiagonals, RightDiagonals -> {return size * 2 - 1;}
            case Horizontals, Vertical -> {return size;}
        }
        throw new GomokuException("WTF");
    }

    public int getSecondSize(int firstIndex, int size) {
        switch (this) {
            case LeftDiagonals, RightDiagonals -> {return Math.abs(size - 1 - Math.abs(size - 1 - firstIndex)) + 1;}
            case Horizontals, Vertical -> {return size;}
        }
        throw new GomokuException("WTF");
    }
}
