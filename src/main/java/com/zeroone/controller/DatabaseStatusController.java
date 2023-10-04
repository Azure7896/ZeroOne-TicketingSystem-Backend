package com.zeroone.controller;

import com.zeroone.service.DatabaseStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseStatusController {

    private final DatabaseStatusService databaseStatusService;


    public DatabaseStatusController(DatabaseStatusService databaseStatusService) {
        this.databaseStatusService = databaseStatusService;
    }

    @GetMapping("/databasestatus")
    public ResponseEntity<?> getDatabaseStatus() {
        return new ResponseEntity<>(databaseStatusService.isDatabaseConnected(), HttpStatus.OK);
    }
}
