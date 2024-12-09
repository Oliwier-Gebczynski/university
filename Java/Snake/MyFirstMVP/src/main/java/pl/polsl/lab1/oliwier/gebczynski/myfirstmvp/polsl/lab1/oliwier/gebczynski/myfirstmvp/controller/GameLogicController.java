package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

import lombok.Getter;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Board;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Candy;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Snake;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Player;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.GameResult;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.annotations.NeedsOptimization;

import java.awt.*;
import java.awt.event.KeyEvent;



/**
 * This class handles the core game logic of the Snake game, including board management, snake movement,
 * collision detection, and game state updates.
 * @author Oliwier Gebczynski
 * @version 1.2
 */
public class GameLogicController {
    private Board board;
    private GameState gameState;
    private Player player;
    private Color snakeColor;
    @Getter
    private GameResult<String> gameResult;

    /**
     * Enum representing the possible game states.
     * It can have one of the following values:
     * - {@code RUNNING} - the game is ongoing,
     * - {@code PAUSED} - the game is paused,
     * - {@code GAME_OVER} - the game is over.
     *
     */
    public enum GameState {
        RUNNING,
        PAUSED,
        GAME_OVER
    }

    /**
     * A functional interface used to check if a point (segment) lies within a specified area.
     * This interface is used to compare points on the board in the context of checking their presence
     * within a rectangular region.
     */
    @FunctionalInterface
    public interface PointChecker {
        boolean isInArea(Point topLeft, Point bottomRight, Point segment);
    }

    /**
     * Constructs a new GameLogicController instance with the provided player and snake color.
     *
     * @param player The player object associated with the game.
     * @param snakeColorName A string representing the desired snake color (e.g., "Blue", "Red", "Yellow"). Defaults to green if not a valid color name.
     */
    public GameLogicController(Player player, String snakeColorName) {
        this.player = player;
        this.gameState = GameState.RUNNING;

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
        this.gameState = gameState.RUNNING;

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
        if (this.gameState == GameState.GAME_OVER) {
            return false;
        }

        Snake snake = board.getSnake();
        snake.move(snake.getDirection());

        if (isCollidingWithWall(snake) || isCollidingWithItself(snake)) {
            this.gameState = GameState.GAME_OVER;
            this.gameResult = new GameResult<>("Game Over! Final Score: " + player.getScore());
            return false;
        }

        if (isFoodEaten(snake)) {
            snake.grow(snake.getDirection());
            player.incrementScore();
            board.spawnCandy();
        }

        // variable number of parameters - usage
        //addExtraCandies(new Point(3,3), new Point(5,5));

        //For future
//        Point topLeft = new Point(3, 3);
//        Point bottomRight = new Point(6, 6);
//
//        int segmentsInArea = countSnakeSegmentsInArea(topLeft, bottomRight);
//        System.out.println("Number of snake segments in the area: " + segmentsInArea);
//
//        if (segmentsInArea > 5) {
//            System.out.println("The snake is too concentrated in this area!");
//        }

        // Lambda and stream usage
//        Point topLeft = new Point(3, 3);
//        Point bottomRight = new Point(6, 6);
//
//        boolean allSegmentsInArea = areAllSnakeSegmentsInArea(topLeft, bottomRight);
//
//        System.out.println("Are all snake segments in the area? " + allSegmentsInArea);
//
//        if (!allSegmentsInArea) {
//            System.out.println("Some snake segments are outside the area!");
//        }

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

        if (this.gameState == GameState.GAME_OVER) {
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
        g.fillRect(candy.x() * 50 + offset, candy.y() * 50 + offset, 50, 50);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + getScore(), 10, 30);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Player: " + getPlayerName(), 100, 30);
    }

    /**
     * Counts how many segments of the snake's body are within a specified rectangular area of the board.
     *
     * @param topLeft The top-left corner of the rectangular area.
     * @param bottomRight The bottom-right corner of the rectangular area.
     * @return The number of snake segments within the specified area.
     */
    @NeedsOptimization("Consider using streams for better performance.")
    public int countSnakeSegmentsInArea(Point topLeft, Point bottomRight) {
        int count = 0;
        for (Point segment : board.getSnake().getBody()) {
            if (segment.x >= topLeft.x && segment.x <= bottomRight.x &&
                    segment.y >= topLeft.y && segment.y <= bottomRight.y) {
                count++;
            }
        }
        return count;
    }

    /**
     * Adds extra candies to the game at specified points.
     * This method allows for the addition of multiple candies on the board by providing their coordinates as arguments.
     *
     * @param ExtraCandies An array of points where extra candies should be added.
     * @see Board#spawnExtraCandies(Point)
     */
    public void addExtraCandies(Point... ExtraCandies) {
        for (Point ExtraCandy : ExtraCandies) {
            board.spawnExtraCandies(ExtraCandy);
        }
    }

    /**
     * Checks if all snake segments are within a specified area.
     * This method uses the {@link PointChecker} interface to check whether each snake segment
     * is within the rectangular area defined by two points.
     *
     * @param topLeft The top-left corner of the rectangle defining the area.
     * @param bottomRight The bottom-right corner of the rectangle defining the area.
     * @return {@code true} if all snake segments are within the area, otherwise {@code false}.
     * @see PointChecker#isInArea(Point, Point, Point)
     */
    public boolean areAllSnakeSegmentsInArea(Point topLeft, Point bottomRight) {
        PointChecker checker = (tL, bR, segment) ->
                segment.x >= tL.x && segment.x <= bR.x && segment.y >= tL.y && segment.y <= bR.y;

        return board.getSnake().getBody().stream()
                .allMatch(segment -> checker.isInArea(topLeft, bottomRight, segment));
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
