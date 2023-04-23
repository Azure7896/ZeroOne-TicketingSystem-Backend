package com.zeroone.service;

import com.zeroone.model.Ticket;
import com.zeroone.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final NameCreatorService nameCreatorService;

    public TicketService(TicketRepository ticketRepository, NameCreatorService nameCreatorService) {
        this.ticketRepository = ticketRepository;
        this.nameCreatorService = nameCreatorService;
    }

    public List<Ticket> getAllTicketsFromDatabase() {
        return ticketRepository.findAll();
    }

    public void saveNewTicket(Ticket ticket) {
        ticket.setTicketNumber(nameCreatorService.createTicketNumber());
        ticketRepository.save(ticket);
    }

    public Ticket getTicketFromDatabaseById(Long id) {
        return ticketRepository.getById(id);
    }

    public Ticket getLastTicketFromDatabase() {
        return ticketRepository.findFirstByOrderByIdDesc();
    }
}
