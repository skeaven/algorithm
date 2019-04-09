package com.skeaven.dbscan;

import com.skeaven.SpatialEntry;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class DbscanSpatialEntry extends SpatialEntry {
    private List<DbscanSpatialEntry> subEntries = new LinkedList<>();
}
