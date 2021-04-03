package sample.game.exception;

public class GomokuException extends RuntimeException {

    public GomokuException() {
        super();
    }

    public GomokuException(String str) {
        super(str);
    }
}
