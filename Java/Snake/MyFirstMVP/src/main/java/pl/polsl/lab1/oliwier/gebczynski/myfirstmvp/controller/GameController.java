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
    private GameLogicController gameLogicController;

    /**
     * Constructor initializes the main game window and sets up the start screen.
     */
    public GameController() {
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);

        gameLogicController = new GameLogicController();
        gamePanel = new GamePanel(this, gameLogicController);

        StartPanel startPanel = new StartPanel(this);  // Poprawny import
        frame.add(startPanel);
        frame.setVisible(true);
    }

    /**
     * Method to start the game by switching to the GamePanel.
     */
    public void startGame() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gamePanel);
        frame.revalidate();
        frame.repaint();

        gamePanel.startGame();
    }

    /**
     * Method to change the direction of the snake.
     */
    public void changeDirection(int keyCode) {
        gameLogicController.changeDirection(keyCode);
    }

    /**
     * Main entry point of the application.
     */
    public static void main(String[] args) {
        new GameController();
    }
}
