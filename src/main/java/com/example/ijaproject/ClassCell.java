package com.example.ijaproject;

import com.fxgraph.cells.AbstractCell;
import com.fxgraph.cells.CellGestures;
import com.fxgraph.graph.Graph;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ClassCell extends AbstractCell {

    public ClassCell() {}

    public Region getGraphic(Graph graph) {
        Rectangle view = new Rectangle(200, 200);
        Pane pane = new ClassController();
        view.widthProperty().bind(pane.prefWidthProperty());
        view.heightProperty().bind(pane.prefHeightProperty());
        CellGestures.makeResizable(pane);
        return pane;
    }

}
