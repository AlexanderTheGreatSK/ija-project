package com.example.ijaproject;

import com.fxgraph.cells.RectangleCell;
import com.fxgraph.cells.TriangleCell;
import com.fxgraph.edges.CorneredEdge;
import com.fxgraph.edges.DoubleCorneredEdge;
import com.fxgraph.edges.Edge;
import com.fxgraph.graph.ICell;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.RandomLayout;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import com.fxgraph.graph.Graph;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * HelloController
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

    public AppController appController;
    FileHandler fileHandler = new FileHandler("/home/alexanderthegreat/IdeaProjects/ija-project/proj1.json");

    private List<ClassPoints> classPoints = new ArrayList<>();
    private List<Position> points = new ArrayList<>();
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
        ResizeLine line = new ResizeLine(100,100,150,150, mainGroup, points);
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

        //FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("class.fxml"));
        //Pane pane = loader.load();
        //ClassController pane = new ClassController();



        /*pane.setLayoutX(150);
        pane.setLayoutY(150);*/

        //pane.set

        /*pane.setCursor(Cursor.HAND);

        pane.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            Pane r = (Pane) (t.getSource());
            r.toFront();
            System.out.println("PANE X:" + pane.getBoundsInParent().getCenterX());
            System.out.println("PANE Y:" + pane.getBoundsInParent().getCenterY());

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
        List<Position> p = new ArrayList<>();
        p = pane.getPos();
        ClassPoints cp = new ClassPoints(pane.getName(), p);
        classPoints.add(cp);
        points.add(p.get(0));
        points.add(p.get(1));
        points.add(p.get(2));
        points.add(p.get(3));*/

        /*Graph graph = new Graph();
        final Model model = graph.getModel();

        graph.beginUpdate();

        final ICell cellA = new RectangleCell();
        final ICell cellB = new RectangleCell();
        final ICell cellC = new RectangleCell();
        final ICell cellD = new TriangleCell();
        final ICell cellE = new TriangleCell();
        final ICell cellF = new RectangleCell();
        final ICell cellG = new RectangleCell();
        final ICell cell = new ClassCell();

        model.addCell(cellA);
        model.addCell(cellB);
        model.addCell(cellC);
        model.addCell(cellD);
        model.addCell(cellE);
        model.addCell(cellF);
        model.addCell(cellG);
        model.addCell(cell);

        final Edge edgeAB = new Edge(cellA, cellB);
        edgeAB.textProperty().set("Edges can have text too!");
        model.addEdge(edgeAB);
        final CorneredEdge edgeAC = new CorneredEdge(cellA, cellC, Orientation.HORIZONTAL);
        edgeAC.textProperty().set("Edges can have corners too!");
        model.addEdge(edgeAC);
        model.addEdge(cellB, cellD);
        final DoubleCorneredEdge edgeBE = new DoubleCorneredEdge(cellB, cellE, Orientation.HORIZONTAL);
        edgeBE.textProperty().set("You can implement custom edges and nodes too!");
        model.addEdge(edgeBE);
        model.addEdge(cellC, cellF);
        model.addEdge(cellC, cellG);

        final Edge edgeClass = new Edge(cellG, cell);
        edgeAB.textProperty().set("HAHAHAHAA");
        model.addEdge(edgeClass);

        graph.endUpdate();

        graph.layout(new RandomLayout());*/
    }

    @FXML
    private void openFile() {
        FileHandler fileHandler2 = new FileHandler("/home/alexanderthegreat/IdeaProjects/ija-project/proj1SAVED.json");
        UMLProject umlProject = this.fileHandler.read();
        umlProject.debugPrint();
        // here we need to call method for drawing that uml project
        fileHandler2.save(umlProject);
    }

    @FXML
    private void save() {

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
     * @param actionEvent
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

    public void generalization(ActionEvent actionEvent) {
        Generalization generalization = new Generalization(100,100,150,150, mainGroup);
    }
}