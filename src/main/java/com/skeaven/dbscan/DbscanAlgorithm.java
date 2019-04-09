package com.skeaven.dbscan;

import static java.lang.Math.*;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class DbscanAlgorithm {

    public static Map<Integer, List<DbscanSpatialEntry>> process2D(double radius, int minpts, DbscanSpatialEntry... data) {
        Map<Integer, List<DbscanSpatialEntry>> result = new HashMap<>();
        //寻找核心对象
        List<DbscanSpatialEntry> coreEntries = new LinkedList<>();
        for (DbscanSpatialEntry entry1 : data) {
            entry1.setType(0);

            int count = 0;
            for (DbscanSpatialEntry entry2 : data) {
                //排除自身
                if (!entry1.getId().equals(entry2.getId()) && europeanDistance(entry1, entry2) <= radius) {
                    entry1.getSubEntries().add(entry2);
                    count++;
                }
            }
            if (count >= minpts) {
                coreEntries.add(entry1);
            } else {
                entry1.getSubEntries().clear();
            }
        }

        int type = 1;
        //根据核心对象进行分类，如果核心对象还没有类别，则进行分类，否则跳过
        for (DbscanSpatialEntry coreEntry : coreEntries) {
            if (coreEntry.getType() == 0) {
                coreEntry.setType(type++);

                //首先设置核心对象的所有所属对象
                for (DbscanSpatialEntry subEntry : coreEntry.getSubEntries()) {
                    setEntryType(coreEntry, subEntry);
                }

                //关联直连的核心对象
                for (DbscanSpatialEntry entry : coreEntries) {
                    if (europeanDistance(coreEntry, entry) <= radius) {
                        setEntryType(coreEntry, entry);
                    }
                }
            }
        }

        //对接空间的所有样本进行记录分类
        for (DbscanSpatialEntry entry : data) {
            if (!result.containsKey(entry.getType())) {
                result.put(entry.getType(), new LinkedList<DbscanSpatialEntry>());
            }
            result.get(entry.getType()).add(entry);

        }

        //删除没有类别的数据
        result.remove(0);

        return result;
    }

    //递归设置所有关联的可达对象
    private static void setEntryType(DbscanSpatialEntry coreEntry, DbscanSpatialEntry entry) {

        List<DbscanSpatialEntry> subEntries = new LinkedList<>(entry.getSubEntries());
        //子类对象排除目标对象
        subEntries.remove(coreEntry);

        if (entry.getType() != coreEntry.getType()) {
            entry.setType(coreEntry.getType());
            for (DbscanSpatialEntry subEntry : subEntries) {
                setEntryType(entry, subEntry);
            }
        }
    }

    //计算欧式距离
    private static double europeanDistance(DbscanSpatialEntry entry1, DbscanSpatialEntry entry2) {
        return sqrt(pow(abs(entry1.getX() - entry2.getX()), 2) + pow(abs(entry1.getY() - entry2.getY()), 2));
    }


    //计算地理距离
    private static double geographicDistance(DbscanSpatialEntry entry1, DbscanSpatialEntry entry2) {
        return sqrt(pow(abs(entry1.getX() - entry2.getX()), 2) + pow(abs(entry1.getY() - entry2.getY()), 2));
    }


    //获取每个聚类结果的质心点
    public static double[][] centroid(Map<Integer, List<DbscanSpatialEntry>> entries) {

        double[][] centroids = new double[entries.size()][2];
        int i = 0;
        for (List<DbscanSpatialEntry> entryList : entries.values()) {
            double totalX = 0.0;
            double totalY = 0.0;
            int count = 0;
            for (DbscanSpatialEntry entry : entryList) {
                count++;
                totalX += entry.getX();
                totalY += entry.getY();
            }

            centroids[i][0] = totalX / count;
            centroids[i][1] = totalY / count;
            System.out.println(centroids[i][0] + "\t" + centroids[i][1]);
            i++;
        }
        return centroids;
    }

}
