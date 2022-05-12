package com.example.ijaproject.UMLbe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UMLParticipant {
    @Expose
    @SerializedName("Name")
    public String name;

    @Expose
    @SerializedName("Time")
    public List<UMLTime> time;

    public UMLParticipant(String name) {
        this.name = name;
    }

    public UMLParticipant(String name, List<UMLTime> time) {
        this.name = name;
        this.time = time;
    }


}
