package com.example.ijaproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UMLSequenceDiagram {
    @Expose
    @SerializedName("Name")
    public String name;

    @Expose
    @SerializedName("Participants")
    public List<UMLParticipant> participants;

    @Expose
    @SerializedName("Operations")
    public List<UMLSeqOperation> operations;

    public UMLSequenceDiagram(String name) {
        this.name = name;
    }

    public UMLSequenceDiagram(String name, List<UMLParticipant> participants, List<UMLSeqOperation> operations) {
        this.name = name;
        this.participants = participants;
        this.operations = operations;
    }

}
