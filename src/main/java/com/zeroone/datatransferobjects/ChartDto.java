package com.zeroone.datatransferobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ChartDto {

    private List<String> sortedDaysFromToday;
}
