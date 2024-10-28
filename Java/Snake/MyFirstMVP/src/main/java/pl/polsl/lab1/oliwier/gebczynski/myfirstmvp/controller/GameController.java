package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Player;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.view.TerminalTester;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.view.StartPanel;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.InvalidName;

import java.util.Scanner;

/**
 * Main controller class that manages the flow of the game.
 * Responsible for handling user input and creating a Player object.
 *
 * @author Oliwier Gebczynski
 * @version 1.0
 */
public class GameController {

    /**
     * The entry point of the application.
     * If no arguments are passed, the user's name is requested via console input.
     * Otherwise, the first argument is treated as the player's name.
     *
     * @param args the command-line arguments. The first argument should be the player's name.
     */
    public static void main(String args[]) {
        GameController game = new GameController();
        TerminalTester terminal = new TerminalTester();
        String name;
        Player player = null;

        if (args.length > 0) {
            name = args[0];
        } else {
            name = game.getNameFromUser();
        }

        try{
            player = new Player(name);
        } catch (InvalidName e) {
            terminal.display(e.getMessage());
            System.exit(1);
        }

        terminal.display(player.getName());
    }

    /**
     * Prompts the user to enter their name via console input.
     *
     * @return the name entered by the user
     */
    public String getNameFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.next();
        System.out.println("------------------------");

        return name;
    }
}
