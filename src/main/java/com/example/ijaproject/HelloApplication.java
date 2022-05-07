package com.example.ijaproject;

import com.fxgraph.cells.RectangleCell;
import com.fxgraph.cells.TriangleCell;
import com.fxgraph.edges.CorneredEdge;
import com.fxgraph.edges.DoubleCorneredEdge;
import com.fxgraph.edges.Edge;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.RandomLayout;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
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
    @Override
    public void start(Stage primaryStage) {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("UML");
        primaryStage.setScene(scene);
        primaryStage.show();*/

        FileHandler fileHandler = new FileHandler("/home/alexanderthegreat/IdeaProjects/ija-project/proj1.json");
        UMLProject umlProject = fileHandler.read();

        List<UMLClass> lc = umlProject.classes;

        Graph graph = new Graph();
        final Model model = graph.getModel();

        graph.beginUpdate();

        List<ICell> cells = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        for(int i=0; i<lc.size(); i++) {
            ClassController classController = new ClassController();
            classController.updateName(lc.get(i).name);
            classController.addAttributeBC(lc.get(i).attributes);
            ICell cell = new ClassCell(classController);
            model.addCell(cell);
        }

        /*final ICell cellA = new RectangleCell();
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
        model.addCell(cell);*/

        /*final Edge edgeAB = new Edge(cellA, cellB);
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
        edgeClass.textProperty().set("HAHAHAHAA");
        model.addEdge(edgeClass);*/

        graph.endUpdate();

        graph.layout(new RandomLayout());
        primaryStage.setScene(new Scene(new BorderPane(graph.getCanvas())));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}