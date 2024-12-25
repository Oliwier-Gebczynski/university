package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.InvalidCandyDimension;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Couple of tests for Candy Class.
 */
class CandyTest {

    /**
     * Verifies that the Candy object's x-coordinate is initialized correctly.
     */
    @Test
    void shouldInitializeCandyXCoordinateCorrectly() {
        // GIVEN: A Candy object will be created with x-coordinate = 5
        int x = 5;

        // WHEN: Creating the Candy object
        Candy candy = new Candy(x, 10);

        // THEN: Verify the x-coordinate is initialized correctly
        assertEquals(5, candy.x(), "Candy x-coordinate should be initialized correctly");
    }

    /**
     * Verifies that the Candy object's y-coordinate is initialized correctly.
     */
    @Test
    void shouldInitializeCandyYCoordinateCorrectly() {
        // GIVEN: A Candy object will be created with y-coordinate = 10
        int y = 10;

        // WHEN: Creating the Candy object
        Candy candy = new Candy(5, y);

        // THEN: Verify the y-coordinate is initialized correctly
        assertEquals(10, candy.y(), "Candy y-coordinate should be initialized correctly");
    }

    /**
     * Verifies that the Candy object is initialized correctly with given coordinates.
     *
     * @param x x-coordinate of the Candy
     * @param y y-coordinate of the Candy
     */
    @ParameterizedTest
    @CsvSource({"5, 10", "1, 15", "20, 0"})
    void shouldInitializeCandyCorrectly(int x, int y) {
        // GIVEN: A Candy object will be created with coordinates (x, y)

        // WHEN: Creating the Candy object
        Candy candy = new Candy(x, y);

        // THEN: Verify the coordinates are initialized correctly
        assertEquals(x, candy.x(), "Candy x-coordinate should be initialized correctly");
        assertEquals(y, candy.y(), "Candy y-coordinate should be initialized correctly");
    }

    /**
     * Verifies that the Candy object's position is returned correctly.
     */
    @Test
    void shouldReturnCorrectPosition() {
        // GIVEN: A Candy object is created with specific coordinates
        Candy candy = new Candy(3, 7);

        // WHEN: Getting the position of the candy
        Point position = candy.getPosition();

        // THEN: Verify the position is returned correctly
        assertNotNull(position, "Position should not be null");
        assertEquals(3, position.x, "Point x-coordinate should match Candy x-coordinate");
        assertEquals(7, position.y, "Point y-coordinate should match Candy y-coordinate");
    }

    /**
     * Verifies that an InvalidCandyDimension exception is thrown for negative width.
     *
     * @param width The width value for the Candy
     */
    @ParameterizedTest
    @CsvSource({"-1", "-5", "-10", "-100", "-1000"})
    void shouldThrowInvalidCandyDimensionExceptionForNegativeWidth(int width) {
        // GIVEN
        String message = "Should throw InvalidCandyDimension for negative width";

        // WHEN
        Throwable thrown = assertThrows(InvalidCandyDimension.class, () -> new Candy(width, 10));

        // THEN
        assertNotNull(thrown, message);
        assertEquals("Coordinates cannot be negative.", thrown.getMessage(), message);
    }

    /**
     * Verifies that an InvalidCandyDimension exception is thrown for negative height.
     *
     * @param height The height value for the Candy
     */
    @ParameterizedTest
    @CsvSource({"-1", "-5", "-10", "-100", "-1000"})
    void shouldThrowInvalidCandyDimensionExceptionForNegativeHeight(int height) {
        // GIVEN
        String message = "Should throw InvalidCandyDimension for negative height";

        // WHEN
        Throwable thrown = assertThrows(InvalidCandyDimension.class, () -> new Candy(10, height));

        // THEN
        assertNotNull(thrown, message);
        assertEquals("Coordinates cannot be negative.", thrown.getMessage(), message);
    }

}
