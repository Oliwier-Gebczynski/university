package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.GameController;

/**
 */
public class StartPanel extends JPanel {
    private JTextField nameField;
    private GameController controller;

    static Font TitleFont = new Font("Arial", Font.PLAIN, 100);
    static Font ButtonFont = new Font("Arial", Font.PLAIN, 40);
    static Font LabelFont = new Font("Arial", Font.PLAIN, 30);

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

        JButton startButton = new JButton("START");
        startButton.setFont(ButtonFont);
        startButton.setMnemonic(KeyEvent.VK_S);
        startButton.setBounds(350, 700, 300, 100);
        add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText();
                if (playerName.isEmpty()) {
                    JOptionPane.showMessageDialog(StartPanel.this, "Please enter your name to start the game.");
                    return;
                }

                controller.startGame();
            }
        });

        InputMap inputMap = startButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = startButton.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("control S"), "startAction");
        actionMap.put("startAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.doClick();
            }
        });
    }
}