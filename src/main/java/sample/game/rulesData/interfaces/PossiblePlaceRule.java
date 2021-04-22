package sample.game.rulesData.interfaces;

import sample.game.board.Board;
import sample.game.rulesData.rules.Rule;

import java.util.List;
import java.util.stream.Collectors;

public interface PossiblePlaceRule  {
    void updateBoard(Board board);
    static List<PossiblePlaceRule> getPossiblePlaceRules(List<Rule> rules) {
        return  rules.stream()
                .filter(rule -> rule instanceof PossiblePlaceRule)
                .map(rule -> (PossiblePlaceRule) rule)
                .collect(Collectors.toList());
    }
}
