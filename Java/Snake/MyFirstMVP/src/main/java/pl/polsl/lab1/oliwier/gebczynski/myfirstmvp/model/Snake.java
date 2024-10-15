package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

/**
 *
 * @author Oliwier Gebczynski
 */
public class Snake {

    String name = "Snake";
    int length = 1;

    //Getters
    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    //Setters
    public void setName(String newName) {
        this.name = newName;
    }

    public void setLength(int newLength) {
        this.length = newLength;
    }

}
