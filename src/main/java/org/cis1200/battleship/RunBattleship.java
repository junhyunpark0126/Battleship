package org.cis1200.battleship;

import javax.swing.*;
import java.awt.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 */
public class RunBattleship implements Runnable {
    public void run() {

        JOptionPane.showMessageDialog(null, "Welcome to Battleship! " +
                "\n\nThere are 4 ships, each of size 3x1, that you must find and " +
                "\ndestroy by clicking on the squares with your mouse! " +
                "\nHitting a ship refunds back the just-used missile.\n\n " +
                "Use all missiles without destroying all ships : You lose." +
                "\n Destroy all the ships : You win.\n\nYou have 12 missiles, good luck.");
        String input = JOptionPane.showInputDialog("Enter Your Name");

        final JFrame frame = new JFrame("Welcome to Battleship " + input + "!");
        frame.setLocation(400, 400);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.NORTH);
        final JLabel status = new JLabel("Setting up");
        status_panel.add(status);

        // Game board
        final GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.SOUTH);

        // Adding action listeners
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        final JButton save = new JButton("Save");
        save.addActionListener(e -> board.save());
        control_panel.add(save);

        final JButton load = new JButton("Load");
        load.addActionListener(e -> board.load());
        control_panel.add(load);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        board.reset();
    }
}