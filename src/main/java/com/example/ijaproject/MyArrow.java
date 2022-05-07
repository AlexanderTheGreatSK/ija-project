package com.example.ijaproject;

import com.fxgraph.edges.AbstractEdge;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class MyArrow extends AbstractEdge {

    private final transient StringProperty textProperty = new SimpleStringProperty();

    public MyArrow(ICell source, ICell target) {
        super(source, target);
    }

    public ArrowGraphic getGraphic(Graph graph) {
        return new ArrowGraphic(graph, this, this.textProperty);
    }

    public StringProperty textProperty() {
        return this.textProperty;
    }

    public static class ArrowGraphic extends Pane {
        private final Group group = new Group();
        private final Line line = new Line();
        private final Text text;

        public ArrowGraphic(Graph graph, MyArrow myArrow, StringProperty textProperty) {
            DoubleBinding sourceX = myArrow.getSource().getXAnchor(graph, myArrow);
            DoubleBinding sourceY = myArrow.getSource().getYAnchor(graph, myArrow);
            DoubleBinding targetX = myArrow.getTarget().getXAnchor(graph, myArrow);
            DoubleBinding targetY = myArrow.getTarget().getYAnchor(graph, myArrow);
            System.out.println("Source X:" + sourceX.get() + " Y:" + sourceY.get());
            System.out.println("Target X:" + targetX.get() + " Y:" + targetY.get());

            this.line.startXProperty().bind(sourceX);
            this.line.startYProperty().bind(sourceY);
            this.line.endXProperty().bind(targetX);
            this.line.endYProperty().bind(targetY);
            this.group.getChildren().add(this.line);
            DoubleProperty textWidth = new SimpleDoubleProperty();
            DoubleProperty textHeight = new SimpleDoubleProperty();
            this.text = new Text();
            this.text.textProperty().bind(textProperty);
            this.text.getStyleClass().add("edge-text");
            this.text.xProperty().bind(this.line.startXProperty().add(this.line.endXProperty()).divide(2).subtract(textWidth.divide(2)));
            this.text.yProperty().bind(this.line.startYProperty().add(this.line.endYProperty()).divide(2).subtract(textHeight.divide(2)));
            Runnable recalculateWidth = () -> {
                textWidth.set(this.text.getLayoutBounds().getWidth());
                textHeight.set(this.text.getLayoutBounds().getHeight());
            };
            this.text.parentProperty().addListener((obs, oldVal, newVal) -> {
                recalculateWidth.run();
            });
            this.text.textProperty().addListener((obs, oldVal, newVal) -> {
                recalculateWidth.run();
            });
            this.group.getChildren().add(this.text);
            this.getChildren().add(this.group);
        }

        public Group getGroup() {
            return this.group;
        }

        public Line getLine() {
            return this.line;
        }

        public Text getText() {
            return this.text;
        }
    }
}
