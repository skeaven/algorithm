package com.skeaven;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 空间实体，算法计算中使用的数据实体父类
 * 提供无参和全参两种构造方法
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpatialEntry {

    //实体唯一标识
    protected String id;

    //实体一维坐标
    protected double x;
    //实体二维坐标
    protected double y;
    //实体三维坐标
    protected double z;
    //实体时间
    protected long time;
    //实体类别
    protected int type;
}
