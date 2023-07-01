package com.zeroone.service;

import com.zeroone.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ChartService {


    @Autowired
    private TicketRepository ticketRepository;

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
        List<String> sortedDays = new ArrayList<>(Stream.concat(firstSubList.stream(), secondSubList.stream()).toList());
        Collections.reverse(sortedDays);
//        sortedDays.set(sortedDays.size() - 1, sortedDays.get(firstSubList.size()) + " (Today)");
        return sortedDays;
    }

    public List<Long> getTicketsCountForEachDay() {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        ArrayList<Long> ticketsCountForEachDay = new ArrayList<>();
        for (byte i = -1; i >=-7; i--) {
            calendar.add(Calendar.DAY_OF_MONTH, i);
            Date dayInDateFormat = calendar.getTime();
            ticketsCountForEachDay.add(ticketRepository.countAllByCreatedDate(dayInDateFormat));
        }
        return ticketsCountForEachDay;
    }
}
