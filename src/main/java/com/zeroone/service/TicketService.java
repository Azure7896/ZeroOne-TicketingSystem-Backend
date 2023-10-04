package com.zeroone.service;

import com.zeroone.datatransferobjects.GET.TicketAllDataGetDto;
import com.zeroone.datatransferobjects.GET.TicketReplyDto;
import com.zeroone.datatransferobjects.POST.TicketPostDto;
import com.zeroone.datatransferobjects.POST.TicketReplyPost;
import com.zeroone.datatransferobjects.TicketDto;
import com.zeroone.enums.TicketStatus;
import com.zeroone.model.Ticket;
import com.zeroone.model.TicketBody;
import com.zeroone.model.TicketReply;
import com.zeroone.repository.TicketReplyRepository;
import com.zeroone.repository.TicketRepository;
import com.zeroone.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    private final NameCreatorService nameCreatorService;

    private final TimeService timeService;

    private final UserRepository userRepository;

    private final TicketReplyRepository ticketReplyRepository;


    private List<Ticket> getAllTicketsFromDatabase() {
        List<Ticket> allTicketsList = ticketRepository.findAllTickets();
        if (allTicketsList.isEmpty()) throw new EntityNotFoundException("ENTITY_NOT_FOUND");
        return allTicketsList;
    }

    public List<TicketDto> mapTicketsToTicketDtoList(List<Ticket> ticketList) {
        return ticketList.stream()
                .map(ticket -> new TicketDto(ticket.getTicketNumber(), ticket.getName(),
                        ticket.getTicketStatus(), ticket.getUser(), ticket.getCreatedDate(), ticket.getAttendant(),
                        timeService.createTimeRemaining(ticket.getCreatedDate(), ticket.getTicketStatus())))
                .toList();
    }

    public List<TicketDto> getAllTicketsByStatus(String status) {
        List<Ticket> ticketListByStatus = ticketRepository.findByTicketStatus(status);
        return mapTicketsToTicketDtoList(ticketListByStatus);
    }


//    public List<TicketReplyDto> mapTicketRepliesToTicketReplyDtoList(List<TicketReply> ticketReplies) {
//        return ticketReplies.stream()
//                .map(reply -> new TicketReplyDto(reply.getTicketReply(), reply.getUser(), reply.getReplyDate()))
//                .toList();
//    }

    public List<Ticket> searchTicketsByContain(String name) {
        return ticketRepository.findByNameContainingIgnoreCaseOrderByTicketStatus(name);
    }

    public List<TicketDto> getAllTicketsFromDatabaseByTicketDtoList() {
        return this.mapTicketsToTicketDtoList(getAllTicketsFromDatabase());
    }

    public TicketAllDataGetDto getByTicketNumber(String ticketNumber) {
        Ticket ticket = ticketRepository.findByTicketNumberContainingIgnoreCase(ticketNumber);
        return TicketAllDataGetDto.builder()
                .name(ticket.getName())
                .ticketNumber(ticket.getTicketNumber())
                .ticketBody(ticket.getTicketBody())
                .ticketStatus(ticket.getTicketStatus())
                .user(ticket.getUser())
                .createdDate(ticket.getCreatedDate())
                .ticketTimeRemaining(timeService.createTimeRemaining(ticket.getCreatedDate(), ticket.getTicketStatus()))
                .ticketReplies(ticket.getTicketReplies())
                .build();
    }

    public void modifyTicketStatus(String ticketNumber, int status) {

        Ticket ticketToUpdate = ticketRepository.findByTicketNumberContainingIgnoreCase(ticketNumber);

        switch (status) {

            case 1:
                ticketToUpdate.setTicketStatus(TicketStatus.IN_PROGRESS.toString());
                break;
            case 2:
                ticketToUpdate.setTicketStatus(TicketStatus.CLOSED.toString());
                break;
            case 3:
                ticketToUpdate.setTicketStatus(TicketStatus.SUSPENDED.toString());
                break;
        }
        ticketRepository.save(ticketToUpdate);
    }

    public void replyTicket(String ticketNumber, TicketReplyPost ticketReplyPost) {
        Ticket ticket = ticketRepository.findByTicketNumberContainingIgnoreCase(ticketNumber);

        //When ticket has status "New", change status to "In progress"

        if(ticket.getTicketStatus().equals("New")) {
            ticket.setTicketStatus(TicketStatus.IN_PROGRESS.toString());
            ticketRepository.save(ticket);
        } else if (ticket.getTicketStatus().equals("Closed")) {

        } else {
            TicketReply ticketReply = TicketReply.builder()
                    .ticketReplyBody(ticketReplyPost.getReplyBody())
                    .replyDate(new Date())
                    .ticket(ticket)
                    .user(userRepository.findUserById(1L))
                    .build();

            ticketReplyRepository.save(ticketReply);
        }
    }


    public Ticket saveNewTicket(TicketPostDto newTicketDto) {
        Ticket newTicket = Ticket.builder()
                .name(newTicketDto.getName())
                .ticketNumber(nameCreatorService.createTicketNumber())
                .ticketBody(new TicketBody(newTicketDto.getTicketBody()))
                .ticketStatus(TicketStatus.NEW.toString())
                .user(userRepository.findUserById(1L))
                .createdDate(new Date())
                .build();

        return ticketRepository.save(newTicket);
    }

}
