package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.view;

import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.GameController;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.GameLogicController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private boolean isGameRunning = false;
    private GameController controller;
    private GameLogicController gameLogicController;

    public GamePanel(GameController controller, GameLogicController gameLogicController) {
        this.controller = controller;
        this.gameLogicController = gameLogicController;
        setFocusable(true);
        setPreferredSize(new Dimension(15 * 40, 15 * 40));  // 15x15 grid with 40px tiles
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isGameRunning) {
                    controller.changeDirection(e.getKeyCode());
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isGameRunning) {
            gameLogicController.render(g);  // Directly call render method from GameLogicController
        }
    }

    public void startGame() {
        isGameRunning = true;
        new Thread(() -> {
            while (isGameRunning) {
                if (!gameLogicController.updateGameState()) {
                    isGameRunning = false;  // Stop the game if game state is invalid (collision)
                }
                repaint();  // Trigger repaint for game graphics
                try {
                    Thread.sleep(100);  // Control game speed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stopGame() {
        isGameRunning = false;
    }
}
