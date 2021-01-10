package sample.game.board;

import sample.game.board.sequence.Sequence;
import sample.game.exception.BadPlaceException;
import sample.game.rules.Rule;
import sample.game.rules.Rules;
import sample.game.rules.interfaces.ActionAfterMove;
import sample.game.rules.interfaces.CheckGameEnd;
import sample.game.rules.interfaces.CheckPutOnPlace;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Board {
    final int size = 19;
    final int winSize = 5;
    List<List<PlaceState>> board = new ArrayList<>(size);
    List<Sequence> whiteSequences = new ArrayList<>(128);
    List<List<Sequence>> whiteSequenceMap = new ArrayList<>(size);
    List<Sequence> blackSequences = new ArrayList<>(128);
    List<List<Sequence>> blackSequenceMap = new ArrayList<>(size);
    Rules rules;

    Board(Rules rules) {
        this.rules = rules;
        IntStream.range(0, size).forEach(i -> board.add(new ArrayList<>(size)));
        IntStream.range(0, size).forEach(i -> whiteSequenceMap.add(new ArrayList<>(size)));
        IntStream.range(0, size).forEach(i -> blackSequenceMap.add(new ArrayList<>(size)));


        IntStream.range(0, size).forEach(i -> {for (int j = 0; j < size; j++) {board.get(i).add(PlaceState.EMPTY);}});
        IntStream.range(0, size).forEach(i -> {for (int j = 0; j < size; j++) {whiteSequenceMap.get(i).add(null);}});
        IntStream.range(0, size).forEach(i -> {for (int j = 0; j < size; j++) {blackSequenceMap.get(i).add(null);}});
    }

    private boolean canPutOnPlace(PlaceState placeState, int i, int j) {
        return rules.getRules(CheckPutOnPlace.class).stream()
                .allMatch(rule -> ((CheckPutOnPlace)rule).canPutOnPlace(this, i, j, placeState));
    }

    public void put(PlaceState placeState, int i, int j) {
        if (!canPutOnPlace(placeState, i, j)) {
            throw new BadPlaceException();
        }
        board.get(i).set(j, placeState);

        rules.getRules(ActionAfterMove.class)
                .forEach(rule -> ((ActionAfterMove)rule).actionAfterMove(this, i, j, placeState));
    }

    public List<List<PlaceState>> getBoard() {
        return board;
    }

    public boolean isGameEnd() {
        return rules.getRules(CheckGameEnd.class).stream().allMatch(rule -> ((CheckGameEnd)rule).isGameEnd(this));
    }

    public List<Sequence> getWhiteSequences() {
        return whiteSequences;
    }

    public List<Sequence> getBlackSequences() {
        return blackSequences;
    }

    public int getWinSize() {
        return winSize;
    }
}
