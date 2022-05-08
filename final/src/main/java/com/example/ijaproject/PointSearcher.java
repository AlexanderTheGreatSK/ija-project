package com.example.ijaproject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class PointSearcher {
    public List<Double> xPoints;
    public List<Double> yPoints;

    PointSearcher(List<Position> points) {
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();

        for(int i = 0; i < points.size(); i++) {
            xPoints.add(points.get(i).X);
            yPoints.add(points.get(i).Y);
        }
    }

    public Position search(double xVal, double yVal) {
        for(int i = 0; i < xPoints.size(); i++) {
            if(abs(xVal - xPoints.get(i)) < 15.00 ||  abs(yVal - yPoints.get(i)) < 15.00) {
                return new Position(xPoints.get(i), yPoints.get(i));
            }
        }
        return null;
    }

}
