package com.example.ijaproject.controller;

import com.example.ijaproject.UMLbe.UMLProject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * FileHandler
 *
 * This controller is json handler from input file and json generator to output file.
 *
 * @author xokruc00
 * @version 2.0
 */
public class FileHandler {
    private String file;

    public FileHandler(String file) {
        this.file = file;
    }

    //public FileHandler() {}

    /**
     * Method to read and parse json from file to UMLProject class
     *
     */
    public UMLProject read() {

        System.out.println("Reading file");
        try {
            Reader reader = Files.newBufferedReader(Paths.get(this.file));
            System.out.println(reader.toString());
            Gson gson = new Gson();
            UMLProject umlProject = gson.fromJson(reader, UMLProject.class);
            umlProject.debugPrint();
            return umlProject;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves UMLProject to file
     *
     * @param umlProject
     */
    public void save(UMLProject umlProject) {
        Gson gson = new Gson();
        String out;

        if(umlProject != null) {
            gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            out = gson.toJson(umlProject);
            System.out.println(out);
        } else {
            out = "";
        }

        try {
            FileWriter myWriter = new FileWriter(this.file);
            myWriter.write(out);
            myWriter.close();
            System.out.println(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveString(String text) {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(this.file);
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void clear() {
        String out = "";
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(this.file);
            myWriter.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
