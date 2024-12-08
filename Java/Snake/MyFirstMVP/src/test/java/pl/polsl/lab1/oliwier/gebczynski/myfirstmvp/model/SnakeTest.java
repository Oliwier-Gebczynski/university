package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    private Snake snake;

    @BeforeEach
    void setUp() {
        snake = new Snake();
    }

    @Test
    void testSnakeInitialization() {
        assertEquals(1, snake.getBody().size(), "Snake's body should contain one segment initially");

        Point expectedInitialPosition = new Point(7, 7);
        assertEquals(expectedInitialPosition, snake.getHead(), "Snake's head should be at the initial position (7, 7)");

        Point expectedInitialDirection = new Point(1, 0);
        assertEquals(expectedInitialDirection, snake.getDirection(), "Snake's initial direction should be (1, 0)");
    }

    @Test
    void testMoveSnake() {
        Point direction = new Point(1, 0);
        snake.move(direction);

        Point expectedNewHead = new Point(8, 7);
        assertEquals(expectedNewHead, snake.getHead(), "Snake's head should move to (8, 7)");

        assertEquals(1, snake.getBody().size(), "Snake's body size should remain the same after moving");
    }

    @Test
    void testGrowSnake() {
        Point direction = new Point(1, 0);
        snake.grow(direction);

        Point expectedNewHead = new Point(8, 7);
        assertEquals(expectedNewHead, snake.getHead(), "Snake's head should move to (8, 7) after growing");

        assertEquals(2, snake.getBody().size(), "Snake's body size should increase by 1 after growing");
    }

    @Test
    void testMultipleMoves() {
        snake.move(new Point(1, 0));
        snake.move(new Point(0, -1));

        Point expectedHeadPosition = new Point(8, 6);
        assertEquals(expectedHeadPosition, snake.getHead(), "Snake's head should be at (8, 6) after multiple moves");

        assertEquals(1, snake.getBody().size(), "Snake's body size should remain 1 after multiple moves");
    }

    @Test
    void testMultipleGrows() {
        snake.grow(new Point(1, 0));
        snake.grow(new Point(0, -1));

        Point expectedHeadPosition = new Point(8, 6);
        assertEquals(expectedHeadPosition, snake.getHead(), "Snake's head should be at (8, 6) after multiple grows");

        assertEquals(3, snake.getBody().size(), "Snake's body size should increase to 3 after multiple grows");
    }

    @Test
    void testGetHead() {
        Point expectedHeadPosition = new Point(7, 7);
        assertEquals(expectedHeadPosition, snake.getHead(), "Snake's head should be at (7, 7) initially");

        snake.move(new Point(1, 0));
        expectedHeadPosition = new Point(8, 7);
        assertEquals(expectedHeadPosition, snake.getHead(), "Snake's head should move to (8, 7) after moving");
    }
}
