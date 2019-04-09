package com.skeaven;

import lombok.Data;

@Data
public class SpatialEntry {

    protected String id;
    protected double x;
    protected double y;
    protected double z;
    protected long time;
    protected int type;
}
