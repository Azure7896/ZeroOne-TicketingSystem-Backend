package com.zeroone.service;
import com.zeroone.repository.TicketRepository;
import org.springframework.stereotype.Service;
@Service
public class NameCreatorService {

    private final TicketRepository ticketRepository;

    private final StringBuilder stringBuilder;

    public NameCreatorService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        stringBuilder = new StringBuilder("ZO-");
    }

    public String createTicketNumber() {
        String lastTicketFullName = ticketRepository.findFirstByOrderByIdDesc().getTicketNumber();
        int newTicketNumber = Integer.parseInt(lastTicketFullName.replaceAll("\\D+",""));
        newTicketNumber++;
        stringBuilder.append(newTicketNumber);
        return stringBuilder.toString();
    }
}
