package com.example.ijaproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    private List<String> operationStack;
    private FileHandler fileHandler;

    private int top;

    public AppController() {
        this.operationStack = new ArrayList<>();
        this.top = -1;
        this.fileHandler = new FileHandler("/home/alex/Dokumenty/ija-project/proj1SAVED.json");
    }

    public void addPath(String path) {
        this.fileHandler = new FileHandler(path);
    }

    public void addOperation(UMLProject newVersionOfUMLProject) {
        Gson gson = new Gson();
        String out;
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        out = gson.toJson(newVersionOfUMLProject);
        System.out.println(out);
        this.operationStack.add(out);
        this.fileHandler.save(newVersionOfUMLProject);
        this.top++;
    }

    public UMLProject getTop() {
        Gson gson = new Gson();
        UMLProject umlProject = gson.fromJson(this.operationStack.get(this.top), UMLProject.class);
        return umlProject;
    }

    /*public void addClass(UMLClass newClas) {
        UMLProject umlProject = null;
        umlProject = this.operationStack.get(this.top);

        System.out.println("MAME:" + umlProject.classes.size());
        try {
            umlProject.addClass(newClas);
            this.addOperation(umlProject);
            this.top++;
        } catch (Exception e) {
            throw newRuntimeException(e);
        }
    }*/

    /*public void addMethod(String target, UMLAttribute method) {
        try {
            this.operationStack.get(this.top).addMethod(target, method);
        } catch (Exception e) {
            //handle error
            throw new RuntimeException(e);
        }
    }*/

    public void simulate() {

    }

    public void undo() {
        /*for(int i=this.top; i >= 0; i--) {
            System.out.println("UNDO:" + this.operationStack.get(i).classes.size());
        }*/
        if(operationStack.size() == 1) {
            this.fileHandler.clear();
        } else if(operationStack.size() > 1) {
            //System.out.println("Top classes: " + this.operationStack.get(this.top).classes.size() + ", before:" + this.operationStack.get(this.top-1).classes.size());
            this.operationStack.remove(this.top);
            this.top--;



            this.fileHandler.saveString(this.operationStack.get(this.top));
            System.out.println(this.top);
        }
    }

}
