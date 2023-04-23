package com.zeroone.service;
import com.zeroone.repository.TicketRepository;
import org.springframework.stereotype.Service;
@Service
public class NameCreatorService {

    public final TicketRepository ticketRepository;

    public NameCreatorService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public String createTicketNumber() {
        String lastTicketFullName = ticketRepository.findFirstByOrderByIdDesc().getTicketNumber();
        int newTicketNumber = Integer.parseInt(lastTicketFullName.replaceAll("\\D+",""));
        newTicketNumber++;
        return "ZO-" + newTicketNumber;
    }
}
