package com.example.ijaproject;

import java.util.ArrayList;
import java.util.List;

/**
 * UMLProject
 *
 * This constructor is backend for UML Project.
 *
 * @author xokruc00
 * @version 1.0
 */
public class UMLProject {
    public String projectName;

    private List<UMLClass> classes = new ArrayList<UMLClass>();

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

}
