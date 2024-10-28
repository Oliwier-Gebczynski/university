package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public abstract class StartPanel extends JPanel implements ActionListener {
    static Font TitleFont = new Font("Arial", Font.PLAIN, 100);
    static Font ButtonFont = new Font("Arial", Font.PLAIN, 40);
    static Font LabelFont = new Font("Arial", Font.PLAIN, 40);

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setLayout(null);
        frame.setVisible(true);

        Label title = new Label("SNAKE");
        title.setFont(TitleFont);
        title.setBounds(330, 100, 400, 100);
        frame.add(title);

        Label setNameLabel = new Label("SET NAME: ");
        setNameLabel.setFont(LabelFont);
        setNameLabel.setBounds(390, 200, 400, 100);
        frame.add(setNameLabel);

        JTextField nameField = new JTextField();
        nameField.setFont(LabelFont);
        nameField.setToolTipText("Enter the name");
        nameField.setBounds(300, 400, 400, 75);
        frame.add(nameField);

        JButton startButton = new JButton("START");
        startButton.setMnemonic(KeyEvent.VK_S);
        startButton.setFont(ButtonFont);
        startButton.setBounds(350, 700, 300, 100);
        frame.add(startButton);

        ActionListener startAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                System.out.println("Entered Name: " + name);
            }
        };

        startButton.addActionListener(startAction);

        InputMap inputMap = startButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = startButton.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("control S"), "startAction");
        actionMap.put("startAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.doClick();
            }
        });

        frame.repaint();
        frame.revalidate();
    }
}

