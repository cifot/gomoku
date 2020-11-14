package sample.game.board.sequence;

import sample.game.board.Point;

public class Sequence {
    Point first;
    Point second;
    int size;
    SequencePosition sequencePosition;


    Sequence(Point point) {
        first = point.clone();
        second = point.clone();
        size = 1;
        sequencePosition = SequencePosition.UNDEFINED;
    }

    public int getSize() {
        return size;
    }

    public void addPointIfPossible(Point point) {

    }
}
