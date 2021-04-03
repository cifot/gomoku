package sample.game.exception;

public class BadPlaceException extends GomokuException {

    public BadPlaceException() {
        super();
    }

    public BadPlaceException(String str) {
        super(str);
    }
}
