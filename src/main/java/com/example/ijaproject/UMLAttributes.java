package com.example.ijaproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * UMLAttributes
 *
 * This constructor is backend for UML Attributes.
 *
 * @author xokruc00
 * @version 1.0
 */
public class UMLAttributes {
    @Expose
    @SerializedName("Name")
    public String name;
    @Expose
    @SerializedName("IsPublic")
    public boolean isPublic;

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
