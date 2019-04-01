package com.skeaven.astar;

import java.util.*;

public class AStarAlgorithm {

    public static int[][] createPanel(int xMin, int xMax, int yMin, int yMax, Point... points) {

        int[][] panel = new int[xMax - xMin][yMax - yMin];

        for (Point point : points) {
            panel[point.getX()][point.getY()] = 1;
        }
        return panel;

    }


    public static Route process2D(int[][] panel, int x, int y, int targetX, int targetY) {
        List<Route> routes = new ArrayList<>();

        int currX = x;
        int currY = y;
        //初始化
        Route route = new Route();
        route.setG(manhattanDistance(x, y, targetX, targetY));
        route.setH(0);
        route.setLastPoint(new Point(x, y));
        route.setRoute(new ArrayList<Point>());
        route.getRoute().add(route.getLastPoint());
        routes.add(route);
        Route bestRoute = route;
        boolean flag;

        while (!(bestRoute.getLastPoint().getX() == targetX && bestRoute.getLastPoint().getY() == targetY)) {
            //获取当前最优解，并初始化最优解为起点，开始进行下一步移动
            Point start = bestRoute.getLastPoint();
            int startX = start.getX();
            int startY = start.getY();

            Point[] nextPoints = new Point[4];
            nextPoints[0] = new Point(startX - 1, startY);
            nextPoints[1] = new Point(startX + 1, startY);
            nextPoints[2] = new Point(startX, startY - 1);
            nextPoints[3] = new Point(startX, startY + 1);

            flag = false;//标识当前迭代是否有解，如果没有解，则结束迭代
            for (Point nextPoint : nextPoints) {
                if (checkPoint(panel, nextPoint)) {
                    //处于解空间内，且可以移动到此的位置
                    int gValue = bestRoute.getH() + 1;
                    int hValue = manhattanDistance(nextPoint.getX(), nextPoint.getY(), targetX, targetY);
                    Route nextRoute = new Route();
                    List<Point> hisRoute = new ArrayList<>(bestRoute.getRoute());
                    hisRoute.add(nextPoint);
                    nextRoute.setRoute(hisRoute);
                    nextRoute.setLastPoint(nextPoint);
                    nextRoute.setG(gValue);
                    nextRoute.setH(hValue);

                    routes.add(nextRoute);
                    panel[nextPoint.getX()][nextPoint.getY()] = 1;
                    flag = true;
                }
            }

            if (flag){
                //有解
                routes.remove(bestRoute);
            }else{
                break;
            }

            //找到当前优解
            int minScore = Integer.MAX_VALUE;
            for (Route currRoute : routes) {
                int score = currRoute.getG() + currRoute.getH();
                if (minScore > score) {
                    minScore = score;
                    bestRoute = currRoute;
                }
            }
        }

        return bestRoute;
    }

    private static int manhattanDistance(int x, int y, int targetX, int targetY) {
        return Math.abs(targetX - x) + Math.abs(targetY - y);
    }

    private static boolean checkPoint(int[][] panel, Point point) {
        int x = point.getX();
        int y = point.getY();

        if (y >= 0 && y < panel.length) {
            if (x >= 0 && x < panel[y].length) {
                return panel[x][y] != 1;
            }
        }
        return false;
    }

}
