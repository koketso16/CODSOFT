package org.koketjo;

import javax.swing.*;
import javax.swing.text.IconView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface extends UserAccount {
    private final JFrame frame;
    public ATMInterface() {

        UIManager.put("Panel.background", Color.CYAN);
        frame = new JFrame();
//        balanceField = new JTextField(15);

        JLabel welcome = new JLabel("                 Welcome to KayATM  ");
        welcome.setFont(new Font("Calibri", Font.BOLD, 20));
        welcome.setForeground(Color.WHITE);
        welcome.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel well = new JLabel("What would you like to do?");
        well.setFont(new Font("Calibri", Font.BOLD, 20));
        well.setForeground(Color.WHITE);
        well.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Calibri", Font.BOLD, 18));
        withdrawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        withdrawButton.setBackground(Color.blue);
        withdrawButton.setForeground(Color.WHITE);

        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Calibri", Font.BOLD, 18));
        depositButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        depositButton.setBackground(Color.blue);
        depositButton.setForeground(Color.WHITE);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setFont(new Font("Calibri", Font.BOLD, 18));
        checkBalanceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkBalanceButton.setBackground(Color.blue);
        checkBalanceButton.setForeground(Color.WHITE);

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Calibri", Font.BOLD, 18));
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setBackground(Color.blue);
        quitButton.setForeground(Color.WHITE);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitATM();
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBackground(Color.darkGray);
        panel.add(welcome);
        panel.add(well);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(checkBalanceButton);
        panel.add(quitButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void quitATM() {

        JLabel quitLabel = new JLabel("Thank you for using KayATM, Until next time!");
        quitLabel.setFont(new Font("Arial", Font.BOLD, 18));
        quitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JOptionPane.showMessageDialog(this,quitLabel,"Goodbye!",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void withdraw() {
        String amount = JOptionPane.showInputDialog(frame, "Enter Withdraw Amount:");
        JLabel label;

        if(Double.parseDouble(amount) > getBankBalance())
        {
            label = new JLabel("Withdrawal Declined! - Your balance is less than the required amount.");
            JOptionPane.showMessageDialog(this,label,"Declined",JOptionPane.INFORMATION_MESSAGE);

        } else
        {
            ImageIcon icon = new ImageIcon("src/main/ATMInterface/java/org/koketjo/money.jpg"); // Replace with the actual path to your image

            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
            ImageIcon resizedIcon = new ImageIcon(image);

            label = new JLabel(resizedIcon, SwingConstants.TRAILING);
            label.setText("Withdrawal Approved! - Please take your money.");
            JOptionPane.showMessageDialog(this,label,"Approved",JOptionPane.INFORMATION_MESSAGE);


            setBankBalance(getBankBalance() - Double.parseDouble(amount));
        }
    }

    private void deposit() {
        String amount = JOptionPane.showInputDialog(frame, "Enter Deposit Amount:");
        setBankBalance(getBankBalance() + Double.parseDouble(amount));
        JLabel label = new JLabel("R" + amount + " successfully deposited in your account.");
        JOptionPane.showMessageDialog(this,label,"Deposited",JOptionPane.INFORMATION_MESSAGE);
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(frame, "Current Balance: R " + getBankBalance(), "Balance", JOptionPane.INFORMATION_MESSAGE);
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






















