package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

/**
 * Represents the game board.
 * The board is a 2D grid where the snake, candy, and other game elements are placed.
 *
 * @author Oliwier Gebczynski
 * @version 1.0
 */
public class Board {
    private int width = 20;
    private int height = 20;
    private char[][] board = new char[width][height];
    
    //Getters

    /**
     * Gets the width of the board.
     *
     * @return the width of the board
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the board.
     *
     * @return the height of the board
     */
    public int getHeight(){
        return height;
    }

    /**
     * Gets the 2D array representing the board.
     *
     * @return the board as a 2D char array
     */
    public char[][] getBoard(){
        return board;
    }
    
    //Setters

    /**
     * Sets the width of the board.
     *
     * @param newWidth the new width of the board
     */
    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    /**
     * Sets the height of the board.
     *
     * @param newHeight the new height of the board
     */
    public void setHeight(int newHeight) {
        this.height = newHeight;
    }
}
