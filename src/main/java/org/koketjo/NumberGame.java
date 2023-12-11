package org.koketjo;


import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static final Random random = new Random();
    public static int generateNumber()
    {
        return random.nextInt(100) + 1;
    }
    public static int getNumberOfGuesses()
    {
        return random.nextInt(6) + 5;
    }

    public static int getGuess()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a number between 1 and 100: ");

        return scan.nextInt();
    }

    public static void checkGuess(int guess, int random)
    {
        if(guess == random)
        {
            System.out.println("Correct guess, the number is: " + guess);
        } else
        {
            System.out.println("Incorrect "+guess + " is not the number.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Number Game!");

        int number = 0;
        int random = generateNumber();
        int guesses = getNumberOfGuesses();

        while(number < guesses)
        {
            int guess = getGuess();
            checkGuess(guess,random);
            number++;
        }

        if(number == guesses)
        {
            System.out.println("You have run out of guesses!");
        }
    }
}