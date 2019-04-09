package com.skeaven.dbscan;

import com.skeaven.SpatialEntry;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class DbscanSpatialEntry extends SpatialEntry {
    private List<DbscanSpatialEntry> subEntries = new LinkedList<>();

    public DbscanSpatialEntry(String id,double x,double y){
        this.id = id;
        this.x = x;
        this.y = y;
    }
}
