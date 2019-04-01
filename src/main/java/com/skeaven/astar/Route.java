package com.skeaven.astar;

import lombok.Data;

import java.util.List;

@Data
public class Route {
    private List<Point> route;
    private Point lastPoint;
    private int g;//估值
    private int h;//花费值
}
