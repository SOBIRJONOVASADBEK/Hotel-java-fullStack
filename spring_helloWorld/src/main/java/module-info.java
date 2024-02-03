module com.example.spring_helloworld {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.spring_helloworld to javafx.fxml;
    exports com.example.spring_helloworld;
}