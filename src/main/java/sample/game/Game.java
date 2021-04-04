package sample.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.rulesData.Rule;
import sample.game.rulesData.interfaces.ActionAfterPutStone;
import sample.game.rulesData.interfaces.PossiblePlaceRule;

import java.util.List;

@Getter
@AllArgsConstructor
public class Game {
    private final Board board;
    private final GameMode gameMode;
    private Color currentColor;
    private final List<Rule> rules;

    public void putStone(Color color, int column, int line) {
        board.putStone(color, column, line);
        actionAfterPutStone(color, column, line);
    }

    public Color changeColor() {
        currentColor = currentColor == Color.BLACK ? Color.WHITE : Color.BLACK;
        updateBoard();
        return currentColor;
    }

    private void updateBoard() {
        rules.stream()
                .filter(rule -> rule instanceof PossiblePlaceRule)
                .map(rule -> (PossiblePlaceRule) rule)
                .forEach(rule -> rule.updateBoard(board));
    }

    private void actionAfterPutStone(Color color, int column, int line) {
        rules.stream()
                .filter(rule -> rule instanceof ActionAfterPutStone)
                .map(rule -> (ActionAfterPutStone) rule)
                .forEach(rule -> rule.actionAfterPutStone(board, color, column, line));
    }

}
