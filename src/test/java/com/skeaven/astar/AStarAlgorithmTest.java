package com.skeaven.astar;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class AStarAlgorithmTest {
    int[][] panel;

    @Test
    @Before
    public void createPanel() {

        Random random = new Random();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            points.add(new Point(random.nextInt(10), random.nextInt(10)));
        }

        panel = AStarAlgorithm.createPanel(0, 10, 0, 10, points.toArray(new Point[points.size()]));
        panel[9][8] = 1;
        panel[9][9] = 0;
        AStarAlgorithm.showPanel(panel);

    }

    @Test
    public void process2D() {
        Route route = AStarAlgorithm.process2D(panel, 0, 0, 9, 9);
        for (Point point : route.getRoute()) {
            System.out.print(point.toString() + "\t");
            panel[point.getX()][point.getY()] = 5;
        }
        System.out.println("------");
        AStarAlgorithm.showPanel(panel);
    }
}