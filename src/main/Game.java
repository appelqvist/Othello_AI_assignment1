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
    private boolean running;
    private AI computer;

    public Game(int cols, int rows) {
        gameBoard = new GameBoard(cols, rows);
        gameBoard.initBoard(); //Setting up init
        running = true;
        computer = new AI(gameBoard, this);
        startGame();
    }

    private void makeMove(Move move) {
        gameBoard.switchBrickColor(move);
    }

    public void startGame() {
        LinkedList<Move> legalMoves;
        boolean playerHasMadeMove = false;
        while (running) {
            int inputCol, inputRow;
            legalMoves = gameBoard.getPlayerLegalMove();
            String str = JOptionPane.showInputDialog(gameBoard.getStrBoard(legalMoves) + "\nIt's your turn. Input format is COL ROW");
            inputCol = Integer.parseInt("" + str.charAt(0));
            inputRow = Integer.parseInt("" + str.charAt(2));
            int pos = ((inputRow - 1) * gameBoard.getTotalCols()) + inputCol - 1;
            System.out.println("du har valt col:" + inputCol + " row:" + inputRow + " Vilket blir pos: " + pos);

            Move m;
            while (!legalMoves.isEmpty()) {
                if ((m = legalMoves.remove()).getStartPos() == pos) {
                    makeMove(m);
                    playerHasMadeMove = true;
                }
            }

            if (playerHasMadeMove) {
                System.out.println("Computer will make move");
                this.makeMove(computer.chooseMove());
                playerHasMadeMove = false;
            } else {
                JOptionPane.showMessageDialog(null, "NOT A LEGAL MOVE");
            }

            if (gameBoard.isGameOver()) {
                System.out.println("No more tiles. GAME OVER. Score: " + gameBoard.getBoardScore() + "" +
                        "\nRestart the application for a new match");
                running = false;
            }

        }
    }
}