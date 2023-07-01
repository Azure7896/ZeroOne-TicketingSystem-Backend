package com.zeroone.service;


import com.zeroone.datatransferobjects.TicketDto;
import com.zeroone.datatransferobjects.TicketSearchGETDto;
import com.zeroone.model.Ticket;
import com.zeroone.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final TicketService ticketService;

    public List<TicketSearchGETDto> searchTicketsByName() {
        List<TicketDto> searchedTickets = ticketService.getAllTicketsFromDatabaseByTicketDtoList();
        return searchedTickets.stream()
                .map(ticket -> new TicketSearchGETDto(ticket.getTicketNumber(), ticket.getName()))
                .toList();
    }

}
