package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

/**
 * Exception thrown when an invalid game result is encountered.
 * This exception is used to signal that a specific game result is not valid.
 * @author Oliwier Gebczynski
 * @version 1.2
 */
public class InvalidGameResult extends RuntimeException {

    /**
     * Constructs a new InvalidGameResult exception with the specified detail message.
     *
     * @param message the detail message explaining why the game result is invalid
     */
    public InvalidGameResult(String message) {
        super(message);
    }
}
