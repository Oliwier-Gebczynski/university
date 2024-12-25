package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

/**
 * The InvalidCandyDimension class represents an exception that is thrown
 * when the dimensions (coordinates) of a candy are invalid.
 * Specifically, this exception is thrown when either the x or y coordinate
 * is negative or zero.
 *
 * This ensures that the coordinates for candies are non-negative and valid
 * for game board operations.
 * @author Oliwier Gebczynski
 * @version 1.2
 */
public class InvalidCandyDimension extends Exception {

  /**
   * Constructs an InvalidCandyDimension exception with a specific message.
   *
   * @param message the detail message for the exception
   */
  public InvalidCandyDimension(String message) {
    super(message);
  }
}
