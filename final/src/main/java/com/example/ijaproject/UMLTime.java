package com.example.ijaproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * UMLTime
 *
 * Lifetime of a Participant in the sequence diagram
 * @author xokruc00
 * @version 2.0
 */
public class UMLTime {
    @Expose
    @SerializedName("Index")
    public int index;

    @Expose
    @SerializedName("Start")
    public int start;

    @Expose
    @SerializedName("End")
    public int end;

    public UMLTime(int index, int start, int end) {
        this.index = index;
        this.start = start;
        this.end = end;
    }

}
