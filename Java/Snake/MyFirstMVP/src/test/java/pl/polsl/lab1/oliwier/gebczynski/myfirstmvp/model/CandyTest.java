package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;

class CandyTest {

    @Test
    void testCandyInitialization() {
        Candy candy = new Candy(5, 10);

        assertEquals(5, candy.x(), "Candy x-coordinate should be initialized correctly");
        assertEquals(10, candy.y(), "Candy y-coordinate should be initialized correctly");
    }

    @Test
    void testGetPosition() {
        Candy candy = new Candy(3, 7);

        Point position = candy.getPosition();

        assertNotNull(position, "Position should not be null");
        assertEquals(3, position.x, "Point x-coordinate should match Candy x-coordinate");
        assertEquals(7, position.y, "Point y-coordinate should match Candy y-coordinate");
    }

    @Test
    void testCandyWithNegativeCoordinates() {
        Candy candy = new Candy(-1, -5);

        assertEquals(-1, candy.x(), "Candy x-coordinate should allow negative values");
        assertEquals(-5, candy.y(), "Candy y-coordinate should allow negative values");
    }
}
