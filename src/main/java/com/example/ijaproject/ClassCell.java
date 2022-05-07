package com.example.ijaproject;

import com.fxgraph.cells.AbstractCell;
import com.fxgraph.cells.CellGestures;
import com.fxgraph.graph.Graph;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class ClassCell extends AbstractCell {
    public ClassController classController;

    public ClassCell(ClassController classController) {
        this.classController = classController;
    }

    public Region getGraphic(Graph graph) {
        Rectangle view = new Rectangle(200, 200);
        Pane pane = classController;
        view.widthProperty().bind(pane.prefWidthProperty());
        view.heightProperty().bind(pane.prefHeightProperty());
        CellGestures.makeResizable(pane);

        return pane;
    }

    public void addAttribute(List<UMLAttribute> attributes) {
        this.classController.addAttributeBC(attributes);
    }

    public void setName(String newName) {
        this.classController.updateName(newName);
    }

}
