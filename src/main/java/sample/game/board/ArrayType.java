package sample.game.board;

public enum ArrayType {
    LeftDiagonals(0), RightDiagonals(1), Horizontals(2), Vertical(3);

    private final int value;

    ArrayType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
