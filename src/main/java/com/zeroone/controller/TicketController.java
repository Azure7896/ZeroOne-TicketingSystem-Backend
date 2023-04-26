package com.zeroone.controller;


import com.zeroone.datatransferobjects.TicketDto;
import com.zeroone.model.Ticket;
import com.zeroone.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {

    private final TicketService ticketService;

    private final ModelMapper modelMapper;

    public TicketController(TicketService ticketService, ModelMapper modelMapper) {
        this.ticketService = ticketService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllTickets() {
        return new ResponseEntity<>(ticketService.getAllTicketsFromDatabaseByTicketDtoList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addTicket(@RequestBody TicketDto ticketDto) {
        ticketService.saveNewTicket(ticketDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/modify/{id}")
    public void modifyTicket (@RequestBody TicketDto ticketDto, @PathVariable Long id) {
//        Ticket ticketToModify = ticketService.getTicketFromDatabaseById(id);
//        ticketToModify = modelMapper.map(ticketDto, Ticket.class);
//        ticketService.saveNewTicket(ticketToModify);
    }

}
