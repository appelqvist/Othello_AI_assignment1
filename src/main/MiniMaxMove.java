package main;

/**
 * Created by Andréas Appelqvist on 2016-09-28.
 */
public class MiniMaxMove {
    private Move move;
    private int score;

    public MiniMaxMove(Move move, int score){
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public int getScore() {
        return score;
    }

}
