package sample.game.rulesData.interfaces;

import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.rulesData.rules.Rule;

import java.util.List;
import java.util.stream.Collectors;

public interface ActionAfterPutStoneRule {
    void actionAfterPutStone(Board board, Color color, int column, int line);
    static List<ActionAfterPutStoneRule> getActionAfterPutStoneRules(List<Rule> rules) {
        return  rules.stream()
                .filter(rule -> rule instanceof ActionAfterPutStoneRule)
                .map(rule -> (ActionAfterPutStoneRule) rule)
                .collect(Collectors.toList());
    }
}
