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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public ClassController sourceCC;
    public ICell destination;
    public ClassController destinationCC;

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

        graph.beginUpdate();

        SequenceDiagram seqDiagram = new SequenceDiagram();

        SequenceDiagram.ActorCell actorA = new SequenceDiagram.ActorCell("Actor A", 400d);
        SequenceDiagram.ActorCell actorB = new SequenceDiagram.ActorCell("Actor B", 400d);
        SequenceDiagram.ActorCell actorC = new SequenceDiagram.ActorCell("Actor C", 400d);
        Arrays.asList(actorA, actorB, actorC).forEach(actor -> seqDiagram.addActor(actor));

        seqDiagram.addMessage(actorA, actorB, "checkEmail");
        seqDiagram.addMessage(actorB, actorC, "readSavedUser");
        seqDiagram.addMessage(actorC, actorB, "savedUser");
        seqDiagram.addMessage(actorB, actorA, "noNewEmails");

        seqDiagram.layout();


        borderPane = new BorderPane();
        borderPane.setTop(toolbar);
        borderPane.setCenter(seqDiagram.getCanvas());
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
        if(this.source != null && this.destination != null) {
            System.out.println("tru");
            final MyArrow myArrow = new MyArrow(this.source, this.destination);
            myArrow.textProperty().set("Edges can have text too!");
            this.model.addEdge(myArrow);
            this.graph.endUpdate();
        }
    }

    private void addTextHandler(ActionEvent event) {
    }

    private void addClassHandler(ActionEvent event) {
        ClassController classController = new ClassController("Class" + this.index);
        ICell cell = new ClassCell(classController);

        classController.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            classController.toFront();
            this.select(cell, classController);
        });

        this.index++;

        this.model.addCell(cell);
        this.graph.endUpdate();
    }

    private void select(ICell cell, ClassController classController) {
        System.out.println("HAHAHA");

        if(this.source == null && this.sourceCC == null) {
            this.source = cell;
            this.sourceCC = classController;
            return;
        } else if(this.destination == null) {
            if(!Objects.equals(sourceCC.getName(), classController.getName())) {
                this.destination = cell;
                this.destinationCC = classController;
                return;
            }
        }
        if(!Objects.equals(destinationCC.getName(), classController.getName())) {
            this.source = this.destination;
            this.sourceCC = this.destinationCC;
            this.destination = cell;
            this.destinationCC = classController;
        }
    }
}