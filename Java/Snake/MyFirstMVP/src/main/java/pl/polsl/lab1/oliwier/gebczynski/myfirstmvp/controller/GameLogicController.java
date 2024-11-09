package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Board;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Snake;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Candy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameLogicController {
    private Board board;
    private Point direction;

    public GameLogicController(Board board) {
        this.board = board;
        this.direction = new Point(1, 0); // Initial direction
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
        Snake snake = board.getSnake();
        Candy candy = board.getCandy();

        Point newHead = new Point(snake.getHead().x + direction.x, snake.getHead().y + direction.y);

        // Check for boundary and self-collision
        if (isOutOfBounds(newHead) || snake.isCollidingWithItself()) {
            return false; // Game over
        }

        // Move the snake
        if (newHead.equals(new Point(candy.getX(), candy.getY()))) {
            snake.grow(direction); // Grow snake if it eats the candy
            board.spawnCandy();    // Spawn new candy
        } else {
            snake.move(direction); // Normal movement
        }

        return true; // Game continues
    }

    private boolean isOutOfBounds(Point head) {
        return head.x < 0 || head.x >= board.getWidth() || head.y < 0 || head.y >= board.getHeight();
    }

    public void render(Graphics g) {
        Snake snake = board.getSnake();
        Candy candy = board.getCandy();

        g.setColor(Color.GREEN);
        for (Point p : snake.getBody()) {
            g.fillRect(p.x * 40 + 80, p.y * 40 + 80, 40, 40);
        }

        g.setColor(Color.RED);
        g.fillRect(candy.getX() * 40 + 80, candy.getY() * 40 + 80, 40, 40);
    }
}
