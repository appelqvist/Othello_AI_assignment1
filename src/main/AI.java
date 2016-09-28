package main;

import java.util.LinkedList;

/**
 * Created by Andréas Appelqvist on 2016-09-27.
 */
public class AI {
    GameBoard mainBoard;
    Game game;

    public AI(GameBoard mainBoard, Game game) {
        this.mainBoard = mainBoard;
        this.game = game;
    }

    public Move chooseMove() {
        Move move = minmax(5, mainBoard, true).getMove();
        move.setPlayer(-1);
        System.out.println("Vald pos av AI är: "+move.getPosOfSwitchingBricks());
        return move;
    }

    private MiniMaxMove minmax(int level, GameBoard board, boolean maxPlayer) { //maxPlayer is AI
        LinkedList<Move> moves = maxPlayer ? board.getComputersLegalMove() : board.getPlayerLegalMove();
        //System.out.println(moves.get(0).getPosOfSwitchingBricks()); //SKA BORT
        int bestScore = maxPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Move bestMove = null;

        if (level == 0 || moves.isEmpty()) {
           bestScore = board.getBoardScore();
        } else {
            for (int i = 0; i < moves.size(); i++) {
                GameBoard copy = board.getCopy();
                copy.switchBrickColor(moves.get(i));

                if (maxPlayer) {
                    MiniMaxMove mmm = minmax(level - 1, copy, false);
                    if (mmm.getScore() > bestScore) {
                        bestScore = mmm.getScore();
                        bestMove = moves.get(i);
                    }
                } else {
                    MiniMaxMove mmm = minmax(level - 1, copy, true);
                    if (mmm.getScore() < bestScore) {
                        bestScore = mmm.getScore();
                        bestMove = moves.get(i);
                    }
                }
            }
        }
        return new MiniMaxMove(bestMove, bestScore);
    }
}
