package org.cis1200.battleship;

import java.io.*;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is a model for Battleship.
 */
public class Battleship {

    private char[][] board;
    private char ship = 's';
    private char nothing = 'n';
    private char hit = 'o';
    private char miss = 'x';
    private boolean gameOver;
    private int score = 0;
    private int livesLeft = 12;
    private int shipsRemaining = 4;
    private List<Integer> highScores = new LinkedList<>();
    private int[][] ship1Coords = new int[3][2];
    private int[][] ship2Coords = new int[3][2];
    private int[][] ship3Coords = new int[3][2];
    private int[][] ship4Coords = new int[3][2];
    private int oneDown = 0;
    private int twoDown = 0;
    private int threeDown = 0;
    private int fourDown = 0;

    /**
     * Constructor sets up game state.
     */
    public Battleship() {
        reset();
    }

    /**
     * makeMove allows players to play a turn.
     *
     * @param i row to play in
     * @param j column to play in
     */
    @SuppressWarnings("DuplicatedCode")
    public void makeMove(int i, int j) {

        if (gameOver) {
            return;
        }

        if (board[i][j] == nothing) {
            board[i][j] = miss;
            livesLeft -= 1;
        }

        if (board[i][j] == ship) {
            board[i][j] = hit;
            score += 1;
        }

        if (board[ship1Coords[0][0]][ship1Coords[0][1]] == hit
                && board[ship1Coords[1][0]][ship1Coords[1][1]] == hit
                && board[ship1Coords[2][0]][ship1Coords[2][1]] == hit) {
            oneDown = 1;
        }

        if (board[ship2Coords[0][0]][ship2Coords[0][1]] == hit
                && board[ship2Coords[1][0]][ship2Coords[1][1]] == hit
                && board[ship2Coords[2][0]][ship2Coords[2][1]] == hit) {
            twoDown = 1;
        }

        if (board[ship3Coords[0][0]][ship3Coords[0][1]] == hit
                && board[ship3Coords[1][0]][ship3Coords[1][1]] == hit
                && board[ship3Coords[2][0]][ship3Coords[2][1]] == hit) {
            threeDown = 1;
        }

        if (board[ship4Coords[0][0]][ship4Coords[0][1]] == hit
                && board[ship4Coords[1][0]][ship4Coords[1][1]] == hit
                && board[ship4Coords[2][0]][ship4Coords[2][1]] == hit) {
            fourDown = 1;
        }

        shipsRemaining = 4 - (oneDown + twoDown + threeDown + fourDown);

    }

    /**
     * checkWinner checks whether the game has reached a win condition.
     * checkWinner only looks for horizontal wins.
     *
     * @return 0 if you lost and 1 2 if you won, 0 if neither
     */
    public int checkWinner() {

        if (livesLeft <= 0) {
            gameOver = true; // do we need this
            highScores.add(score);
            return 1;
        }
        if (score >= 12) {
            gameOver = true;
            highScores.add(score);
            return 2;
        }
        return 0;
    }

    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        board = new char[8][8];
        livesLeft = 12;
        score = 0;
        generateBoardWithShips();
        gameOver = false;
        oneDown = 0;
        twoDown = 0;
        threeDown = 0;
        fourDown = 0;
    }

    // Algorithm for generating ships on to board; only generates horizontal ships for now
    @SuppressWarnings("DuplicatedCode")
    public void generateBoardWithShips() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = nothing;
            }
        }

        int randomX1 = (int) Math.floor(Math.random() * 6); // generate number between 0 and 5
        int randomY1 = (int) Math.floor(Math.random() * 8);

        board[randomX1][randomY1] = ship;
        board[randomX1 + 1][randomY1] = ship;
        board[randomX1 + 2][randomY1] = ship;

        for (int i = 0; i < 3; i++) {
            ship1Coords[i][1] = randomY1;
        }

        ship1Coords[0][0] = randomX1;
        ship1Coords[1][0] = randomX1 + 1;
        ship1Coords[2][0] = randomX1 + 2;

        // making another horizontal ship
        int randomX2 = (int) Math.floor(Math.random() * 6);
        int randomY2 = (int) Math.floor(Math.random() * 8);

        while (randomY1 == randomY2) {
            //ensures doesn't overlap with first boat
            randomY2 = (int) Math.floor(Math.random() * 8);
        }

        //noinspection DuplicatedCode
        board[randomX2][randomY2] = ship;
        board[randomX2 + 1][randomY2] = ship;
        board[randomX2 + 2][randomY2] = ship;

        for (int i = 0; i < 3; i++) {
            ship2Coords[i][1] = randomY2;
        }

        ship2Coords[0][0] = randomX2;
        ship2Coords[1][0] = randomX2 + 1;
        ship2Coords[2][0] = randomX2 + 2;

        // making another horizontal ship
        int randomX3 = (int) Math.floor(Math.random() * 6);
        int randomY3 = (int) Math.floor(Math.random() * 8);

        while (randomY3 == randomY2 || randomY3 == randomY1) {
            //ensures doesn't overlap with first or second boats
            randomY3 = (int) Math.floor(Math.random() * 8);
        }

        board[randomX3][randomY3] = ship;
        board[randomX3 + 1][randomY3] = ship;
        board[randomX3 + 2][randomY3] = ship;

        for (int i = 0; i < 3; i++) {
            ship3Coords[i][1] = randomY3;
        }

        ship3Coords[0][0] = randomX3;
        ship3Coords[1][0] = randomX3 + 1;
        ship3Coords[2][0] = randomX3 + 2;

        // making another horizontal ship
        int randomX4 = (int) Math.floor(Math.random() * 6);
        int randomY4 = (int) Math.floor(Math.random() * 8);

        while (randomY4 == randomY1 || randomY4 == randomY2 || randomY4 == randomY3) {
            //ensures doesn't overlap with first or second boats
            randomY4 = (int) Math.floor(Math.random() * 8);
        }

        board[randomX4][randomY4] = ship;
        board[randomX4 + 1][randomY4] = ship;
        board[randomX4 + 2][randomY4] = ship;

        for (int i = 0; i < 3; i++) {
            ship4Coords[i][1] = randomY4;
        }

        ship4Coords[0][0] = randomX4;
        ship4Coords[1][0] = randomX4 + 1;
        ship4Coords[2][0] = randomX4 + 2;

    }


    /**
     * getCell is a getter for the contents of the cell specified by the method
     * arguments.
     *
     * @param i row to retrieve
     * @param j column to retrieve
     * @return a character denoting the contents of the corresponding cell on the
     *         game board. 's' = ship, 'n' = nothing (water), 'x' = water that has been clicked
     *         'o' = ship that has been hit
     */
    public char getCell(int i, int j) {
        return board[i][j];
    }

    public int getLivesLeft() {
        return this.livesLeft;
    }

    public int getScore() {
        return this.score;
    }

    public int getShipsRemaining() {
        return this.shipsRemaining;
    }

    public void setBoard(int y, int x, char c) {
        board[y][x] = c;
    }

    public boolean getGameOver() {
        return this.gameOver;
    }

    public char getBoard(int y, int x) {
        return this.board[y][x];
    }

    public void setShip1Coords(int x1, int y1, int x2, int y2, int x3, int y3) {
        ship1Coords[0][0] = x1;
        ship1Coords[0][1] = y1;
        ship1Coords[1][0] = x2;
        ship1Coords[1][1] = y2;
        ship1Coords[2][0] = x3;
        ship1Coords[2][1] = y3;
    }

    public void setShip2Coords(int x1, int y1, int x2, int y2, int x3, int y3) {
        ship2Coords[0][0] = x1;
        ship2Coords[0][1] = y1;
        ship2Coords[1][0] = x2;
        ship2Coords[1][1] = y2;
        ship2Coords[2][0] = x3;
        ship2Coords[2][1] = y3;
    }

    public void setShip3Coords(int x1, int y1, int x2, int y2, int x3, int y3) {
        ship3Coords[0][0] = x1;
        ship3Coords[0][1] = y1;
        ship3Coords[1][0] = x2;
        ship3Coords[1][1] = y2;
        ship3Coords[2][0] = x3;
        ship3Coords[2][1] = y3;
    }

    public void setShip4Coords(int x1, int y1, int x2, int y2, int x3, int y3) {
        ship4Coords[0][0] = x1;
        ship4Coords[0][1] = y1;
        ship4Coords[1][0] = x2;
        ship4Coords[1][1] = y2;
        ship4Coords[2][0] = x3;
        ship4Coords[2][1] = y3;
    }

    public int getHighestScore() {
        int max = 0;
        for (int integer : highScores) {
            if (integer > max) {
                max = integer;
            }
        }
        return max;
    }

    public void writeStringsToFile(String filePath, boolean append) {
        File file = Paths.get(filePath).toFile();
        try {
            FileWriter w = new FileWriter(file, append);
            BufferedWriter bw = new BufferedWriter(w);

            bw.write("HIGH SCORE : " + this.getHighestScore() + "\n");
            bw.write("Score : " + this.getScore() + "\n");
            bw.write("Missiles Left : " + this.getLivesLeft() + "\n");
            bw.write("Ships Remaining : " + this.getShipsRemaining() + "\n");

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    bw.write(board[j][i] + " ");
                }
                bw.write("\n");
            }

            bw.write("Ship Coordinates\n");

            bw.write(this.ship1Coords[0][1] + " " + this.ship1Coords[0][0] +
                    " " + this.ship1Coords[1][1] + " " +
                    this.ship1Coords[1][0] + " " + this.ship1Coords[2][1] + " " +
                    this.ship1Coords[2][0] + "\n");

            bw.write(this.ship2Coords[0][1] + " " + this.ship2Coords[0][0] +
                    " " + this.ship2Coords[1][1] + " " +
                    this.ship2Coords[1][0] + " " + this.ship2Coords[2][1] + " " +
                    this.ship2Coords[2][0] + "\n");

            bw.write(this.ship3Coords[0][1] + " " + this.ship3Coords[0][0] +
                    " " + this.ship3Coords[1][1] + " " +
                    this.ship3Coords[1][0] + " " + this.ship3Coords[2][1] + " " +
                    this.ship3Coords[2][0] + "\n");

            bw.write(this.ship4Coords[0][1] + " " + this.ship4Coords[0][0] +
                    " " + this.ship4Coords[1][1] + " " +
                    this.ship4Coords[1][0] + " " + this.ship4Coords[2][1] + " " +
                    this.ship4Coords[2][0] + "\n");

            bw.write("Miscellaneous\n");

            bw.write(this.oneDown + "\n");
            bw.write(this.twoDown + "\n");
            bw.write(this.threeDown + "\n");
            bw.write(this.fourDown + "\n");

            bw.write("Score History: \n");
            for (int highScores : highScores) {
                bw.write(highScores + "\n");
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("IOException Thrown");
        }

    }

    @SuppressWarnings("DuplicatedCode")
    public void readStringsFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./files/gamestatus.txt"));

            br.readLine(); // filler to skip reading the first line of high scores

            int loadedScore = Integer.parseInt("" + br.readLine().charAt(8));
            int loadedMissilesLeft = Integer.parseInt("" + br.readLine().charAt(16));
            int loadedShipsRemaining = Integer.parseInt("" + br.readLine().charAt(18));


            this.score = loadedScore;
            this.livesLeft = loadedMissilesLeft;
            this.shipsRemaining = loadedShipsRemaining;


            for (int i = 0; i < 8; i++) {
                String boardLine = br.readLine();
                for (int j = 0; j < 16; j += 2) {
                    board[j / 2][i] = boardLine.charAt(j);
                }
            }

            br.readLine(); // filler to skip reading the first line of high scores

            String boardLine1 = br.readLine();
            int x1 = Integer.parseInt("" + boardLine1.charAt(0));
            int y1 = Integer.parseInt("" + boardLine1.charAt(2));
            int x2 = Integer.parseInt("" + boardLine1.charAt(4));
            int y2 = Integer.parseInt("" + boardLine1.charAt(6));
            int x3 = Integer.parseInt("" + boardLine1.charAt(8));
            int y3 = Integer.parseInt("" + boardLine1.charAt(10));

            setShip1Coords(y1, x1, y2, x2, y3, x3);

            boardLine1 = br.readLine();
            x1 = Integer.parseInt("" + boardLine1.charAt(0));
            y1 = Integer.parseInt("" + boardLine1.charAt(2));
            x2 = Integer.parseInt("" + boardLine1.charAt(4));
            y2 = Integer.parseInt("" + boardLine1.charAt(6));
            x3 = Integer.parseInt("" + boardLine1.charAt(8));
            y3 = Integer.parseInt("" + boardLine1.charAt(10));

            setShip2Coords(y1, x1, y2, x2, y3, x3);

            boardLine1 = br.readLine();
            x1 = Integer.parseInt("" + boardLine1.charAt(0));
            y1 = Integer.parseInt("" + boardLine1.charAt(2));
            x2 = Integer.parseInt("" + boardLine1.charAt(4));
            y2 = Integer.parseInt("" + boardLine1.charAt(6));
            x3 = Integer.parseInt("" + boardLine1.charAt(8));
            y3 = Integer.parseInt("" + boardLine1.charAt(10));

            setShip3Coords(y1, x1, y2, x2, y3, x3);

            boardLine1 = br.readLine();
            x1 = Integer.parseInt("" + boardLine1.charAt(0));
            y1 = Integer.parseInt("" + boardLine1.charAt(2));
            x2 = Integer.parseInt("" + boardLine1.charAt(4));
            y2 = Integer.parseInt("" + boardLine1.charAt(6));
            x3 = Integer.parseInt("" + boardLine1.charAt(8));
            y3 = Integer.parseInt("" + boardLine1.charAt(10));

            setShip4Coords(y1, x1, y2, x2, y3, x3);

            br.readLine();

            String oneDown1 = br.readLine();
            this.oneDown = Integer.parseInt("" + oneDown1);

            String oneDown2 = br.readLine();
            this.twoDown = Integer.parseInt("" + oneDown2);

            String oneDown3 = br.readLine();
            this.threeDown = Integer.parseInt("" + oneDown3);

            String oneDown4 = br.readLine();
            this.fourDown = Integer.parseInt("" + oneDown4);


        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setGameOver(boolean bool) {
        this.gameOver = bool;
    }
}
