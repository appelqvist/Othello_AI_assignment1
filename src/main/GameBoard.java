package main;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Andréas-PC on 2016-09-23.
 */
public class GameBoard {

    private ArrayList<Tile> board;
    private int totalRows, totalCols;

    public GameBoard(int cols, int rows) {
        this.totalCols = cols;
        this.totalRows = rows;
    }
    public int getTotalCols(){
        return this.totalCols;
    }

    public ArrayList<Tile> getBoard(){
        return board;
    }

    public void setBoard(ArrayList<Tile> board){
        this.board = board;
    }

    public GameBoard getCopy(){
        GameBoard copy = new GameBoard(this.totalCols, this.totalRows);
        ArrayList<Tile> copyOfBoard = new ArrayList<>(this.board.size());
        for(int i = 0; i < this.board.size(); i++) {
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

    public int getBoardScore(){
        int score = 0;
        for(int i = 0; i < board.size(); i++){
            score += board.get(i).currentColor;
        }
        return score;
    }

    public boolean isGameOver(){
        for(int i = 0; i < board.size(); i++){
            if(board.get(i).getCurrentTileInt() == 0){
                return false;
            }
        }
        return true;
    }

    public void switchBrickColor(Move move){
        int player = move.getMovePlayerInt();
        for(int i = 0; i < move.getPosOfSwitchingBricks().size(); i++){
            this.board.get(i).setCurrentTileInt(player);
        }
        this.board.get(move.getStartPos()).setCurrentTileInt(player);
    }

    public LinkedList<Move> getComputersLegalMove() {
        return getLegalMoves(-1);
    }

    public LinkedList<Move> getPlayerLegalMove() {
        return getLegalMoves(1);
    }

    private LinkedList<Move> getLegalMoves(int player) {
        LinkedList<Move> legalMoves = new LinkedList<>();

        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).isEmpty()) {
                //Check tile under board(i)
                if (i + totalRows < board.size() && (player * -1) == board.get(i + totalRows).getCurrentTileInt()) {
                    int increase = totalRows;
                    Move aMove = new Move(i, player);
                    while (i + increase < board.size() && (player * -1) == board.get(i + increase).getCurrentTileInt()) {
                        aMove.addPosition(i + increase);
                        increase += totalRows;
                    }
                    if (i + increase < board.size() && player == board.get(i + increase).getCurrentTileInt()) {
                        if (!mergeMove(legalMoves, aMove))
                            legalMoves.add(aMove);
                    }
                }

                //Check tile over board(i)
                if (i - totalRows >= 0 && (player * -1) == board.get(i - totalRows).getCurrentTileInt()) {
                    int increase = totalRows;
                    Move aMove = new Move(i, player);
                    while (i - increase >= 0 && (player * -1) == board.get(i - increase).getCurrentTileInt()) {
                        aMove.addPosition(i - increase);
                        increase += totalRows;
                    }
                    if (i - increase >= 0 && player == board.get(i - increase).getCurrentTileInt()) {
                        if (!mergeMove(legalMoves, aMove))
                            legalMoves.add(aMove);
                    }
                }

                //Looking right of board(i)
                if ((i + 1) % totalCols > 0) {  //If it's not a wall on right side
                    if (i + 1 < board.size() && (player * -1) == board.get(i + 1).getCurrentTileInt()) {
                        int increase = 1;
                        Move aMove = new Move(i, player);
                        while ((i + increase) % totalCols > 0 && i + increase < board.size() && (player * -1) == board.get(increase + i).getCurrentTileInt()) {
                            aMove.addPosition(i + increase);
                            increase++;
                        }
                        if ((i + increase) % totalCols > 0 && i + increase < board.size() && player == board.get(increase + i).getCurrentTileInt()) {
                            if (!mergeMove(legalMoves, aMove))
                                legalMoves.add(aMove);
                        }
                    }

                    //Looking SouthEast
                    if (i + totalRows + 1 < board.size() && (player * -1) == board.get(i + totalRows + 1).getCurrentTileInt()) {
                        int increase = totalRows + 1;
                        Move aMove = new Move(i, player);
                        while (i + increase % totalCols > 0 && increase + i < board.size() && (player * -1) == board.get(i + increase).getCurrentTileInt()) {
                            aMove.addPosition(i + increase);
                            increase += (totalRows + 1);
                        }
                        if (i + increase % totalCols > 0 && increase + i < board.size() && player == board.get(i + increase).getCurrentTileInt()) {
                            if (!mergeMove(legalMoves, aMove))
                                legalMoves.add(aMove);
                        }
                    }
                }

                //Looking NorthEast
                if (i + 1 - totalRows >= 0 && (player * -1) == board.get(i + 1 - totalRows).getCurrentTileInt()) {
                    int increase = totalRows - 1;
                    Move aMove = new Move(i, player);
                    while (i - increase % totalCols > 0 && i - increase >= 0 && (player * -1) == board.get(i - increase).getCurrentTileInt()) {
                        aMove.addPosition(i - increase);
                        increase += (totalRows - 1);
                    }
                    if (i - increase % totalCols > 0 && i - increase >= 0 && player == board.get(i - increase).getCurrentTileInt()) {
                        if (!mergeMove(legalMoves, aMove))
                            legalMoves.add(aMove);
                    }
                }
            }

            //Looking left of board(i)
            if (i % totalCols > 0) { //If it's not a wall on left side of board(i)
                if (i - 1 >= 0 && (player * -1) == board.get(i - 1).getCurrentTileInt()) {
                    int increase = 1;
                    Move aMove = new Move(i, player);
                    while (i - increase >= 0 && (i - increase) % totalCols > 0 && (player * -1) == board.get(i - increase).getCurrentTileInt()) {
                        aMove.addPosition(i - increase);
                        increase++;
                    }
                    if (i - increase >= 0 && (i - increase) % totalCols > 0 && player == board.get(i - increase).getCurrentTileInt()) {
                        if (!mergeMove(legalMoves, aMove))
                            legalMoves.add(aMove);
                    }
                }

                //Looking SouthWest
                if (i + totalCols - 1 < board.size() && (player * -1) == board.get(i + totalCols - 1).getCurrentTileInt()) {
                    int increase = totalCols - 1;
                    Move aMove = new Move(i, player);
                    while (i + increase < board.size() && i + increase % totalCols > 0 && (player * -1) == board.get(i + increase).getCurrentTileInt()) {
                        aMove.addPosition(i + increase);
                        increase += (totalCols - 1);
                    }
                    if (i + increase < board.size() && i + increase % totalCols > 0 && player == board.get(i + increase).getCurrentTileInt()) {
                        if (!mergeMove(legalMoves, aMove))
                            legalMoves.add(aMove);
                    }
                }

                //Looking NothWest
                if (i - totalCols - 1 >= 0 && (player * -1) == board.get(i - 1 - totalCols).getCurrentTileInt()) {
                    int increase = (totalCols + 1);
                    Move aMove = new Move(i, player);
                    while (i - increase >= 0 && i - increase % totalCols > 0 && (player * -1) == board.get(i - increase).getCurrentTileInt()) {
                        aMove.addPosition(i - increase);
                        increase += totalCols + 1;
                    }
                    if (i - increase >= 0 && i - increase % totalCols > 0 && player == board.get(i - increase).getCurrentTileInt()) {
                        if (!mergeMove(legalMoves, aMove)) {
                            legalMoves.add(aMove);
                        }
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
                }
                else{
                    boolean contained = false;
                    for(int k = 0; k < legalMoves.size(); k++){
                       if((i * totalCols) + j == legalMoves.get(k).getStartPos()){
                           contained = true;
                       }
                    }
                    if(contained){
                        str += "{  } ";
                    }else{
                        str += "[   ] ";
                    }
                }
            }
            str += "\n";
        }
        return str;
    }
}


