package org.koketjo;


import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static int correctCount = 0;
    private static int rounds = 1;
    private static final Random random = new Random();
    private static Scanner scan = new Scanner(System.in);
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
        System.out.println("Guess a number between 1 and 100: ");

        return scan.nextInt();
    }

    public static boolean checkGuess(int guess, int random)
    {
        if(guess == random)
        {
            System.out.println("Correct guess, the number is: " + guess);
            correctCount();
            return true;

        } else if(guess < random)
        {
            System.out.println("Incorrect, "+guess + " is too low.");
            return false;

        } else {

            System.out.println("Incorrect, "+guess + " is too high.");
            return false;
        }

    }

    public static void correctCount()
    {
        correctCount++;
    }

    public static void displayScore()
    {
        System.out.println("-----------------------------");
        System.out.println("|                           |");
        System.out.println("|    YOUR SCORE : " + correctCount +"         |");
        System.out.println("|                           |");
        System.out.println("----------------------------");
        System.out.println("        GAME OVER!          ");
    }

    public static void playGame()
    {
        int number = 0;
        int random = generateNumber();
        int guesses = getNumberOfGuesses();
        int guessesInitial = guesses;

        while(number < guessesInitial)
        {
            System.out.println("You have " + guesses + " attempts!");
            int guess = getGuess();
            if(checkGuess(guess,random))
            {
                anotherRound();
                break;
            }
            guesses--;
            number++;
        }

        if(number == guessesInitial)
        {
            System.out.println("You have run out of guesses!");
            anotherRound();

        }
    }

    public static void anotherRound()
    {
        System.out.println("Enter \"p\" to play another round or enter \"q\" to quit game.");
        String input = scan.next();

        if(input.equalsIgnoreCase("q"))
        {
            displayScore();

        } else if(input.equalsIgnoreCase("p"))
        {
            if(rounds == 3)
            {
                System.out.println("You have played a full 3 rounds");
                displayScore();

            } else
            {
                System.out.println("Entered another round! The maximum number of rounds is 3.");
                rounds++;
                System.out.println("You are on round " + rounds + "!");
                playGame();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Number Game!");
        playGame();

    }
}