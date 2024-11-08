package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private List<SnakeSegment> segments;
    private int directionX = 1; // 1 oznacza ruch w prawo, -1 w lewo
    private int directionY = 0; // 1 oznacza ruch w dół, -1 w górę

    public Snake() {
        segments = new LinkedList<>();
        segments.add(new SnakeSegment(5, 5)); // Początkowa pozycja głowy węża
    }

    public int getHeadX() {
        return segments.get(0).getX();
    }

    public int getHeadY() {
        return segments.get(0).getY();
    }

    public List<SnakeSegment> getSegments() {
        return segments;
    }

    public void setDirection(int x, int y) {
        // Zapobieganie zawracaniu na siebie samego
        if ((x != -directionX || y != -directionY) && (x != 0 || y != 0)) {
            directionX = x;
            directionY = y;
        }
    }

    public void move() {
        // Przesuwanie segmentów do przodu
        for (int i = segments.size() - 1; i > 0; i--) {
            segments.get(i).setPosition(segments.get(i - 1).getX(), segments.get(i - 1).getY());
        }
        // Przesunięcie głowy w wyznaczonym kierunku
        SnakeSegment head = segments.get(0);
        head.setPosition(head.getX() + directionX, head.getY() + directionY);
    }

    public void grow() {
        // Dodanie nowego segmentu na końcu węża
        SnakeSegment tail = segments.get(segments.size() - 1);
        segments.add(new SnakeSegment(tail.getX(), tail.getY()));
    }

    public boolean isCollidingWithItself() {
        int headX = getHeadX();
        int headY = getHeadY();
        for (int i = 1; i < segments.size(); i++) {
            if (segments.get(i).getX() == headX && segments.get(i).getY() == headY) {
                return true;
            }
        }
        return false;
    }
}

