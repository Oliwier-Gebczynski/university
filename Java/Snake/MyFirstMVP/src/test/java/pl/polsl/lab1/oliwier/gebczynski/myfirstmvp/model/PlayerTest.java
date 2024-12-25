package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.InvalidName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Couple of tests for Player Class.
 */
class PlayerTest {

    /**
     * Verifies that a Player object is initialized correctly with a given name and an initial score of 0.
     *
     * @param name The player's name
     * @param initialScore The initial score of the player
     * @throws InvalidName If the provided name is invalid
     */
    @ParameterizedTest
    @CsvSource({"John, 0", "Alice, 0", "Bob, 0"})
    void shouldInitializePlayerCorrectly(String name, int initialScore) throws InvalidName {
        // GIVEN: A Player object will be created with a name and an initial score

        // WHEN
        Player player = new Player(name);

        // THEN
        assertEquals(name, player.getName(), "Player's name should be initialized correctly");
        assertEquals(initialScore, player.getScore(), "Player's score should be initialized to 0");
    }

    /**
     * Verifies that the score is incremented by 1 correctly.
     *
     * @throws InvalidName If the provided name is invalid
     */
    @Test
    void shouldIncrementScoreByOne() throws InvalidName {
        // GIVEN: A Player object is initialized with a name
        Player player = new Player("John");

        // WHEN
        player.incrementScore();

        // THEN
        assertEquals(1, player.getScore(), "Score should be incremented by 1");
    }

    /**
     * Verifies that an InvalidName exception is thrown for invalid player names.
     *
     * @param invalidName An invalid name input
     */
    @ParameterizedTest
    @CsvSource({" 12", "12 ", "12345", "John Doe"})
    void shouldThrowInvalidNameForInvalidInputs(String invalidName) {
        // GIVEN: A Player object will be created with an invalid name

        // WHEN & THEN
        Exception exception = assertThrows(InvalidName.class, () -> new Player(invalidName),
                "Invalid name should throw InvalidName exception");
        if (invalidName.isEmpty()) {
            assertEquals("Name cannot be empty.", exception.getMessage());
        } else if (invalidName.matches("\\d+")) {
            assertEquals("Name cannot consist solely of numbers.", exception.getMessage());
        } else {
            assertEquals("Name cannot contain spaces.", exception.getMessage());
        }
    }

    /**
     * Verifies that a Player's name can be updated correctly.
     *
     * @param initialName The initial name of the player
     * @param updatedName The updated name of the player
     * @throws InvalidName If the provided name is invalid
     */
    @ParameterizedTest
    @CsvSource({"John, Jane", "Alice, Bob"})
    void shouldUpdatePlayerName(String initialName, String updatedName) throws InvalidName {
        // GIVEN: A Player object is created with an initial name
        Player player = new Player(initialName);

        // WHEN
        player.setName(updatedName);

        // THEN
        assertEquals(updatedName, player.getName(), "Player's name should be updated correctly");
    }

    /**
     * Verifies that a Player's score can be updated correctly.
     *
     * @param initialScore The initial score of the player
     * @param updatedScore The updated score of the player
     * @throws InvalidName If the provided name is invalid
     */
    @ParameterizedTest
    @CsvSource({"0, 50", "10, 20", "0, 100"})
    void shouldUpdatePlayerScore(int initialScore, int updatedScore) throws InvalidName {
        // GIVEN: A Player object is created with an initial score
        Player player = new Player("John");
        player.setScore(initialScore);

        // WHEN
        player.setScore(updatedScore);

        // THEN
        assertEquals(updatedScore, player.getScore(), "Player's score should be updated correctly");
    }
}
