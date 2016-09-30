package main;

import java.util.LinkedList;

/**
 * Created by Andréas Appelqvist on 2016-09-27.
 */
public class AI {
    private GameBoard mainBoard;

    private int nodesExamine;
    private int depthLevel;
    public AI(GameBoard mainBoard) {
        this.mainBoard = mainBoard;
    }

    public Move chooseMove() {
        this.nodesExamine = 0;
        this.depthLevel = 0;
        int level = 12;
        Move move = minmax(level, mainBoard,Integer.MIN_VALUE, Integer.MAX_VALUE, true).getMove();
        System.out.println("Choosen pos by AI: "+move.getStartPos()+"\nSearch depth: "+(level-this.depthLevel)+", Nr of nodes examine: "+this.nodesExamine);
        return move;
    }

    private MiniMaxMove minmax(int level, GameBoard board,int alpha, int beta, boolean maxPlayer) { //maxPlayer is AI
        LinkedList<Move> moves = maxPlayer ? board.getComputersLegalMove() : board.getPlayerLegalMove();
        Move bestMove = null;
        depthLevel = level;
        if (level == 0 || moves.isEmpty()) {
            return new MiniMaxMove(null, board.getBoardScore()*-1); // getBoardScore will return a int where AI has neg och player pos. but in this method the AI need a pos value.
        } else {
            for (int i = 0; i < moves.size(); i++) {
                nodesExamine++;
                GameBoard copy = board.getCopy();
                copy.switchBrickColor(moves.get(i));

                if (maxPlayer) {
                    MiniMaxMove mmm = minmax(level - 1, copy, alpha, beta, false);
                    if (mmm.getScore() > alpha) {
                        alpha = mmm.getScore();
                        bestMove = moves.get(i);
                    }
                } else {
                    MiniMaxMove mmm = minmax(level - 1, copy, alpha, beta, true);
                    if (mmm.getScore() < beta) {
                        beta = mmm.getScore();
                        bestMove = moves.get(i);
                    }
                }
                if(alpha >= beta){ //Cut off
                    break;
                }
            }
        }
        return new MiniMaxMove(bestMove, maxPlayer ? alpha : beta);
    }
}
