package com.example.ijaproject;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @brief FileHandler
 *
 * This controller is json handler from input file and json generator to output file.
 *
 * @author xokruc00
 * @version 1.0
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
     * @brief pathToFile
     */
    public UMLProject read() {

        Gson gson = new Gson();
        UMLProject umlProject;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(this.file));

            Map<String, ?> map = gson.fromJson(reader, Map.class);

            umlProject = new UMLProject((String) map.get("ProjectName"));

            Map<?, ?> project = (Map<?, ?>) map.get("Project");
            Map<?, ?> classDiagram = (Map<?, ?>) project.get("ClassDiagram");

            for (Map.Entry<?, ?> entry : classDiagram.entrySet()) {
                UMLClass umlClass = new UMLClass(entry.getKey().toString());
                Map<? , ?> content = (Map<?, ?>) entry.getValue();

                System.out.println(content);
                umlClass.updatePosition(Double.parseDouble(content.get("X").toString()),Double.parseDouble(content.get("Y").toString()));

                //umlClass.setSuperClass();
            }



            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new UMLProject("sd");
    }


}
