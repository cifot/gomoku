package sample.game.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sample.game.exception.BadPlaceException;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public class Place {
    boolean[] canPlace = new boolean[Color.values().length - 1]; // Исключаю EMPTY

    @Setter
    Color color;

    @Override
    public String toString() {
        switch (color) {
            case WHITE -> {return "W";}
            case EMPTY -> {return "*";}
            case BLACK -> {return "B";}
            default -> throw new IllegalStateException("Unexpected value: " + color);
        }
    }

    public Place() {
        Arrays.fill(canPlace, true);
        color = Color.EMPTY;
    }

    @Override
    public Place clone() {
        boolean[] newCanPlace = new boolean[2];
        System.arraycopy(this.canPlace, 0, newCanPlace, 0, newCanPlace.length);
        return new Place(newCanPlace, this.color);
    }

    public void putStone(Color color) {
        if (this.color != Color.EMPTY) {
            throw new BadPlaceException();
        }
        Arrays.fill(canPlace, false);
        this.color = color;
    }
}
