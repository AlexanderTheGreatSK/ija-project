package com.example.ijaproject;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.IOException;

public class HelloController {
    public double orgSceneX;
    public double orgSceneY;

    @FXML
    private Button classButton;

    @FXML
    private Button lineButton;

    @FXML
    private Button lineButton1;

    @FXML
    private Button textButton;

    @FXML
    private Button saveButton;

    @FXML
    private Group mainGroup;

    @FXML
    public void addLine() {
        ResizeLine line = new ResizeLine(100,100,150,150, mainGroup);
    }

    @FXML
    public void addClass() throws IOException {

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("class.fxml"));

        Pane pane = loader.load();

        pane.setLayoutX(150);
        pane.setLayoutY(150);

        pane.setCursor(Cursor.HAND);

        pane.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            Pane r = (Pane) (t.getSource());
            r.toFront();
        });

        pane.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Pane r = (Pane) (t.getSource());

            r.setLayoutX(r.getLayoutX() + offsetX);
            r.setLayoutY(r.getLayoutY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });

        mainGroup.getChildren().add(pane);

    }

    private Line createLineV(double x, double y) {
        Line line = new Line();
        line.setStartX(x);
        line.setStartY(y);
        line.setEndX(x);
        line.setEndY(y + 100);

        line.setCursor(Cursor.HAND);

        line.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            Line r = (Line) (t.getSource());
            r.toFront();
        });

        line.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Line r = (Line) (t.getSource());

            r.setLayoutX(r.getLayoutX() + offsetX);
            r.setLayoutY(r.getLayoutY() + offsetY);


            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });

        return line;
    }

    @FXML
    public void addLineV() throws IOException {

        Line l = createLineV(0, 100);

        mainGroup.getChildren().add(l);
    }

    private Line createLineH(double x, double y) {
        Line line = new Line();
        line.setStartX(x);
        line.setStartY(y);
        line.setEndX(x  + 100);
        line.setEndY(y);

        line.setCursor(Cursor.HAND);

        line.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            Line r = (Line) (t.getSource());
            r.toFront();
        });

        line.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Line r = (Line) (t.getSource());

            r.setLayoutX(r.getLayoutX() + offsetX);
            r.setLayoutY(r.getLayoutY() + offsetY);


            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });

        return line;
    }

    @FXML
    public void addLineH() throws IOException {

        Line l = createLineH(100, 0);

        mainGroup.getChildren().add(l);
    }

    private TextField createText(){
        TextField text = new TextField();
        text.setPrefWidth(60);
        text.setCursor(Cursor.HAND);

        text.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            Line r = (Line) (t.getSource());
            r.toFront();
        });

        text.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            TextField r = (TextField) (t.getSource());

            r.setLayoutX(r.getLayoutX() + offsetX);
            r.setLayoutY(r.getLayoutY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });

        return text;
    }


    @FXML
    public void addText(ActionEvent actionEvent) {

        TextField t = createText();

        mainGroup.getChildren().add(t);

    }
}