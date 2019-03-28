package com.skeaven.dbscan;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class TwoDimensionEntry {

    private String id;
    private int type;

    private double x;
    private double y;

    private List<TwoDimensionEntry> subEntries = new LinkedList<>();

    public TwoDimensionEntry(String id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }


    public String toString() {
        return id + "\t" + x + "\t" + y;
    }
}
