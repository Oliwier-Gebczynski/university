package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body;

    public Snake() {
        body = new LinkedList<>();
        body.add(new Point(7, 7)); // Starting point, adjust as needed
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public Point getHead() {
        return body.getFirst();
    }

    public void move(Point direction) {
        Point newHead = new Point(getHead().x + direction.x, getHead().y + direction.y);
        body.addFirst(newHead);
        body.removeLast();
    }

    public boolean isCollidingWithItself() {
        Point head = getHead();
        return body.stream().skip(1).anyMatch(part -> part.equals(head));
    }

    public void grow(Point direction) {
        Point newHead = new Point(getHead().x + direction.x, getHead().y + direction.y);
        body.addFirst(newHead);
    }
}
