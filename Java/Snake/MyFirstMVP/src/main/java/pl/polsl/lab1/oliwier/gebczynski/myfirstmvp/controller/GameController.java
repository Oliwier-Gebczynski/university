package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.view.GamePanel;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.view.StartPanel;

import javax.swing.*;

/**
 * Main controller class that manages the flow of the game.
 * Responsible for initializing the start screen and switching to the game screen when required.
 */
public class GameController {
    private JFrame frame;
    private GamePanel gamePanel;

    /**
     * Constructor initializes the main game window and sets up the start screen.
     */
    public GameController() {
        // Initialize the main game window
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);  // Center the window on the screen

        // Create an instance of GamePanel and StartPanel
        gamePanel = new GamePanel();
        StartPanel startPanel = new StartPanel(this);

        // Set the start screen as the initial view
        frame.add(startPanel);
        frame.setVisible(true);
    }

    /**
     * Method to start the game by switching to the GamePanel.
     */
    public void startGame() {
        // Switch to the GamePanel
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gamePanel);
        frame.revalidate();
        frame.repaint();

        // Start the game logic in GamePanel
        gamePanel.startGame();
    }

    /**
     * Main entry point of the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // Create an instance of GameController to start the application
        new GameController();
    }
}
