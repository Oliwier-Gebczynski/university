package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

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
        super(name);
    }

    /**
     * Validates the name according to the rules:
     * - The name must not contain spaces.
     * - The name must not consist only of digits.
     *
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    public static void validateName(String name) throws InvalidName {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidName("Name cannot be empty.");
        }
        if (name.matches("\\d+")) {
            throw new InvalidName("Name cannot consist solely of numbers.");
        }
        if (name.contains(" ")) {
            throw new InvalidName("Name cannot contain spaces.");
        }
    }
}
