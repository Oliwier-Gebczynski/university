package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import java.awt.Point;
import java.util.LinkedList;

/**
 * The Snake class represents the snake in the Snake game.
 * It maintains the position and movement of the snake, as well as its growth when eating candy.
 * The snake is represented as a LinkedList of points, where each point corresponds to a part of the snake's body.
 *
 * @author Oliwier Gebczynski
 * @version 1.1
 */
public class Snake {
    private LinkedList<Point> body;
    private Point direction;

    /**
     * Constructs a new Snake with an initial position and direction.
     * The snake's body initially contains one point at (7, 7), and its direction is set to (1, 0) (moving right).
     */
    public Snake() {
        body = new LinkedList<>();
        body.add(new Point(7, 7));
        direction = new Point(1, 0);
    }

    /**
     * Returns the LinkedList representing the entire body of the snake.
     * The head of the snake is the first point in the list.
     *
     * @return a LinkedList of Points representing the snake's body
     */
    public LinkedList<Point> getBody() {
        return body;
    }

    /**
     * Returns the position of the snake's head.
     * The head is the first point in the body list.
     *
     * @return the position of the snake's head
     */
    public Point getHead() {
        return body.getFirst();
    }

    /**
     * Returns the length of the snake (the number of body parts).
     *
     * @return the length of the snake
     */
    public int getLength() {
        return body.size();
    }

    /**
     * Returns the current direction of the snake.
     *
     * @return the current direction as a Point object (x, y)
     */
    public Point getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the snake to the given point.
     * The direction is represented as a point (x, y), where x and y can be -1, 0, or 1.
     *
     * @param newDirection the new direction the snake should move in
     */
    public void setDirection(Point newDirection) {
        this.direction = newDirection;
    }

    /**
     * Moves the snake in the current direction by adding a new head in front of the current head
     * and removing the last part of the body.
     * This simulates the snake's movement on the board.
     *
     * @param direction the direction in which the snake should move
     */
    public void move(Point direction) {
        Point newHead = new Point(getHead().x + direction.x, getHead().y + direction.y);
        body.addFirst(newHead);
        body.removeLast();
    }

    /**
     * Makes the snake grow by adding a new head in the current direction.
     * This method is called when the snake eats food, and it increases the snake's length.
     *
     * @param direction the direction in which the snake should grow
     */
    public void grow(Point direction) {
        Point newHead = new Point(getHead().x + direction.x, getHead().y + direction.y);
        body.addFirst(newHead);
    }
}
