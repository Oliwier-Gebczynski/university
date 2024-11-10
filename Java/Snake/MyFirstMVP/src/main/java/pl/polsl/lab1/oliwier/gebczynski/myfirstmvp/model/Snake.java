package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import java.awt.Point;
import java.util.LinkedList;

/**
 * Model klasy Snake, odpowiedzialny za reprezentację węża
 * i operacje na jego ciele.
 */
public class Snake {
    private LinkedList<Point> body;
    private Point direction;

    public Snake() {
        body = new LinkedList<>();
        body.add(new Point(7, 7)); // Starting point
        direction = new Point(1, 0);
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public Point getHead() {
        return body.getFirst();
    }

    public int getLength() {
        return body.size();
    }

    public Point getDirection() {
        return direction;
    }

    public void setDirection(Point newDirection) {
        this.direction = newDirection;
    }

    /**
     * Moves the snake by adding a new head at the front of the body and removing the tail.
     * @param direction The direction in which to move.
     */
    public void move(Point direction) {
        Point newHead = new Point(getHead().x + direction.x, getHead().y + direction.y);
        body.addFirst(newHead);
        body.removeLast();
    }

    /**
     * Grows the snake by adding a new head at the front of the body.
     * @param direction The direction in which the snake grows.
     */
    public void grow(Point direction) {
        Point newHead = new Point(getHead().x + direction.x, getHead().y + direction.y);
        body.addFirst(newHead);
    }
}
