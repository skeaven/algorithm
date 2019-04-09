package com.skeaven.meanShift;

import com.skeaven.SpatialEntry;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

@Slf4j
public class MeanShiftAlgorithmTest {
    private static final int TYPE_NUM = 5;

    List<SpatialEntry> data = new ArrayList<>();

    @Before
    public void init() {
        for (int i = 0; i < 60; i++) {
            double value = Math.random() * 1000;
            SpatialEntry entry = new SpatialEntry();
            entry.setId(UUID.randomUUID().toString());
            entry.setX(value);
            data.add(entry);

            System.out.println(value + "\t" + 0);

        }
    }

    @Test
    public void process1D() {
        SpatialEntry[] array = new SpatialEntry[data.size()];
        Map<Double, List<SpatialEntry>> result = MeanShiftAlgorithm.process1D(20, data.toArray(array));
        System.out.println("-----------------------------");
        for (Map.Entry<Double, List<SpatialEntry>> entry : result.entrySet()) {
            if (entry.getValue().size() > TYPE_NUM)
                System.out.println(entry.getKey() + "\t" + 0);
        }
        System.out.println("-----------------------------");
        for (Map.Entry<Double, List<SpatialEntry>> entry : result.entrySet()) {
            if (entry.getValue().size() > TYPE_NUM)
                System.out.println(entry.getKey() + "\t" + entry.getValue().size());
        }
    }

    @Test
    public void process2D() {
    }


    public static String formatList(List<SpatialEntry> entries) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        for (SpatialEntry entry : entries) {
            stringBuffer.append(entry.getId()).append(",");
        }
        stringBuffer.append("}");
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);

        return stringBuffer.toString();
    }

}