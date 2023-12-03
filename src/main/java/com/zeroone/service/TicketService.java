package com.zeroone.service;

import com.zeroone.datatransferobjects.GET.TicketAllDataGetDto;
import com.zeroone.datatransferobjects.GET.TicketDto;
import com.zeroone.datatransferobjects.POST.TicketPostDto;
import com.zeroone.datatransferobjects.POST.TicketReplyPost;
import com.zeroone.enums.TicketStatus;
import com.zeroone.exceptions.TicketNotFoundException;
import com.zeroone.exceptions.TicketNotSavedException;
import com.zeroone.exceptions.TicketStatusUpdateFailedException;
import com.zeroone.model.Ticket;
import com.zeroone.model.TicketBody;
import com.zeroone.model.TicketReply;
import com.zeroone.model.User;
import com.zeroone.repository.CategoryRepository;
import com.zeroone.repository.TicketReplyRepository;
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

    private final TicketReplyRepository ticketReplyRepository;

    private final CategoryRepository categoryRepository;


    private List<Ticket> getAllTicketsFromDatabase() {
        List<Ticket> allTicketsList = ticketRepository.findAllTickets();
        return allTicketsList;
    }

    public List<TicketDto> mapTicketsToTicketDtoList(List<Ticket> ticketList) {
        return ticketList.stream()
                .map(ticket -> new TicketDto(ticket.getTicketNumber(), ticket.getName(),
                        ticket.getTicketStatus(), ticket.getUser(), ticket.getCreatedDate(), ticket.getAttendant(),
                        timeService.createTimeRemaining(ticket.getCreatedDate(), ticket.getTicketStatus()), ticket.getCategory()))
                .toList();
    }

    public List<TicketDto> getAllTicketsByStatus(String status) {
        List<Ticket> ticketListByStatus = ticketRepository.findByTicketStatus(status);
        return mapTicketsToTicketDtoList(ticketListByStatus);
    }

//    public List<TicketDto> getAllTicketsByAttendant(String email) {
//        List<Ticket> ticketListByAttendant = ticketRepository.findTicketsByAttendant(email);
//    }


    public List<Ticket> searchTicketsByContain(String name) {
        return ticketRepository.findByNameContainingIgnoreCaseOrderByTicketStatus(name);
    }

    public List<TicketDto> getAllTicketsFromDatabaseByTicketDtoList() {
        return this.mapTicketsToTicketDtoList(getAllTicketsFromDatabase());
    }

    public TicketAllDataGetDto getTicketByNumber(String ticketNumber) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findByTicketNumberContainingIgnoreCase(ticketNumber);
        if (ticket == null) {
            throw new TicketNotFoundException("Ticket not found: " + ticketNumber);
        }

        return TicketAllDataGetDto.builder()
                .name(ticket.getName())
                .ticketNumber(ticket.getTicketNumber())
                .ticketBody(ticket.getTicketBody())
                .ticketStatus(ticket.getTicketStatus())
                .user(ticket.getUser())
                .attendant(ticket.getAttendant())
                .createdDate(ticket.getCreatedDate())
                .ticketTimeRemaining(timeService.createTimeRemaining(ticket.getCreatedDate(), ticket.getTicketStatus()))
                .ticketReplies(ticket.getTicketReplies())
                .category(ticket.getCategory())
                .build();
    }

    public void modifyTicketStatus(String ticketNumber, int status) throws TicketStatusUpdateFailedException {
        Ticket ticketToUpdate = ticketRepository.findByTicketNumberContainingIgnoreCase(ticketNumber);

        if (ticketToUpdate == null) {
            throw new TicketStatusUpdateFailedException("Ticket not found: " + ticketNumber);
        }

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
            default:
                throw new TicketStatusUpdateFailedException("Wrong status: " + status);
        }

        try {
            ticketRepository.save(ticketToUpdate);
        } catch (Exception e) {
            throw new TicketStatusUpdateFailedException("Error when ticket updating: " + e.getMessage());
        }
    }


    public void replyTicket(String ticketNumber, TicketReplyPost ticketReplyPost) {
        Ticket ticket = ticketRepository.findByTicketNumberContainingIgnoreCase(ticketNumber);

        //When ticket has status "New", change status to "In progress"

        User userWhoReplied = userRepository.findUserByEmail(ticketReplyPost.getUserEmail());

        if (ticket.getTicketStatus().equals("New")) {
            ticket.setTicketStatus(TicketStatus.IN_PROGRESS.toString());
            ticket.setAttendant(userWhoReplied);
            ticketRepository.save(ticket);
        }

        TicketReply ticketReply = TicketReply.builder()
                .ticketReplyBody(ticketReplyPost.getReplyBody())
                .replyDate(new Date())
                .ticket(ticket)
                .user(userWhoReplied)
                .build();

        ticketReplyRepository.save(ticketReply);
    }


    public Ticket createTicket(TicketPostDto newTicketDto) throws TicketNotSavedException {
        Ticket newTicket = Ticket.builder()
                .name(newTicketDto.getName())
                .ticketNumber(nameCreatorService.createTicketNumber())
                .ticketBody(new TicketBody(newTicketDto.getTicketBody()))
                .ticketStatus(TicketStatus.NEW.toString())
                .user(userRepository.findUserByEmail(newTicketDto.getUserEmail()))
                .createdDate(new Date())
                .category(categoryRepository.findCategoriesByCategoryName(newTicketDto.getCategory()))
                .build();

        try {
            return ticketRepository.save(newTicket);
        } catch (Exception e) {
            throw new TicketNotSavedException("Error when ticket saving: " + e.getMessage());
        }
    }

}
