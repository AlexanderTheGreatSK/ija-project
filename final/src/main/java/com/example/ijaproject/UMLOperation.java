package com.example.ijaproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UMLOperation {
    @Expose
    @SerializedName("Name")
    public String name;
    @Expose
    @SerializedName("Target")
    public String target;

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
