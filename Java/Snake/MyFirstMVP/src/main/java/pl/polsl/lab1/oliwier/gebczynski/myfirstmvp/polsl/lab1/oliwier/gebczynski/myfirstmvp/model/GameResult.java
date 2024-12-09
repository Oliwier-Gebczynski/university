package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

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
     * Constructs a new GameResult with the specified result.
     *
     * @param result The result of the game. Can be of any type, depending on the context (e.g., score, message, etc.).
     */
    public GameResult(T result) {
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
}

