package com.zeroone.controller;


import com.zeroone.model.Ticket;
import com.zeroone.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTicketsFromDatabase();
    }

    @PostMapping
    public void addTicket(@RequestBody Ticket ticket) {
        ticketService.saveTicket(ticket);
    }


}
