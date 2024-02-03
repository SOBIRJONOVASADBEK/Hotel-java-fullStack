module com.example.bot {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bot to javafx.fxml;
    exports com.example.bot;
}