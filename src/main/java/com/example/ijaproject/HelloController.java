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

}