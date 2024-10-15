package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Player;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.view.TerminalTester;

/**
 *
 * @author Oliwier Gebczynski
 */
public class GameController {

    public static void main(String args[]) {
        TerminalTester terminal = new TerminalTester();

        terminal.getNameFromUser();
        String name = terminal.getName();
        Player player = new Player(name);

        terminal.display(player.getName());
    }
}
