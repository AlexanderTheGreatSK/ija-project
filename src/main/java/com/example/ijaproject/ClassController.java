package com.example.ijaproject;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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
public class ClassController {

    @FXML
    public Button buttonAddMethod;

    @FXML
    public Button buttonAddAttribute;

    @FXML
    public ListView listViewAddMethod;

    @FXML
    public ListView listViewAddAttribute;

    @FXML
    private Pane paneMove;

    /**
     * Method which handles pressing button "+ Method" (onAction="#addMethod").
     * After pressing button "+ Method" Pane and ListView listViewAddMethod will resize,
     * TextField will be added to ListView listViewAddMethod
     * and Button buttonAddMethod, Button buttonAddAttribute, ListView listViewAddMethod,
     * and ListView listViewAddAttribute will change their y-coordinates.
     */
    @FXML
    protected void addMethod() {
        paneMove.setMinHeight(paneMove.getHeight() + 24);
        buttonAddMethod.setLayoutY(buttonAddMethod.getLayoutY() + 24);
        buttonAddAttribute.setLayoutY(buttonAddAttribute.getLayoutY() + 24);
        listViewAddMethod.setMinHeight(listViewAddMethod.getHeight() + 24);
        listViewAddAttribute.setLayoutY(listViewAddAttribute.getLayoutY() + 24);
        ObservableList observableList = listViewAddMethod.getItems();

        TextField text = new TextField();

        observableList.add(text);
        text.setText("+");

        listViewAddMethod.setItems(observableList);

    }

    /**
     * Method which handles pressing button "+ Attribute" (onAction="#addAttribute").
     * After pressing button "+ Attribute" Pane and ListView listViewAddAttribute will resize,
     * TextField will be added to ListView listViewAddAttribute
     * and Button buttonAddMethod, Button buttonAddAttribute, ListView listViewAddMethod,
     * and ListView listViewAddAttribute will change their y-coordinates.
     */
    @FXML
    protected void addAttribute() {
        paneMove.setMinHeight(paneMove.getHeight() + 24);
        buttonAddMethod.setLayoutY(buttonAddMethod.getLayoutY() + 24);
        buttonAddAttribute.setLayoutY(buttonAddAttribute.getLayoutY() + 24);
        listViewAddAttribute.setMinHeight(listViewAddAttribute.getHeight() + 24);
        ObservableList observableList = listViewAddAttribute.getItems();

        TextField text = new TextField();

        observableList.add(text);
        text.setText("+");

        listViewAddAttribute.setItems(observableList);

    }
}
