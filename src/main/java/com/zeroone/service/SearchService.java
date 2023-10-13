package com.zeroone.service;


import com.zeroone.datatransferobjects.GET.TicketSearchGetDto;
import com.zeroone.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final TicketService ticketService;

    public List<TicketSearchGetDto> searchTicketsByName(String name) {
        List<Ticket> searchedTickets = ticketService.searchTicketsByContain(name);
        return searchedTickets.stream()
                .map(ticket -> new TicketSearchGetDto(ticket.getTicketNumber(), ticket.getName()))
                .toList();
    }

}
