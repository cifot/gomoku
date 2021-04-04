package sample.game.board;

import lombok.Getter;
import lombok.Setter;
import sample.game.exception.BadPlaceException;

import java.util.Arrays;

@Getter
public class Place {
    boolean[] canPlace = new boolean[Color.values().length - 1]; // Исключаю EMPTY

    @Setter
    Color color;

    public Place() {
        Arrays.fill(canPlace, true);
        color = Color.EMPTY;
    }

    public void putStone(Color color) {
        if (this.color != Color.EMPTY) {
            throw new BadPlaceException();
        }
        Arrays.fill(canPlace, false);
        this.color = color;
    }
}
