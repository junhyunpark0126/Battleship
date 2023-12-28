package org.cis1200.battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class instantiates a Battleship object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.

 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private Battleship battleship; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 600;
    public static final int BOARD_HEIGHT = 600;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        battleship = new Battleship(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        /*
         * Listens for mouseclicks. Updates the model, then updates the game
         * board based off of the updated model.
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point p = e.getPoint();

                // updates the model given the coordinates of the mouseclick
                battleship.makeMove(p.x / 75, p.y / 75);

                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        battleship.reset();
        status.setText("Missiles Left : 12 \n Score : 0 \n Ships Remaining : 4 " +
                "\n HIGH SCORE : " + battleship.getHighestScore());
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    public void save() {
        battleship.writeStringsToFile("./files/gamestatus.txt", false);
        requestFocusInWindow();
    }

    public void load() {
        battleship.readStringsFromFile();
        battleship.setGameOver(false);
        status.setText("Missiles Left : " + battleship.getLivesLeft() + " " +
                "\n Score : " + battleship.getScore() + " " +
                "\n Ships Remaining : " + battleship.getShipsRemaining() +
                "\n HIGH SCORE : " + battleship.getHighestScore());
        repaint();
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {

        status.setText("Missiles Left : " + battleship.getLivesLeft() + " \n Score : " +
                battleship.getScore() + " \n Ships Remaining : " + battleship.getShipsRemaining() +
                " \n HIGH SCORE : " + battleship.getHighestScore());
        int winner = battleship.checkWinner();

        if (winner == 1) {
            status.setText("YOU LOST");
        } else if (winner == 2) {
            status.setText("YOU WON!");
        }
    }

    /**
     * Draws the game` board.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(106, 193, 238));
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.black);
        // Draws board grid
        for (int i = 0; i < 600; i += 75) {
            g.drawLine(i, 0, i, 600);
        }
        for (int i = 0; i < 600; i += 75) {
            g.drawLine(0, i, 600, i);
        }

        // Draws water
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int state = battleship.getCell(i, j);
                if (state == 'x') {
                    g.drawOval(23 + i * 75, 23 + j * 75, 30, 30);
                } else if (state == 'o') {
                    g.fillOval(23 + i * 75, 23 + j * 75, 30, 30);
                }
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
