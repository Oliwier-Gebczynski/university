package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Couple of tests for Snake Class.
 */
class SnakeTest {

    private Snake snake;

    /**
     * Sets up the Snake object before each test.
     */
    @BeforeEach
    void setUp() {
        //GIVEN: A Snake object is created with the initial position at (7, 7) and one body segment.
        snake = new Snake();
    }

    /**
     * Verifies that the initial size of the snake's body is 1.
     */
    @Test
    void testInitialBodySize() {
        //WHEN: No actions are performed, just verifying body size.

        //THEN: Verify the snake's body contains one segment initially.
        assertEquals(1, snake.getBody().size(), "Snake's body should contain one segment initially");
    }

    /**
     * Verifies that the initial head position of the snake is (7, 7).
     */
    @Test
    void testInitialHeadPosition() {
        //WHEN: No actions are performed, just verifying initial head position.
        Point expectedInitialPosition = new Point(7, 7);

        //THEN: Verify the snake's head is at the initial position (7, 7).
        assertEquals(expectedInitialPosition, snake.getHead(), "Snake's head should be at the initial position (7, 7)");
    }

    /**
     * Verifies that the initial direction of the snake is (1, 0).
     */
    @Test
    void testInitialDirection() {
        //WHEN: No actions are performed, just verifying initial direction.
        Point expectedInitialDirection = new Point(1, 0);

        //THEN: Verify the snake's initial direction is (1, 0).
        assertEquals(expectedInitialDirection, snake.getDirection(), "Snake's initial direction should be (1, 0)");
    }

    /**
     * Verifies that the snake moves correctly in the given direction.
     *
     * @param dx The change in x-coordinate
     * @param dy The change in y-coordinate
     * @param expectedX The expected new x-coordinate
     * @param expectedY The expected new y-coordinate
     */
    @ParameterizedTest
    @CsvSource({"1, 0, 8, 7", "0, -1, 7, 6", "-1, 0, 6, 7"})
    void testMoveSnake(int dx, int dy, int expectedX, int expectedY) {
        // GIVEN: A Snake object initialized at position (7, 7)

        // WHEN: The snake moves in a given direction.
        snake.move(new Point(dx, dy));

        // THEN: Verify the head moves correctly and the body size remains unchanged.
        Point expectedNewHead = new Point(expectedX, expectedY);
        assertEquals(expectedNewHead, snake.getHead(), "Snake's head should move to the expected position");
        assertEquals(1, snake.getBody().size(), "Snake's body size should remain the same after moving");
    }

    /**
     * Verifies that the snake grows correctly in the given direction.
     *
     * @param dx The change in x-coordinate
     * @param dy The change in y-coordinate
     * @param expectedX The expected new x-coordinate
     * @param expectedY The expected new y-coordinate
     * @param expectedSize The expected size of the body after growing
     */
    @ParameterizedTest
    @CsvSource({"1, 0, 8, 7, 2", "0, -1, 7, 6, 2", "-1, 0, 6, 7, 2"})
    void testGrowSnake(int dx, int dy, int expectedX, int expectedY, int expectedSize) {
        // GIVEN: A Snake object initialized at position (7, 7)

        // WHEN: The snake grows in a given direction.
        snake.grow(new Point(dx, dy));

        // THEN: Verify the head moves correctly and the body size increases.
        Point expectedNewHead = new Point(expectedX, expectedY);
        assertEquals(expectedNewHead, snake.getHead(), "Snake's head should move to the expected position after growing");
        assertEquals(expectedSize, snake.getBody().size(), "Snake's body size should increase after growing");
    }

    /**
     * Verifies that the snake moves multiple times correctly.
     *
     * @param dx1 The first change in x-coordinate
     * @param dy1 The first change in y-coordinate
     * @param expectedX The expected new x-coordinate
     * @param expectedY The expected new y-coordinate
     * @param expectedSize The expected size of the body after multiple moves
     */
    @ParameterizedTest
    @CsvSource({"0, -1, 7, 6, 1", "1, 0, 8, 7, 1", "0, 1, 7, 8, 1"})
    void testMultipleMoves(int dx1, int dy1, int expectedX, int expectedY, int expectedSize) {
        // GIVEN: A Snake object initialized at position (7, 7)

        // WHEN: The snake moves multiple times.
        snake.move(new Point(dx1, dy1));

        // THEN: Verify the head's position and the body size remain correct.
        Point expectedHeadPosition = new Point(expectedX, expectedY);
        assertEquals(expectedHeadPosition, snake.getHead(), "Snake's head should be at the expected position after multiple moves");
        assertEquals(expectedSize, snake.getBody().size(), "Snake's body size should remain correct after multiple moves");
    }
}
