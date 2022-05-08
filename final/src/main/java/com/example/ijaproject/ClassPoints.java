package com.example.ijaproject;

import java.util.ArrayList;
import java.util.List;

public class ClassPoints {
    public String className;
    public List<Position> points;

    public ClassPoints(String name, List<Position> points) {
        this.className = name;
        this.points = points;
    }

}
