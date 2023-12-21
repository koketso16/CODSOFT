package org.koketjo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface {
    private JFrame frame;
    private JTextField balanceField;

    public ATMInterface() {
        frame = new JFrame("ATM Interface");
        balanceField = new JTextField(15);
        balanceField.setEditable(false);

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("Balance:"));
        panel.add(balanceField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(checkBalanceButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void handleWithdraw() {
        String withdrawAmount = JOptionPane.showInputDialog(frame, "Enter Withdraw Amount:");
        if (withdrawAmount != null && !withdrawAmount.isEmpty()) {
            double amount = Double.parseDouble(withdrawAmount);
            updateBalance(-amount);
        }
    }

    private void handleDeposit() {
        String depositAmount = JOptionPane.showInputDialog(frame, "Enter Deposit Amount:");
        if (depositAmount != null && !depositAmount.isEmpty()) {
            double amount = Double.parseDouble(depositAmount);
            updateBalance(amount);
        }
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(frame, "Current Balance: $" + balanceField.getText(), "Balance", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateBalance(double amount) {
        double currentBalance = Double.parseDouble(balanceField.getText());
        currentBalance += amount;
        balanceField.setText(String.format("%.2f", currentBalance));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMInterface();
            }
        });
    }
}
