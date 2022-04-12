package com.example.ijaproject;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;


public class ClassController {
    @FXML
    public Button buttonAddEntity;

    @FXML
    public Button buttonArrayDown;

    @FXML
    public ListView listViewAdd;

    @FXML
    private Pane paneMove;

    @FXML
    protected void addEntity() {
        System.out.println("HAAA");
        paneMove.setMinHeight(paneMove.getHeight() + 10);
        buttonAddEntity.setLayoutY(buttonAddEntity.getLayoutY() + 10);
        listViewAdd.setMinHeight(listViewAdd.getHeight() + 10);
        ObservableList observableList = listViewAdd.getItems();

        TextField text = new TextField();

        observableList.add(text);
        text.setText("+");

        listViewAdd.setItems(observableList);

    }



}
