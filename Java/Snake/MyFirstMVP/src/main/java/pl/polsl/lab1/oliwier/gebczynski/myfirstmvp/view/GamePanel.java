package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private boolean isGameRunning = false;
    private LinkedList<Point> snake;  // Lista segmentów węża
    private Point candy;              // Pozycja "candy"
    private Point direction;          // Kierunek poruszania się węża
    private static final int TILE_SIZE = 40;
    private static final int BOARD_WIDTH = 15;
    private static final int BOARD_HEIGHT = 15;

    public GamePanel() {
        setFocusable(true);
        setPreferredSize(new Dimension(BOARD_WIDTH * TILE_SIZE, BOARD_HEIGHT * TILE_SIZE));
        setBackground(Color.BLACK);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Dodanie KeyListener z KeyAdapter
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isGameRunning) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            if (direction.y == 0) direction = new Point(0, -1);  // Poruszanie się w górę
                            break;
                        case KeyEvent.VK_DOWN:
                            if (direction.y == 0) direction = new Point(0, 1);   // Poruszanie się w dół
                            break;
                        case KeyEvent.VK_LEFT:
                            if (direction.x == 0) direction = new Point(-1, 0);  // Poruszanie się w lewo
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction.x == 0) direction = new Point(1, 0);   // Poruszanie się w prawo
                            break;
                    }
                }
            }
        });

        // Inicjalizacja węża, cukierka i kierunku
        snake = new LinkedList<>();
        snake.add(new Point(BOARD_WIDTH / 2, BOARD_HEIGHT / 2));
        direction = new Point(1, 0);  // Domyślnie wąż porusza się w prawo
        spawnCandy();

        // Timer do odświeżania logiki gry
        timer = new Timer(200, this);  // Prędkość gry
    }

    private void spawnCandy() {
        Random random = new Random();
        boolean isValid = false;

        // Generowanie "candy" w losowym miejscu, które nie koliduje z wężem
        while (!isValid) {
            candy = new Point(random.nextInt(BOARD_WIDTH), random.nextInt(BOARD_HEIGHT));

            // Sprawdzanie, czy "candy" nie koliduje z ciałem węża
            isValid = true;
            for (Point p : snake) {
                if (p.equals(candy)) {
                    isValid = false;  // Jeśli "candy" koliduje z wężem, generuj ponownie
                    break;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isGameRunning) {
            return;
        }

        // Ustalamy marginesy
        int offsetX = 80;  // Przesunięcie w poziomie (odległość od lewej krawędzi)
        int offsetY = 80;  // Przesunięcie w pionie (odległość od górnej krawędzi)

        // Rysowanie pustych kratek z przesunięciem
        g.setColor(Color.WHITE);
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                g.drawRect(x * TILE_SIZE + offsetX, y * TILE_SIZE + offsetY, TILE_SIZE, TILE_SIZE);
            }
        }

        // Rysowanie węża z przesunięciem
        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x * TILE_SIZE + offsetX, p.y * TILE_SIZE + offsetY, TILE_SIZE, TILE_SIZE);
        }

        // Rysowanie "candy" z przesunięciem
        g.setColor(Color.RED);
        g.fillRect(candy.x * TILE_SIZE + offsetX, candy.y * TILE_SIZE + offsetY, TILE_SIZE, TILE_SIZE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameRunning) {
            // Aktualizacja pozycji węża
            Point head = snake.getFirst();
            Point newHead = new Point(head.x + direction.x, head.y + direction.y);

            // Sprawdzanie kolizji ze ścianami lub samym sobą
            if (newHead.x < 0 || newHead.x >= BOARD_WIDTH || newHead.y < 0 || newHead.y >= BOARD_HEIGHT || snake.contains(newHead)) {
                timer.stop();  // Zatrzymanie gry po kolizji
                JOptionPane.showMessageDialog(this, "Game Over!");
                return;
            }

            // Dodanie nowej głowy do węża
            snake.addFirst(newHead);

            // Sprawdzanie, czy wąż zjada "candy"
            if (newHead.equals(candy)) {
                spawnCandy();  // Wygenerowanie nowego "candy"
            } else {
                snake.removeLast();  // Usunięcie ostatniego segmentu, jeżeli wąż nie zjadł
            }

            repaint();
        }
    }

    public void startGame() {
        isGameRunning = true;
        timer.start();
        requestFocusInWindow();  // Zapewnia, że GamePanel uzyska fokus, aby odbierać zdarzenia klawiatury
    }

    public void stopGame() {
        isGameRunning = false;
        timer.stop();
    }
}