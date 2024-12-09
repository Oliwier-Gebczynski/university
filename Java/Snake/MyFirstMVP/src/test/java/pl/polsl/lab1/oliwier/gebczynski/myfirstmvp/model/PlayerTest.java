package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.Test;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.InvalidName;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void shouldInitializePlayerCorrectly() throws InvalidName {
        // GIVEN: A Player object will be created with the name "John"
        String name = "John";

        // WHEN
        Player player = new Player(name);

        // THEN
        assertEquals("John", player.getName(), "Player's name should be initialized correctly");
        assertEquals(0, player.getScore(), "Player's score should be initialized to 0");
    }

    @Test
    void shouldIncrementScoreByOne() throws InvalidName {
        // GIVEN: A Player object is initialized with the name "John" and score 0
        Player player = new Player("John");

        // WHEN
        player.incrementScore();

        // THEN
        assertEquals(1, player.getScore(), "Score should be incremented by 1");
    }

    @Test
    void shouldThrowInvalidNameForEmptyString() {
        // GIVEN: A Player object will be created with an empty name, which is invalid
        String invalidName = "";

        // WHEN & THEN
        Exception exception = assertThrows(InvalidName.class, () -> new Player(invalidName),
                "Empty name should throw InvalidName exception");
        assertEquals("Name cannot be empty.", exception.getMessage());
    }

    @Test
    void shouldThrowInvalidNameForDigitsOnly() {
        // GIVEN: A Player object will be created with a name consisting only of digits, which is invalid
        String invalidName = "12345";

        // WHEN & THEN
        Exception exception = assertThrows(InvalidName.class, () -> new Player(invalidName),
                "Name with only digits should throw InvalidName exception");
        assertEquals("Name cannot consist solely of numbers.", exception.getMessage());
    }

    @Test
    void shouldThrowInvalidNameForNameWithSpaces() {
        // GIVEN: A Player object will be created with a name containing spaces, which is invalid
        String invalidName = "John Doe";

        // WHEN & THEN
        Exception exception = assertThrows(InvalidName.class, () -> new Player(invalidName),
                "Name with spaces should throw InvalidName exception");
        assertEquals("Name cannot contain spaces.", exception.getMessage());
    }

    @Test
    void shouldUpdatePlayerName() throws InvalidName {
        // GIVEN: A Player object is created with the name "John"
        Player player = new Player("John");

        // WHEN
        player.setName("Jane");

        // THEN
        assertEquals("Jane", player.getName(), "Player's name should be updated correctly");
    }

    @Test
    void shouldUpdatePlayerScore() throws InvalidName {
        // GIVEN: A Player object is created with the name "John" and score 0
        Player player = new Player("John");

        // WHEN
        player.setScore(50);

        // THEN
        assertEquals(50, player.getScore(), "Player's score should be updated correctly");
    }
}
