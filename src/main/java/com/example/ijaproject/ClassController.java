package com.example.ijaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Objects;

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
    final int ROW_HEIGHT = 24;

    public Button buttonAddMethod;

    public Button buttonAddAttribute;

    public TextField textField;

    public ListView listViewAddAttribute;

    public ListView listViewAddMethod;

    /**
     * Method which handles pressing button "+ Method" (onAction="#addMethod").
     * After pressing button "+ Method" Pane and ListView listViewAddMethod will resize,
     * TextField will be added to ListView listViewAddMethod
     * and Button buttonAddMethod, Button buttonAddAttribute, ListView listViewAddMethod,
     * and ListView listViewAddMethod will change their y-coordinates.
     */
    protected void addAttribute() {
        super.setMinHeight(super.getHeight() + 24);
        buttonAddMethod.setLayoutY(buttonAddMethod.getLayoutY() + 24);
        buttonAddAttribute.setLayoutY(buttonAddAttribute.getLayoutY() + 24);
        listViewAddMethod.setLayoutY(listViewAddMethod.getLayoutY() + 24);
        listViewAddAttribute.setMaxHeight(listViewAddAttribute.getHeight() + 24);
        ObservableList observableList = listViewAddAttribute.getItems();

        TextField text = new TextField();

        observableList.add(text);
        text.setText("+");

        listViewAddAttribute.setItems(observableList);

        text.textProperty().addListener(e ->  {
            System.out.println(text.getText());
            if(text.getText().equals("")){
                observableList.remove(text);
                    super.setMinHeight(super.getHeight() - 24);
                    listViewAddMethod.setLayoutY(listViewAddMethod.getLayoutY() - 24);
                    listViewAddAttribute.setMaxHeight(listViewAddAttribute.getHeight() - 24);
                    buttonAddMethod.setLayoutY(buttonAddMethod.getLayoutY() - 24);
                    buttonAddAttribute.setLayoutY(buttonAddAttribute.getLayoutY() - 24);
            }
        });

        listViewAddAttribute.setItems(observableList);

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
        listViewAddMethod.setMinHeight(listViewAddMethod.getHeight() + offset);
        listViewAddMethod.setMaxHeight(listViewAddMethod.getHeight() + offset);
        listViewAddMethod.setItems(listView);
    }

    public void addMethodsBC(List<UMLAttribute> methods) {
        ObservableList<TextField> listView = FXCollections.observableArrayList();;
        int offset = 0;

        for(int i = 0; i < methods.size(); i++) {
            TextField tf = new TextField();
            tf.setText(methods.get(i).isPublic + " " + methods.get(i).name);
            offset += 24;
            listView.add(tf);
        }


        super.setMinHeight(super.getHeight() + offset);
        buttonAddMethod.setLayoutY(buttonAddMethod.getLayoutY() + offset);
        buttonAddAttribute.setLayoutY(buttonAddAttribute.getLayoutY() + offset);
        listViewAddMethod.setMinHeight(listViewAddMethod.getHeight() + offset);
        listViewAddMethod.setMaxHeight(listViewAddMethod.getHeight() + offset);
        listViewAddMethod.setItems(listView);
    }

    /**
     * Method which handles pressing button "+ Attribute" (onAction="#addAttribute").
     * After pressing button "+ Attribute" Pane and ListView listViewAddMethod will resize,
     * TextField will be added to ListView listViewAddMethod
     * and Button buttonAddMethod, Button buttonAddAttribute, ListView listViewAddMethod,
     * and ListView listViewAddMethod will change their y-coordinates.
     */
    protected void addMethod() {
        super.setMinHeight(super.getHeight() + 24);
        buttonAddMethod.setLayoutY(buttonAddMethod.getLayoutY() + 24);
        buttonAddAttribute.setLayoutY(buttonAddAttribute.getLayoutY() + 24);
        listViewAddMethod.setMinHeight(listViewAddMethod.getHeight() + 24);
        ObservableList observableList = listViewAddMethod.getItems();

        TextField text = new TextField();

        observableList.add(text);
        text.setText("+");

        listViewAddMethod.setItems(observableList);

        text.textProperty().addListener(e ->  {
            System.out.println(text.getText());
            if(text.getText().equals("")){
                observableList.remove(text);
                super.setMinHeight(super.getHeight() - 24);
                listViewAddMethod.setMinHeight(listViewAddMethod.getHeight() - 24);
                buttonAddMethod.setLayoutY(buttonAddMethod.getLayoutY() - 24);
                buttonAddAttribute.setLayoutY(buttonAddAttribute.getLayoutY() - 24);
            }
        });

    }

    public ClassController(String name) {
        uiBuild();
        this.updateName(name);
    }

    private void uiBuild() {
        super.setPrefHeight(183);
        super.setMinHeight(183);
        super.setPrefWidth(295);
        super.setMinWidth(295);
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

        listViewAddAttribute = new ListView();
        listViewAddAttribute.setLayoutY(54.0);
        listViewAddAttribute.setLayoutX(19.0);
        listViewAddAttribute.prefHeight(42);
        listViewAddAttribute.prefWidth(258);
        listViewAddAttribute.setMaxHeight(42);
        listViewAddAttribute.setMaxWidth(258);

        listViewAddMethod = new ListView();
        listViewAddMethod.setLayoutY(96.0);
        listViewAddMethod.setLayoutX(19.0);
        listViewAddMethod.prefHeight(42);
        listViewAddMethod.prefWidth(258);
        listViewAddMethod.setMaxHeight(42);
        listViewAddMethod.setMaxWidth(258);



        super.getChildren().add(textField);
        super.getChildren().add(buttonAddMethod);
        super.getChildren().add(buttonAddAttribute);
        super.getChildren().add(listViewAddMethod);
        super.getChildren().add(listViewAddAttribute);
    }

    public String getName() {
        return textField.getText();
    }

    public void updateName(String newName) {
        this.textField.setText(newName);
    }


}
