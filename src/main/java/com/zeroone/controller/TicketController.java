package com.zeroone.controller;


import com.zeroone.model.Ticket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @GetMapping("/tickets")
    public Ticket getAllTickets() {

    }

}
