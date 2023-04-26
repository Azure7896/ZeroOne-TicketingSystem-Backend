package com.zeroone.service;

import com.zeroone.datatransferobjects.TicketDto;
import com.zeroone.model.Ticket;
import com.zeroone.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final NameCreatorService nameCreatorService;

    private final ModelMapper modelMapper;

    public TicketService(TicketRepository ticketRepository, NameCreatorService nameCreatorService, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.nameCreatorService = nameCreatorService;
        this.modelMapper = modelMapper;
    }

    //Get all tickets from database with all fields
    public List<Ticket> getAllTicketsFromDatabase() {
        return ticketRepository.findAll();
    }

    //Map list of Tickets from database with all fields to Ticket Data Transfer Object list
    public List<TicketDto> mapTicketsToTicketDtoList() {
        List<Ticket> ticketList = this.getAllTicketsFromDatabase();
        return ticketList.stream()
                .map(ticket -> new TicketDto(ticket.getTicketNumber(), ticket.getName(),
                        ticket.getTicketStatus(), ticket.getUser(), ticket.getCreatedDate(), ticket.getAttendant()))
                .toList();
    }

    //Get all ticket from database by TicketDto collection
    public List<TicketDto> getAllTicketsFromDatabaseByTicketDtoList() {
        return this.mapTicketsToTicketDtoList();
    }


    public void saveNewTicket(TicketDto ticketDto) {
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
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
