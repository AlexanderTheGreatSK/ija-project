package com.example.ijaproject;

//TODO maybe delete, not up to date

public class FileController {
    private String pathToFIle;

    public FileController(String pathToFile) {
        this.pathToFIle = pathToFile;
    }


    public void updatePathToFile(String newPath) {
        this.pathToFIle = newPath;
    }



}
