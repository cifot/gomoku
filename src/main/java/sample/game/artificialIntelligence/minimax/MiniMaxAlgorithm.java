package sample.game.artificialIntelligence.minimax;

import lombok.AllArgsConstructor;
import sample.game.Game;
import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.board.Place;
import sample.game.rulesData.interfaces.PossiblePlaceRule;
import sample.game.rulesData.interfaces.WinRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class MiniMaxAlgorithm {
    final int maxDepth;
    final int boardSize;
    final List<WinRule> winRules;


    private void minimax(MiniMaxNode root, Color color) {
        Color enemyColor = color.getEnemyColor();
        int depth = root.getDepth();
        MiniMaxNode parent = root.getParent();

        if (depth == maxDepth - 1) {
            root.setScore(addLastPossibleNodes(root, enemyColor));
            long parentScore = depth % 2 == 0 ? Long.min(root.getScore(), parent.getScore()) : Long.max(root.getScore(), parent.getScore());
            parent.setScore(parentScore);

        } else {
            addPossibleNodes(root, enemyColor);
            if (parent == null) {
                if (root.getChildren().size() == 0)
                    return;
                root.getChildren().forEach(miniMaxNode -> minimax(miniMaxNode, enemyColor));
                root.setScore(root.getChildren().stream().map(MiniMaxNode::getScore).max(Long::compare).get());
                return;
            }
            long score = depth % 2 == 0 ? Long.MIN_VALUE : Long.MAX_VALUE;
            for (MiniMaxNode childNode : root.getChildren()) {
                minimax(childNode, enemyColor);
                if (depth % 2 == 0) {
                    score = Math.max(childNode.getScore(), score);
                    childNode.setScore(score);
                    if (score >= parent.getScore()) {
                        root.setScore(score);
                        return;
                    }

                } else {
                    score = Math.min(childNode.getScore(), score);
                    childNode.setScore(score);
                    if (score <= parent.getScore()) {
                        root.setScore(score);
                        return;
                    }
                }
            }
            if (depth % 2 == 0) {
                parent.setScore(Long.min(parent.getScore(), score));
            } else {
                parent.setScore(Long.max(parent.getScore(), score));
            }
            root.setScore(score);
        }
    }

    public void putStone(Board board, Color color, Game game) {
        MiniMaxNode root = new MiniMaxNode(board, 0, 0,  null, 0, 0);
        minimax(root, color);
        MiniMaxNode findNode = root.getChildren().stream().filter(childNode -> childNode.getScore() == root.getScore()).findFirst().orElse(null);
        System.out.println(root);
        for (var s : root.getChildren()) {
            System.out.println(s);
        }
        game.putStone(color, findNode.getColumn(), findNode.getLine());
    }

    public Long addLastPossibleNodes(MiniMaxNode node, Color color) {
        Board board = node.getBoard();
        int depth = node.getDepth();
        Place[][] places = node.getBoard().getStoneBoard();
        Board tmpBoard;
        MiniMaxNode parent = node.getParent();
        long parentScore = parent.getScore();
        long score = depth % 2 == 0 ? Long.MIN_VALUE : Long.MAX_VALUE;
        for (int column = 0; column < boardSize; column++) {
            for (int line = 0; line < boardSize; line++) {
                if (needPutStone(places, column, line, color)) {
                    tmpBoard = board.clone();
                    tmpBoard.putStone(color, column, line);
                    long currentScore = 0;
                    for (var winRule: winRules) {
                        currentScore = Long.max(winRule.getMiniMaxScore(board, color), currentScore);
                    }
                    if (depth % 2 == 0) {
                        score = Math.max(currentScore, score);
                        node.setScore(score);
                        if (score >= parentScore) {
                            return score;
                        }

                    } else {
                        score = Math.min(currentScore, score);
                        node.setScore(score);
                        if (score <= parentScore) {
                            return score;
                        }
                    }
                }
            }
        }
        if (depth % 2 == 0) {
            parentScore = Long.min(parent.getScore(), score);
            parent.setScore(parentScore);
        } else {
            parentScore = Long.max(parent.getScore(), score);
            parent.setScore(parentScore);
        }
        return score;
    }

    public void addPossibleNodes(MiniMaxNode node, Color color) {
        Board board = node.getBoard();
        int depth = node.getDepth();
        Place[][] places = node.getBoard().getStoneBoard();
        ArrayList<MiniMaxNode> children = node.getChildren();
        Board tmpBoard;
        for (int column = 0; column < boardSize; column++) {
            for (int line = 0; line < boardSize; line++) {
                if (needPutStone(places, column, line, color)) {
                    tmpBoard = board.clone();
                    tmpBoard.putStone(color, column, line);
                    if (depth % 2 == 0) {
                        children.add(new MiniMaxNode(tmpBoard, Long.MAX_VALUE, depth + 1, node, column, line));
                    } else {
                        children.add(new MiniMaxNode(tmpBoard, Long.MIN_VALUE, depth + 1, node, column, line));
                    }
                }
            }
        }
    }

    private boolean needPutStone(Place[][] board, int column, int line, Color color) {
        if (!board[column][line].getCanPlace()[color.ordinal()]) {
            return false;
        }
        return (column > 0 && board[column - 1][line].getColor() != Color.EMPTY)
                || (column > 0 && line > 0 && board[column - 1][line - 1].getColor() != Color.EMPTY)
                || (column > 0 && line < boardSize - 1 && board[column - 1][line + 1].getColor() != Color.EMPTY)
                || (column < boardSize - 1 && board[column + 1][line].getColor() != Color.EMPTY)
                || (column < boardSize - 1 && line > 0 && board[column + 1][line - 1].getColor() != Color.EMPTY)
                || (column < boardSize - 1 && line < boardSize - 1 && board[column + 1][line + 1].getColor() != Color.EMPTY)
                || (line > 0 && board[column][line - 1].getColor() != Color.EMPTY)
                || (line < boardSize - 1 && board[column][line + 1].getColor() != Color.EMPTY);
    }




}
