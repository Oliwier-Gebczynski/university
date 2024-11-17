package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Board;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Candy;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Snake;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Player;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * This class handles the core game logic of the Snake game, including board management, snake movement,
 * collision detection, and game state updates.
 * @author Oliwier Gebczynski
 * @version 1.1
 */
public class GameLogicController {
    private Board board;
    private boolean gameOver;
    private Player player;
    private Color snakeColor;

    /**
     * Constructs a new GameLogicController instance with the provided player and snake color.
     *
     * @param player The player object associated with the game.
     * @param snakeColorName A string representing the desired snake color (e.g., "Blue", "Red", "Yellow"). Defaults to green if not a valid color name.
     */
    public GameLogicController(Player player, String snakeColorName) {
        this.player = player;
        this.gameOver = false;

        switch (snakeColorName) {
            case "Blue":
                this.snakeColor = Color.BLUE;
                break;
            case "Red":
                this.snakeColor = Color.RED;
                break;
            case "Yellow":
                this.snakeColor = Color.YELLOW;
                break;
            default:
                this.snakeColor = Color.GREEN;
        }
    }

    /**
     * Initializes the game by creating a new board, setting the initial snake direction, and resetting game over state.
     */
    public void startGame() {
        board = new Board(15, 15);
        gameOver = false;

        Snake snake = board.getSnake();
        snake.setDirection(new Point(1, 0));
    }

    /**
     * Allows the player to change the snake's direction based on the provided key code.
     * Prevents the snake from moving backwards immediately.
     *
     * @param keyCode The key code representing the pressed arrow key.
     */
    public void changeDirection(int keyCode) {
        Snake snake = board.getSnake();
        Point currentDirection = snake.getDirection();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (!currentDirection.equals(new Point(0, 1))) {
                    snake.setDirection(new Point(0, -1)); // Move up
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!currentDirection.equals(new Point(0, -1))) {
                    snake.setDirection(new Point(0, 1)); // Move down
                }
                break;
            case KeyEvent.VK_LEFT:
                if (!currentDirection.equals(new Point(1, 0))) {
                    snake.setDirection(new Point(-1, 0)); // Move left
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!currentDirection.equals(new Point(-1, 0))) {
                    snake.setDirection(new Point(1, 0)); // Move right
                }
                break;
            default:
                break;
        }
    }

    /**
     * Updates the game state by moving the snake, checking for collisions, handling food consumption,
     * and incrementing the player's score. Returns true if the game is still ongoing, false otherwise.
     *
     * @return True if the game is not over, false otherwise.
     */
    public boolean updateGameState() {
        if (gameOver) {
            return false;
        }

        Snake snake = board.getSnake();
        snake.move(snake.getDirection());

        if (isCollidingWithWall(snake) || isCollidingWithItself(snake)) {
            gameOver = true;
            return false;
        }

        if (isFoodEaten(snake)) {
            snake.grow(snake.getDirection());
            player.incrementScore();
            board.spawnCandy();
        }

        return true;
    }

    /**
     * Checks if the snake is colliding with the walls of the game board.
     *
     * @param snake The snake object that needs to be checked for wall collisions.
     * @return True if the snake's head is outside the board boundaries, false otherwise.
     */
    private boolean isCollidingWithWall(Snake snake) {
        Point head = snake.getHead();
        int boardWidth = board.getWidth();
        int boardHeight = board.getHeight();
        return head.x < 0 || head.x >= boardWidth || head.y < 0 || head.y >= boardHeight;
    }

    /**
     * Checks if the snake is colliding with its own body.
     *
     * @param snake The snake object that needs to be checked for self-collision.
     * @return True if the snake's head is colliding with any part of its body, false otherwise.
     */
    private boolean isCollidingWithItself(Snake snake) {
        Point head = snake.getHead();
        return snake.getBody().stream().skip(1).anyMatch(part -> part.equals(head));
    }

    /**
     * Checks if the snake has eaten the candy.
     *
     * @param snake The snake object that needs to be checked for food consumption.
     * @return True if the snake's head is at the same position as the candy, false otherwise.
     */
    private boolean isFoodEaten(Snake snake) {
        return snake.getHead().equals(board.getCandy().getPosition());
    }

    /**
     * Renders the current game state (board, snake, candy, score, and game over message) onto the graphics object.
     * This method should be called in the paintComponent method of the view to draw the game scene.
     *
     * @param g The Graphics object used to render the game elements.
     */
    public void render(Graphics g) {
        int offset = 50;

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", 250, 300);
            return;
        }

        g.setColor(Color.WHITE);
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                g.drawRect(x * 50 + offset, y * 50 + offset, 50, 50);
            }
        }

        Snake snake = board.getSnake();
        g.setColor(snakeColor);
        for (Point p : snake.getBody()) {
            g.fillRect(p.x * 50 + offset, p.y * 50 + offset, 50, 50);
        }

        Candy candy = board.getCandy();
        g.setColor(Color.RED);
        g.fillRect(candy.getX() * 50 + offset, candy.getY() * 50 + offset, 50, 50);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + getScore(), 10, 30);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Player: " + getPlayerName(), 100, 30);
    }

    /**
     * Gets the current score of the player.
     *
     * @return The player's score.
     */
    public int getScore() {
        return player.getScore();
    }

    /**
     * Gets the name of the player.
     *
     * @return The player's name.
     */
    public String getPlayerName() {
        return player.getName();
    }
}
