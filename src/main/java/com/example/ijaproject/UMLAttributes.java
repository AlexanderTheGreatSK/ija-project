package com.example.ijaproject;

/**
 * UMLAttributes
 *
 * This constructor is backend for UML Attributes.
 *
 * @author xokruc00
 * @version 1.0
 */
public class UMLAttributes {
    private String name;
    private boolean isPublic;

    public UMLAttributes(String name, boolean isPublic) {
        this.name = name;
        this.isPublic = isPublic;
    }

    public UMLAttributes(String name) {
        this.name = name;
        this.isPublic = true;
    }

    public void update(String name) {
        this.name = name;
    }

    public void update(String name, Boolean isPublic) {
        this.name = name;
        this.isPublic = isPublic;
    }

    public void update(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsPublic() {
        return this.isPublic;
    }
}
