module com.example.ijaproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ijaproject to javafx.fxml;
    exports com.example.ijaproject;
}