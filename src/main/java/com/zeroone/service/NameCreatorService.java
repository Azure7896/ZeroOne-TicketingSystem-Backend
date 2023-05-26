package com.zeroone.service;
import com.zeroone.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class NameCreatorService {

    private final TicketRepository ticketRepository;

    public String createTicketNumber() {
        String lastTicketFullName = ticketRepository.findFirstByOrderByIdDesc().getTicketNumber();
        int newTicketNumber = Integer.parseInt(lastTicketFullName.replaceAll("\\D+",""));
        newTicketNumber++;
        StringBuilder ticketNumber = new StringBuilder("ZO-");
        ticketNumber.append(newTicketNumber);
        return ticketNumber.toString();
    }

}
