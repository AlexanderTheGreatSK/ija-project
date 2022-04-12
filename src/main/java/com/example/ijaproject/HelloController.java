package com.example.ijaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button myButton;

    @FXML
    private Pane mainPane;

    @FXML
    protected void addClass(ActionEvent event) throws IOException {
        System.out.println("PRESSED");
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("class.fxml"));
        mainPane.getChildren().add(0, loader.load());


    }
}