package com.zeroone.service;

import com.zeroone.model.Ticket;
import com.zeroone.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTicketsFromDatabase() {
        return ticketRepository.findAll();
    }

    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public Ticket getTicketFromDatabaseById(Long id) {
        return ticketRepository.getById(id);
    }
}
