package com.example.ijaproject;

import com.fxgraph.cells.AbstractCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.fxgraph.cells.*;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassController
 *
 * This controller is backend for class frontend.
 * GUI Controller includes how GUI will be build, and it handles user input. Buttons call one
 * method which will run specific code based on button's label. There are two Buttons in this GUI.
 * One for adding methods and one for adding attributes to a class.
 *
 * @author xkovac59
 * @version 1.0
 */
public class ClassController extends Pane {

    public Button buttonAddMethod;

    public Button buttonAddAttribute;

    public TextField textField;

    public ListView listViewAddMethod;

    public ListView listViewAddAttribute;

    public Circle circleTOP;

    public Circle circleBOT;

    public Circle circleRIGHT;

    public Circle circleLEFT;


    /*public double posX;
    public double posY;*/

    /**
     * Method which handles pressing button "+ Method" (onAction="#addMethod").
     * After pressing button "+ Method" Pane and ListView listViewAddMethod will resize,
     * TextField will be added to ListView listViewAddMethod
     * and Button buttonAddMethod, Button buttonAddAttribute, ListView listViewAddMethod,
     * and ListView listViewAddAttribute will change their y-coordinates.
     */
    @FXML
    protected void addAttribute() {
        super.setMinHeight(super.getHeight() + 24);
        buttonAddMethod.setLayoutY(buttonAddMethod.getLayoutY() + 24);
        buttonAddAttribute.setLayoutY(buttonAddAttribute.getLayoutY() + 24);
        listViewAddMethod.setMinHeight(listViewAddMethod.getHeight() + 24);
        circleBOT.setLayoutY(circleBOT.getLayoutY() + 24);
        listViewAddAttribute.setLayoutY(listViewAddAttribute.getLayoutY() + 24);
        ObservableList observableList = listViewAddMethod.getItems();

        TextField text = new TextField();

        observableList.add(text);
        text.setText("+");

        listViewAddMethod.setItems(observableList);
        System.out.println("X:" + super.parentProperty() + " Y:" + super.getLayoutY());

    }

    public void addAttributeBC(List<UMLAttribute> attributes) {
        ObservableList<TextField> listView = FXCollections.observableArrayList();;
        int offset = 0;

        for(int i = 0; i < attributes.size(); i++) {
            TextField tf = new TextField();
            tf.setText(attributes.get(i).isPublic + " " + attributes.get(i).name);
            offset += 24;
            listView.add(tf);
        }


        super.setMinHeight(super.getHeight() + offset);
        buttonAddMethod.setLayoutY(buttonAddMethod.getLayoutY() + offset);
        buttonAddAttribute.setLayoutY(buttonAddAttribute.getLayoutY() + offset);
        listViewAddAttribute.setMinHeight(listViewAddAttribute.getHeight() + offset);
        listViewAddAttribute.setMaxHeight(listViewAddAttribute.getHeight() + offset);
        listViewAddAttribute.setItems(listView);
    }

    /**
     * Method which handles pressing button "+ Attribute" (onAction="#addAttribute").
     * After pressing button "+ Attribute" Pane and ListView listViewAddAttribute will resize,
     * TextField will be added to ListView listViewAddAttribute
     * and Button buttonAddMethod, Button buttonAddAttribute, ListView listViewAddMethod,
     * and ListView listViewAddAttribute will change their y-coordinates.
     */
    @FXML
    protected void addMethod() {
        super.setMinHeight(super.getHeight() + 24);
        buttonAddMethod.setLayoutY(buttonAddMethod.getLayoutY() + 24);
        buttonAddAttribute.setLayoutY(buttonAddAttribute.getLayoutY() + 24);
        listViewAddAttribute.setMinHeight(listViewAddAttribute.getHeight() + 24);
        circleBOT.setLayoutY(circleBOT.getLayoutY() + 24);
        ObservableList observableList = listViewAddAttribute.getItems();

        TextField text = new TextField();

        observableList.add(text);
        text.setText("+");

        listViewAddAttribute.setItems(observableList);

    }

    public ClassController() {
        uiBuild();
        /*this.posX = X;
        this.posY = Y;*/
    }

    private void uiBuild() {
        super.setPrefHeight(183);
        super.setPrefWidth(295);
        super.styleProperty().set("-fx-background-color: #89CFF0;");

        textField = new TextField();
        textField.setLayoutY(30.0);
        textField.setLayoutX(19.0);
        textField.prefHeight(24.0);
        textField.prefWidth(258);
        textField.setMinHeight(24);
        textField.setMinWidth(247);
        textField.setText("Name");

        buttonAddMethod = new Button();
        buttonAddMethod.defaultButtonProperty();
        buttonAddMethod.setLayoutY(147.0);
        buttonAddMethod.setLayoutX(19.0);

        buttonAddMethod.setOnAction(e -> {
            this.addAttribute();
        });
        buttonAddMethod.prefHeight(24.0);
        buttonAddMethod.prefWidth(101.0);
        buttonAddMethod.setText("+ Attribute");
        buttonAddMethod.underlineProperty();

        buttonAddAttribute = new Button();
        buttonAddAttribute.defaultButtonProperty();
        buttonAddAttribute.setLayoutY(147.0);

        buttonAddAttribute.setOnAction(e -> {
            this.addMethod();
        });
        buttonAddAttribute.setLayoutX(176.0);
        buttonAddAttribute.prefHeight(24.0);
        buttonAddAttribute.prefWidth(101.0);
        buttonAddAttribute.setText("+ Method");
        buttonAddAttribute.underlineProperty();

        listViewAddMethod = new ListView();
        listViewAddMethod.setLayoutY(54.0);
        listViewAddMethod.setLayoutX(19.0);
        listViewAddMethod.prefHeight(42);
        listViewAddMethod.prefWidth(258);
        listViewAddMethod.setMaxHeight(42);
        listViewAddMethod.setMaxWidth(258);

        listViewAddAttribute = new ListView();
        listViewAddAttribute.setLayoutY(96.0);
        listViewAddAttribute.setLayoutX(19.0);
        listViewAddAttribute.prefHeight(42);
        listViewAddAttribute.prefWidth(258);
        listViewAddAttribute.setMaxHeight(42);
        listViewAddAttribute.setMaxWidth(258);

        circleLEFT = new Circle();
        circleLEFT.setLayoutX(9.0);
        circleLEFT.setLayoutY(75.0);
        circleLEFT.setFill(Color.WHITE);
        circleLEFT.setRadius(6.0);
        circleLEFT.setStroke(Color.BLACK);
        circleLEFT.setStrokeType(StrokeType.INSIDE);
        circleLEFT.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            System.out.println("POINT X:" + circleLEFT.getScene().getX() + " Y:" + circleLEFT.getScene().getY());
        });

        circleRIGHT = new Circle();
        circleRIGHT.setLayoutX(285);
        circleRIGHT.setLayoutY(75.0);
        circleRIGHT.setFill(Color.WHITE);
        circleRIGHT.setRadius(6.0);
        circleRIGHT.setStroke(Color.BLACK);
        circleRIGHT.setStrokeType(StrokeType.INSIDE);

        circleBOT = new Circle();
        circleBOT.setLayoutX(148);
        circleBOT.setLayoutY(170);
        circleBOT.setFill(Color.WHITE);
        circleBOT.setRadius(6.0);
        circleBOT.setStroke(Color.BLACK);
        circleBOT.setStrokeType(StrokeType.INSIDE);

        circleTOP = new Circle();
        circleTOP.setLayoutX(148);
        circleTOP.setLayoutY(13);
        circleTOP.setFill(Color.WHITE);
        circleTOP.setRadius(6.0);
        circleTOP.setStroke(Color.BLACK);
        circleTOP.setStrokeType(StrokeType.INSIDE);

        super.getChildren().add(textField);
        super.getChildren().add(buttonAddMethod);
        super.getChildren().add(buttonAddAttribute);
        super.getChildren().add(listViewAddMethod);
        super.getChildren().add(listViewAddAttribute);
        super.getChildren().add(circleLEFT);
        super.getChildren().add(circleRIGHT);
        super.getChildren().add(circleBOT);
        super.getChildren().add(circleTOP);
    }

    public void updatePos(double newX, double newY) {
        /*this.posX = newX;
        this.posY = newY;*/
        //System.out.println("Given X:" + posX + " Y:" + newY);
    }

    public String getName() {
        return textField.getText();
    }

    public List<Position> getPos() {
        List<Position> pos = new ArrayList<>();
        Position top = new Position(circleTOP.getBoundsInParent().getCenterX(), circleTOP.getBoundsInParent().getCenterY());
        Position bot = new Position(circleBOT.getBoundsInParent().getCenterX(), circleBOT.getBoundsInParent().getCenterY());
        Position left = new Position(circleLEFT.getBoundsInParent().getCenterX(), circleLEFT.getBoundsInParent().getCenterY());
        Position right = new Position(circleRIGHT.getBoundsInParent().getCenterX(), circleRIGHT.getBoundsInParent().getCenterY());
        pos.add(top);
        pos.add(bot);
        pos.add(left);
        pos.add(right);
        return pos;
    }

    public void updateName(String newName) {
        this.textField.setText(newName);
    }


}
