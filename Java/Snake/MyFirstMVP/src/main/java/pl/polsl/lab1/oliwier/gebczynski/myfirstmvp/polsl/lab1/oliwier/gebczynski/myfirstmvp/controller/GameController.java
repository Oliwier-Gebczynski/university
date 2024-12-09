package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.view.GamePanel;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.view.StartPanel;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Player;

import javax.swing.*;


/**
 * This class controls the overall flow of the Snake game, acting as the primary controller in the Model-View-Controller (MVC) architecture.
 * @author Oliwier Gebczynski
 * @version 1.2
 */
public class GameController {
    private JFrame frame;
    private GamePanel gamePanel;
    private GameLogicController gameLogicController;
    private Player player;

    /**
     * Constructs a new GameController, initializes the main JFrame, and displays the start panel.
     */
    public GameController() {
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);

        StartPanel startPanel = new StartPanel(this);
        frame.add(startPanel);
        frame.setVisible(true);
    }

    /**
     * Starts the game with the given player name and snake color.
     *
     * @param playerName The name of the player.
     * @param snakeColor The color of the snake.
     */
    public void startGame(String playerName, String snakeColor) {
        try {
            this.player = new Player(playerName);

            gameLogicController = new GameLogicController(player, snakeColor);
            gamePanel = new GamePanel(gameLogicController);

            frame.getContentPane().removeAll();
            frame.getContentPane().add(gamePanel);
            frame.revalidate();
            frame.repaint();

            gameLogicController.startGame();

            gamePanel.requestFocusInWindow();
        } catch (InvalidName e) {
            JOptionPane.showMessageDialog(
                    frame,
                    e.getMessage(),
                    "Invalid Name",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new GameController();
    }
}


