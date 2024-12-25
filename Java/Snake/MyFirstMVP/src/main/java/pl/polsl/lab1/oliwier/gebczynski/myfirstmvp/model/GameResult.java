package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.InvalidGameResult;

/**
 * The GameResult class represents the result of a game.
 * It holds a result of any type, providing a generic way to store game outcomes.
 *
 * @author Oliwier Gebczynski
 * @version 1.2
 * @param <T> The type of the result (e.g., String, Integer, etc.).
 */
public class GameResult<T> {
    private T result;

    /**
     * Constructs a GameResult with the specified value.
     *
     * @param result the result to store
     * @throws InvalidGameResult if the result is null or an invalid type
     */
    public GameResult(T result) throws InvalidGameResult {
        validate(result);
        this.result = result;
    }

    /**
     * Gets the result of the game.
     *
     * @return The result of the game, which can be of any type defined when creating the GameResult object.
     */
    public T getResult() {
        return result;
    }

    /**
     * Validates the result. Throws InvalidGameResultException if the result is null or of an unsupported type.
     *
     * @param result the result to validate
     * @throws InvalidGameResult if the result is null or of an invalid type
     */
    private void validate(T result) throws InvalidGameResult {

        if (result == null) {
            throw new InvalidGameResult("Result cannot be null.");
        }
        if (!(result instanceof Integer || result instanceof String || result instanceof Double)) {
            throw new InvalidGameResult("Unsupported type: ");
        }
    }
}

