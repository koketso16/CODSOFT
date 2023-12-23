package org.koketjo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CurrencyConverter {
    private static final Scanner scanner = new Scanner(System.in);

    private static String getBaseCurrency()
    {
        System.out.print("Enter the base currency code (e.g. ZAR) or enter \"q\" to quit: ");
        return scanner.next();
    }
    private static String getTargetCurrency()
    {
        System.out.print("Enter the target currency code (e.g. USD) or enter \"q\" to quit: ");
        return scanner.next();
    }

    private static BigDecimal getAmount()
    {
        System.out.print("Enter the amount to convert or enter \"q\" to quit: ");
        return scanner.nextBigDecimal();
    }

    private static void displayAmount(BigDecimal convertedAmount, String targetCurrency)
    {
        System.out.println("Converted Amount: " + convertedAmount + " " + targetCurrency);
    }

    private static BigDecimal convertCurrency(BigDecimal amount, BigDecimal exchangeRate)
    {
        return amount.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args)
    {

        System.out.println("Welcome to my Currency Converter!");

        String baseCurrency = "hey";

        while (!baseCurrency.equalsIgnoreCase(""))
        {

            baseCurrency = getBaseCurrency().toUpperCase();

            if(baseCurrency.equalsIgnoreCase("q"))
            {
                System.out.println("Thank you for using my currency converter, Until next time!");
                break;
            }

            String targetCurrency = getTargetCurrency().toUpperCase();

            if(targetCurrency.equalsIgnoreCase("q"))
            {
                System.out.println("Thank you for using my currency converter, Until next time!");
                break;
            }

            FetchFromAPI fromAPI = new FetchFromAPI();
            BigDecimal exchangeRate = fromAPI.fetchExchangeRate(baseCurrency, targetCurrency);

            BigDecimal amountToConvert = getAmount();

            BigDecimal convertedAmount = convertCurrency(amountToConvert, exchangeRate);

            displayAmount(convertedAmount, targetCurrency);

        }
        scanner.close();
    }

}
