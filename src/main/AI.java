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
        Move move = minmax(2, mainBoard,Integer.MIN_VALUE, Integer.MAX_VALUE, true).getMove();
        //move.setPlayer(-1);
        System.out.println("Vald pos av AI är: "+move.getStartPos());
        return move;
    }

    private MiniMaxMove minmax(int level, GameBoard board,int alpha, int beta, boolean maxPlayer) { //maxPlayer is AI
        LinkedList<Move> moves = maxPlayer ? board.getComputersLegalMove() : board.getPlayerLegalMove();

        //int bestScore = maxPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE; Just used without alphabeta-pruning
        Move bestMove = null;

        if (level == 0 || moves.isEmpty()) {
            return new MiniMaxMove(null, board.getBoardScore());
        } else {
            for (int i = 0; i < moves.size(); i++) {
                GameBoard copy = board.getCopy();
                copy.switchBrickColor(moves.get(i));

                if (maxPlayer) {
                    MiniMaxMove mmm = minmax(level - 1, copy, alpha, beta, false);
                    if (mmm.getScore() > alpha) {
                        //bestScore = mmm.getScore(); Just used without alphabeta-pruning
                        alpha = mmm.getScore();
                        bestMove = moves.get(i);
                    }
                } else {
                    MiniMaxMove mmm = minmax(level - 1, copy, alpha, beta, true);
                    if (mmm.getScore() < beta) {
                        beta = mmm.getScore();
                        //bestScore = mmm.getScore(); Just used without alphabeta-pruning
                        bestMove = moves.get(i);
                    }
                }
                if(alpha >= beta){ //Cut off
                    System.out.println("CUT OFF");
                    break;
                }
            }
        }
        return new MiniMaxMove(bestMove, maxPlayer ? alpha : beta);
    }
}
