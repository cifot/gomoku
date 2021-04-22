package sample.game.rulesData.interfaces;

import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.rulesData.rules.Rule;

import java.util.List;
import java.util.stream.Collectors;

public interface WinRule {
    boolean isWin(Board board, Color color);
    long getScore(Board board, Color color);
    long getMiniMaxScore(Board board, Color color);
    boolean hasChance(Board board, Color color, List<WinRule> winRules);
    static List<WinRule> getWinRules(List<Rule> rules) {
        return  rules.stream()
                .filter(rule -> rule instanceof WinRule)
                .map(rule -> (WinRule) rule)
                .collect(Collectors.toList());
    }
}
