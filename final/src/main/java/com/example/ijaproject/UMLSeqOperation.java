package com.example.ijaproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UMLSeqOperation {
    @Expose
    @SerializedName("Index")
    public int index;

    @Expose
    @SerializedName("Source")
    public String source;

    @Expose
    @SerializedName("Destination")
    public String destination;

    @Expose
    @SerializedName("Type")
    public String type;

    @Expose
    @SerializedName("Message")
    public String message;

    public UMLSeqOperation(int index, String source, String destination, String type, String message) {
        this.index = index;
        this.source = source;
        this.destination = destination;
        this.type = type;
        this.message = message;
    }

    public UMLSeqOperation(int index, String source, String destination, String type) {
        this.index = index;
        this.source = source;
        this.destination = destination;
        this.type = type;
    }

}
