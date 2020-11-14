package sample.game.rules;

import sample.game.rules.interfaces.ActionAfterMove;
import sample.game.rules.interfaces.CheckGameEnd;
import sample.game.rules.interfaces.CheckPutOnPlace;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Rules {
    final private Map<Class<?>, List<Rule>> rulesByInterface = new HashMap<>();

    Rules(Rule[] rules) {
        final List<Rule> allRules = Arrays.asList(rules);

        rulesByInterface.put(Rule.class, allRules);
        List<Class<?>> interfaces = Arrays.asList(ActionAfterMove.class,
                CheckGameEnd.class,
                CheckPutOnPlace.class);
        interfaces.forEach(typeRule ->
                rulesByInterface.put(typeRule, allRules.stream()
                        .filter(rule -> typeRule.isAssignableFrom(rule.getClass()))
                        .collect(Collectors.toList())));
    }

    public List<Rule> getRules(Class<?> typeRule) {
        return rulesByInterface.get(typeRule);
    }
}
