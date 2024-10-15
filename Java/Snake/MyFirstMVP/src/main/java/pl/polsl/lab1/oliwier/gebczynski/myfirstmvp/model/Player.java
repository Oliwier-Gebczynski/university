package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

/**
 * Represents a player in the game.
 * Stores the player's name, score, and record.
 *
 * @author Oliwier Gebczynski
 * @version 1.0
 */
public class Player {

    private String name;
    private int score = 0;
    private int record = 0;

    /**
     * Constructor that creates a Player with a specified name.
     * Throws an InvalidName exception if the name is invalid.
     *
     * @param playerName the name of the player
     * @throws InvalidName if the player's name is invalid (null or empty)
     */
    public Player(String playerName) throws InvalidName {
        name = playerName;
    }

    //Getters

    /**
     * Gets the player's name.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's current score.
     *
     * @return the player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the player's highest score.
     *
     * @return the player's record
     */
    public int getRecord() {
        return record;
    }

    //Setters

    /**
     * Sets the player's name.
     * If the new name is null or empty, an InvalidName exception is thrown.
     *
     * @param newName the new name for the player
     * @throws InvalidName if the new name is null or empty
     */
    public void setName(String newName) throws InvalidName {
        if (newName == null || newName.trim().isEmpty() ) {
            throw new InvalidName("The player's name cannot be null or empty!");
        }

        this.name = newName;
    }

    /**
     * Sets the player's current score.
     *
     * @param newScore the new score for the player
     */
    public void setScore(int newScore) {
        this.score = newScore;
    }

    /**
     * Sets the player's highest score (record).
     *
     * @param newRecord the new record for the player
     */
    public void setRecord(int newRecord) {
        this.record = newRecord;
    }
}
