package main;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Andréas Appelqvist on 2016-09-22.
 */

public class Game {
    private GameBoard gameBoard;

    public Game(int cols, int rows) {
        gameBoard = new GameBoard(cols, rows);
        startGame();
    }

    public void startGame() {
        System.out.println(gameBoard.getPlayerLegalMove().toString());

        /*int inputCol = -1, inputRow = -1;
        String str = JOptionPane.showInputDialog(gameBoard.getStrBoard() + "\nIt's your turn. Input format is COL ROW");
        inputCol = Integer.parseInt("" + str.charAt(0));
        inputRow = Integer.parseInt("" + str.charAt(2));
        System.out.println("du har valt col:" + inputCol + " row:" + inputRow);*/
    }
}