package main;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Andréas Appelqvist on 2016-09-23.
 */
public class GameBoard {

    private ArrayList<Tile> board;
    private int totalRows, totalCols;

    public GameBoard(int cols, int rows) {
        this.totalCols = cols;
        this.totalRows = rows;
    }

    public int getTotalCols() {
        return this.totalCols;
    }

    public void setBoard(ArrayList<Tile> board) {
        this.board = board;
    }

    public GameBoard getCopy() {
        GameBoard copy = new GameBoard(this.totalCols, this.totalRows);
        ArrayList<Tile> copyOfBoard = new ArrayList<>(this.board.size());
        for (int i = 0; i < this.board.size(); i++) {
            copyOfBoard.add(new Tile(board.get(i).getCurrentTileInt()));
        }
        copy.setBoard(copyOfBoard);
        return copy;
    }

    public void initBoard() {
        board = new ArrayList<Tile>();
        for (int i = 0; i < totalCols * totalRows; i++) {
            board.add(new Tile());
        }

        int startCol, startRow;
        startCol = (int) (totalCols / 2) - 1;
        startRow = (int) (totalRows / 2) - 1;
        board.get((startRow * totalCols) + startCol).setWhite();
        board.get((startRow * totalCols) + startCol + 1).setBlack();
        board.get((startRow * totalCols) + startCol + totalCols).setBlack();
        board.get((startRow * totalCols) + startCol + totalCols + 1).setWhite();

    }

    public int getBoardScore() {
        int score = 0;
        for (int i = 0; i < board.size(); i++) {
            score += board.get(i).currentColor;
        }
        return score;
    }

    public boolean isGameOver() {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).getCurrentTileInt() == 0) {
                return false;
            }
        }
        return true;
    }

    public void switchBrickColor(Move move) {
        int player = move.getMovePlayerInt();
        for (int i = 0; i < move.getPosOfSwitchingBricks().size(); i++) {
            this.board.get(move.getPosOfSwitchingBricks().get(i)).setCurrentTileInt(player);
        }
        this.board.get(move.getStartPos()).setCurrentTileInt(player);
    }

    public LinkedList<Move> getComputersLegalMove() {
        return getLegalMoves(-1);
    }

    public LinkedList<Move> getPlayerLegalMove() {
        return getLegalMoves(1);
    }

    private Move moveLookUp(int pos, int player) {
        int increase = totalCols;
        if (pos - increase >= 0 && (player * -1) == board.get(pos - increase).getCurrentTileInt()) {

            Move m = new Move(pos, player);
            while (pos - increase >= 0 && (player * -1) == board.get(pos - increase).getCurrentTileInt()) {
                m.addPosition(pos - increase);
                increase += totalCols;
            }
            if (pos - increase >= 0 && player == board.get(pos - increase).getCurrentTileInt()) {
                return m;
            }
        }
        return null;
    }

    private Move moveLookDown(int pos, int player) {
        int increase = totalCols;
        if (pos + increase < board.size() && (player * -1) == board.get(pos + increase).getCurrentTileInt()) {
            Move m = new Move(pos, player);
            while (pos + increase < board.size() && (player * -1) == board.get(pos + increase).getCurrentTileInt()) {
                m.addPosition(pos + increase);
                increase += totalCols;
            }
            if (pos + increase < board.size() && player == board.get(pos + increase).getCurrentTileInt()) {
                return m;
            }
        }
        return null;
    }

    private Move moveLookRight(int pos, int player) {
        int increase = 1;
        if (pos % totalCols != totalCols - 1 &&
                pos + increase < board.size() &&
                (player * -1) == board.get(pos + increase).getCurrentTileInt()) {

            Move m = new Move(pos, player);
            while ((pos + (increase - 1)) % totalCols != (totalCols - 1) &&
                    pos + increase < board.size() &&
                    (player * -1) == board.get(pos + increase).getCurrentTileInt()) {

                m.addPosition(pos + increase);
                increase++;
            }
            if ((pos + (increase - 1)) % totalCols != (totalCols - 1) &&
                    pos + increase < board.size() &&
                    player == board.get(pos + increase).getCurrentTileInt()) {
                return m;
            }
        }
        return null;
    }

    private Move moveLookLeft(int pos, int player) {
        int increase = 1;
        if (pos % totalCols != 0 &&
                pos - increase > 0 &&
                (player * -1) == board.get(pos - increase).getCurrentTileInt()) {

            Move m = new Move(pos, player);
            while ((pos - increase + 1) % totalCols != 0 &&
                    pos - increase > 0 &&
                    (player * -1) == board.get(pos - increase).getCurrentTileInt()) {

                m.addPosition(pos - increase);
                increase++;
            }
            if ((pos - increase + 1) % totalCols != 0 &&
                    pos - increase >= 0 &&
                    player == board.get(pos - increase).getCurrentTileInt()) {
                return m;
            }
        }
        return null;
    }

    private Move moveLookUpRight(int pos, int player) {
        int increaseUp = totalCols;
        int increaseRight = 1;
        if (pos % totalCols != (totalCols - 1) &&
                pos - (increaseUp - increaseRight) >= 0 &&
                (player * -1) == board.get(pos - (increaseUp - increaseRight)).getCurrentTileInt()) {

            Move m = new Move(pos, player);

            while ((pos + (increaseRight - 1)) % totalCols != totalCols - 1 &&
                    (pos - (increaseUp - increaseRight)) >= 0 &&
                    (player * -1) == board.get(pos - (increaseUp - increaseRight)).getCurrentTileInt()) {

                m.addPosition(pos - (increaseUp - increaseRight));
                increaseUp += totalCols;
                increaseRight++;
            }
            if ((pos + (increaseRight - 1)) % totalCols != totalCols - 1 &&
                    (pos - (increaseUp - increaseRight)) >= 0 &&
                    player == board.get(pos - (increaseUp - increaseRight)).getCurrentTileInt()) {

                return m;
            }
        }
        return null;
    }

    private Move moveLookUpLeft(int pos, int player) {
        int increaseLeft = 1;
        int increaseUp = totalCols;

        if (pos % totalCols != 0 &&
                pos - (increaseLeft + increaseUp) > 0 &&
                (player * -1 == board.get(pos - (increaseLeft + increaseUp)).getCurrentTileInt())) {
            Move m = new Move(pos, player);

            while ((pos - increaseLeft + 1) % totalCols != 0 &&
                    pos - (increaseLeft + increaseUp) > 0 &&
                    (player * -1) == board.get(pos - (increaseLeft + increaseUp)).getCurrentTileInt()) {

                m.addPosition(pos - (increaseLeft + increaseUp));
                increaseLeft++;
                increaseUp += totalCols;
            }

            if ((pos - increaseLeft + 1) % totalCols != 0 &&
                    pos - (increaseLeft + increaseUp) >= 0 &&
                    player == board.get(pos - (increaseLeft + increaseUp)).getCurrentTileInt()) {
                return m;
            }
        }
        return null;
    }

    private Move moveLookDownRight(int pos, int player) {
        int increaseDown = totalCols;
        int increaseRight = 1;
        if (pos % totalCols != (totalCols - 1) &&
                pos + increaseDown + increaseRight < board.size() &&
                (player * -1) == board.get(pos + increaseRight + increaseDown).getCurrentTileInt()) {

            Move m = new Move(pos, player);

            while ((pos + (increaseRight - 1)) % totalCols != totalCols - 1 &&
                    pos + increaseDown + increaseRight < board.size() &&
                    (player * -1) == board.get(pos + increaseRight + increaseDown).getCurrentTileInt()) {

                m.addPosition(pos + increaseDown + increaseRight);
                increaseDown += totalCols;
                increaseRight++;
            }

            if ((pos + (increaseRight - 1)) % totalCols != totalCols - 1 &&
                    pos + increaseDown + increaseRight < board.size() &&
                    player == board.get(pos + increaseRight + increaseDown).getCurrentTileInt()) {

                return m;
            }
        }

        return null;
    }

    private Move moveLookDownLeft(int pos, int player) {
        int increaseLeft = 1;
        int increaseDown = totalCols;

        if (pos % totalCols != 0 &&
                pos + (increaseDown - increaseLeft) < board.size() &&
                (player * -1 == board.get(pos + (increaseDown - increaseLeft)).getCurrentTileInt())) {

            Move m = new Move(pos, player);
            while ((pos - increaseLeft + 1) % totalCols != 0 &&
                    pos + (increaseDown - increaseLeft) < board.size() &&
                    (player * -1 == board.get(pos + (increaseDown - increaseLeft)).getCurrentTileInt())) {

                m.addPosition(pos + (increaseDown - increaseLeft));
                increaseDown += totalCols;
                increaseLeft ++;
            }
            if((pos - increaseLeft + 1) % totalCols != 0 &&
                    pos + (increaseDown - increaseLeft) < board.size() &&
                    (player == board.get(pos + (increaseDown - increaseLeft)).getCurrentTileInt())){
                return m;
            }

        }
        return null;
    }

    private LinkedList<Move> getLegalMoves(int player) {
        LinkedList<Move> legalMoves = new LinkedList<>();

        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).isEmpty()) {
                Move move;
                if ((move = moveLookUp(i, player)) != null) {
                    if (!mergeMove(legalMoves, move))
                        legalMoves.add(move);
                }

                if ((move = moveLookDown(i, player)) != null) {
                    if (!mergeMove(legalMoves, move))
                        legalMoves.add(move);
                }

                if ((move = moveLookRight(i, player)) != null) {
                    if (!mergeMove(legalMoves, move))
                        legalMoves.add(move);
                }

                if ((move = moveLookLeft(i, player)) != null) {
                    if (!mergeMove(legalMoves, move))
                        legalMoves.add(move);
                }

                if ((move = moveLookUpRight(i, player)) != null) {
                    if (!mergeMove(legalMoves, move))
                        legalMoves.add(move);
                }

                if ((move = moveLookUpLeft(i, player)) != null) {
                    if (!mergeMove(legalMoves, move))
                        legalMoves.add(move);
                }

                if ((move = moveLookDownRight(i, player)) != null) {
                    if (!mergeMove(legalMoves, move)) {
                        legalMoves.add(move);
                    }
                }

                if ((move = moveLookDownLeft(i, player)) != null) {
                    if (!mergeMove(legalMoves, move)) {
                        legalMoves.add(move);
                    }
                }
            }
        }
        return legalMoves;
    }

    /**
     * @param allMoves
     * @param move
     * @return true if it's already a move and will also add the switching bricks pos to its move
     * false if it's not already a move.
     */
    private boolean mergeMove(LinkedList<Move> allMoves, Move move) {
        boolean isAlreadyAMove = false;
        for (int i = 0; i < allMoves.size(); i++) {
            if (allMoves.get(i).getStartPos() == move.getStartPos()) {
                isAlreadyAMove = true;
                for (int j = 0; j < move.getPosOfSwitchingBricks().size(); j++) {
                    allMoves.get(i).addPosition(move.getPosOfSwitchingBricks().get(j));
                }
            }
        }
        return isAlreadyAMove;
    }

    public String getStrBoard(LinkedList<Move> legalMoves) {
        String str = "     ";
        for (int i = 0; i < totalCols; i++) {
            str += "  " + (i + 1) + "  ";
        }

        str += "\n";
        for (int i = 0; i < totalRows; i++) {
            str += " " + (i + 1) + " ";
            for (int j = 0; j < totalCols; j++) {
                if (board.get((i * totalCols) + j).isBlack()) {
                    str += "[X] ";
                } else if (board.get((i * totalCols) + j).isWhite()) {
                    str += "[O] ";
                } else {
                    boolean contained = false;
                    if (legalMoves != null) {
                        for (int k = 0; k < legalMoves.size(); k++) {
                            if ((i * totalCols) + j == legalMoves.get(k).getStartPos()) {
                                contained = true;
                            }
                        }
                    }
                    if (contained) {
                        str += "{  } ";
                    } else {
                        str += "[   ] ";
                    }
                }
            }
            str += "\n";
        }
        return str;
    }
}


