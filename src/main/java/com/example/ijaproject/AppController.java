package com.example.ijaproject;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    private List<UMLProject> operationStack;
    private FileHandler fileHandler;

    private int top;

    public AppController(String pathToFile) {
        this.operationStack = new ArrayList<UMLProject>();
        this.fileHandler = new FileHandler(pathToFile);
        this.top = 0;
    }

    public void addOperation(UMLProject newVersionOfUMLProject) {
        operationStack.add(newVersionOfUMLProject);
        fileHandler.save(newVersionOfUMLProject);
        this.top++;
    }

    public void addClass(String name, double X, double Y) {
        UMLClass umlClass = new UMLClass(name, X, Y);
        UMLProject umlProject = operationStack.get(top);
        try {
            umlProject.addClass(umlClass);
            this.addOperation(umlProject);
        } catch (Exception e) {
            // TODO error handling to FE
            throw new RuntimeException(e);
        }
    }

    public void addMethod(String target, UMLAttribute method) {
        try {
            this.operationStack.get(this.top).addMethod(target, method);
        } catch (Exception e) {
            //handle error
            throw new RuntimeException(e);
        }
    }

    public void simulate() {

    }

    public void undo() {

        if(operationStack.size() == 1) {
            fileHandler.clear();
            // TODO update UI
        } else if(operationStack.size() > 1) {
            operationStack.remove(this.top);
            this.top--;
            fileHandler.save(operationStack.get(this.top));
            // TODO update UI
        }
    }

}
