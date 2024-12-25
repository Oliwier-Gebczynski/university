package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Couple of tests for Board Class.
 */
class BoardTest {

    private Board board;

    /**
     * Sets up the basic data for the board before each test.
     */
    @BeforeEach
    void setUp() {
        // GIVEN: A Board is initialized with width 10 and height 10
        board = new Board(10, 10);
    }

    /**
     * Tests the correct initialization of the board width.
     */
    @Test
    void shouldInitializeWidthCorrectly() {
        // WHEN: Getting the width of the board
        int width = board.getWidth();

        // THEN: Verify the width is initialized correctly
        assertEquals(10, width, "Width should be initialized correctly");
    }

    /**
     * Tests the correct initialization of the board height.
     */
    @Test
    void shouldInitializeHeightCorrectly() {
        // WHEN: Getting the height of the board
        int height = board.getHeight();

        // THEN: Verify the height is initialized correctly
        assertEquals(10, height, "Height should be initialized correctly");
    }

    /**
     * Checks if negative dimensions are not allowed for the board.
     */
    @Test
    void shouldNotAllowNegativeBoardSize() {
        // GIVEN: Negative dimensions for the board
        int width = -1;
        int height = -5;

        // WHEN: Trying to create a board with negative dimensions

        // THEN: Expect an IllegalArgumentException to be thrown
        assertThrows(IllegalArgumentException.class, () -> new Board(width, height),
                "Board should not accept negative dimensions");
    }

    /**
     * Checks if candy is generated within the correct bounds of the board.
     */
    @Test
    void shouldSpawnCandyWithinBounds() {
        // WHEN: Spawning candy on the board
        board.spawnCandy();
        Candy candy = board.getCandy();

        // THEN: Verify the candy is within board bounds
        assertNotNull(candy, "Candy should not be null after being spawned");
        assertTrue(candy.x() >= 0 && candy.x() < board.getWidth(), "Candy x-coordinate should be within bounds");
        assertTrue(candy.y() >= 0 && candy.y() < board.getHeight(), "Candy y-coordinate should be within bounds");
    }

    /**
     * Tests whether extra candy is spawned at a specific location.
     *
     * @param x The x-coordinate of the extra candy location.
     * @param y The y-coordinate of the extra candy location.
     */
    @ParameterizedTest
    @CsvSource({"3, 5", "0, 0", "9, 9"})
    void shouldSpawnExtraCandyAtSpecificLocation(int x, int y) {
        // GIVEN: A specific location for extra candy
        Point extraCandyLocation = new Point(x, y);

        // WHEN: Spawning extra candy at a specific location
        board.spawnExtraCandies(extraCandyLocation);
        Candy candy = board.getCandy();

        // THEN: Verify the candy is spawned at the specific location
        assertNotNull(candy, "Candy should not be null after spawning at specific location");
        assertEquals(x, candy.x(), "Candy x-coordinate should match the specified location");
        assertEquals(y, candy.y(), "Candy y-coordinate should match the specified location");
    }
}