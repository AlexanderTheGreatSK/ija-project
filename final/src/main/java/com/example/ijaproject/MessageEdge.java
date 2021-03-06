package com.example.ijaproject;

import com.fxgraph.edges.AbstractEdge;
import com.fxgraph.graph.Graph;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * MessageEdge
 *
 * Part of code for lib com.sirolf2009:fxgraph:0.0.3 (source: https://github.com/sirolf2009/fxgraph)
 *
 * @author  sirolf2009, xokruc00, xkovac59 (we edited parts from library as we needed)
 * @version 2.0
 */
public class MessageEdge extends AbstractEdge implements SequenceDiagram.IMessageEdge {

    private final String name;
    private final DoubleProperty yOffsetProperty = new SimpleDoubleProperty();

    public MessageEdge(SequenceDiagram.IActorCell source, SequenceDiagram.IActorCell target, String name) {
        super(source, target);
        this.name = name;
    }

    @Override
    public DoubleProperty yOffsetProperty() {
        return yOffsetProperty;
    }

    @Override
    public Region getGraphic(Graph graph) {
        return new EdgeGraphic(graph, this);
    }

    private static class EdgeGraphic extends AbstractEdgeGraphic {
        public EdgeGraphic(Graph graph, MessageEdge edge) {
            final DoubleBinding sourceX = edge.getSource().getXAnchor(graph, edge);
            final DoubleBinding sourceY = edge.getSource().getYAnchor(graph, edge).add(edge.yOffsetProperty);
            final DoubleBinding targetX = edge.getTarget().getXAnchor(graph, edge);
            final DoubleBinding targetY = edge.getTarget().getYAnchor(graph, edge).add(edge.yOffsetProperty);

            arrow.getStyleClass().add("arrow");

            arrow.startXProperty().bind(sourceX);
            arrow.startYProperty().bind(sourceY);

            arrow.endXProperty().bind(targetX);
            arrow.endYProperty().bind(targetY);

            group.getChildren().add(arrow);

            final DoubleProperty textWidth = new SimpleDoubleProperty();
            final DoubleProperty textHeight = new SimpleDoubleProperty();

            Text text = new Text(edge.name);
            text.getStyleClass().add("edge-text");
            text.xProperty().bind(sourceX.add(targetX).divide(2).subtract(textWidth.divide(2)));
            text.yProperty().bind(sourceY.add(targetY).divide(2).subtract(textHeight.divide(2)));
            final Runnable recalculateWidth = () -> {
                textWidth.set(text.getLayoutBounds().getWidth());
                textHeight.set(text.getLayoutBounds().getHeight());
            };
            text.parentProperty().addListener((obs, oldVal, newVal) -> recalculateWidth.run());
            text.textProperty().addListener((obs, oldVal, newVal) -> recalculateWidth.run());
            group.getChildren().add(text);
            getChildren().add(group);
            getStyleClass().add("message-edge");
        }
    }
}

