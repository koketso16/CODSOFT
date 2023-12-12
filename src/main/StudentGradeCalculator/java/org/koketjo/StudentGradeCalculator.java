package org.koketjo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentGradeCalculator {

    private static final Scanner read = new Scanner(System.in);
    private static final List<String> subjects = new ArrayList<>();
    private static int getNoOfSubjects()
    {
        System.out.println("Enter total number of subjects done: ");
        return read.nextInt();
    }

    private static int getSubjects()
    {
        int count = 1;
        int numberSubjects = getNoOfSubjects();

        while(count < numberSubjects + 1)
        {
            System.out.println("Enter subject " + count + ": ");
            String subject = read.next();
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
            System.out.println("Enter mark for " + sub + ": ");
            int mark = read.nextInt();
            sum+=mark;
        }
        return sum;
    }

    private static String getGrade(double average)
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
        }else
        {
            return "F";
        }
    }
    private static void printResults(int sum, int numberSubjects)
    {
        double average = (double) sum /numberSubjects;
        System.out.println("Total sum: " + sum);
        System.out.println("Average percentage: " + average);

        String grade = getGrade(average);
        System.out.println("Overall grade obtained: " + grade);
    }

    public static void main(String[] args) {

        int numberSubjects = getSubjects();
        int sum = getSubjectMarks();
        printResults(sum, numberSubjects);
    }
}
