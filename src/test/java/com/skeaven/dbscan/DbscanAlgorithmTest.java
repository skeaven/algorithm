package com.skeaven.dbscan;

import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class DbscanAlgorithmTest {

    List<TwoDimensionEntry> data = new ArrayList<>();

    @Before
    public void init() {
        for (int i = 0; i < 700; i++) {
            double xValue = Math.random() * 5000;
            double yValue = Math.random() * 5000;
            data.add(new TwoDimensionEntry(UUID.randomUUID().toString(), xValue, yValue));

            if (i % 90 == 0) {
                for (int j = 0; j < 20; j++) {
                    TwoDimensionEntry entry = new TwoDimensionEntry(UUID.randomUUID().toString(), xValue + getRandomNum(-100, 100), yValue + getRandomNum(-100, 100));
                    data.add(entry);

                }


                for (int j = 0; j < 40; j++) {
                    TwoDimensionEntry entry = new TwoDimensionEntry(UUID.randomUUID().toString(), xValue + getRandomNum(-300, 300), yValue + getRandomNum(-300, 300));
                    data.add(entry);

                }
            }
        }
    }

    @Test
    public void process2D() {
        TwoDimensionEntry[] array = new TwoDimensionEntry[data.size()];
        for (TwoDimensionEntry entry : data) {
            System.out.println(entry.getX() + "\t" + entry.getY());
        }

        Map<Integer, List<TwoDimensionEntry>> result = DbscanAlgorithm.process2D(300, 20, data.toArray(array));
        System.out.println("***************************************************");
        for (List<TwoDimensionEntry> entryList : result.values()) {
            for (TwoDimensionEntry entry : entryList) {
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