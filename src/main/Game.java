package main;
import java.util.ArrayList;
/**
 * Created by Andréas Appelqvist on 2016-09-22.
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

        int startCol, startRow;
        startCol = (int)(totalCols/2)-1;
        startRow = (int)(totalRows/2)-1;

        board.get((startRow*totalCols)+startCol).setWhite();
        board.get((startRow*totalCols)+startCol+1).setBlack();
        board.get((startRow*totalCols)+startCol+totalCols).setBlack();
        board.get((startRow*totalCols)+startCol+totalCols+1).setWhite();

        System.out.println(getBoard());
    }

    public void startGame(){
    }

    private String getBoard(){
        String str = "";
        for(int i = 0; i < totalRows; i ++){
            for(int j = 0; j < totalCols; j ++){
                if(board.get((i*totalCols)+j).isBlack()){
                    str += "[B] ";
                }else if(board.get((i*totalCols)+j).isWhite()){
                    str += "[W] ";
                }else{
                    str += "[ ] ";
                }
            }
            str += "\n";
        }
        return str;
    }
}