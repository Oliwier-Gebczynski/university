package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final int width;
    private final int height;
    private Snake snake;
    private Candy candy;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake();
        spawnCandy();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Snake getSnake() {
        return snake;
    }

    public Candy getCandy() {
        return candy;
    }

    public void spawnCandy() {
        Random rand = new Random();
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        candy = new Candy(x, y);
    }

    public boolean checkCollision() {
        // Sprawdzanie kolizji węża z krawędzią planszy lub z samym sobą
        if (snake.getHeadX() < 0 || snake.getHeadX() >= width ||
                snake.getHeadY() < 0 || snake.getHeadY() >= height) {
            return true;
        }
        return snake.isCollidingWithItself();
    }

    public boolean checkCandyCollision() {
        // Sprawdzanie, czy głowa węża jest na tej samej pozycji, co cukierek
        return snake.getHeadX() == candy.getX() && snake.getHeadY() == candy.getY();
    }
}
