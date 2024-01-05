module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires unirest.java;


    opens com.example.demo3 to javafx.fxml;
    exports org.koketjo;
    opens org.koketjo to javafx.fxml;
}