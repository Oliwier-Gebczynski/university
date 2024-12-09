package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    private Snake snake;

    @BeforeEach
    void setUp() {
        snake = new Snake();
        // GIVEN: A Snake object is created, with the initial position at (7, 7) and one body segment.
    }

    @Test
    void testInitialBodySize() {
        // GIVEN: Snake initialized with 1 body segment

        // WHEN: No actions are performed, just verifying body size.

        // THEN
        assertEquals(1, snake.getBody().size(), "Snake's body should contain one segment initially");
    }

    @Test
    void testInitialHeadPosition() {
        // GIVEN: Snake initialized with head at (7, 7)

        // WHEN: No actions are performed, just verifying initial head position.

        // THEN
        Point expectedInitialPosition = new Point(7, 7);
        assertEquals(expectedInitialPosition, snake.getHead(), "Snake's head should be at the initial position (7, 7)");
    }

    @Test
    void testInitialDirection() {
        // GIVEN: Snake initialized with direction (1, 0)

        // WHEN: No actions are performed, just verifying initial direction.

        // THEN
        Point expectedInitialDirection = new Point(1, 0);
        assertEquals(expectedInitialDirection, snake.getDirection(), "Snake's initial direction should be (1, 0)");
    }

    @Test
    void testMoveSnake() {
        // GIVEN: Snake is at position (7, 7)

        // WHEN: Snake moves by (1, 0), expecting head at position (8, 7)
        snake.move(new Point(1, 0));

        // THEN
        Point expectedNewHead = new Point(8, 7);
        assertEquals(expectedNewHead, snake.getHead(), "Snake's head should move to (8, 7)");

        assertEquals(1, snake.getBody().size(), "Snake's body size should remain the same after moving");
    }

    @Test
    void testGrowSnake() {
        // GIVEN: Snake is at position (7, 7)

        // WHEN: Snake grows, expecting head at position (8, 7) and body size increases
        snake.grow(new Point(1, 0));

        // THEN
        Point expectedNewHead = new Point(8, 7);
        assertEquals(expectedNewHead, snake.getHead(), "Snake's head should move to (8, 7) after growing");

        assertEquals(2, snake.getBody().size(), "Snake's body size should increase by 1 after growing");
    }

    @Test
    void testMultipleMoves() {
        // GIVEN: Snake starts at position (7, 7)

        // WHEN: Snake moves twice: first to (8, 7), then to (8, 6)
        snake.move(new Point(1, 0));
        snake.move(new Point(0, -1));

        // THEN
        Point expectedHeadPosition = new Point(8, 6);
        assertEquals(expectedHeadPosition, snake.getHead(), "Snake's head should be at (8, 6) after multiple moves");

        assertEquals(1, snake.getBody().size(), "Snake's body size should remain 1 after multiple moves");
    }

    @Test
    void testMultipleGrows() {
        // GIVEN: Snake starts at position (7, 7)

        // WHEN: Snake grows twice: first to (8, 7), then to (8, 6)
        snake.grow(new Point(1, 0));
        snake.grow(new Point(0, -1));

        // THEN
        Point expectedHeadPosition = new Point(8, 6);
        assertEquals(expectedHeadPosition, snake.getHead(), "Snake's head should be at (8, 6) after multiple grows");

        assertEquals(3, snake.getBody().size(), "Snake's body size should increase to 3 after multiple grows");
    }

    @Test
    void testGetHead() {
        // GIVEN: Snake starts at position (7, 7)

        // WHEN: Get the head position after initialization
        Point expectedHeadPosition = new Point(7, 7);
        assertEquals(expectedHeadPosition, snake.getHead(), "Snake's head should be at (7, 7) initially");

        // WHEN: Snake moves to (8, 7)
        snake.move(new Point(1, 0));
        expectedHeadPosition = new Point(8, 7);

        // THEN
        assertEquals(expectedHeadPosition, snake.getHead(), "Snake's head should move to (8, 7) after moving");
    }
}
