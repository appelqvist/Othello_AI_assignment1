package main;

/**
 * Created by Andréas-PC on 2016-09-28.
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

    public void setMove(Move move) {
        this.move = move;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
