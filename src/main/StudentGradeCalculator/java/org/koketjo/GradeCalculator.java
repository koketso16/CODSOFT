package org.koketjo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculator extends Component {
    private JFrame mainFrame;
    private int totalSubjects;
    private JTextField totalSubjectsField;
    private JButton submitTotalSubjectsButton;

    private JFrame inputFrame;
    private JTextField[] subjectNameFields;
    private JTextField[] subjectMarkFields;
    private JButton submitMarksButton;

    private JFrame resultFrame;
    private JButton okButton;

    public GradeCalculator() {
        // Main frame to get total subjects
        mainFrame = new JFrame("Welcome to the Grade Calculator!");
        totalSubjectsField = new JTextField(10);
        totalSubjectsField.setFont(new Font("Arial",Font.BOLD,18));
        submitTotalSubjectsButton = new JButton("Submit");
        submitTotalSubjectsButton.setFont(new Font("Arial",Font.BOLD,18));
        submitTotalSubjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalSubjects = Integer.parseInt(totalSubjectsField.getText());
                showInputFrame();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        JLabel label2 = new JLabel("Enter total subjects: ");
        label2.setFont(new Font("Arial",Font.BOLD,18));
        mainPanel.add(label2);
        mainPanel.add(totalSubjectsField);
        mainPanel.add(submitTotalSubjectsButton);

        mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        mainFrame.setSize(800, 130);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    private void showInputFrame() {
        // Input frame to get subject names and marks
        inputFrame = new JFrame("Subject Details");
        inputFrame.setSize(new Dimension(500,500));
        inputFrame.setLayout(new GridLayout(totalSubjects + 1, 3));

        subjectNameFields = new JTextField[totalSubjects];
        subjectMarkFields = new JTextField[totalSubjects];

        for (int i = 0; i < totalSubjects; i++) {
            inputFrame.add(new JLabel("Subject " + (i + 1) + " Name: "));
            subjectNameFields[i] = new JTextField(20);
            subjectNameFields[i].setFont(new Font("Arial",Font.BOLD,15));
            inputFrame.add(subjectNameFields[i]);

            inputFrame.add(new JLabel("Subject " + (i + 1) + " Mark: "));
            subjectMarkFields[i] = new JTextField(20);
            subjectMarkFields[i].setFont(new Font("Arial",Font.BOLD,15));
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

        inputFrame.setSize(800, totalSubjects * 80);
        inputFrame.setLocationRelativeTo(null);
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setVisible(true);
    }

    private void calculateAndShowResult() {
        inputFrame.dispose();
//        resultFrame.dispose();
        mainFrame.dispose();
        // Result frame to display average and overall grade
        resultFrame = new JFrame("Goodbye");
        Box resultBox = Box.createVerticalBox(); // Use Box to easily manage vertical alignment

        double totalMarks = 0;
        for (int i = 0; i < totalSubjects; i++) {
            String markText = subjectMarkFields[i].getText();
            double mark = Double.parseDouble(markText);
            totalMarks += mark;
        }

        double average = totalMarks / totalSubjects;
        String grade = checkGrade(average);

        JPanel customPanel = new JPanel();
        customPanel.setLayout(new BoxLayout(customPanel, BoxLayout.Y_AXIS));
        JLabel customLabel = new JLabel("Average mark: " + average);
        JLabel me = new JLabel( "\nOverall grade obtained: " + grade);
        customLabel.setFont(new Font("Arial", Font.BOLD, 15));
        customLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        me.setFont(new Font("Arial", Font.BOLD, 15));
        me.setAlignmentX(Component.CENTER_ALIGNMENT);
        customPanel.add(customLabel);
//        customPanel.add(me);

        // Show the custom JOptionPane
        JOptionPane.showMessageDialog(this, customPanel, "Final results", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(this, me, "Final results", JOptionPane.INFORMATION_MESSAGE);


        JLabel label1 = new JLabel("Thank you for using our Grade Calculator!");
        label1.setFont(new Font("Arial",Font.ITALIC | Font.BOLD,18));
        JLabel label2 = new JLabel( "\uD83D\uDCDA");

        JLabel label3 = new JLabel("Good luck with your academic endeavors!");
        label3.setFont(new Font("Arial",Font.ITALIC | Font.BOLD,18));
        JLabel label4 = new JLabel( "\uD83D\uDCDA");

        // Set alignment for labels
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        label4.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultBox.add(label1);
        resultBox.add(label2);
        resultBox.add(label3);
        resultBox.add(label4);
        resultBox.add(Box.createRigidArea(new Dimension(0, 10))); // Add some space between labels and button

        okButton = new JButton("OK");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultFrame.dispose();
                System.exit(0);
            }
        });

        resultBox.add(okButton);
        resultFrame.add(resultBox);

        resultFrame.setSize(500, 150);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultFrame.setVisible(true);
    }




    private static String checkGrade(double average)
    {

        if(average >= 90 && average <= 100)
        {
            return "A+";
        } else if(average >= 85 && average <= 89)
        {
            return "A";
        }else if(average >= 80 && average <= 84)
        {
            return "A-";
        }else if(average >= 75 && average <= 79)
        {
            return "B+";
        }else if(average >= 70 && average <= 74)
        {
            return "B";
        }else if(average >= 65 && average <= 69)
        {
            return "C+";
        }else if(average >= 60 && average <= 64)
        {
            return "C";
        }else if(average >= 55 && average <= 59)
        {
            return "D+";
        }else if(average >= 50 && average <= 54)
        {
            return "D";
        }else if(average >= 40 && average <= 49)
        {
            return "E";
        }else if(average <= 39)
        {
            return "F";
        }
        return "Error getting grade.";
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
