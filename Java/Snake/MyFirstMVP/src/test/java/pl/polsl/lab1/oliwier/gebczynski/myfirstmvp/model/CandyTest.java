package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CandyTest {

    @Test
    void shouldInitializeCandyXCoordinateCorrectly() {
        // GIVEN: A Candy object will be created with x-coordinate = 5
        int x = 5;

        // WHEN: Creating the Candy object
        Candy candy = new Candy(x, 10);

        // THEN: Verify the x-coordinate is initialized correctly
        assertEquals(5, candy.x(), "Candy x-coordinate should be initialized correctly");
    }

    @Test
    void shouldInitializeCandyYCoordinateCorrectly() {
        // GIVEN: A Candy object will be created with y-coordinate = 10
        int y = 10;

        // WHEN: Creating the Candy object
        Candy candy = new Candy(5, y);

        // THEN: Verify the y-coordinate is initialized correctly
        assertEquals(10, candy.y(), "Candy y-coordinate should be initialized correctly");
    }

    @Test
    void shouldReturnCorrectPosition() {
        // GIVEN: A Candy object is created with x-coordinate = 3 and y-coordinate = 7
        Candy candy = new Candy(3, 7);

        // WHEN: Getting the position of the candy
        Point position = candy.getPosition();

        // THEN: Verify the position is returned correctly
        assertNotNull(position, "Position should not be null");
        assertEquals(3, position.x, "Point x-coordinate should match Candy x-coordinate");
        assertEquals(7, position.y, "Point y-coordinate should match Candy y-coordinate");
    }

    @Test
    void shouldAllowNegativeXCoordinateForCandy() {
        // GIVEN: A Candy object is created with negative x-coordinate = -1
        int x = -1;

        // WHEN: Creating the Candy object
        Candy candy = new Candy(x, 10);

        // THEN: Verify the x-coordinate allows negative values
        assertEquals(-1, candy.x(), "Candy x-coordinate should allow negative values");
    }

    @Test
    void shouldAllowNegativeYCoordinateForCandy() {
        // GIVEN: A Candy object is created with negative y-coordinate = -5
        int y = -5;

        // WHEN: Creating the Candy object
        Candy candy = new Candy(5, y);

        // THEN: Verify the y-coordinate allows negative values
        assertEquals(-5, candy.y(), "Candy y-coordinate should allow negative values");
    }
}
