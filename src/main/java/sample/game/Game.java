package sample.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sample.game.artificialIntelligence.minimax.MiniMaxAlgorithm;
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
public class Game {
    private final Board board;
    private final GameMode gameMode;
    private Color currentColor;
    private final List<Rule> rules;
    private final Weight weight = new Weight(5, 3);
    private final boolean[] wasLastChance = new boolean[Color.values().length - 1];
    private final List<WinRule> winRules;
    private final List<ActionAfterPutStoneRule> actionAfterPutStoneRules;
    private final List<PossiblePlaceRule> possiblePlaceRules;
    private final MiniMaxAlgorithm miniMaxAlgorithm;

    public Game(Board board, GameMode gameMode, Color color, List<Rule> rules) {
        this.board = board;
        this.gameMode = gameMode;
        this.currentColor = color;
        this.rules = rules;
        this.winRules = WinRule.getWinRules(rules);
        this.actionAfterPutStoneRules = ActionAfterPutStoneRule.getActionAfterPutStoneRules(rules);
        this.possiblePlaceRules = PossiblePlaceRule.getPossiblePlaceRules(rules);
        this.miniMaxAlgorithm = new MiniMaxAlgorithm(5, board.getSize(), winRules);
    }
    public void putStone(Color color, int column, int line) {
        board.putStone(color, column, line);
        actionAfterPutStone(color, column, line);
    }

    public Color changeColor() {
        currentColor = currentColor.getEnemyColor();
        updateBoard();
        return currentColor;
    }

    public boolean isWin(Color color) {
        Color enemyColor = color.getEnemyColor();
        return wasLastChance[enemyColor.ordinal()] || winRules.stream()
                .anyMatch(rule -> rule.isWin(board, color));
    }

    public boolean hasLastChance(Color color) {
        if (wasLastChance[color.ordinal()])
            return false;
        wasLastChance[color.ordinal()] = true;
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
        possiblePlaceRules.forEach(rule -> rule.updateBoard(board));
    }

    public Color putComputerStone() {
        miniMaxAlgorithm.putStone(board, currentColor, this);
        return changeColor();
    }

    private void actionAfterPutStone(Color color, int column, int line) {
        actionAfterPutStoneRules.forEach(rule -> rule.actionAfterPutStone(board, color, column, line));
    }

}
