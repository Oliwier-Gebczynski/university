package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.view;

import java.util.Scanner;

/**
 *
 * @author Oliwier Gebczynski
 */
public class TerminalTester {

    String name;

    public void display(String value) {
        System.out.println("Your name: ");
        System.out.println(value);
    }

    public void getNameFromUser() { //controller
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        name = scanner.next();
        System.out.println("------------------------");
    }

    //Getters
    public String getName() {
        return name;
    }
}
