package com.zeroone.controller;

import com.zeroone.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChartController {

    private final ChartService chartService;

    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping("/chart")
    public ResponseEntity<?> getChartsData() {
        return new ResponseEntity<>(chartService.sortDaysFromToday(), HttpStatus.OK);
    }

    @GetMapping("/latest7days")
    public ResponseEntity<?> getTicketsCountForEeachDay() {
        return new ResponseEntity<>(chartService.getTicketsCountForEachDay(), HttpStatus.OK);
    }
}
