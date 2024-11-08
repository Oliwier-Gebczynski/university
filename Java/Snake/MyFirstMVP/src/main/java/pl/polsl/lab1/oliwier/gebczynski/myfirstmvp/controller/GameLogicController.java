package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.awt.event.KeyEvent;

public class GameLogicController {
    private LinkedList<Point> snake;
    private Point candy;
    private Point direction;
    private static final int BOARD_WIDTH = 15;
    private static final int BOARD_HEIGHT = 15;

    public GameLogicController() {
        // Initialize game state
        snake = new LinkedList<>();
        snake.add(new Point(BOARD_WIDTH / 2, BOARD_HEIGHT / 2));  // Starting position
        direction = new Point(1, 0);  // Snake starts moving to the right
        spawnCandy();
    }

    public void changeDirection(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (direction.y == 0) direction = new Point(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                if (direction.y == 0) direction = new Point(0, 1);
                break;
            case KeyEvent.VK_LEFT:
                if (direction.x == 0) direction = new Point(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                if (direction.x == 0) direction = new Point(1, 0);
                break;
        }
    }

    public boolean updateGameState() {
        Point head = snake.getFirst();
        Point newHead = new Point(head.x + direction.x, head.y + direction.y);

        // Check for collisions (walls or self)
        if (newHead.x < 0 || newHead.x >= BOARD_WIDTH || newHead.y < 0 || newHead.y >= BOARD_HEIGHT || snake.contains(newHead)) {
            return false;  // Game over
        }

        // Add new head to snake
        snake.addFirst(newHead);

        // Check if snake eats candy
        if (newHead.equals(candy)) {
            spawnCandy();  // Generate new candy
        } else {
            snake.removeLast();  // Remove last segment if no candy eaten
        }

        return true;
    }

    private void spawnCandy() {
        Random random = new Random();
        boolean isValid = false;

        // Generate candy at a random position not colliding with the snake
        while (!isValid) {
            candy = new Point(random.nextInt(BOARD_WIDTH), random.nextInt(BOARD_HEIGHT));
            isValid = !snake.contains(candy);
        }
    }

    public void render(Graphics g) {
        // Render the snake
        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x * 40 + 80, p.y * 40 + 80, 40, 40);
        }

        // Render the candy
        g.setColor(Color.RED);
        g.fillRect(candy.x * 40 + 80, candy.y * 40 + 80, 40, 40);
    }

    public LinkedList<Point> getSnake() {
        return snake;
    }

    public Point getCandy() {
        return candy;
    }
}
