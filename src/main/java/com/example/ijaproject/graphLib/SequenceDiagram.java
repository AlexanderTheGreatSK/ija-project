package com.example.ijaproject.graphLib;

import com.example.ijaproject.controller.AppController;
import com.example.ijaproject.UMLbe.UMLProject;
import com.fxgraph.cells.AbstractCell;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import com.fxgraph.graph.IEdge;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SequenceDiagram
 *
 * Part of code for lib com.sirolf2009:fxgraph:0.0.3 (source: https://github.com/sirolf2009/fxgraph)
 *
 * @author  sirolf2009, xokruc00, xkovac59 (we changed part of library as we needed)
 * @version 2.0
 */
public class SequenceDiagram extends Graph {

    private double verticalSpacing = 200;
    private double horizontalSpacing = 50;

    private List<IActorCell> actors = new ArrayList<>();
    private List<IMessageEdge> messages = new ArrayList<>();

    public void addActor(String actor, double length, Pane pane, UMLProject umlProject, AppController appController) {
        addActor(new ActorCell(actor, new SimpleDoubleProperty(length), pane, umlProject, appController));
    }

    public void addActor(IActorCell actor) {
        actors.add(actor);
        getModel().addCell(actor);
        endUpdate();
    }

    public void addMessage(IActorCell source, IActorCell target, String name) {
        addMessagee(new MessageEdge(source, target, name));
    }

    public void addMessagee(IMessageEdge edge) {
        messages.add(edge);
        getModel().addEdge(edge);
        endUpdate();
    }

    public void layout() {
        AtomicInteger counter = new AtomicInteger();
        actors.stream().map(actor -> getGraphic(actor)).forEach(actor -> {
            actor.setLayoutX(counter.getAndIncrement()*verticalSpacing);
            actor.setLayoutY(0);
            actor.toFront();
        });

        counter.set(0);
        messages.forEach(edge -> {
            edge.yOffsetProperty().set(counter.incrementAndGet() * horizontalSpacing);
        });
    }

    public double getVerticalSpacing() {
        return verticalSpacing;
    }

    public void setVerticalSpacing(double verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    public double getHorizontalSpacing() {
        return horizontalSpacing;
    }

    public void setHorizontalSpacing(double horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    public List<IActorCell> getActors() {
        return actors;
    }

    public List<IMessageEdge> getMessages() {
        return messages;
    }

    public interface IActorCell extends ICell {
        String getName();

    }

    public interface IMessageEdge extends IEdge {

        DoubleProperty yOffsetProperty();

    }

    public static class ActorCell extends AbstractCell implements IActorCell {
        private TextField textField;
        private String name;
        private String oldName;
        private DoubleProperty lifeLineLength;
        public Pane pane;
        private Line lifeLine;
        private Line dots;

        private final String black = "#000000";
        private final String red = "#ec7063";
        private final String blue = "#89CFF0";
        private UMLProject umlProject;
        private AppController appController;


        public ActorCell(String name, Double lifeLineLength, Pane pane, UMLProject umlProject, AppController appController) {
            this(name, new SimpleDoubleProperty(lifeLineLength), pane, umlProject, appController);
        }

        public ActorCell(String name, DoubleProperty lifeLineLength, Pane pane, UMLProject umlProject, AppController appController) {
            this.name = name;
            this.oldName = name;
            this.appController = appController;
            this.umlProject = umlProject;
            this.lifeLineLength = lifeLineLength;
            this.textField = new TextField();
            this.pane = pane;
        }

        public void selected() {
            this.textField.setStyle("-fx-text-fill: blue;");
        }

        public void unselected() {
            this.textField.setStyle("-fx-text-fill: black;");
        }

        public String getActorName() {
            return this.name;
        }

        public DoubleProperty getActorLife() {
            return this.lifeLineLength;
        }

        public void updateName(String newName) {
            this.name = newName;
        }

        public void updateLife(DoubleProperty newLife) {
            this.lifeLineLength = newLife;
        }

        @Override
        public Region getGraphic(Graph graph) {
            this.textField.setText(name);

            this.lifeLine = new Line();
            this.dots = new Line();
            this.lifeLine.getStyleClass().add("life-line");
            this.dots.getStyleClass().add("life-line");
            this.lifeLine.startXProperty().bind(this.textField.widthProperty().divide(2));
            this.dots.startXProperty().bind(this.textField.widthProperty().divide(2));
            this.lifeLine.setStartY(35);
            this.dots.setStartY(25);
            this.lifeLine.endXProperty().bind(this.textField.widthProperty().divide(2));
            this.dots.endXProperty().bind(this.textField.widthProperty().divide(2));
            this.lifeLine.endYProperty().bind(lifeLineLength);
            this.dots.endYProperty().bind(lifeLineLength.add(20));
            this.dots.getStrokeDashArray().addAll(25d, 10d);
            this.lifeLine.setStrokeWidth(10);
            this.lifeLine.setStroke(Color.web(blue));
            this.lifeLine.setStrokeLineCap(StrokeLineCap.SQUARE);

            textField.textProperty().addListener(e ->  {
                this.updateName(this.textField.getText());
            });

            this.textField.focusedProperty().addListener((obs, oldP, newP) -> {
                String newName = this.textField.getText();
                if(Objects.equals(newName, oldName)) {
                    return;
                }
                if(umlProject.participantExists(newName)) {
                    this.lifeLine.setStroke(Color.web(red));
                } else {
                    if(!newP ) {
                        this.lifeLine.setStroke(Color.web(blue));
                        this.umlProject.getClass(this.oldName).updateName(newName);
                        this.appController.addOperation(umlProject);
                        this.oldName = this.getName();
                    }
                }
            });

            this.pane.getChildren().addAll(this.textField, lifeLine, dots);
            this.pane.getStyleClass().add("actor-cell");
            return pane;
        }

        public void setRed() {
            this.lifeLine.setStroke(Color.web(red));
        }

        public void setBlue() {
            this.lifeLine.setStroke(Color.web(blue));
        }

        public DoubleBinding getXAnchor(Graph graph) {
            final Region graphic = graph.getGraphic(this);
            final TextField textField = (TextField) graphic.getChildrenUnmodifiable().get(0);
            return graphic.layoutXProperty().add(textField.widthProperty().divide(2));
        }

        public DoubleBinding getYAnchor(Graph graph) {
            final Region graphic = graph.getGraphic(this);
            final TextField textField = (TextField) graphic.getChildrenUnmodifiable().get(0);
            return graphic.layoutYProperty().add(textField.heightProperty().divide(2));
        }

        public String getName() {
            return name;
        }

    }
}