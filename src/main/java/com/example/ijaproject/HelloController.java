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


/**
 * @brief HelloController
 *
 * This controller is backend for hello-view frontend.
 * GUI Controller includes how GUI will be build, and it handles user input. Buttons call one
 * method which will run specific code based on button's label.
 * There are six Buttons in this GUI - for adding:
 *  class
 *  text
 *  association
 *  aggregation
 *  composition
 *  generalization
 *
 * @author xkovac59, xokruc00
 * @version 1.0
 */
public class HelloController {
    public double orgSceneX;
    public double orgSceneY;

    @FXML
    public Button addLineButton;

    @FXML
    public Button textButton;

    @FXML
    public Button classButton;

    @FXML
    private Group mainGroup;

    /**
     * Method which handles pressing button "Add association" (onAction="#addLine").
     * After pressing button "Add association" line will occur.
     */
    @FXML
    public void addLine() {
        ResizeLine line = new ResizeLine(100,100,150,150, mainGroup);
    }

    @FXML
    public void aggregation() {
        Aggregation aggregation = new Aggregation(100,100,150,150, mainGroup);
    }

    /**
     * Method which handles pressing button "Add class" (onAction="#addClass").
     * After pressing button "Add class" class from class.fxml will occur.
     */
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

    /**
     * Method for creating Text Field, which would be added to Pane after pressing "Add text".
     */
    private TextField createText(){
        TextField text = new TextField();
        text.setPrefWidth(60);
        text.setCursor(Cursor.HAND);

        text.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

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

    /**
     * Method which handles pressing button "Add text" (onAction="#addText").
     * After pressing button "Add text" Text Field will occur.
     *
     * param actionEvent
     */
    @FXML
    public void addText(ActionEvent actionEvent) {

        TextField t = createText();

        mainGroup.getChildren().add(t);

    }

    @FXML
    public void composition(ActionEvent actionEvent) {
        Composition composition = new Composition(100,100,150,150, mainGroup);
    }
}