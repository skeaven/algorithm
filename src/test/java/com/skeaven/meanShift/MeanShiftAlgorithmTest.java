package com.skeaven.meanShift;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

@Slf4j
public class MeanShiftAlgorithmTest {
    private static final int TYPE_NUM = 5;

    List<OneDimensionEntry> data = new ArrayList<>();

    @Before
    public void init() {
        for (int i = 0; i < 60; i++) {
            double value = Math.random() * 1000;
            OneDimensionEntry entry = new OneDimensionEntry();
            entry.setId(UUID.randomUUID().toString());
            entry.setX(value);
            data.add(entry);

            System.out.println(value + "\t" + 0);

        }
    }

    @Test
    public void process1D() {
        OneDimensionEntry[] array = new OneDimensionEntry[data.size()];
        Map<Double, List<OneDimensionEntry>> result = MeanShiftAlgorithm.process1D(20, data.toArray(array));
        System.out.println("-----------------------------");
        for (Map.Entry<Double, List<OneDimensionEntry>> entry : result.entrySet()) {
            if (entry.getValue().size() > TYPE_NUM)
                System.out.println(entry.getKey() + "\t" + 0);
        }
        System.out.println("-----------------------------");
        for (Map.Entry<Double, List<OneDimensionEntry>> entry : result.entrySet()) {
            if (entry.getValue().size() > TYPE_NUM)
                System.out.println(entry.getKey() + "\t" + entry.getValue().size());
        }
    }

    @Test
    public void process2D() {
    }


    public static String formatList(List<OneDimensionEntry> entries) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        for (OneDimensionEntry entry : entries) {
            stringBuffer.append(entry.getId()).append(",");
        }
        stringBuffer.append("}");
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);

        return stringBuffer.toString();
    }

}