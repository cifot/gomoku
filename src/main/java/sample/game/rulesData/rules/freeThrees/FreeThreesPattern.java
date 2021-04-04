package sample.game.rulesData.rules.freeThrees;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sample.game.board.Color;

@AllArgsConstructor
@Getter
public class FreeThreesPattern {
    private final Color[] pattern;
    private final int index;
}
