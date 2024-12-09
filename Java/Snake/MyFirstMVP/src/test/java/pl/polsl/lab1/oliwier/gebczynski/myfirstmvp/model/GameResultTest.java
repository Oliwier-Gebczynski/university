package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GameResultTest {

    @Test
    void shouldStoreAndReturnCorrectIntegerValue() {
        // GIVEN: A GameResult object will be created to store an Integer value of 100
        Integer resultValue = 100;

        // WHEN: Creating a GameResult object to store the Integer value
        GameResult<Integer> gameResult = new GameResult<>(resultValue);

        // THEN: Verify the Integer value is stored and returned correctly
        assertEquals(100, gameResult.getResult(), "GameResult should store and return the correct Integer value");
    }

    @Test
    void shouldStoreAndReturnCorrectStringValue() {
        // GIVEN: A GameResult object will be created to store a String value "Victory"
        String resultValue = "Victory";

        // WHEN: Creating a GameResult object to store the String value
        GameResult<String> gameResult = new GameResult<>(resultValue);

        // THEN: Verify the String value is stored and returned correctly
        assertEquals("Victory", gameResult.getResult(), "GameResult should store and return the correct String value");
    }

    @Test
    void shouldStoreAndReturnCorrectDoubleValue() {
        // GIVEN: A GameResult object will be created to store a Double value 99.9
        Double resultValue = 99.9;

        // WHEN: Creating a GameResult object to store the Double value
        GameResult<Double> gameResult = new GameResult<>(resultValue);

        // THEN: Verify the Double value is stored and returned correctly
        assertEquals(99.9, gameResult.getResult(), "GameResult should store and return the correct Double value");
    }

    @Test
    void shouldHandleNullValuesCorrectly() {
        // GIVEN: A GameResult object will be created to store a null value
        Object resultValue = null;

        // WHEN: Creating a GameResult object to store the null value
        GameResult<Object> gameResult = new GameResult<>(resultValue);

        // THEN: Verify that the null value is handled correctly
        assertNull(gameResult.getResult(), "GameResult should handle null values correctly");
    }

    @Test
    void shouldStoreAndReturnCustomObjectCorrectly() {
        // GIVEN: A GameResult object will be created to store a custom object of type CustomResult
        class CustomResult {
            private final String description;

            CustomResult(String description) {
                this.description = description;
            }

            public String getDescription() {
                return description;
            }
        }

        CustomResult customResult = new CustomResult("Custom outcome");

        // WHEN: Creating a GameResult object to store the custom object
        GameResult<CustomResult> gameResult = new GameResult<>(customResult);

        // THEN: Verify that the custom object is stored and returned correctly
        assertNotNull(gameResult.getResult(), "GameResult should store non-null custom objects");
        assertEquals("Custom outcome", gameResult.getResult().getDescription(),
                "GameResult should correctly store and return custom object values");
    }
}
