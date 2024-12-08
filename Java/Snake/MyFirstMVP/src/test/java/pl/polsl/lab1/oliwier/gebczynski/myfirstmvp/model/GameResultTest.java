package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameResultTest {

    @Test
    void testGameResultWithInteger() {
        GameResult<Integer> gameResult = new GameResult<>(100);

        assertEquals(100, gameResult.getResult(), "GameResult should store and return the correct Integer value");
    }

    @Test
    void testGameResultWithString() {
        GameResult<String> gameResult = new GameResult<>("Victory");

        assertEquals("Victory", gameResult.getResult(), "GameResult should store and return the correct String value");
    }

    @Test
    void testGameResultWithDouble() {
        GameResult<Double> gameResult = new GameResult<>(99.9);

        assertEquals(99.9, gameResult.getResult(), "GameResult should store and return the correct Double value");
    }

    @Test
    void testGameResultWithNull() {
        GameResult<Object> gameResult = new GameResult<>(null);

        assertNull(gameResult.getResult(), "GameResult should handle null values correctly");
    }

    @Test
    void testGameResultWithCustomObject() {
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
        GameResult<CustomResult> gameResult = new GameResult<>(customResult);

        assertNotNull(gameResult.getResult(), "GameResult should store non-null custom objects");
        assertEquals("Custom outcome", gameResult.getResult().getDescription(),
                "GameResult should correctly store and return custom object values");
    }
}
