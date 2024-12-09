package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.view;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.GameController;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.InvalidName;


/**
 * The StartPanel class represents the starting screen of the game where the user can input their name,
 * choose a color, and start the game. This panel contains fields for user input, a start button, and handles
 * validation of the player's name.
 *
 * @author Oliwier Gebczynski
 * @version 1.2
 */
public class StartPanel extends JPanel {
    private JTextField nameField;
    private GameController controller;

    static Font TitleFont = new Font("Arial", Font.PLAIN, 100);
    static Font ButtonFont = new Font("Arial", Font.PLAIN, 40);
    static Font LabelFont = new Font("Arial", Font.PLAIN, 30);

    /**
     * Constructs the StartPanel with a given GameController and sets up all UI components,
     * including labels, text fields, and buttons.
     *
     * @param controller the GameController used to start the game
     */
    public StartPanel(GameController controller) {
        this.controller = controller;

        setLayout(null);
        setBackground(Color.BLACK);

        JLabel title = new JLabel("SNAKE");
        title.setFont(TitleFont);
        title.setForeground(Color.WHITE);
        title.setBounds(330, 100, 400, 100);
        add(title);

        JLabel setNameLabel = new JLabel("SET NAME: ");
        setNameLabel.setFont(LabelFont);
        setNameLabel.setForeground(Color.WHITE);
        setNameLabel.setBounds(390, 200, 400, 100);
        add(setNameLabel);

        nameField = new JTextField();
        nameField.setFont(LabelFont);
        nameField.setToolTipText("Enter the name");
        nameField.setBounds(300, 400, 400, 75);
        add(nameField);

        List<String> colorOptions = Arrays.asList("Green", "Blue", "Red", "Yellow");
        JComboBox<String> colorComboBox = new JComboBox<>(colorOptions.toArray(new String[0]));
        colorComboBox.setToolTipText("Enter the color");
        colorComboBox.setBounds(300, 500, 400, 75);
        add(colorComboBox);

        JButton startButton = new JButton("START");
        startButton.setFont(ButtonFont);
        startButton.setToolTipText("Press to start the game");
        startButton.getAccessibleContext().setAccessibleDescription("Press to start the game");
        startButton.setMnemonic(KeyEvent.VK_S);
        startButton.setBounds(350, 700, 300, 100);
        add(startButton);

        startButton.addActionListener(new ActionListener() {
            /**
             * This method is triggered when the start button is clicked.
             * It validates the player's name and starts the game.
             * If the name is invalid, it shows an error message.
             *
             * @param e The ActionEvent triggered by the button click.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InvalidName.validateName(nameField.getText());
                    String selectedColor = (String) colorComboBox.getSelectedItem();
                    controller.startGame(nameField.getText(), selectedColor);
                } catch (InvalidName ex) {
                    JOptionPane.showMessageDialog(
                            StartPanel.this,
                            ex.getMessage(),
                            "Invalid Name",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        InputMap inputMap = startButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = startButton.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("control S"), "startAction");
        actionMap.put("startAction", new AbstractAction() {
            /**
             * Maps the keyboard shortcut (Ctrl + S) to trigger the start action.
             * When Ctrl + S is pressed, the button click is simulated to start the game.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.doClick();
            }
        });
    }
}
