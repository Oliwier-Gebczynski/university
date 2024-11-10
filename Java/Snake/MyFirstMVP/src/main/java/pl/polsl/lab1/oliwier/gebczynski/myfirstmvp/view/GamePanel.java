package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.view;

import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.GameLogicController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private GameLogicController gameLogic;
    private JDialog gameOverDialog;

    public GamePanel(GameLogicController controller) {
        this.gameLogic = controller;
        setLayout(new BorderLayout());

        // Ustawienia panelu gry
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameLogic.changeDirection(e.getKeyCode());
            }
        });

        // Timer do aktualizacji stanu gry co 300 ms
        new Timer(175, e -> {
            if (gameLogic.updateGameState()) {
                repaint();  // Odświeżenie widoku
            } else {
                showGameOverDialog();  // Pokazanie dialogu z opcjami
            }
        }).start();

        setBackground(Color.BLACK);  // Tło na czarne
    }

    public void startGame() {
        gameLogic.startGame();  // Uruchomienie gry
        requestFocusInWindow();  // Wymuszenie skupienia na panelu, aby natychmiast odbierał naciśnięcia klawiszy
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameLogic.render(g);  // Rysowanie stanu gry
    }

    private void showGameOverDialog() {
        if (gameOverDialog == null) {
            gameOverDialog = new JDialog((Frame) null, "Game Over", true);
            gameOverDialog.setLayout(new FlowLayout());
            gameOverDialog.setSize(250, 150);

            JButton newGameButton = new JButton("Nowa Gra");
            newGameButton.addActionListener(e -> {
                gameLogic.startGame();
                gameOverDialog.setVisible(false);
            });

            JButton exitButton = new JButton("Zakończ");
            exitButton.addActionListener(e -> System.exit(0));  // Zakończenie gry

            gameOverDialog.add(newGameButton);
            gameOverDialog.add(exitButton);
        }
        gameOverDialog.setVisible(true);
    }
}
