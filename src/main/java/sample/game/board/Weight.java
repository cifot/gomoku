package sample.game.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Weight {
    private final long winLength;
    private final long multiplier;

    public long getWeight(int length) {
        if (length >= winLength)
            return Integer.MAX_VALUE;
        else
            return (winLength - length) * multiplier;
    }
}
