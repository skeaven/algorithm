package com.skeaven.astar;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AStarAlgorithmTest {
    int[][] panel;

    @Test
    @Before
    public void createPanel() {
        panel = AStarAlgorithm.createPanel(0, 10, 0, 10, new Point(1, 1), new Point(2, 2));
        for (int i = 0; i < panel.length; i++) {
            for (int j = 0; j < panel[i].length; j++) {
                System.out.print(panel[i][j] + "\t");
            }
            System.out.println();
        }
    }

    @Test
    public void process2D() {
        Route route = AStarAlgorithm.process2D(panel, 1, 2, 4, 6);
        for (Point point : route.getRoute()) {
            System.out.print(point.toString() + "\t");
        }
        System.out.println("------");
    }
}