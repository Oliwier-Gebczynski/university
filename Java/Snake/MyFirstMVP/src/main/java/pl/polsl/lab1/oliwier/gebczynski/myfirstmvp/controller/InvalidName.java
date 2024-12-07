package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

/**
 * A custom exception class that is thrown when a player's name is invalid.
 * This exception is used to enforce name validation rules such as no spaces,
 * no digits-only names, and non-empty names.
 *
 * @author Oliwier Gebczynski
 * @version 1.2
 */
public class InvalidName extends Exception {

    /**
     * Constructor that creates an InvalidName exception with a specific message.
     * This message will describe the cause of the invalid name.
     *
     * @param message the detail message for the exception
     */
    public InvalidName(String name) throws InvalidName {
        super(name);
    }

    /**
     * Validates the player's name according to the following rules:
     * - The name must not be null or empty.
     * - The name must not consist only of digits.
     * - The name must not contain spaces.
     *
     * @param name the name to validate
     * @throws InvalidName if the name is invalid based on the defined rules
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
