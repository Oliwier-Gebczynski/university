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
    public InvalidName(String name) throws InvalidName {
        super("Invalid name: " + name);

        if (!isValidName(name)) {
            throw this;
        }
    }

    /**
     * Validates the name according to the rules:
     * - The name must not contain spaces.
     * - The name must not consist only of digits.
     *
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    private boolean isValidName(String name) {
        return name != null && !name.contains(" ") && !name.matches("\\d+");
    }
}
