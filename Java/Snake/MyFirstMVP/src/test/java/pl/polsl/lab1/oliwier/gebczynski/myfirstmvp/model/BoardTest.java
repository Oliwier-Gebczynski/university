package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        // GIVEN: A Board is initialized with width 10 and height 10
        board = new Board(10, 10);
    }

    @Test
    void shouldInitializeWidthCorrectly() {
        // WHEN: Getting the width of the board
        int width = board.getWidth();

        // THEN: Verify the width is initialized correctly
        assertEquals(10, width, "Width should be initialized correctly");
    }

    @Test
    void shouldInitializeHeightCorrectly() {
        // WHEN: Getting the height of the board
        int height = board.getHeight();

        // THEN: Verify the height is initialized correctly
        assertEquals(10, height, "Height should be initialized correctly");
    }

    @Test
    void shouldInitializeSnakeCorrectly() {
        // WHEN: Getting the snake object from the board
        Snake snake = board.getSnake();

        // THEN: Verify the snake is initialized correctly
        assertNotNull(snake, "Snake should not be null after initialization");
    }

    @Test
    void shouldInitializeCandyCorrectly() {
        // WHEN: Getting the candy object from the board
        Candy candy = board.getCandy();

        // THEN: Verify the candy is initialized correctly
        assertNotNull(candy, "Candy should be spawned on board initialization");
    }

    @Test
    void shouldSpawnCandy() {
        // GIVEN: A Board is initialized

        // WHEN: Spawning candy
        board.spawnCandy();
        Candy candy = board.getCandy();

        // THEN: Verify the candy is spawned correctly within bounds
        assertNotNull(candy, "Candy should not be null after being spawned");
        assertTrue(candy.x() >= 0 && candy.x() < board.getWidth(), "Candy x-coordinate should be within bounds");
        assertTrue(candy.y() >= 0 && candy.y() < board.getHeight(), "Candy y-coordinate should be within bounds");
    }

    @Test
    void shouldSpawnExtraCandyAtSpecificLocation() {
        // GIVEN: An extra candy location is specified
        Point extraCandyLocation = new Point(3, 5);

        // WHEN: Spawning extra candy at a specific location
        board.spawnExtraCandies(extraCandyLocation);
        Candy candy = board.getCandy();

        // THEN: Verify the candy is spawned at the specific location
        assertNotNull(candy, "Candy should not be null after spawning at specific location");
        assertEquals(3, candy.x(), "Candy x-coordinate should match the specified location");
        assertEquals(5, candy.y(), "Candy y-coordinate should match the specified location");
    }
}
