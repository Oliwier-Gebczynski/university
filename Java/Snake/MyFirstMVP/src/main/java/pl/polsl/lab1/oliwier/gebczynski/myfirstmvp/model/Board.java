package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

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
}
