package com.skeaven.dbscan;

import com.skeaven.SpatialEntry;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

/**
 * dbscan算法使用的实体类
 */
@NoArgsConstructor
public class DbscanSpatialEntry extends SpatialEntry {

    @Getter
    @Setter
    private List<DbscanSpatialEntry> subEntries = new LinkedList<>();

    public DbscanSpatialEntry(String id,double x,double y){
        this.id = id;
        this.x = x;
        this.y = y;
    }
}
