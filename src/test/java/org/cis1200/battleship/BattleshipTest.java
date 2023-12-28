package org.cis1200.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BattleshipTest {

    @Test
    public void testShipClickShowsHitAndIncrementsScore() {
        Battleship battleship = new Battleship();
        battleship.setBoard(0, 0, 's');
        battleship.makeMove(0, 0);
        assertEquals('o', battleship.getBoard(0, 0));
        assertEquals(1, battleship.getScore());
    }

    @Test
    public void testNothingClickShowsMissAndDecrementsScore() {
        Battleship battleship = new Battleship();
        battleship.setBoard(0, 0, 'n');
        battleship.makeMove(0, 0);
        assertEquals('x', battleship.getBoard(0, 0));
        assertEquals(11, battleship.getLivesLeft());
    }

    @Test
    public void testAlreadyClickedDoesNothing() {
        Battleship battleship = new Battleship();

        battleship.setBoard(0, 0, 'x');
        battleship.makeMove(0, 0);
        assertEquals('x', battleship.getBoard(0, 0));

        battleship.setBoard(0, 1, 'o');
        battleship.makeMove(0, 1);
        assertEquals('o', battleship.getBoard(0, 1));
    }

    @Test
    public void testResetResetsEverythingExceptHighScore() {
        Battleship battleship = new Battleship();

        for (int i = 0; i < 8; i++) {
            battleship.setBoard(0, i, 's');
            battleship.makeMove(0, i);
        }

        for (int i = 0; i < 3; i++) {
            battleship.setBoard(1, i, 's');
            battleship.makeMove(1, i);
        }

        battleship.setBoard(2, 0, 'n');
        battleship.makeMove(2, 0);

        battleship.setBoard(1, 3, 's');
        battleship.makeMove(1, 3);

        battleship.checkWinner();

        assertTrue(battleship.getGameOver());
        assertEquals(12, battleship.getScore());
        assertEquals(11, battleship.getLivesLeft());
        assertEquals(12, battleship.getHighestScore());

        battleship.reset();

        assertFalse(battleship.getGameOver());
        assertEquals(0, battleship.getScore());
        assertEquals(12, battleship.getLivesLeft());
        assertEquals(12, battleship.getHighestScore());

    }

    @Test
    public void testResetPriorToGameEndDoesNotChangeHighScore() {
        Battleship battleship = new Battleship();

        for (int i = 0; i < 8; i++) {
            battleship.setBoard(0, i, 's');
            battleship.makeMove(0, i);
        }

        assertEquals(0, battleship.getHighestScore());

        battleship.reset();

        assertEquals(0, battleship.getHighestScore());

    }

    @Test
    public void testHittingEntireShipDecrementsShipsLeft() {
        Battleship battleship = new Battleship();

        battleship.setBoard(0, 0, 's');
        battleship.setBoard(0, 1, 's');
        battleship.setBoard(0, 2, 's');
        battleship.setShip1Coords(0, 0, 0, 1, 0, 2);
        battleship.makeMove(0, 0);
        battleship.makeMove(0, 1);
        battleship.makeMove(0, 2);

        assertEquals(3, battleship.getShipsRemaining());
    }

    @Test
    public void testHittingEverythingWins() {
        Battleship battleship = new Battleship();

        for (int i = 0; i < 8; i++) {
            battleship.setBoard(0, i, 's');
            battleship.makeMove(0, i);
        }

        for (int i = 0; i < 3; i++) {
            battleship.setBoard(1, i, 's');
            battleship.makeMove(1, i);
        }

        battleship.setBoard(1, 3, 's');
        battleship.makeMove(1, 3);

        battleship.checkWinner();

        assertTrue(battleship.getGameOver());
        assertEquals(12, battleship.getScore());
        assertEquals(12, battleship.getLivesLeft());
        assertEquals(12, battleship.getHighestScore());
    }

}
