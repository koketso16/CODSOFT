package org.koketjo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentGradeCalculator {

    private static final Scanner read = new Scanner(System.in);
    private static final List<String> subjects = new ArrayList<>();
    private static final List<Integer> marks = new ArrayList<>();
    private static int numberSubjects = 0;
    private static int sum = 0;
    private static double average = 0;
    private static String grade = "";
    private static int getNoOfSubjects()
    {
        int subjects = 0;
        while (subjects < 1 || subjects > 10) {
            System.out.println("Enter total number of subjects done (maximum number of subjects is 10): ");
            subjects = read.nextInt();
        }
        return subjects;
    }

    public int getNumberSubjects()
    {
        return numberSubjects;
    }

    private static int inputSubjects()
    {
        int count = 1;
        numberSubjects = getNoOfSubjects();

        while(count < numberSubjects + 1)
        {
            System.out.println("Enter subject " + count + ": ");
            String subject = read.next().toUpperCase();
            subjects.add(subject);
            count++;
        }
        return numberSubjects;
    }

    private static int getSubjectMarks()
    {
        int sum = 0;

        for(String sub : subjects)
        {
            int mark = -1;
            while (mark < 0 || mark > 100)
            {
                System.out.println("Enter mark for " + sub + " (0 - 100): ");
                mark = read.nextInt();
                marks.add(mark);
            }
            sum+=mark;
        }
        return sum;
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
    private static void getResults(int sum, int numberSubjects)
    {
        average = (double) sum /numberSubjects;
//        System.out.println("Total sum: " + sum);
//        System.out.println("Average percentage: " + Math.round(average * 100) / 100);

        grade = checkGrade(average);
//        System.out.println("Overall grade obtained: " + grade);
    }

    public int getSum()
    {
        return sum;
    }

    public double getAverage()
    {
        return average;
    }

    public String getGrade()
    {
        return grade;
    }

    public List<String> getSubjects()
    {
        return subjects;
    }

    public List<Integer> getMarks()
    {
        return marks;
    }
    public static void main(String[] args) {

        int numberSubjects = inputSubjects();
        sum = getSubjectMarks();
        getResults(sum, numberSubjects);

        GradeTable table = new GradeTable();
//        table.setVisible(true);
        table.drawTable();

    }


}
