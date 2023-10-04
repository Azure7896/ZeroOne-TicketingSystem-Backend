package com.zeroone.service;

import com.zeroone.datatransferobjects.GET.ChartsGetDto;
import com.zeroone.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;
import java.util.stream.Stream;

@Service
public class ChartService {


    private final List<String> daysOfWeek = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");

    private final TicketRepository ticketRepository;

    public ChartService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    private String getDayName() {
        Format format = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        return format.format(new Date());
    }

    public List<String> sortDaysFromToday() {
        String day = getDayName().substring(0, 3);
        int index = daysOfWeek.indexOf(day);
        List<String> firstSubList = daysOfWeek.subList(index, daysOfWeek.size());
        List<String> secondSubList = daysOfWeek.subList(0, index);
        Collections.reverse(firstSubList);
        Collections.reverse(secondSubList);
        List<String> sortedDays = new ArrayList<>(Stream.concat(secondSubList.stream(), firstSubList.stream()).toList());
        Collections.reverse(sortedDays);
        sortedDays.set(0, sortedDays.get(0) + " (Today)");
        return sortedDays;
    }

    public List<Long> countTicketsByLast7Days() {
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Warsaw");
        List<Long> ticketCounts = new ArrayList<>();

        Calendar calendar = Calendar.getInstance(timeZone);

        for (int i = 0; i < 7; i++) {
            Date endDate = calendar.getTime();
            calendar.add(Calendar.DAY_OF_YEAR, -1);

            Date startDate = calendar.getTime();

            Long count = ticketRepository.countTicketsByDateRange(startDate, endDate);
            ticketCounts.add(count);
        }

        return ticketCounts;
    }

    private List<Integer> getTicketsCountByMonth() {

        int year = Year.now().getValue();

        List<Integer> ticketCounts = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {
            Integer count = ticketRepository.getTicketCountByMonth(year, month);
            ticketCounts.add(count);
        }

        return ticketCounts;
    }

    public ChartsGetDto getChartsData() {
        return ChartsGetDto.builder()
                .ticketCountByMonth(getTicketsCountByMonth())
                .build();
    }
}
