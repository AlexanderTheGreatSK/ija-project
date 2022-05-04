package com.example.ijaproject;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.Cursor;

/**
 * @brief HelloController
 *
 * This constructor is backend for creating resizable line.
 *
 * @author xkovac59, xokruc00
 * @version 1.0
 */
public class ResizeLine extends Line {

    //private double mouseClickPozX;
    //private double mouseClickPozY;

    private Circle start;
    private Circle end;

    private double lineX;
    private double lineY;

    private double sX;
    private double sY;
    private double eX;
    private double eY;

    private Color active = Color.BLACK;
    private Color inActive = Color.WHITE;

    /**
     * Method which handles creating resizable line. Line has circles at the ends which allows resizing and moving line.
     *
     */
    public ResizeLine(double startX, double startY, double endX, double endY,  Group group) {
        super(startX, startY, endX, endY);
        super.setStrokeWidth(2);

        start = new Circle(startX, startY, 5);
        start.setStroke(inActive);
        start.setFill(inActive);
        end = new Circle(endX, endY, 5);
        end.setStroke(inActive);
        end.setFill(inActive);

        group.getChildren().add(this);
        group.getChildren().add(start);
        group.getChildren().add(end);

        super.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            start.setStroke(active);
            start.setFill(active);
            end.setStroke(active);
            end.setFill(active);
        });

        super.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            start.setStroke(inActive);
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
            start.setStroke(inActive);
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
            start.setStroke(inActive);
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

            Circle c = (Circle) (event.getSource());

            super.setStartX(c.getCenterX() + offsetSX);
            super.setStartY(c.getCenterY() + offsetSY);

            c.setCenterX(c.getCenterX() + offsetSX);
            c.setCenterY(c.getCenterY() + offsetSY);

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
            lineX = event.getSceneX();
            lineY = event.getSceneY();

            Line r = (Line) (event.getSource());
            r.toFront();
            start.toFront();
            end.toFront();
        });

        start.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {

        });

        /*super.addEventHandler(MouseEvent.MOUSE_DRAGGED, event ->  {
            double offsetX = event.getSceneX() - lineX;
            double offsetY = event.getSceneY() - lineY;

            Line r = (Line) (event.getSource());

            r.setLayoutX(r.getLayoutX() + offsetX);
            r.setLayoutY(r.getLayoutY() + offsetY);

            start.setCenterX(r.getStartX());
            start.setCenterY(r.getStartY());

            end.setCenterX(r.getEndX());
            end.setCenterY(r.getEndY());

            sX = start.getCenterX();
            sY = start.getCenterY();

            eX = end.getCenterX();
            eY = end.getCenterY();

            lineX = event.getSceneX();
            lineY = event.getSceneY();
        });

        Line moveLine = new Line(0,0,0,0);

        group.getChildren().add(this);

        moveLine.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            moveLine.getParent().setCursor(Cursor.HAND);
        });

        moveLine.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            moveLine.getParent().setCursor(Cursor.MOVE);
            mouseClickPozX = event.getX();
            mouseClickPozY = event.getY();
        });

        moveLine.addEventHandler(MouseEvent.MOUSE_RELEASED, event ->
                moveLine.getParent().setCursor(Cursor.HAND));

        moveLine.addEventHandler(MouseEvent.MOUSE_EXITED, event ->
                moveLine.getParent().setCursor(Cursor.DEFAULT));

        moveLine.addEventHandler(MouseEvent.MOUSE_DRAGGED,event -> {

        });*/


        //group
    }


}
