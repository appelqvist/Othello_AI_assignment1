package main;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Andréas Appelqvist on 2016-09-22.
 */

public class Game {
    private ArrayList<Brick> board;
    private GameBoard gameBoard;
    private boolean playerTurn, gamePlaying;

    public Game(int cols, int rows) {
        gameBoard = new GameBoard(cols, rows);
        gamePlaying = true;
        startGame();
    }

    public void startGame() {
        String str = JOptionPane.showInputDialog(gameBoard.getStrBoard() + "\nIt's your turn. Input format is COL ROW");
        int inputCol = Integer.parseInt(""+str.charAt(0));
        int inputRow = Integer.parseInt(""+str.charAt(2));
        System.out.println("du har valt col:"+inputCol+" row:"+inputRow);
    }
}