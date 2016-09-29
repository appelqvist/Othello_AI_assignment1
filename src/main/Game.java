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

        boolean playerHasMadeALegalMove;
        while (running) {
            LinkedList<Move> legalMoves;
            playerHasMadeALegalMove = false;

            if ((legalMoves = gameBoard.getPlayerLegalMove()).isEmpty()) {
                JOptionPane.showMessageDialog(null, gameBoard.getStrBoard(null) + "\nYou has no available move\nAI will move");
                playerHasMadeALegalMove = true;
            } else {
                Move m;
                int inputCol, inputRow;
                String str = JOptionPane.showInputDialog(gameBoard.getStrBoard(legalMoves) + "\nIt's your turn. Input format is COL ROW");
                inputCol = Integer.parseInt("" + str.charAt(0));
                inputRow = Integer.parseInt("" + str.charAt(2));
                int pos = ((inputRow - 1) * gameBoard.getTotalCols()) + inputCol - 1;
                System.out.println("du har valt col:" + inputCol + " row:" + inputRow + " Vilket blir pos: " + pos);

                if ((m = legalMoves.remove()).getStartPos() == pos) {
                    makeMove(m);
                    playerHasMadeALegalMove = true;
                    JOptionPane.showMessageDialog(null, gameBoard.getStrBoard(null) + "\nBoard after your move\nAI will now move");
                }
            }

            if (gameBoard.isGameOver()) {
                JOptionPane.showMessageDialog(null, gameBoard.getStrBoard(null) + "\nGAME OVER!\n" +
                        "Score: " + gameBoard.getBoardScore());
                running = false;
                break;
            }

            if (playerHasMadeALegalMove) {
                if (gameBoard.getComputersLegalMove().isEmpty()) {
                    JOptionPane.showMessageDialog(null, gameBoard.getStrBoard(null) + "\nComputer had no available move");
                } else {
                    System.out.println("Computer will make move");
                    this.makeMove(computer.chooseMove());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Input");
            }

            if (gameBoard.isGameOver()) {
                JOptionPane.showMessageDialog(null, gameBoard.getStrBoard(null) + "\nGAME OVER!\n" +
                        "Score: " + gameBoard.getBoardScore());
                running = false;
                break;
            }
        }
    }
}
