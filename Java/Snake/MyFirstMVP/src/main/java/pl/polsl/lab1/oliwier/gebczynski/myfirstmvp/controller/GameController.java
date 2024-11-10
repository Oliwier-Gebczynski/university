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

    public GameController() {
        // Tworzenie głównego okna gry
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);  // Dopasowanie rozmiaru okna
        frame.setLocationRelativeTo(null);  // Ustawienie okna na środku ekranu

        // Tworzenie StartPanel (jeśli jest taki panel startowy)
        StartPanel startPanel = new StartPanel(this);
        frame.add(startPanel);
        frame.setVisible(true);
    }

    public void startGame() {
        // Inicjalizacja kontrolera logiki gry
        gameLogicController = new GameLogicController();
        gamePanel = new GamePanel(gameLogicController);

        // Przełączanie na widok gry
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gamePanel);
        frame.revalidate();
        frame.repaint();

        // Rozpoczęcie gry
        gameLogicController.startGame();

        // Ustawienie fokusowania na GamePanel, aby natychmiast odbierał zdarzenia klawiatury
        gamePanel.requestFocusInWindow();  // Zapewni to, że panel odbiera naciśnięcia klawiszy
    }

    public static void main(String[] args) {
        new GameController();  // Uruchomienie gry
    }
}


