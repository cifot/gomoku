package sample.game.artificialIntelligence.minimax;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sample.game.board.Board;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class MiniMaxNode {
    private Board board;
    final ArrayList<MiniMaxNode> children = new ArrayList<>();
    private long score;
    private int depth;
    private final MiniMaxNode parent;
    int column;
    int line;

    @Override
    public String toString() {
        return "MiniMaxNode{" +
                "board=" + board +
                ", score=" + score +
                ", depth=" + depth +
                ", column=" + column +
                ", line=" + line +
                '}';
    }
}
