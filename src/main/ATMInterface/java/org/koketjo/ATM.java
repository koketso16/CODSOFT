package org.koketjo;

import java.util.Scanner;

public class ATM extends UserAccount{
    private static final Scanner scan = new Scanner(System.in);

    public static void listServices()
    {
        System.out.println("Welcome to Koketjo's ATM. What would you like to do, select(C, D or W):");
        System.out.println("C : Check Balance");
        System.out.println("D : Deposit money");
        System.out.println("W : Withdraw money");
    }

    public static String getInput()
    {
        return scan.nextLine();
    }

    public static void checkInput(String input)
    {
        if(input.equalsIgnoreCase("C"))
        {
            checkBalance();

        }else if(input.equalsIgnoreCase("W"))
        {
            System.out.println("How much would you like to withdraw? : ");
            String amount = getInput();
            withdraw(Double.parseDouble(amount));

        }else if(input.equalsIgnoreCase("D"))
        {
            System.out.println("How much would you like to deposit? : ");
            String amount = getInput();
            deposit(Double.parseDouble(amount));

        } else
        {
            System.out.println("Error reading input");
        }
    }
    public static void withdraw(double amount)
    {
        if(amount > getBankBalance())
        {
            System.out.println("Withdrawal Declined! - Your balance is less than the required amount.");
        } else
        {
            System.out.println("Withdrawal Approved! - Please take your money.");
            setBankBalance(getBankBalance() - amount);
        }

    }

    public static void deposit(double amount)
    {
        setBankBalance(getBankBalance() + amount);
        System.out.println("R" + amount + "0 successfully deposited in your account.");
    }

    public static void checkBalance()
    {
        System.out.println("Your current balance is: R"+ getBankBalance());
    }

    public static void main(String[] args) {
        listServices();
        String input = "";
        while(!input.equalsIgnoreCase("w") || !input.equalsIgnoreCase("c") || !input.equalsIgnoreCase("d"))
        {
            input = getInput();
            checkInput(input);
            System.out.println("What would you like to do next? Enter \"v\" to view options or enter \"q\" to quit session.");
            String option = getInput();
            if(option.equalsIgnoreCase("v"))
            {
                listServices();

            } else if (option.equalsIgnoreCase("q"))
            {
                System.out.println("Thank you for using Koketjo's bank. Come back again!");
                System.exit(0);
            }
        }

    }
}
