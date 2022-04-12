package com.example.ijaproject;

public class FileController {
    private String pathToFIle;

    public FileController(String pathToFIle) {
        this.pathToFIle = pathToFIle;
    }

    public void updatePathToFIle(String newPath) {
        this.pathToFIle = newPath;
    }



}
