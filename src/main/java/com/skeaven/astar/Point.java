package com.skeaven.astar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    int x;
    int y;

    @Override
    public String toString() {
        return x + "-" + y;
    }

}
