package com.zeroone.controller;


import com.zeroone.datatransferobjects.GET.TicketAllDataGetDto;
import com.zeroone.datatransferobjects.GET.TicketDto;
import com.zeroone.datatransferobjects.POST.TicketPostDto;
import com.zeroone.datatransferobjects.POST.TicketReplyPost;
import com.zeroone.exceptions.TicketNotFoundException;
import com.zeroone.exceptions.TicketNotSavedException;
import com.zeroone.model.Ticket;
import com.zeroone.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<?> getAllTickets() {
        try {
            List<TicketDto> allTicketsList = ticketService.getAllTicketsFromDatabaseByTicketDtoList();
            return new ResponseEntity<>(allTicketsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving tickets: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ticket")
    public ResponseEntity<?> getTicket(@RequestParam("ticketnumber") String ticketNumber) {
        try {
            TicketAllDataGetDto ticketData = ticketService.getTicketByNumber(ticketNumber);
            return new ResponseEntity<>(ticketData, HttpStatus.OK);
        } catch (TicketNotFoundException ticketNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket of number" + ticketNumber + " not found.");
        }
    }

    @PostMapping("/saveTicket")
    public ResponseEntity<?> saveTicket(@RequestBody TicketPostDto newTicketDto) {
        try {
            Ticket ticket = ticketService.createTicket(newTicketDto);
            return new ResponseEntity<>(ticket, HttpStatus.CREATED);
        } catch (TicketNotSavedException ticketNotSavedException) {
            return new ResponseEntity<>("Ticket has not been saved. Try again.",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/ticket/reply")
    public ResponseEntity<?> replyTicket(@RequestParam("ticketnumber") String ticketNumber, @RequestBody TicketReplyPost ticketReplyPost) {
        ticketService.replyTicket(ticketNumber, ticketReplyPost);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getTicketListByStatus(@RequestParam("ticketstatus") String status) {
        return new ResponseEntity<>(ticketService.getAllTicketsByStatus(status), HttpStatus.OK);
    }


    @PutMapping("/ticket")
    public ResponseEntity<?> modifyTicket (@RequestParam("ticketnumber") String ticketNumber, @RequestParam("status") int status) {
        ticketService.modifyTicketStatus(ticketNumber, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
