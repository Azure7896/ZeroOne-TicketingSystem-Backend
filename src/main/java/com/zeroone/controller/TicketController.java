package com.zeroone.controller;


import com.zeroone.datatransferobjects.TicketDto;
import com.zeroone.model.Ticket;
import com.zeroone.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    private final ModelMapper modelMapper;

    public TicketController(TicketService ticketService, ModelMapper modelMapper) {
        this.ticketService = ticketService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDto> getAllTickets() {
    }

    @PostMapping
    public void addTicket(@RequestBody TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket = modelMapper.map(ticketDto, Ticket.class);
        ticketService.saveTicket(ticket);
    }


    @PutMapping("/modify/{id}")
    public void modifyTicket (@RequestBody TicketDto ticketDto, @PathVariable Long id) {
        Ticket ticketToModify = ticketService.getTicketFromDatabaseById(id);
        ticketToModify = modelMapper.map(ticketDto, Ticket.class);
        ticketService.saveTicket(ticketToModify);
    }

}
