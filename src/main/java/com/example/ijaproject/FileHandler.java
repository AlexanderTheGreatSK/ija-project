package com.example.ijaproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * FileHandler
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
     */
    public UMLProject read() {

        System.out.println("Reading file");

        Gson gson = new Gson();
        UMLProject umlProject;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(this.file));

            Map<String, ?> map = gson.fromJson(reader, Map.class);
            umlProject = new UMLProject((String) map.get("ProjectName"));
            Map<?, ?> classDiagram = (Map<?, ?>) map.get("ClassDiagram");

            for (Map.Entry<?, ?> entry : classDiagram.entrySet()) {
                UMLClass umlClass = new UMLClass(entry.getKey().toString());
                Map<? , ?> content = (Map<?, ?>) entry.getValue();

                System.out.println(content);
                umlClass.updatePosition((double) content.get("X"), (double) content.get("Y"));
                System.out.println(content.get("X").toString() + " " + content.get("Y").toString());
                if(content.get("Operation") != null && content.get("Operation") != "null") {
                    Map<? , ?> operation = (Map<?, ?>) content.get("Operation");
                    umlClass.setOperation(new UMLOperation((String) operation.get("Name"), (String) operation.get("Target")));
                }
                System.out.println("HERE");

                List<Map<? , ?>> attributes = (List<Map<? , ?>>) content.get("Attributes");

                for(int i = 0; i < attributes.size(); i++) {
                    umlClass.addAttribute(new UMLAttributes((String) attributes.get(i).get("Name"), (boolean) attributes.get(i).get("IsPublic")));
                }

                List<Map<? , ?>> methods = (List<Map<? , ?>>) content.get("Methods");

                for(int i = 0; i < methods.size(); i++) {
                    umlClass.addMethod(new UMLAttributes((String) methods.get(i).get("Name"), (boolean) methods.get(i).get("IsPublic")));
                }
                try {
                    umlProject.addClass(umlClass);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            reader.close();
            return umlProject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
