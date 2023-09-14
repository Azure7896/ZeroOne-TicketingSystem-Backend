package com.zeroone.controller;


import com.zeroone.datatransferobjects.GET.TicketAllDataGetDto;
import com.zeroone.datatransferobjects.POST.TicketPostDto;
import com.zeroone.datatransferobjects.POST.TicketReplyPost;
import com.zeroone.model.Ticket;
import com.zeroone.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<?> getAllTickets() {
        return new ResponseEntity<>(ticketService.getAllTicketsFromDatabaseByTicketDtoList(), HttpStatus.OK);
    }

    @GetMapping("/ticket")
    public ResponseEntity<?> getOneTicket(@RequestParam("ticketnumber") String ticketNumber) {
        TicketAllDataGetDto ticketAllDataGetDto = ticketService.getOneTicketByTicketNumber(ticketNumber);
        return new ResponseEntity<>(ticketAllDataGetDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveTicket(@RequestBody TicketPostDto newTicketDto) {
        Ticket ticket = ticketService.saveNewTicket(newTicketDto);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @PostMapping("/ticket/reply")
    public ResponseEntity<?> replyTicket(@RequestParam("ticketnumber") String ticketNumber, @RequestBody TicketReplyPost ticketReplyPost) {
        ticketService.replyTicket(ticketNumber, ticketReplyPost);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/ticket/replies")
    public ResponseEntity<?> getAllTicketReplies(@RequestParam("ticketnumber") String ticketNumber) {
        return new ResponseEntity<>(ticketService.getAllTicketReplies(ticketNumber), HttpStatus.OK);
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
