package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

/**
 * Represents a snake in the game.
 * Contains the snake's name and length.
 *
 * @author Oliwier Gebczynski
 * @version 1.0
 */
public class Snake {

    private String name = "Snake";
    private int length = 1;

    //Getters

    /**
     * Gets the snake's name.
     *
     * @return the name of the snake
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the snake's length.
     *
     * @return the length of the snake
     */
    public int getLength() {
        return length;
    }

    //Setters

    /**
     * Sets the snake's name.
     *
     * @param newName the new name of the snake
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Sets the snake's length.
     *
     * @param newLength the new length of the snake
     */
    public void setLength(int newLength) {
        this.length = newLength;
    }

}
