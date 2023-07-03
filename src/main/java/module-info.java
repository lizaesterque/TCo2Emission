module com.example.tco2emission {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.tco2emission to javafx.fxml;
    exports com.example.tco2emission;
}