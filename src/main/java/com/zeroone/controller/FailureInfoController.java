package com.zeroone.controller;

import com.zeroone.datatransferobjects.GET.FailureInfoDto;
import com.zeroone.datatransferobjects.GET.TicketDto;
import com.zeroone.datatransferobjects.POST.TicketPostDto;
import com.zeroone.exceptions.FailureInfoNotSavedException;
import com.zeroone.exceptions.TicketNotSavedException;
import com.zeroone.model.FailureInfo;
import com.zeroone.model.Ticket;
import com.zeroone.service.FailureInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/failures")
public class FailureInfoController {

    private final FailureInfoService failureInfoService;

    @PostMapping("/saveFailure")
    public ResponseEntity<?> saveFailure(@RequestBody FailureInfoDto failureInfoDto) {
        try {
            FailureInfo failureInfo = failureInfoService.saveFailureInfo(failureInfoDto);
            return new ResponseEntity<>(failureInfo, HttpStatus.CREATED);
        } catch (FailureInfoNotSavedException failureInfoNotSavedException) {
            return new ResponseEntity<>("Ticket has not been saved. Try again.",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllFailures() {
        try {
            List<FailureInfo> failures = failureInfoService.getAllFailures();
            return new ResponseEntity<>(failures, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving failures: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?> getAllActiveFailures() {
        try {
            List<FailureInfo> failures = failureInfoService.getAllActiveFailures();
            return new ResponseEntity<>(failures, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving active failures: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/setactive")
    public ResponseEntity<?> updateFailureStatus (@RequestParam("failureid") Long failureId) {
        failureInfoService.setFailureStatus(failureId);
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

}
