package com.example.ijaproject;

public class UMLOperation {
    private String name;
    private String target;

    public UMLOperation(String name, String target) {
        this.name = name;
        this.target = target;
    }

    public void updateName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }

    public String getTarget() {
        return this.target;
    }

    public void updateTarget(String newTarget) {
        this.target = newTarget;
    }


}
