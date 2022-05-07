package com.example.ijaproject;

import com.fxgraph.edges.Edge;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.RandomLayout;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * HelloApplication
 *
 * This constructor handles start of an application.
 *
 * @author xokruc00
 * @version 1.0
 */
public class HelloApplication extends Application {
    public BorderPane borderPane;
    public ToolBar toolbar;
    public Model model;
    public Graph graph;
    int index = 0;


    public ICell source;
    public ICell destination;

    @Override
    public void start(Stage primaryStage) {
        graph = new Graph();

        model = graph.getModel();
        graph.beginUpdate();
        toolbar = new ToolBar();
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
        toolbar.getItems().addAll(addClass, addText, addAssociation, addAggregation, addComposition, addGeneralization, importClassDiagram, saveClassDiagram);

        borderPane = new BorderPane();
        borderPane.setTop(toolbar);
        borderPane.setCenter(graph.getCanvas());
        primaryStage.setMinHeight(1000);
        primaryStage.setMaxHeight(1000);
        primaryStage.setMinWidth(1500);
        primaryStage.setMaxWidth(1500);
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void saveHandler(ActionEvent event) {
    }

    private void importHandler(ActionEvent event) {
        FileHandler fileHandler = new FileHandler("/home/alexanderthegreat/IdeaProjects/ija-project/proj1.json");
        UMLProject umlProject = fileHandler.read();
        List<UMLClass> lc = umlProject.classes;

        List<ICell> cells = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        graph.beginUpdate();

        for(int i=0; i<lc.size(); i++) {
            ClassController classController = new ClassController(lc.get(i).name);
            classController.addAttributeBC(lc.get(i).attributes);
            ICell cell = new ClassCell(classController);
            model.addCell(cell);
        }
        graph.endUpdate();
        graph.layout(new RandomLayout());
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
        ClassController classController = new ClassController("Class" + this.index);
        this.index++;
        ICell cell = new ClassCell(classController);
        this.model.addCell(cell);
        this.graph.endUpdate();
    }
}