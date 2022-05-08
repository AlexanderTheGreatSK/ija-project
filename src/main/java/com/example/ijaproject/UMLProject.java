package com.example.ijaproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * UMLProject
 *
 * This constructor is backend for UML Project.
 *
 * @author xokruc00
 * @version 1.0
 */
public class UMLProject implements Cloneable {
    @Expose
    @SerializedName("ProjectName")
    public String projectName;

    @Expose
    @SerializedName("ClassDiagram")
    public List<UMLClass> classes = new ArrayList<UMLClass>();

    @Expose
    @SerializedName("SequenceDiagram")
    public List<UMLSequenceDiagram> sequenceDiagrams = new ArrayList<UMLSequenceDiagram>();

    public UMLProject(String projectName) {
        this.projectName = projectName;
    }

    public UMLProject(String projectName, List<UMLClass> classes) {
        this.projectName = projectName;
        this.classes = classes;
    }

    public UMLProject(String projectName, List<UMLClass> classes, List<UMLSequenceDiagram> sequenceDiagrams) {
        this.projectName = projectName;
        this.classes = classes;
        this.sequenceDiagrams = sequenceDiagrams;
    }

    public void addClass(UMLClass newClass) throws Exception {
        if(this.classes.isEmpty()) {
            this.classes.add(newClass);
        } else {
            System.out.println(this.classExists(newClass.name));
            if(this.classExists(newClass.name)) {
                throw new Exception();
            } else {
                this.classes.add(newClass);
            }
        }
    }

    public UMLProject clone() {
        final UMLProject c;
        try {
            c = (UMLProject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("superclass messed up", e);
        }
        return c;
    }
    public boolean classExists(String name) {
        if(this.getClass(name) == null) {
            return false;
        }
        return true;
    }


    public UMLClass getClass(String name) {
        if(this.classes.isEmpty()) {
            return null;
        } else {
            for(int i = 0; i < this.classes.size(); i++) {
                if(Objects.equals(name, this.classes.get(i).name)) {
                    return this.classes.get(i);
                }
            }
            return null;
        }
    }

    public void addMethod(String targetName, UMLAttribute method) throws Exception {
        try {
            this.getClass(targetName).addMethod(method);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void debugPrint() {
        List<UMLAttribute> attributes;
        List<UMLAttribute> methods;
        Map<String, Double> pos;
        UMLOperation operation;

        System.out.println("---------------------------------------------------------------");
        System.out.println("Name of project: " + this.projectName);
        for(int i = 0; i < this.classes.size(); i++) {
            System.out.println("---------------------------------------------------------------");
            System.out.println(i + " Class name: " + this.classes.get(i).getName());
            pos = this.classes.get(i).getPosition();
            System.out.println(i + " Class X: " + pos.get("X") + " Y: " + pos.get("Y"));
            //operation = this.classes.get(i).getOperation();
            /*if(operation != null) {
                System.out.println(i + " Class operation: " + operation.getName() + " target: " + operation.getTarget());
            } else {
                System.out.println("Operation null");
            }*/
            attributes = this.classes.get(i).getAttributes();
            methods = this.classes.get(i).getMethods();
            if(attributes != null) {
                for(int j = 0; j < attributes.size(); j++) {
                    System.out.println(j + " Attribute name: " + attributes.get(j).getName() + " is public: " + attributes.get(j).getIsPublic());
                }
            } else {
                System.out.println("Arguments null");
            }
            if(methods != null) {
                for(int j = 0; j < methods.size(); j++) {
                    System.out.println(j + " Method name: " + methods.get(j).getName() + " is public: " + methods.get(j).getIsPublic());
                }
            } else {
                System.out.println("Methods null");
            }
        }
        System.out.println("---------------------------------------------------------------");
    }

}
