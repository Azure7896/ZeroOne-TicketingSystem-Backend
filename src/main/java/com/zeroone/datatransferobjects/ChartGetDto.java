package com.zeroone.datatransferobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ChartGetDto {

    private List<String> sortedDaysFromToday;

    private List<Integer> numberOfTicketsFromTheLastDays;
}
