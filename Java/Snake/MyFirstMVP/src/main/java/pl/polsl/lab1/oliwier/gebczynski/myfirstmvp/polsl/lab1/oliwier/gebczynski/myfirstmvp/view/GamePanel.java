package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.view;

import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.GameLogicController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The GamePanel class represents the graphical user interface (GUI) panel where the Snake game is displayed.
 * It is responsible for rendering the game, handling keyboard input, and showing the game over dialog.
 * The panel interacts with the GameLogicController to update the game state and display changes.
 *
 * @author Oliwier Gebczynski
 * @version 1.2
 */
public class GamePanel extends JPanel {
    private GameLogicController gameLogic;
    private JDialog gameOverDialog;

    /**
     * Constructs a GamePanel instance with the given GameLogicController.
     * Initializes the panel layout, keyboard input listener, and game update timer.
     *
     * @param controller The GameLogicController that handles game state updates and logic
     */
    public GamePanel(GameLogicController controller) {
        this.gameLogic = controller;
        setLayout(new BorderLayout());

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            /**
             * This method is called when a key is pressed. It updates the snake's direction
             * based on the key code of the pressed key.
             *
             * @param e The KeyEvent object containing information about the pressed key
             */
            @Override
            public void keyPressed(KeyEvent e) {
                gameLogic.changeDirection(e.getKeyCode());
            }
        });

        new Timer(150, e -> {
            if (gameLogic.updateGameState()) {
                repaint();
            } else {
                showGameOverDialog();
            }
        }).start();

        setBackground(Color.BLACK);
    }

    /**
     * Paints the game components on the panel. This method is called by the repaint() method
     * to render the updated game state.
     *
     * @param g The Graphics object used to draw the game components
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameLogic.render(g);
    }

    /**
     * Displays a game over dialog when the game ends. The dialog allows the player to
     * start a new game or exit the application.
     */
    private void showGameOverDialog() {
        if (gameOverDialog == null) {
            gameOverDialog = new JDialog((Frame) null, "Game Over", true);
            gameOverDialog.setLayout(new BorderLayout());
            gameOverDialog.setSize(300, 200);

            String[][] data = {
                    {"Oliwier", "Gębczyński"},
                    {"Anna", "Kowalska"},
                    {"Jan", "Nowak"},
                    {"Marta", "Zielińska"},
                    {"Piotr", "Wiśniewski"},
                    {"Katarzyna", "Jankowska"},
                    {"Tomasz", "Mazur"},
                    {"Piotr", "Wiśniewski"},
                    {"Katarzyna", "Jankowska"},
                    {"Tomasz", "Mazur"}
            };
            String[] columnNames = {"Game Over", gameLogic.getGameResult().getResult()};

            JTable authorTable = new JTable(data, columnNames);
            authorTable.setEnabled(false);

            JScrollPane scrollPane = new JScrollPane(authorTable);
            scrollPane.setPreferredSize(new Dimension(300, 100));

            gameOverDialog.add(scrollPane, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            JButton newGameButton = new JButton("New Game");
            newGameButton.setToolTipText("Press to start new game");
            newGameButton.getAccessibleContext().setAccessibleDescription("Press to start new game");
            newGameButton.addActionListener(e -> {
                gameLogic.startGame();
                gameOverDialog.setVisible(false);
            });

            JButton exitButton = new JButton("Exit");
            exitButton.setToolTipText("Press to exit the game");
            exitButton.getAccessibleContext().setAccessibleDescription("Press to exit the game");
            exitButton.addActionListener(e -> System.exit(0));

            buttonPanel.add(newGameButton);
            buttonPanel.add(exitButton);

            gameOverDialog.add(buttonPanel, BorderLayout.SOUTH);

            InputMap inputMap = gameOverDialog.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = gameOverDialog.getRootPane().getActionMap();

            inputMap.put(KeyStroke.getKeyStroke("control N"), "newGameAction");
            actionMap.put("newGameAction", new AbstractAction() {
                /**
                 * Maps the keyboard shortcut (Ctrl + N) to trigger the start new game action.
                 * When Ctrl + S is pressed, the button click is simulated to start the game.
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    newGameButton.doClick();
                }
            });

            inputMap.put(KeyStroke.getKeyStroke("control E"), "exitGameAction");
            actionMap.put("exitGameAction", new AbstractAction() {
                /**
                 * Maps the keyboard shortcut (Ctrl + E) to trigger the exit action.
                 * When Ctrl + S is pressed, the button click is simulated to exit the game.
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    exitButton.doClick();
                }
            });
        }

        gameOverDialog.setLocationRelativeTo(null);
        gameOverDialog.setVisible(true);
    }
}
