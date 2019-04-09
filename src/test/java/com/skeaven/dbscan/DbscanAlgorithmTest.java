package com.skeaven.dbscan;

import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class DbscanAlgorithmTest {

    List<DbscanSpatialEntry> data = new ArrayList<>();

    @Before
    public void init() {
        for (int i = 0; i < 700; i++) {
            double xValue = Math.random() * 5000;
            double yValue = Math.random() * 5000;
            data.add(new DbscanSpatialEntry(UUID.randomUUID().toString(), xValue, yValue));

            if (i % 90 == 0) {
                for (int j = 0; j < 20; j++) {
                    DbscanSpatialEntry entry = new DbscanSpatialEntry(UUID.randomUUID().toString(), xValue + getRandomNum(-100, 100), yValue + getRandomNum(-100, 100));
                    data.add(entry);

                }


                for (int j = 0; j < 40; j++) {
                    DbscanSpatialEntry entry = new DbscanSpatialEntry(UUID.randomUUID().toString(), xValue + getRandomNum(-300, 300), yValue + getRandomNum(-300, 300));
                    data.add(entry);

                }
            }
        }
    }

    @Test
    public void process2D() {
        DbscanSpatialEntry[] array = new DbscanSpatialEntry[data.size()];
        for (DbscanSpatialEntry entry : data) {
            System.out.println(entry.getX() + "\t" + entry.getY());
        }

        Map<Integer, List<DbscanSpatialEntry>> result = DbscanAlgorithm.process2D(300, 20, data.toArray(array));
        System.out.println("***************************************************");
        for (List<DbscanSpatialEntry> entryList : result.values()) {
            for (DbscanSpatialEntry entry : entryList) {
                System.out.println(entry.getX() + "\t" + entry.getY());
            }
            System.out.println("---------------------------分类-------------------------------");
        }
        System.out.println("************************************************");
        double[][] centroids = DbscanAlgorithm.centroid(result);
        for (int i = 0; i < centroids.length; i++) {
            System.out.println(centroids[i][0] + "\t" + centroids[i][1]);
        }

    }

    // 获得一个给定范围的随机整数
    public static int getRandomNum(int smallistNum, int BiggestNum) {
        Random random = new Random();
        return (Math.abs(random.nextInt()) % (BiggestNum - smallistNum + 1)) + smallistNum;
    }

}