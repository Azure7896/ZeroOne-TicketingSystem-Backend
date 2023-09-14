package com.zeroone.service;

import com.zeroone.datatransferobjects.TicketDto;
import com.zeroone.model.Ticket;
import com.zeroone.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SortService {

    private final TicketRepository ticketRepository;

    private final TicketService ticketService;

    private List<Ticket> getAllTicketsFromDatabaseByOldest() {
        List<Ticket> allTicketsList = ticketRepository.findAllTicketsByOldest();
        if (allTicketsList.isEmpty()) throw new EntityNotFoundException("ENTITY_NOT_FOUND");
        return allTicketsList;
    }

    public List<TicketDto> getAllTicketsDtoByOldest() {
        return ticketService.mapTicketsToTicketDtoList(getAllTicketsFromDatabaseByOldest());
    }

}
