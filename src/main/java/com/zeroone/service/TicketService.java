package com.zeroone.service;

import com.zeroone.datatransferobjects.TicketPostDto;
import com.zeroone.enums.TicketStatus;
import com.zeroone.datatransferobjects.TicketDto;
import com.zeroone.model.Ticket;
import com.zeroone.model.TicketBody;
import com.zeroone.repository.TicketRepository;
import com.zeroone.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    private final NameCreatorService nameCreatorService;


    private final TimeService timeService;

    private final UserRepository userRepository;


    //Get all tickets from database with all fields

    //Map list of Tickets from database with all fields to Ticket Data Transfer Object list and calculate their times
    public List<TicketDto> mapTicketsToTicketDtoList() throws EntityNotFoundException{
        List<Ticket> ticketList = ticketRepository.findAllTickets();
        if (ticketList.isEmpty()) throw new EntityNotFoundException("ENTITY_NOT_FOUND");
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


    public void saveNewTicket(TicketPostDto newTicketDto) {
        Ticket newTicket = Ticket.builder()
                .name(newTicketDto.getName())
                .ticketNumber(nameCreatorService.createTicketNumber())
                .ticketBody(new TicketBody(newTicketDto.getTicketBody()))
                .ticketStatus(TicketStatus.NEW.toString())
                .user(userRepository.findUserById(1L))
                .createdDate(new Date())
                .build();

        ticketRepository.save(newTicket);
    }

}
