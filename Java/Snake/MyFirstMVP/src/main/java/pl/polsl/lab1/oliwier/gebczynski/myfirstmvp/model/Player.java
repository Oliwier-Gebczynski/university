package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.InvalidName;

/**
 * The Player class represents a player in the Snake game.
 * It stores the player's name and score, and provides methods to retrieve and update this information.
 *
 * @author Oliwier Gebczynski
 * @version 1.1
 */
@Getter
@Setter
public class Player {
    private String name;
    private int score;

    /**
     * Constructs a new Player with the specified name.
     * The score is initialized to 0.
     *
     * @param name the name of the player
     * @throws InvalidName if the provided name is invalid (e.g., empty, contains spaces, or is only digits)
     */
    public Player(String name) throws InvalidName {
        this.name = name;
        this.score = 0;
    }

    /**
     * Increments the player's score by 1.
     * This method is called whenever the player eats candy.
     */
    public void incrementScore() {
        score++;
    }
}
