package com.skeaven.meanShift;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class MeanShiftAlgorithm {

    //最大迭代100次
    private static final int MAXIMUM_ITERATION = 100;

    /**
     * 一维空间的均值漂移据类算法
     *
     * @param radius 均值漂移的半径
     * @param data   样本空间
     * @return 返回聚类结果，每个聚类的解和相关的样本集
     */
    public static Map<Double, List<OneDimensionEntry>> process1D(double radius, OneDimensionEntry... data) {

        //聚类结果集
        Map<Double, List<OneDimensionEntry>> result = new HashMap<>();

        //如果样本集为空，则直接返回
        if (data.length > 0) {
            double minBoundary = data[0].getX();
            double maxBoundary = data[0].getX();

            //获取当前计算样本集的边界
            for (OneDimensionEntry entry : data) {
                minBoundary = minBoundary < entry.getX() ? minBoundary : entry.getX();
                maxBoundary = maxBoundary > entry.getX() ? maxBoundary : entry.getX();
            }

            //初始化算子
            int operatorNum = (int) Math.round(Math.ceil((maxBoundary - minBoundary) / radius));
            log.info("当前样本集边界为:{}-{},初始化算子数量为:{}", minBoundary, maxBoundary, operatorNum);
            double[] operators = new double[operatorNum];

            //记录算子所在类别的样本密度
            Map<Double, Integer> operatorDensity = new HashMap<>();

            //初始化算子，以及样本密度,默认样本密度为0
            for (int i = 0; i < operatorNum; i++) {
                double operatorValue = minBoundary + radius * i;
                operators[i] = operatorValue;
                operatorDensity.put(operatorValue, 0);
            }

            //均值漂移迭代
            for (int i = 0; i < MAXIMUM_ITERATION; i++) {

                //记录当前迭代的总迭代更新得分
                double score = 0.0;
                //计算每个算子的解空间样本数量，如果样本数量大于上次迭代的样本数量，则更新算子的位置，否则跳过
                for (int j = 0; j < operatorNum; j++) {
                    double operator = operators[j];

                    //保存当前迭代的解空间总得分
                    double totalValue = 0.0;

                    //记录当前迭代的解空间的样本数量
                    int sampleCount = 0;
                    for (OneDimensionEntry entry : data) {
                        if (Math.abs(entry.getX() - operator) <= radius) {
                            totalValue += entry.getX();
                            sampleCount++;
                        }
                    }

                    //如果解空间样本数量大于0，且样本密度大于上一次解空间的样本密度，则更新算子
                    if (sampleCount > 0 && operatorDensity.containsKey(operator) && operatorDensity.get(operator) <= sampleCount) {
                        double newOperator = totalValue / sampleCount;
                        score += Math.abs(operator - newOperator);
                        operators[j] = newOperator;
                        operatorDensity.remove(operator);
                        operatorDensity.put(newOperator, sampleCount);
                    }
                }

                //当前迭代更新得分如果为0.0,则表示算法的解空间已经稳定，直接退出迭代
                if (Double.compare(score, 0.0) <= 0) {
                    log.info("一共迭代次数：{}", i);
                    break;
                }

            }

            //根据聚类结果保存分类结果集
            for (int i = 0; i < operatorNum; i++) {
                List<OneDimensionEntry> list = new ArrayList<>();
                for (OneDimensionEntry entry : data) {
                    if (Math.abs(entry.getX() - operators[i]) <= radius) {
                        list.add(entry);
                    }
                }
                if (list.size() > 0) {
                    result.put(operators[i], list);
                }
            }

        }
        return result;
    }


    public static double[][] process2D() {
        return null;
    }

}
