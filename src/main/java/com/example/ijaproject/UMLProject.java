package com.example.ijaproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * UMLProject
 *
 * This constructor is backend for UML Project.
 *
 * @author xokruc00
 * @version 1.0
 */
public class UMLProject {
    @Expose
    @SerializedName("ProjectName")
    public String projectName;

    @Expose
    @SerializedName("ClassDiagram")
    public List<UMLClass> classes = new ArrayList<UMLClass>();

    public UMLProject(String projectName) {
        this.projectName = projectName;
    }

    public UMLProject(String projectName, List<UMLClass> classes) {
        this.projectName = projectName;
        this.classes = classes;
    }

    public void addClass(UMLClass newClass) throws Exception {
        if(classes.isEmpty()) {
            classes.add(newClass);
        } else {
            for(int i = 0; i < classes.size(); i++) {
                if(newClass.getName() == classes.get(i).getName()) {
                    throw new Exception();
                }
            }
            this.classes.add(newClass);
        }
    }

    public void debugPrint() {
        List<UMLAttributes> attributes;
        List<UMLAttributes> methods;
        Map<String, Double> pos;
        UMLOperation operation;

        System.out.println("---------------------------------------------------------------");
        System.out.println("Name of project: " + this.projectName);
        for(int i = 0; i < this.classes.size(); i++) {
            System.out.println("---------------------------------------------------------------");
            System.out.println(i + " Class name: " + this.classes.get(i).getName());
            pos = this.classes.get(i).getPosition();
            System.out.println(i + " Class X: " + pos.get("X") + " Y: " + pos.get("Y"));
            operation = this.classes.get(i).getOperation();
            if(operation != null) {
                System.out.println(i + " Class operation: " + operation.getName() + " target: " + operation.getTarget());
            } else {
                System.out.println("Operation null");
            }
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
