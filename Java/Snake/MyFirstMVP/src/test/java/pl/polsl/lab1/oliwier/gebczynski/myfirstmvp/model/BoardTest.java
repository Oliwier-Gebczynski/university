package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(10, 10);
    }

    @Test
    void testBoardInitialization() {
        assertEquals(10, board.getWidth(), "Width should be initialized correctly");
        assertEquals(10, board.getHeight(), "Height should be initialized correctly");
        assertNotNull(board.getSnake(), "Snake should not be null after initialization");
        assertNotNull(board.getCandy(), "Candy should be spawned on board initialization");
    }

    @Test
    void testSpawnCandyWithinBounds() {
        board.spawnCandy();
        Candy candy = board.getCandy();

        assertTrue(candy.x() >= 0 && candy.x() < board.getWidth(), "Candy x-coordinate should be within bounds");
        assertTrue(candy.y() >= 0 && candy.y() < board.getHeight(), "Candy y-coordinate should be within bounds");
    }

    @Test
    void testSpawnExtraCandyAtSpecificLocation() {
        Point extraCandyLocation = new Point(3, 5);
        board.spawnExtraCandies(extraCandyLocation);

        Candy candy = board.getCandy();
        assertEquals(3, candy.x(), "Candy x-coordinate should match the specified location");
        assertEquals(5, candy.y(), "Candy y-coordinate should match the specified location");
    }
}
