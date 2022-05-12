package com.example.ijaproject.UMLbe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.*;

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
    public List<UMLOperation> operations;

    @Expose
    @SerializedName("X")
    public double posX;
    @Expose
    @SerializedName("Y")
    public double posY;

    @Expose
    @SerializedName("Attributes")
    public List<UMLAttribute> attributes = new ArrayList<>();
    @Expose
    @SerializedName("Methods")
    public List<UMLAttribute> methods = new ArrayList<>();


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
        this.operations.add(operation);
    }

    public List<UMLOperation> getOperation() {
        if(this.operations == null) {
            return null;
        } else {
            return this.operations;
        }

    }

    public void removeOperation() {
        //this.operation = null;
    }

    public void addAttribute(UMLAttribute attribute) throws Exception {

        if(this.attributeExists(attribute.name)) {
            throw new Exception();
        } else {
            this.attributes.add(attribute);
        }
    }

    public void removeAttribute(UMLAttribute attribute) {
        this.attributes.remove(attribute);
    }

    public void updateAttribute(UMLAttribute oldAttribute, UMLAttribute newAttribute) {
        int oldIndex = this.attributes.indexOf(oldAttribute);
        this.attributes.set(oldIndex, newAttribute);
    }

    public UMLAttribute getAttribute(String name) {
        if(this.attributes.isEmpty()) {
            return null;
        }

        for(int i = 0; i < this.attributes.size(); i++) {
            if(Objects.equals(this.attributes.get(i).name, name)) {
                return this.attributes.get(i);
            }
        }
        return null;
    }

    public boolean attributeExists(String name) {
        if(this.getAttribute(name) == null) {
            return false;
        } else {
            return true;
        }
    }

    public List<UMLAttribute> getAttributes() {
        return attributes;
    }
    //------------------------------------------------------------------------------------------------------------------
    public void addMethod(UMLAttribute method) throws Exception {
        if(this.methodExists(method.name)) {
            throw new Exception();
        } else {
            this.methods.add(method);
        }
    }

    public void removeMethod(UMLAttribute Method) {
        this.methods.remove(Method);
    }

    public void updateMethod(UMLAttribute oldMethod, UMLAttribute newMethod) {
        int oldIndex = this.methods.indexOf(oldMethod);
        this.methods.set(oldIndex, newMethod);
    }

    public UMLAttribute getMethods(String name) {
        if(this.methods.isEmpty()) {
            return null;
        }

        for(int i = 0; i < this.methods.size(); i++) {
            if(Objects.equals(this.methods.get(i).name, name)) {
                return this.methods.get(i);
            }
        }
        return null;
    }

    public boolean methodExists(String name) {
        if(this.getMethods(name) == null) {
            return false;
        } else {
            return true;
        }
    }

    public List<UMLAttribute> getMethods() {
        return methods;
    }



}
