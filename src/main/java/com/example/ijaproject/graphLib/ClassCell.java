package com.example.ijaproject.graphLib;

import com.example.ijaproject.UMLbe.UMLAttribute;
import com.fxgraph.cells.AbstractCell;
import com.fxgraph.cells.CellGestures;
import com.fxgraph.graph.Graph;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
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
        view.widthProperty().bind(pane.minWidthProperty());
        view.heightProperty().bind(pane.minHeightProperty());
        CellGestures.makeResizable(pane);

        return pane;
    }

    public void addAttribute(List<UMLAttribute> attributes) {
        this.classController.addAttributeBC(attributes);
    }
    public void addMethods(List<UMLAttribute> methods) {
        this.classController.addMethodsBC(methods);
    }

    public void setName(String newName) {
        this.classController.updateName(newName);
    }

    public String getName() {
        return this.classController.getName();
    }

}
