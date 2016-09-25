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
        initBoard();
    }

    private void initBoard() {
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

    public LinkedList<Integer> getComputersLegalMove() {
        return getLegalMoves(-1);
    }

    public LinkedList<Integer> getPlayerLegalMove() {
        return getLegalMoves(1);
    }

    private LinkedList<Integer> getLegalMoves(int player) {
        LinkedList<Integer> legalMoves = new LinkedList<>();
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).isEmpty()) {
                //Check tile under board(i)
                if (i + totalRows < board.size() && player * -1 == board.get(i + totalRows).getCurrentTileInt()) {
                    int multiplier = totalRows;
                    while (i + multiplier < board.size() && player * -1 == board.get(i + multiplier).getCurrentTileInt()) {
                        multiplier += totalRows;
                    }
                    if (i + multiplier < board.size() && player == board.get(i + multiplier).getCurrentTileInt()) {
                        legalMoves.add(i);
                    }
                } else if (i - totalRows >= 0 && player * -1 == board.get(i - totalRows).getCurrentTileInt()){ //Check tile over board(i)
                    int multiplier = totalRows;
                    while (i - multiplier >= 0 && player * -1 == board.get(i - multiplier).getCurrentTileInt()) {
                        multiplier += totalRows;
                    }
                    if (i - multiplier >= 0 && player == board.get(i - multiplier).getCurrentTileInt()) {
                        legalMoves.add(i);
                    }
                    //Looking right of board(i)
                }else if((i + 1) % totalCols > 0){  //If it's not a wall on right side
                    if(i + 1 < board.size() && player * -1 == board.get(i+1).getCurrentTileInt()){
                        int increase = 1;
                        while ( (i + increase)% totalCols > 0 && i + increase < board.size() && player * -1 == board.get(increase + i).getCurrentTileInt()){
                            increase ++;
                        }
                        if((i + increase)% totalCols > 0 && i + increase < board.size() && player == board.get(increase + i).getCurrentTileInt()){
                            legalMoves.add(i);
                        }
                    }
                    //Looking left of board(i)
                }else if(i % totalCols > 0){ //If it's not a wall on left side of board(i)
                    if(i-1 >= 0 && !board.get(i-1).isEmpty() && player * -1 == board.get(i-1).getCurrentTileInt()){
                        int increse = 1;
                        while( i - increse >= 0 && (i - increse) % totalCols > 0 && player * -1 == board.get(i -increse).getCurrentTileInt()){
                            increse++;
                        }
                        if(i - increse >= 0 && (i - increse) % totalCols > 0 && player == board.get(i -increse).getCurrentTileInt()){
                            legalMoves.add(i);
                        }
                    }
                }

            }
        }
        return legalMoves;
    }

    public String getStrBoard() {

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
                    str += "[   ] ";
                }
            }
            str += "\n";
        }
        return str;
    }
}


