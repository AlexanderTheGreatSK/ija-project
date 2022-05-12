module com.example.ijaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jsobject;
    requires com.google.gson;
    requires fxgraph;

    opens com.example.ijaproject to javafx.fxml;
    exports com.example.ijaproject;
    exports com.example.ijaproject.UMLbe;
    opens com.example.ijaproject.UMLbe to javafx.fxml;
    exports com.example.ijaproject.graphLib;
    opens com.example.ijaproject.graphLib to javafx.fxml;
    exports com.example.ijaproject.controller;
    opens com.example.ijaproject.controller to javafx.fxml;
}