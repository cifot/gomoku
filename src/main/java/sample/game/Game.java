package sample.game;

import lombok.Getter;
import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.rulesData.Rule;
import sample.game.rulesData.interfaces.PossiblePlaceRule;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class Game {
    private final Board board;
    private final GameMode gameMode;
    private Color currentColor;
    private final List<Rule> rules;
    private final List<PossiblePlaceRule> possiblePlaceRules;

    public Game(Board board, GameMode gameMode, Color currentColor, List<Rule> rules) {
        this.board = board;
        this.gameMode = gameMode;
        this.currentColor = currentColor;
        this.rules = rules;
        this.possiblePlaceRules = rules.stream()
                .filter(rule -> rule instanceof PossiblePlaceRule)
                .map(rule -> (PossiblePlaceRule) rule)
                .collect(Collectors.toList());
    }

    public Color changeColor() {
        currentColor = currentColor == Color.BLACK ? Color.WHITE : Color.BLACK;
        return currentColor;
    }

    public void updateBoard() {
        possiblePlaceRules.forEach(rule -> rule.updateBoard(board));
    }

}
