package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.InvalidGameResult;

/**
 * Couple of tests for GameResult Class.
 */
class GameResultTest {

    /**
     * Verifies that a GameResult object correctly stores and returns an Integer value.
     *
     * @param value An integer value
     */
    @ParameterizedTest
    @CsvSource({"100", "-50", "0"})
    void shouldStoreAndReturnCorrectIntegerValue(int value) {
        // GIVEN: A GameResult object with an Integer value
        GameResult<Integer> gameResult = new GameResult<>(value);

        // WHEN: Getting the result
        Integer result = gameResult.getResult();

        // THEN: Verify the result is correct
        assertEquals(value, result, "GameResult should store and return the correct Integer value");
    }

    /**
     * Verifies that a GameResult object correctly stores and returns a String value.
     *
     * @param value A string value
     */
    @ParameterizedTest
    @CsvSource({"Victory", "Defeat", "Draw"})
    void shouldStoreAndReturnCorrectStringValue(String value) {
        // GIVEN: A GameResult object with a String value
        GameResult<String> gameResult = new GameResult<>(value);

        // WHEN: Getting the result
        String result = gameResult.getResult();

        // THEN: Verify the result is correct
        assertEquals(value, result, "GameResult should store and return the correct String value");
    }

    /**
     * Verifies that a GameResult object correctly stores and returns a Double value.
     *
     * @param value A double value
     */
    @ParameterizedTest
    @CsvSource({"99.9", "-99.9", "0.0"})
    void shouldStoreAndReturnCorrectDoubleValue(double value) {
        // GIVEN: A GameResult object with a Double value
        GameResult<Double> gameResult = new GameResult<>(value);

        // WHEN: Getting the result
        Double result = gameResult.getResult();

        // THEN: Verify the result is correct
        assertEquals(value, result, "GameResult should store and return the correct Double value");
    }

    /**
     * Verifies that a GameResult object correctly handles null values.
     */
    @Test
    void shouldHandleNullValuesCorrectly() {
        // GIVEN: A null value for GameResult
        Throwable thrown = assertThrows(InvalidGameResult.class, () -> new GameResult<>(null));

        // THEN: Verify that the null value is handled correctly
        assertEquals("Result cannot be null.", thrown.getMessage(), "GameResult should handle null values correctly");
    }

    /**
     * Verifies that a GameResult object correctly handles unsupported types.
     */
    @Test
    void shouldHandleUnsupportedTypesCorrectly() {
        // GIVEN: An unsupported type
        class UnsupportedType {}
        Throwable thrown = assertThrows(InvalidGameResult.class, () -> new GameResult<>(new UnsupportedType()));

        // THEN: Verify that unsupported types are handled correctly
        assertEquals("Unsupported type: ", thrown.getMessage(), "GameResult should handle unsupported types correctly");
    }
}
