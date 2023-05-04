package com.zeroone.service;

import com.zeroone.datatransferobjects.NewTicketDto;
import com.zeroone.enums.TicketStatus;
import com.zeroone.datatransferobjects.TicketDto;
import com.zeroone.model.Ticket;
import com.zeroone.model.TicketBody;
import com.zeroone.model.User;
import com.zeroone.repository.TicketRepository;
import com.zeroone.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final NameCreatorService nameCreatorService;

    private final ModelMapper modelMapper;

    private final TimeService timeService;

    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, NameCreatorService nameCreatorService, ModelMapper modelMapper, TimeService timeService, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.nameCreatorService = nameCreatorService;
        this.modelMapper = modelMapper;
        this.timeService = timeService;
        this.userRepository = userRepository;
    }

    //Get all tickets from database with all fields
    public List<Ticket> getAllTicketsFromDatabase() {
        return ticketRepository.findAllTickets();
    }

    //Map list of Tickets from database with all fields to Ticket Data Transfer Object list and calculate their times
    public List<TicketDto> mapTicketsToTicketDtoList() {
        List<Ticket> ticketList = this.getAllTicketsFromDatabase();
        return ticketList.stream()
                .map(ticket -> new TicketDto(ticket.getTicketNumber(), ticket.getName(),
                        ticket.getTicketStatus(), ticket.getUser(), ticket.getCreatedDate(), ticket.getAttendant(),
                        timeService.createTimeRemaining(ticket.getCreatedDate())))
                .toList();
    }

    //Get all ticket from database by TicketDto collection
    public List<TicketDto> getAllTicketsFromDatabaseByTicketDtoList() {
        return this.mapTicketsToTicketDtoList();
    }


    public void saveNewTicket(NewTicketDto newTicketDto) {
        Ticket newTicket = Ticket.builder()
                .name(newTicketDto.getName())
                .ticketNumber(nameCreatorService.createTicketNumber())
                .ticketBody(new TicketBody(newTicketDto.getTicketBody()))
                .ticketStatus(TicketStatus.NEW.toString())
                .user(userRepository.findUserById(1L))
                .createdDate(new Date())
                .ticketStatus(TicketStatus.NEW.toString())
                .build();

        ticketRepository.save(newTicket);
    }

}
