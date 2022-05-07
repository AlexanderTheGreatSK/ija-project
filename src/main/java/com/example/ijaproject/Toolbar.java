package com.example.ijaproject;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;


public class Toolbar extends ToolBar {

    public Toolbar(){
        Button addClass = new Button("Add Class");
        addClass.setOnAction(this::addClassHandler);
        Button addText = new Button("Add Text");
        addText.setOnAction(this::addTextHandler);
        Button addAssociation = new Button("Add Association");
        addAssociation.setOnAction(this::addAssociationHandler);
        Button addAggregation = new Button("Add Aggregation");
        addAggregation.setOnAction(this::addAggregationHandler);
        Button addComposition = new Button("Add Composition");
        addComposition.setOnAction(this::addCompositionHandler);
        Button addGeneralization = new Button("Add Generalization");
        addGeneralization.setOnAction(this::addGeneralizationHandler);
        Button importClassDiagram = new Button("Import Class Diagram");
        importClassDiagram.setOnAction(this::importHandler);
        Button saveClassDiagram = new Button("Save");
        saveClassDiagram.setOnAction(this::saveHandler);

        this.getItems().addAll(addClass, addText, addAssociation, addAggregation, addComposition, addGeneralization, importClassDiagram, saveClassDiagram);

    }

    private void saveHandler(ActionEvent event) {
    }

    private void importHandler(ActionEvent event) {
    }

    private void addGeneralizationHandler(ActionEvent event) {
    }

    private void addCompositionHandler(ActionEvent event) {
    }

    private void addAggregationHandler(ActionEvent event) {
    }

    private void addAssociationHandler(ActionEvent event) {
    }

    private void addTextHandler(ActionEvent event) {
    }

    private void addClassHandler(ActionEvent event) {
    }
}
