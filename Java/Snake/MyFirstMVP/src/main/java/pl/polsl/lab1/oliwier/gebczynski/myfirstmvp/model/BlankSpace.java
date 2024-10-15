package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

/**
 * Represents a blank space on the game board.
 * This is used to denote empty areas of the board where no game elements are present.
 *
 * @author Oliwier Gebczynski
 * @version 1.0
 */
public class BlankSpace {

    private String type = "Blank";

    /**
     * Gets the type of this object.
     *
     * @return the type of this object
     */
    public String getType() {
        return type;
    }
}
