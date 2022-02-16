package com.example.ija;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(1000,1000);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        graphicsContext.setLineWidth(2.0);
        graphicsContext.setFill(Color.RED);
        graphicsContext.strokeRoundRect(10, 10, 50, 50, 10, 10);
        graphicsContext.fillRoundRect(100, 10, 50, 50, 10, 10);
    }

    public static void main(String[] args) {
        launch();
    }
}