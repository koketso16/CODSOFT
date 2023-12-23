package org.koketjo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CurrencyConverter {
    private static final Scanner scanner = new Scanner(System.in);

    private static String getBaseCurrency()
    {
        System.out.print("Enter the base currency code (e.g., USD): ");
        return scanner.nextLine();
    }
    private static String getTargetCurrency() {
        System.out.print("Enter the target currency code (e.g., EUR): ");
        return scanner.nextLine();
    }

    private static BigDecimal getAmount()
    {
        System.out.print("Enter the amount to convert: ");
        return scanner.nextBigDecimal();

    }

    private static void displayAmount(BigDecimal convertedAmount, String targetCurrency)
    {
        System.out.println("Converted Amount: " + convertedAmount + " " + targetCurrency);
    }

    private static BigDecimal convertCurrency(BigDecimal amount, BigDecimal exchangeRate) {
        return amount.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) {

        System.out.println("Welcome to my Currency Converter!");

        String baseCurrency = "";

        while (baseCurrency.isEmpty()) {

            baseCurrency = getBaseCurrency().toUpperCase();

            String targetCurrency = getTargetCurrency().toUpperCase();

            FetchFromAPI fromAPI = new FetchFromAPI();
            BigDecimal exchangeRate = fromAPI.fetchExchangeRate(baseCurrency, targetCurrency);

            BigDecimal amountToConvert = getAmount();

            BigDecimal convertedAmount = convertCurrency(amountToConvert, exchangeRate);

            displayAmount(convertedAmount, targetCurrency);
        }
        scanner.close();
    }




}
