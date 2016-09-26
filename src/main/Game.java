package main;

import sun.awt.image.ImageWatched;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Andréas Appelqvist on 2016-09-22.
 */

public class Game {
    private GameBoard gameBoard;
    private boolean running = true;
    private int cols, rows;

    public Game(int cols, int rows) {
        gameBoard = new GameBoard(cols, rows);
        this.cols = cols;
        startGame();
    }

    public void startGame() {
        LinkedList<Move> legalMoves;
        while (running) {
            int inputCol = -1, inputRow = -1;
            legalMoves = gameBoard.getPlayerLegalMove();

            /*
            for (int i = 0; i < legalMoves.size(); i++) {
                for (int j = 0; j < legalMoves.get(i).getPosOfSwitchingBricks().size(); j++) {
                    System.out.println(legalMoves.get(i).getPosOfSwitchingBricks().get(j));
                }
            }
            */

            String str = JOptionPane.showInputDialog(gameBoard.getStrBoard() + "\nIt's your turn. Input format is COL ROW");
            inputCol = Integer.parseInt("" + str.charAt(0));
            inputRow = Integer.parseInt("" + str.charAt(2));
            int pos = ((inputRow - 1) * cols) + inputCol - 1;
            System.out.println("du har valt col:" + inputCol + " row:" + inputRow + " Vilket blir pos: " + pos);
        }
    }
}