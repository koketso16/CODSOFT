package org.koketjo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGameGUI extends JFrame {
    private static int correctCount = 0;
    private static int rounds = 1;
    private static final Random random = new Random();
    private static int numberOfGuesses = getNumberOfGuesses();
    private static int randomNumber = generateNumber();
    private static boolean anotherRound = false;
    private final JLabel infoLabel;
    private final JTextField guessTextField;

    public NumberGameGUI() {

        setTitle("Number Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        infoLabel = new JLabel("Welcome to the Number Game!");
        infoLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        add(infoLabel, BorderLayout.NORTH);

        guessTextField = new JTextField();
        guessTextField.setFont(new Font("Georgia", Font.BOLD, 18));
        add(guessTextField, BorderLayout.CENTER);

        JButton guessButton = new JButton("Guess");
        JButton quitButton = new JButton("Quit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guessButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitGame();
            }
        });
    }

    private void handleGuess() {

        try {
            if (numberOfGuesses == 0) {
                displayOutOfGuessesMessage();
            }
            String guessText = guessTextField.getText();
            int guess = Integer.parseInt(guessText);


            if (guess == randomNumber) {
                correctGuessMessage();
                correctCount++;
            } else if (guess < randomNumber) {
                JOptionPane.showMessageDialog(this, "Incorrect, " + guess + " is too low.\nYou have " + numberOfGuesses + " attempts left!");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect, " + guess + " is too high.\nYou have " + numberOfGuesses + " attempts left!");
            }
        } catch (NumberFormatException ex) {

            if(anotherRound)
            {
                JOptionPane.showMessageDialog(this, "You are now on round : "+rounds+"\nYou have " + numberOfGuesses + " attempts left!");
                anotherRound = false;

            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.\nYou have " + numberOfGuesses + " attempts left!");
            }
        }
        numberOfGuesses--;
    }

    private void displayOutOfGuessesMessage() {
        JButton quitButton = new JButton("Quit");
        JButton anotherRoundButton = new JButton("Another round");

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitGame();
            }
        });

        anotherRoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anotherRound = true;
                Window window = SwingUtilities.getWindowAncestor((Component) e.getSource());
                window.dispose();


                if (rounds < 3) {

                    playGame();

                    numberOfGuesses = getNumberOfGuesses();
                    randomNumber = generateNumber();
                    rounds++;
                    infoLabel.setText("Guess a number between 1 and 100:");
                    guessTextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(NumberGameGUI.this, "You have played a full 3 rounds.");
                    quitGame();
                }
            }
        });

        Object[] options = {quitButton, anotherRoundButton};
        JOptionPane.showOptionDialog(this, "You have run out of guesses!", "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        guessTextField.setText("");
    }

    private void correctGuessMessage() {
        JButton quitButton = new JButton("Quit");
        JButton anotherRoundButton = new JButton("Another round");

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitGame();
            }
        });

        anotherRoundButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                anotherRound = true;
                Window window = SwingUtilities.getWindowAncestor((Component) e.getSource());
                window.dispose();

                if (rounds < 3) {

                    playGame();

                    numberOfGuesses = getNumberOfGuesses();
                    randomNumber = generateNumber();
                    rounds++;
                    infoLabel.setText("Guess a number between 1 and 100:");
                    guessTextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(NumberGameGUI.this, "You have played a full 3 rounds.");
                    quitGame();
                }
            }
        });

        Object[] options = {quitButton, anotherRoundButton};
        JOptionPane.showOptionDialog(this, "  Correct Guess!\n The number is : " + randomNumber, "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        guessTextField.setText("");
    }


    private void quitGame() {
        displayScore();
        System.exit(0);
    }

    private static int generateNumber() {
        return random.nextInt(100) + 1;
    }

    public static int getNumberOfGuesses()
    {
        return random.nextInt(6) + 5;
    }

    private void playGame() {
        infoLabel.setText("Guess a number between 1 and 100:");
        guessTextField.setText("");

    }

    private void displayScore() {
        JOptionPane.showMessageDialog(this, "Your final score: " + correctCount, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGameGUI gameGUI = new NumberGameGUI();
            gameGUI.playGame();
            gameGUI.setLocationRelativeTo(null);
            gameGUI.setVisible(true);
        });
    }
}
