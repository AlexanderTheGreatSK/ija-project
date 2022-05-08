package com.example.ijaproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * UMLSequenceDiagram
 *
 * Parts of a Sequence diagram.
 *
 * @author xokruc00
 * @version 2.0
 */
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
