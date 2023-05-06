package com.zeroone.service;
import com.zeroone.repository.TicketRepository;
import org.springframework.stereotype.Service;
@Service
public class NameCreatorService {

    private final TicketRepository ticketRepository;

    public NameCreatorService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public String createTicketNumber() {
        String lastTicketFullName = ticketRepository.findFirstByOrderByIdDesc().getTicketNumber();
        int newTicketNumber = Integer.parseInt(lastTicketFullName.replaceAll("\\D+",""));
        newTicketNumber++;
        StringBuilder ticketNumber = new StringBuilder("ZO-");
        ticketNumber.append(newTicketNumber);
        return ticketNumber.toString();
    }
}
