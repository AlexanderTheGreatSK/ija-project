package com.example.ijaproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.input.MouseEvent;
import javafx.scene.Cursor;

import java.io.IOException;

public class Composition extends Line {

    private Polygon start;
    private Circle end;
    private Line left;
    private Line right;

    private double sX;
    private double sY;
    private double eX;
    private double eY;

    private Color active = Color.WHITE;
    private Color inActive = Color.BLACK;

    /**
     * TBD, code from resize line
     *
     */
    public Composition(double startX, double startY, double endX, double endY,  Group group) {
        super(startX, startY, endX, endY);
        super.setStrokeWidth(2);

        start = new Polygon();
        start.getPoints().addAll(startX, startY,
                startX + 15.0, startY + 10.0,
                startX, startY + 20.0,
                startX + 20.0 - 35.0, startY + 20.0 - 10.0);

        end = new Circle(endX, endY, 3);
        end.setStroke(inActive);
        end.setFill(inActive);

        left = new Line();
        left.setStartX(endX);
        left.setEndX(endX + 2);
        left.setStartY(endX);
        left.setEndX(endY + 2);
        
        right = new Line();

        group.getChildren().add(this);
        group.getChildren().add(start);
        group.getChildren().add(end);
        group.getChildren().add(left);
        group.getChildren().add(right);


        super.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            start.setStroke(active);
            start.setFill(active);
            end.setStroke(active);
            end.setFill(active);
        });

        super.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            start.setStroke(active);
            start.setFill(inActive);
            end.setStroke(inActive);
            end.setFill(inActive);
        });

        start.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            start.setStroke(active);
            start.setFill(active);
            end.setStroke(active);
            end.setFill(active);
        });

        start.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            start.setStroke(active);
            start.setFill(inActive);
            end.setStroke(inActive);
            end.setFill(inActive);
        });

        end.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            start.setStroke(active);
            start.setFill(active);
            end.setStroke(active);
            end.setFill(active);
        });

        end.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            start.setStroke(active);
            start.setFill(inActive);
            end.setStroke(inActive);
            end.setFill(inActive);
        });

        start.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            sX = event.getSceneX();
            sY = event.getSceneY();
        });

        end.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            eX = event.getSceneX();
            eY = event.getSceneY();
        });


        start.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double offsetSX= event.getSceneX() - sX;
            double offsetSY= event.getSceneY() - sY;

            Polygon p = (Polygon) (event.getSource());

            p.setLayoutX(p.getLayoutX() + offsetSX);
            p.setLayoutY(p.getLayoutY() + offsetSY);

            super.setStartX(p.getLayoutX() + 87.0);
            super.setStartY(p.getLayoutY() + 110.0);

            sX = event.getSceneX();
            sY = event.getSceneY();
        });

        end.addEventHandler(MouseEvent.MOUSE_DRAGGED, event ->  {
            double offsetEX = event.getSceneX() - eX;
            double offsetEY = event.getSceneY() - eY;

            Circle c = (Circle) (event.getSource());

            super.setEndX(c.getCenterX() + offsetEX);
            super.setEndY(c.getCenterY() + offsetEY);


            c.setCenterX(c.getCenterX() + offsetEX);
            c.setCenterY(c.getCenterY() + offsetEY);

            eX = event.getSceneX();
            eY = event.getSceneY();
        });

        super.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            Line r = (Line) (event.getSource());
            r.toFront();
            start.toFront();
            end.toFront();
        });
    }
}