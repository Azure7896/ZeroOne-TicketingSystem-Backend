package com.zeroone.controller;

import com.zeroone.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChartController {

    private final ChartService chartService;

    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping("/chart")
    public ResponseEntity<?> getChartsDataFor7DaysChart() {
        return new ResponseEntity<>(chartService.sortDaysFromToday(), HttpStatus.OK);
    }

    @GetMapping("/latst7days")
    public ResponseEntity<?> getTicketsCountForEachDay() {
        return new ResponseEntity<>(chartService.countTicketsByLast7Days(), HttpStatus.OK);
    }

    @GetMapping("/charts")
    public ResponseEntity<?> getAllChartsData() {
        return new ResponseEntity<>(chartService.getChartsData(), HttpStatus.OK);
    }
}
