package org.koketjo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import java.math.BigDecimal;

public class FetchFromAPI {

    private static final String API_KEY = "73c118ff499f61268bca3a02";
    public BigDecimal fetchExchangeRate(String baseCurrency, String targetCurrency) {
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
}
