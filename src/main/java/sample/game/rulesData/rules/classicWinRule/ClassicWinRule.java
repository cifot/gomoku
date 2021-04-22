package sample.game.rulesData.rules.classicWinRule;

import lombok.AllArgsConstructor;
import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.board.Place;
import sample.game.rulesData.Weight;
import sample.game.rulesData.interfaces.WinRule;
import sample.game.rulesData.rules.Rule;

import java.util.List;

@AllArgsConstructor
public class ClassicWinRule extends Rule
    implements WinRule {

    private final Weight weight;

    @Override
    public boolean isWin(Board board, Color color) {
        return this.getScore(board, color) > Integer.MAX_VALUE;
    }

    @Override
    public long getScore(Board board, Color color) {
        Place[][][] stoneBoards = board.getStoneBoards();
        long score = 0L;
        for (Place[][] stoneBoard: stoneBoards) {
            for (Place[] line : stoneBoard) {
                for (int j = 0; j < line.length; j++) {
                    while (j < line.length && line[j].getColor() != color) {
                        j++;
                    }
                    int startPos = j;
                    while (j < line.length && line[j].getColor() == color) {
                        j++;
                    }
                    score += weight.getWeight(j - startPos);
                }
            }
        }
        return score;
    }

    @Override
    public long getMiniMaxScore(Board board, Color color) {
        Place[][][] stoneBoards = board.getStoneBoards();
        long score = 0L;
        for (Place[][] stoneBoard: stoneBoards) {
            for (Place[] line : stoneBoard) {
                for (int j = 0; j < line.length; j++) {
                    while (j < line.length && line[j].getColor() != color) {
                        j++;
                    }
                    int startPos = j;
                    while (j < line.length && line[j].getColor() == color) {
                        j++;
                    }
                    if (j - startPos >= 5) {
                        score += Integer.MAX_VALUE;
                    } else {
                        score += weight.getWeight(j - startPos);
                    }
                }
            }
        }
        return score;
    }

    @Override
    public boolean hasChance(Board board, Color color, List<WinRule> winRules) {
        return false;
    }
}
