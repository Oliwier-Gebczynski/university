package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

/**
 * A custom exception class that is thrown when a player's name is invalid.
 *
 * @author Oliwier Gebczynski
 * @version 1.0
 */
public class InvalidName extends Exception {

    /**
     * Constructor that creates an InvalidName exception with a specific message.
     *
     * @param message the detail message for the exception
     */
    public InvalidName(String message) {
        super(message);
    }
}
