package org.koketjo;

public class UserAccount {

    private static double bankBalance = 1577.23;

    public static void setBankBalance(double amount)
    {
        bankBalance = amount;
    }

    public static double getBankBalance()
    {
        return bankBalance;
    }
}
