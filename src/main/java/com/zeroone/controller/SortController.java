package com.zeroone.controller;

import com.zeroone.service.SortService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class SortController {

    private final SortService sortService;

    @GetMapping("byoldest")
    public ResponseEntity<?> getAllTicketsByOldest() {
        return new ResponseEntity<>(sortService.getAllTicketsDtoByOldest(), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<?> getAllTickets() {
//        return new ResponseEntity<>(ticketService.getAllTicketsFromDatabaseByTicketDtoList(), HttpStatus.OK);
//    }


}
