package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.Test;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.InvalidName;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerInitialization() throws InvalidName {
        Player player = new Player("John");

        assertEquals("John", player.getName(), "Player's name should be initialized correctly");
        assertEquals(0, player.getScore(), "Player's score should be initialized to 0");
    }

    @Test
    void testIncrementScore() throws InvalidName {
        Player player = new Player("John");

        assertEquals(0, player.getScore(), "Initial score should be 0");

        player.incrementScore();

        assertEquals(1, player.getScore(), "Score should be incremented by 1");
    }

    @Test
    void testInvalidNameEmptyString() {
        Exception exception = assertThrows(InvalidName.class, () -> new Player(""), "Empty name should throw InvalidName exception");

        assertEquals("Name cannot be empty.", exception.getMessage());
    }

    @Test
    void testInvalidNameOnlyDigits() {
        Exception exception = assertThrows(InvalidName.class, () -> new Player("12345"), "Name with only digits should throw InvalidName exception");

        assertEquals("Name cannot consist solely of numbers.", exception.getMessage());
    }

    @Test
    void testInvalidNameWithSpaces() {
        Exception exception = assertThrows(InvalidName.class, () -> new Player("John Doe"), "Name with spaces should throw InvalidName exception");

        assertEquals("Name cannot contain spaces.", exception.getMessage());
    }

    @Test
    void testSetName() throws InvalidName {
        Player player = new Player("John");

        player.setName("Jane");

        assertEquals("Jane", player.getName(), "Player's name should be updated correctly");
    }

    @Test
    void testSetScore() throws InvalidName {
        Player player = new Player("John");

        player.setScore(50);

        assertEquals(50, player.getScore(), "Player's score should be updated correctly");
    }
}
