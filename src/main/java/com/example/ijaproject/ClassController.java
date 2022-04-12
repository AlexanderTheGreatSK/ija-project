package com.example.ijaproject;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


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
