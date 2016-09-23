package main;

import java.util.ArrayList;

/**
 * Created by Andréas-PC on 2016-09-23.
 */
public class GameBoard {

    private ArrayList<Brick> board;
    private int totalRows, totalCols;

    public GameBoard(int cols, int rows){
        this.totalCols = cols;
        this.totalRows = rows;
        initBoard();
    }

    private void initBoard(){
        board = new ArrayList<Brick>();
        for(int i = 0; i < totalCols*totalRows; i++){
            board.add(new Brick());
        }

        int startCol, startRow;
        startCol = (int)(totalCols/2)-1;
        startRow = (int)(totalRows/2)-1;

        board.get((startRow*totalCols)+startCol).setWhite();
        board.get((startRow*totalCols)+startCol+1).setBlack();
        board.get((startRow*totalCols)+startCol+totalCols).setBlack();
        board.get((startRow*totalCols)+startCol+totalCols+1).setWhite();
    }

    public String getStrBoard(){
        String str = "";
        for(int i = 0; i < totalRows; i ++){
            for(int j = 0; j < totalCols; j ++){
                if(board.get((i*totalCols)+j).isBlack()){
                    str += "[B] ";
                }else if(board.get((i*totalCols)+j).isWhite()){
                    str += "[W] ";
                }else{
                    str += "[   ] ";
                }
            }
            str += "\n";
        }
        return str;
    }
}


