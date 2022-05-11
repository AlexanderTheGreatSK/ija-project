package com.example.ijaproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

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

    public UMLSequenceDiagram(String name, List<UMLParticipant> participants) {
        this.name = name;
        this.participants = participants;
    }

    public boolean participantExists(String name) {
        if(this.findParticipant(name) == null) {
            return false;
        } else {
            return true;
        }
    }
    public UMLParticipant findParticipant(String name) {
        for(int i=0; i < this.participants.size(); i++) {
            if(Objects.equals(this.participants.get(i).name, name)) {
                return this.participants.get(i);
            }
        }
        return null;
    }

    public void addParticipant(UMLParticipant participant) {
        this.participants.add(participant);
    }

}
