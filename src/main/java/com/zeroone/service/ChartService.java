package com.zeroone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ChartService {

    private final List<String> daysOfWeek = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");

    private String getDayName() {
        Format format = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        return format.format(new Date());
    }

    public List<String> sortDaysFromToday() {
        String day = getDayName().substring(0, 3);
        int index = daysOfWeek.indexOf(day);
        List<String> firstSubList =  daysOfWeek.subList(index, daysOfWeek.size());
        List<String> secondSubList = daysOfWeek.subList(0, index);
        return Stream.concat(firstSubList.stream(), secondSubList.stream()).toList();
    }


}
