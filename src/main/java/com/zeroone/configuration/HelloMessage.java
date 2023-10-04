package com.zeroone.configuration;

import com.zeroone.model.Ticket;
import com.zeroone.model.TicketBody;
import com.zeroone.model.User;
import com.zeroone.repository.TicketRepository;
import com.zeroone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class HelloMessage {


    private final UserRepository userRepository;

    private final TicketRepository ticketRepository;

    public HelloMessage(UserRepository userRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    private User createZerOneDefaultUser() {
        UUID uuid = UUID.randomUUID();
        User user = User.builder()
                .firstName("ZeroOne")
                .lastName("ZeroOne")
                .isActive(false)
                .email("ZeroOne")
                .password(uuid.toString())
                .build();
        return userRepository.save(user);
    }

    public void createHelloTicket() {
        if (!ticketRepository.existsById(1L)) {
            User zeroOne = createZerOneDefaultUser();
            Ticket ticket = Ticket.builder()
                    .attendant(zeroOne)
                    .user(zeroOne)
                    .createdDate(new Date())
                    .ticketNumber("ZO-1")
                    .ticketStatus("Closed")
                    .name("Welcome in the ZerOne Ticketing System!")
                    .ticketBody(new TicketBody("<b>Welcome to ZeroOne Ticketing System!</b><br><br>" +
                            "Our ticketing system is both efficient and highly functional, designed to streamline your ticket management processes. With our dynamic table feature, you can easily view and organize tickets, making it a breeze to handle customer inquiries, issues, and requests. You have full control to change ticket statuses, ensuring that your team stays on top of every task.<br>" +
                            "<br>Managing users is a breeze with our dedicated user management panel, allowing you to efficiently control access and permissions. You can also generate detailed reports and display insightful charts and graphs to help you gain valuable insights into your support operations<br>" +
                            "<br>But that is not all! We have ve also incorporated a user-friendly client panel, empowering your customers to submit their requests and view the status of their inquiries. This customer-oriented feature enhances communication and transparency between your team and your clients, ultimately improving overall satisfaction<br>" +
                            "<br>At ZeroOne Ticketing System, we are committed to providing you with a comprehensive and user-friendly ticketing solution that streamlines your support processes and ensures efficient ticket management. <br>"+
                            "<br>Best regards! <b>ZeroOne</b>"))
                    .build();
            ticketRepository.save(ticket);
        }
    }
}
