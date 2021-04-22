package sample.game.artificialIntelligence.bots;

import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.rulesData.rules.Rule;

import java.util.List;

public abstract class Bot {
    List<Rule> rules;
    abstract void putStone(Board board, Color color);
}
