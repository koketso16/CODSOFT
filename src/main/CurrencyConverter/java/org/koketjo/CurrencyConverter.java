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
    private static final String API_KEY = "YOUR_API_KEY"; // Replace with your API key

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Currency Selection
        System.out.print("Enter the base currency code (e.g., USD): ");
        String baseCurrency = scanner.nextLine();

        System.out.print("Enter the target currency code (e.g., EUR): ");
        String targetCurrency = scanner.nextLine();

        // Step 2: Currency Rates
        BigDecimal exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

        // Step 3: Amount Input
        System.out.print("Enter the amount to convert: ");
        BigDecimal amountToConvert = scanner.nextBigDecimal();

        // Step 4: Currency Conversion
        BigDecimal convertedAmount = convertCurrency(amountToConvert, exchangeRate);

        // Step 5: Display Result
        System.out.println("Converted Amount: " + convertedAmount + " " + targetCurrency);

        scanner.close();
    }

    private static BigDecimal getExchangeRate(String baseCurrency, String targetCurrency) {
        String apiUrl = "https://open.er-api.com/v6/latest/" + baseCurrency;
        HttpResponse<JsonNode> response = Unirest.get(apiUrl)
                .header("Accept", "application/json")
                .queryString("apikey", API_KEY)
                .asJson();

        JsonObject rates = JsonParser.parseString(response.getBody().toString()).getAsJsonObject().getAsJsonObject("rates");
        BigDecimal exchangeRate = rates.getAsJsonPrimitive(targetCurrency).getAsBigDecimal();

        System.out.println("Exchange Rate: 1 " + baseCurrency + " = " + exchangeRate + " " + targetCurrency);

        return exchangeRate;
    }

    private static BigDecimal convertCurrency(BigDecimal amount, BigDecimal exchangeRate) {
        return amount.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
    }
}
