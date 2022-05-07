module com.example.ijaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jsobject;
    requires com.google.gson;
    requires fxgraph;

    opens com.example.ijaproject to javafx.fxml;
    exports com.example.ijaproject;
}