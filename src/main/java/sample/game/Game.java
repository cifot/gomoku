package sample.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.rulesData.Weight;
import sample.game.rulesData.interfaces.ActionAfterPutStoneRule;
import sample.game.rulesData.interfaces.PossiblePlaceRule;
import sample.game.rulesData.interfaces.WinRule;
import sample.game.rulesData.rules.Rule;
import sample.game.rulesData.rules.captures.Capture;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Game {
    private final Board board;
    private final GameMode gameMode;
    private Color currentColor;
    private final List<Rule> rules;
    private final Weight weight = new Weight(5, 3);
    private final boolean[] wasLastChance = new boolean[Color.values().length - 1];

    public void putStone(Color color, int column, int line) {
        board.putStone(color, column, line);
        actionAfterPutStone(color, column, line);
    }

    public Color changeColor() {
        currentColor = currentColor == Color.BLACK ? Color.WHITE : Color.BLACK;
        updateBoard();
        return currentColor;
    }

    public boolean isWin(Color color) {
        Color enemyColor = color == Color.BLACK ? Color.WHITE : Color.BLACK;
        return wasLastChance[enemyColor.ordinal()] || rules.stream()
                .filter(rule -> rule instanceof WinRule)
                .map(rule -> (WinRule) rule)
                .anyMatch(rule -> rule.isWin(board, color));
    }

    public boolean hasLastChance(Color color) {
        if (wasLastChance[color.ordinal()])
            return false;
        wasLastChance[color.ordinal()] = true;
        List<WinRule> winRules = rules.stream()
                .filter(rule -> rule instanceof WinRule)
                .map(rule -> (WinRule) rule)
                .collect(Collectors.toList());
        return winRules.stream().anyMatch(rule -> rule.hasChance(board, color, winRules));
    }
    public boolean wasLastChance(Color color) {
        return wasLastChance[color.ordinal()];
    }

    public long getCaptureScore(Color color) {
        var capture =rules.stream()
                .filter(rule -> rule instanceof Capture)
                .map(rule -> (Capture) rule)
                .collect(Collectors.toList());
        return capture.get(0).getScore(board, color);
    }

    public void updateBoard() {
        rules.stream()
                .filter(rule -> rule instanceof PossiblePlaceRule)
                .map(rule -> (PossiblePlaceRule) rule)
                .forEach(rule -> rule.updateBoard(board));
    }

    private void actionAfterPutStone(Color color, int column, int line) {
        rules.stream()
                .filter(rule -> rule instanceof ActionAfterPutStoneRule)
                .map(rule -> (ActionAfterPutStoneRule) rule)
                .forEach(rule -> rule.actionAfterPutStone(board, color, column, line));
    }

}
