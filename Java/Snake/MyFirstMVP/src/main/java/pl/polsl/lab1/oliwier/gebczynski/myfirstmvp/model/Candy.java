package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import java.awt.Point;

/**
 * The Candy record represents a piece of candy on the board.
 * It has x and y coordinates to specify its position.
 * @param x the x-coordinate of the candy
 * @param y the y-coordinate of the candy
 * @author Oliwier Gebczynski
 * @version 1.2
 */
public record Candy(int x, int y) {

    /**
     * Returns the position of the candy as a Point object.
     *
     * @return the position of the candy as a Point object
     */
    public Point getPosition() {
        return new Point(x, y);
    }
}
