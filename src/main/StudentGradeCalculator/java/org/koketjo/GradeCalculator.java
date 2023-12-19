package org.koketjo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculator {
    private JFrame mainFrame;
    private int totalSubjects;
    private JTextField totalSubjectsField;
    private JButton submitTotalSubjectsButton;

    private JFrame inputFrame;
    private JTextField[] subjectNameFields;
    private JTextField[] subjectMarkFields;
    private JButton submitMarksButton;

    private JFrame resultFrame;
    private JLabel averageLabel;
    private JButton okButton;

    public GradeCalculator() {
        // Main frame to get total subjects
        mainFrame = new JFrame("Grade Calculator");
        totalSubjectsField = new JTextField(10);
        submitTotalSubjectsButton = new JButton("Submit");
        submitTotalSubjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalSubjects = Integer.parseInt(totalSubjectsField.getText());
                showInputFrame();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(new JLabel("Enter total subjects: "));
        mainPanel.add(totalSubjectsField);
        mainPanel.add(submitTotalSubjectsButton);

        mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        mainFrame.setSize(300, 100);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    private void showInputFrame() {
        // Input frame to get subject names and marks
        inputFrame = new JFrame("Enter Subject Details");
        inputFrame.setLayout(new GridLayout(totalSubjects + 1, 3));

        subjectNameFields = new JTextField[totalSubjects];
        subjectMarkFields = new JTextField[totalSubjects];

        for (int i = 0; i < totalSubjects; i++) {
            inputFrame.add(new JLabel("Subject " + (i + 1) + " Name: "));
            subjectNameFields[i] = new JTextField(10);
            inputFrame.add(subjectNameFields[i]);

            inputFrame.add(new JLabel("Subject " + (i + 1) + " Mark: "));
            subjectMarkFields[i] = new JTextField(10);
            inputFrame.add(subjectMarkFields[i]);
        }

        submitMarksButton = new JButton("Submit");
        submitMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndShowResult();
            }
        });

        inputFrame.add(new JLabel(""));
        inputFrame.add(new JLabel(""));
        inputFrame.add(submitMarksButton);

        inputFrame.setSize(400, totalSubjects * 60);
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setVisible(true);
    }

    private void calculateAndShowResult() {
        // Result frame to display average
        resultFrame = new JFrame("Result");
        resultFrame.setLayout(new FlowLayout());

        double totalMarks = 0;
        for (int i = 0; i < totalSubjects; i++) {
            String markText = subjectMarkFields[i].getText();
            double mark = Double.parseDouble(markText);
            totalMarks += mark;
        }

        double average = totalMarks / totalSubjects;
        averageLabel = new JLabel("Average mark: " + average);

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        resultFrame.add(averageLabel);
        resultFrame.add(okButton);

        resultFrame.setSize(300, 100);
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculator();
            }
        });
    }
}
