package org.koketjo;

import javax.swing.*;
import java.awt.*;
public class GradeTable extends JFrame {

    private static final StudentGradeCalculator grades = new StudentGradeCalculator();
    public GradeTable() {
        setTitle("Grades Table");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel tablePanel = new JPanel(new GridLayout(0, 2)); // 3 columns
        JPanel mainPanel = new JPanel(new BorderLayout());
        tablePanel.add(createHeaderLabel("Subject"));
        tablePanel.add(createHeaderLabel("Mark"));

        for (int i = 0; i < grades.getNumberSubjects(); i++) {
            tablePanel.add(createCellLabel("                                        "+grades.getSubjects().get(i)));
            tablePanel.add(createCellLabel(String.valueOf("                                              "+grades.getMarks().get(i))));
        }

        JScrollPane scrollPane = new JScrollPane(tablePanel);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel,BoxLayout.Y_AXIS));
        messagePanel.add(new JLabel("."));
        messagePanel.add(new JLabel("."));
        messagePanel.add(new JLabel("."));
        messagePanel.add(new JLabel("                        Total sum of marks:         "+grades.getSum()));
        messagePanel.add(new JLabel("."));
        messagePanel.add(new JLabel("                        Average percentage:       "+grades.getAverage()));
        messagePanel.add(new JLabel("."));
        messagePanel.add(new JLabel("                        Overall grade obtained:   "+grades.getGrade()));

        mainPanel.add(scrollPane, BorderLayout.NORTH);
        mainPanel.add(messagePanel, BorderLayout.WEST);

        add(mainPanel);

        setLocationRelativeTo(null);
    }

    private JLabel createHeaderLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }

    private JLabel createCellLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }

    public void drawTable()
    {
//        SwingUtilities.invokeLater(() -> {
//            GradeTable example = new GradeTable();
            setVisible(true);
//        });
    }
//    public static void main(String[] args)
//    {
//        drawTable();
//    }
}
