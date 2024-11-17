package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import java.awt.*;

/**
 * The Candy class represents a piece of candy that the snake can eat in the Snake game.
 * It has a position on the board defined by x and y coordinates.
 * @author Oliwier Gebczynski
 * @version 1.1
 */
public class Candy {
    private int x;
    private int y;

    /**
     * Constructs a new Candy object with the specified position.
     *
     * @param x the x-coordinate of the candy's position
     * @param y the y-coordinate of the candy's position
     */
    public Candy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the candy's position.
     *
     * @return the x-coordinate of the candy's position
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the candy's position.
     *
     * @return the y-coordinate of the candy's position
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the position of the candy as a Point object.
     * The Point object contains both the x and y coordinates.
     *
     * @return the position of the candy as a Point object
     */
    public Point getPosition() {
        return new Point(x, y);
    }

    /**
     * Sets the position of the candy to the specified x and y coordinates.
     *
     * @param x the new x-coordinate of the candy's position
     * @param y the new y-coordinate of the candy's position
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
