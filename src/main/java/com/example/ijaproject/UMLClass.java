package com.example.ijaproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;

/**
 * UMLClass
 *
 * This constructor is backend for UML Class.
 *
 * @author xokruc00
 * @version 1.0
 */
public class UMLClass {
    @Expose
    @SerializedName("Name")
    public String name;
    @Expose
    @SerializedName("Operation")
    public UMLOperation operation;

    @Expose
    @SerializedName("X")
    public double posX;
    @Expose
    @SerializedName("Y")
    public double posY;

    @Expose
    @SerializedName("Attributes")
    public List<UMLAttributes> attributes = new ArrayList<>();
    @Expose
    @SerializedName("Methods")
    public List<UMLAttributes> methods = new ArrayList<>();


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
        position.put("X", posX);
        position.put("Y", posY);

        return position;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setOperation(UMLOperation operation) {
        this.operation = operation;
    }

    public UMLOperation getOperation() {
        if(this.operation == null) {
            return null;
        } else {
            return this.operation;
        }

    }

    public void removeOperation() {
        this.operation = null;
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
