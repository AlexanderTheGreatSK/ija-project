package com.example.ijaproject;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;

/**
 * @brief UMLClass
 *
 * This constructor is backend for UML Class.
 *
 * @author xokruc00
 * @version 1.0
 */
public class UMLClass {
    private String name;
    private UMLClass superClass;

    private double posX;
    private double posY;

    private List<UMLAttributes> attributes = new ArrayList<>();
    private List<UMLAttributes> methods = new ArrayList<>();


    public UMLClass(String name, double posX, double posY) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
    }

    public UMLClass(String name) {
        this.name = name;
    }

    public void updatePosition(double newPosX, double newPosY) {
        this.posX = newPosX;
        this.posY = newPosY;
    }

    public Map<String, Double> getPosition() {
        Map<String, Double> position = new IdentityHashMap<String, Double>();
        position.put("x", posX);
        position.put("y", posY);

        return position;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    //TODO change
    public void setSuperClass(UMLClass superClass) {
        this.superClass = superClass;
    }

    public void addAttribute(UMLAttributes attribute) {
        this.attributes.add(attribute);
    }

    public void removeAttribute(UMLAttributes attribute) {
        this.attributes.remove(attribute);
    }

    public void updateAttribute(UMLAttributes oldAttribute, UMLAttributes newAttribute) {
        int oldIndex = this.attributes.indexOf(oldAttribute);
        this.attributes.set(oldIndex, newAttribute);
    }

    public List<UMLAttributes> getAttributes() {
        return attributes;
    }
    //------------------------------------------------------------------------------------------------------------------
    public void addMethod(UMLAttributes Method) {
        this.methods.add(Method);
    }

    public void removeMethod(UMLAttributes Method) {
        this.methods.remove(Method);
    }

    public void updateMethod(UMLAttributes oldMethod, UMLAttributes newMethod) {
        int oldIndex = this.attributes.indexOf(oldMethod);
        this.methods.set(oldIndex, newMethod);
    }

    public List<UMLAttributes> getMethods() {
        return methods;
    }



}
