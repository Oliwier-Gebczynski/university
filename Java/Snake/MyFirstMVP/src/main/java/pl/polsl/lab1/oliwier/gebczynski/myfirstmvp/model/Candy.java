package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

/**
 * Represents a candy in the game.
 * Candies have a name and a value that affects the player's score.
 *
 * @author Oliwier Gebczynski
 * @version 1.0
 */
public class Candy {

    private String name = "Candy";
    private int value = 1;

    //Getters

    /**
     * Gets the name of the candy.
     *
     * @return the name of the candy
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the value of the candy.
     *
     * @return the value of the candy
     */
    public int getValue() {
        return value;
    }

    //Setters

    /**
     * Sets the name of the candy.
     *
     * @param newName the new name for the candy
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Sets the value of the candy.
     *
     * @param newValue the new value for the candy
     */
    public void setValue(int newValue) {
        this.value = newValue;
    }

}
