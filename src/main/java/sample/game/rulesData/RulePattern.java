package sample.game.rulesData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sample.game.board.Color;
import sample.game.board.Place;

@AllArgsConstructor
@Getter
public class RulePattern {
    private final Color[] pattern;
    private final int index;

    public boolean checkMatchPattern(Place[] row, int rowIndex) {
        int newStartRawIndex = rowIndex - index;
        if (newStartRawIndex >= 0 && newStartRawIndex + pattern.length <= row.length) {
            boolean match = true;
            for (int i = 0; i < pattern.length && match; i++) {
                match = match && (row[newStartRawIndex + i].getColor() == pattern[i]);
            }
            return match;
        } else {
            return false;
        }
    }
}
