package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.view;

/**
 * A simple class for displaying output in the console.
 * This class represents the "view" in the MVC pattern.
 *
 * @author Oliwier Gebczynski
 * @version 1.2
 */
public class TerminalTester {

    /**
     * Displays the specified value on the console.
     *
     * @param value the string to be displayed
     */
    public void display(String value) {
        System.out.println("Your name: ");
        System.out.println(value);
    }
}
