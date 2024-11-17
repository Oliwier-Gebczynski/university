package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import java.util.Random;

/**
 * The Board class represents the game board for the Snake game.
 * It holds the snake and candy objects and manages their interactions.
 * The board is a grid of a specified width and height.
 * @author Oliwier Gebczynski
 * @version 1.1
 */
public class Board {
    private final int width;
    private final int height;
    private Snake snake;
    private Candy candy;

    /**
     * Constructs a new Board with the specified width and height.
     * It initializes the snake and spawns the first candy on the board.
     *
     * @param width  the width of the board in grid cells
     * @param height the height of the board in grid cells
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake();
        spawnCandy();
    }

    /**
     * Returns the width of the board.
     *
     * @return the width of the board
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the board.
     *
     * @return the height of the board
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the Snake object representing the player on the board.
     *
     * @return the snake object
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Returns the Candy object currently placed on the board.
     *
     * @return the candy object
     */
    public Candy getCandy() {
        return candy;
    }

    /**
     * Randomly spawns a new candy on the board at a random position within the grid.
     * The candy will be placed at a random (x, y) coordinate on the board.
     */
    public void spawnCandy() {
        Random rand = new Random();
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        candy = new Candy(x, y);
    }
}
