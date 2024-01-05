package org.koketjo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CurrencyConverter extends Application {

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40, 40, 40, 40));
        grid.setStyle("-fx-background-color: #808080;");

        grid.setVgap(20);
        grid.setHgap(20);

        ComboBox<String> baseCurrencyComboBox = new ComboBox<>();
        ComboBox<String> targetCurrencyComboBox = new ComboBox<>();

        TextField amountTextField = new TextField();
        Label resultLabel = new Label();

        baseCurrencyComboBox.getItems().addAll(
                "USD", "EUR", "GBP", "AUD", "CAD", "JPY", "CNY", "INR", "MXN", "BRL", "ZAR");

        targetCurrencyComboBox.getItems().addAll(
                "USD", "EUR", "GBP", "AUD", "CAD", "JPY", "CNY", "INR", "MXN", "BRL", "ZAR");

        Label label = new Label("Base Currency:");
        label.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        grid.add(label, 0, 0);

        grid.add(baseCurrencyComboBox, 1, 0);

        Label label2 = new Label("Target Currency:");
        label2.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        grid.add(label2, 0, 1);

        grid.add(targetCurrencyComboBox, 1, 1);

        Label label3 = new Label("Amount:");
        label3.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        grid.add(label3, 0, 2);

        grid.add(amountTextField, 1, 2);

        Label label4 = new Label("Converted Amount:");
        label4.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        grid.add(label4, 0, 3);
        grid.add(resultLabel, 1, 3);

        Button convertButton = new Button("Convert");
        convertButton.setStyle("-fx-font-size: 16;");
        convertButton.setOnAction(e -> {
            try {
                String baseCurrency = baseCurrencyComboBox.getValue();
                String targetCurrency = targetCurrencyComboBox.getValue();
                double amount = Double.parseDouble(amountTextField.getText());

                double conversionRate = FetchFromAPI.fetchExchangeRate(baseCurrency,targetCurrency);

                double convertedAmount = amount * conversionRate;
                resultLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
                resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, baseCurrency, convertedAmount, targetCurrency));

            } catch (NumberFormatException ex) {
                resultLabel.setText("Enter a valid amount.");
            }
        });

        Button quitButton = new Button("Quit");
        quitButton.setStyle("-fx-font-size: 16;");
        quitButton.setOnAction(a -> {
            System.exit(0);
        });

        grid.add(convertButton, 1, 4);
        grid.add(quitButton, 2, 4);

        Scene scene = new Scene(grid, 700, 400);
        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
