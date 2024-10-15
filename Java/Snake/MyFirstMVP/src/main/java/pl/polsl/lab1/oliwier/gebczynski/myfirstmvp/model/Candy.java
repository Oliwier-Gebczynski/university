package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

/**
 *
 * @author Oliwier Gebczynski
 */
public class Candy {

    String name = "Candy";
    int value = 1;

    //Getters
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    //Setters
    public void setName(String newName) {
        this.name = newName;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }

}
