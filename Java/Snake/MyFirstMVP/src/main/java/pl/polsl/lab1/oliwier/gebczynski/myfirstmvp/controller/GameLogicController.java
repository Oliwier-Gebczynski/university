package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller;

import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Board;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Candy;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model.Snake;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.view.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Kontroler odpowiedzialny za logikę gry, zarządzający stanem gry, kolizjami, wynikiem itp.
 */
public class GameLogicController {
    private Board board;
    private boolean gameOver;
    private int score;

    public GameLogicController() {
        this.gameOver = false;
        this.score = 0;
    }

    public void startGame() {
        board = new Board(15, 15);  // Resetowanie planszy i węża
        score = 0;
        gameOver = false;

        // Ustawienie początkowego kierunku węża (np. w prawo)
        Snake snake = board.getSnake();
        snake.setDirection(new Point(1, 0));  // Domyślny kierunek w prawo
    }

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

    public boolean updateGameState() {
        if (gameOver) {
            return false;
        }

        Snake snake = board.getSnake();
        snake.move(snake.getDirection());

        if (isCollidingWithWall(snake) || isCollidingWithItself(snake)) {
            gameOver = true;
            return false;  // Gra zakończona
        }

        if (isFoodEaten(snake)) {
            snake.grow(snake.getDirection());
            score++;  // Zwiększ wynik
            board.spawnCandy();  // Generowanie nowego jedzenia
        }

        return true;  // Gra nadal trwa
    }

    private boolean isCollidingWithWall(Snake snake) {
        Point head = snake.getHead();
        int boardWidth = board.getWidth();
        int boardHeight = board.getHeight();
        return head.x < 0 || head.x >= boardWidth || head.y < 0 || head.y >= boardHeight;
    }

    private boolean isCollidingWithItself(Snake snake) {
        Point head = snake.getHead();
        return snake.getBody().stream().skip(1).anyMatch(part -> part.equals(head));
    }

    private boolean isFoodEaten(Snake snake) {
        return snake.getHead().equals(board.getCandy().getPosition());
    }

    public void render(Graphics g) {
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", 250, 300);  // Pozycja komunikatu "Game Over"
            return;
        }

        // Rysowanie tła planszy (kratki)
        g.setColor(Color.WHITE);  // Kolor tła (kratek) na biały
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                g.drawRect(x * 50, y * 50, 50, 50);  // Rysowanie pustych białych kratek (tylko obramowanie)
            }
        }

        // Rysowanie węża
        Snake snake = board.getSnake();
        g.setColor(Color.GREEN);
        for (Point p : snake.getBody()) {
            g.fillRect(p.x * 50, p.y * 50, 50, 50);  // Zwiększenie rozmiaru kratek
        }

        // Rysowanie jedzenia
        Candy candy = board.getCandy();
        g.setColor(Color.RED);
        g.fillRect(candy.getX() * 50, candy.getY() * 50, 50, 50);  // Zwiększenie rozmiaru jedzenia

        // Rysowanie wyniku
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + score, 10, 30);
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
