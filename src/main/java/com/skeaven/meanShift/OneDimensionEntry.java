package com.skeaven.meanShift;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneDimensionEntry {

    private String id;
    private String type;

    private double x;
}
