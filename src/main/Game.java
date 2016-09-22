package main;

import java.util.ArrayList;

/**
 *
 * Created by Andréas Appelqvist on 2016-09-22.
 *
 */

public class Game {

    ArrayList<Brick> board;
    int totalRows, totalCols;

    public Game(int cols, int rows){
        this.totalCols = cols;
        this.totalRows = rows;
        initBoard();
    }

    private void initBoard(){
        board = new ArrayList<Brick>();

        for(int i = 0; i < totalCols*totalRows; i++){
            board.add(new Brick());
        }


        //Just for even squares
        int startCol = -1, startRow = -1;
        if(totalCols % 2 == 0){
            startCol = (totalCols/2)-1;
        }else{
            //Do not work with uneven squares yet
        }

        if(totalRows % 2 == 0){
            startRow = (totalRows/2)-1;
        }else{
            //Do not work with uneven squares yet
        }

        board.get((startRow*totalCols)+startCol).setWhite();
        board.get((startRow*totalCols)+startCol+1).setBlack();
        board.get((startRow*totalCols)+startCol+totalCols).setBlack();
        board.get((startRow*totalCols)+startCol+totalCols+1).setWhite();

        printCurrentBoard();
    }

    public void startGame(){

    }

    private void printCurrentBoard(){

        for(int i = 0; i < totalRows; i ++){
            for(int j = 0; j < totalCols; j ++){
                if(board.get((i*totalCols)+j).isBlack()){
                    System.out.print("[B] ");
                }else if(board.get((i*totalCols)+j).isWhite()){
                    System.out.print("[W] ");
                }else{
                    System.out.print("[ ] ");
                }
            }
            System.out.println();
        }
    }

}
