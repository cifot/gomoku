package sample.game.board;

public class Point {
    int line, column;

    Point(int line, int column) {
        this.column = column;
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public Point clone() {
        return new Point(line, column);
    }
}
