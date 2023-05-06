package com.zeroone.controller;


import com.zeroone.datatransferobjects.NewTicketDto;
import com.zeroone.datatransferobjects.TicketDto;
import com.zeroone.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<?> getAllTickets() {
            ticketService.getAllTicketsFromDatabaseByTicketDtoList();
        return new ResponseEntity<>(ticketService.getAllTicketsFromDatabaseByTicketDtoList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveTicket(@RequestBody NewTicketDto newTicketDto) {
        ticketService.saveNewTicket(newTicketDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/modify/{id}")
    public void modifyTicket (@RequestBody TicketDto ticketDto, @PathVariable Long id) {
//        Ticket ticketToModify = ticketService.getTicketFromDatabaseById(id);
//        ticketToModify = modelMapper.map(ticketDto, Ticket.class);
//        ticketService.saveNewTicket(ticketToModify);
    }


}
