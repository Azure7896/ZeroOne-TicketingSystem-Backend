package com.zeroone.configuration;

import com.zeroone.model.*;
import com.zeroone.repository.CategoryRepository;
import com.zeroone.repository.RoleRepository;
import com.zeroone.repository.TicketRepository;
import com.zeroone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FirstStartup {


    private final UserRepository userRepository;

    private final TicketRepository ticketRepository;

    private final RoleRepository roleRepository;

    private final CategoryRepository categoryRepository;

    private User createZerOneDefaultUser() {
        UUID uuid = UUID.randomUUID();
        User user = User.builder()
                .firstName("ZeroOne")
                .lastName("ZeroOne")
                .isDisabled(true)
                .email("ZeroOne")
                .role(roleRepository.findRoleByName("USER"))
                .createdDate(new Date())
                .password(uuid.toString())
                .build();
        return userRepository.save(user);
    }

    private void createHelloTicket(User zeroOne) {
            Ticket ticket = Ticket.builder()
                    .attendant(zeroOne)
                    .user(zeroOne)
                    .createdDate(new Date())
                    .ticketNumber("ZO-1")
                    .ticketStatus("Closed")
                    .name("Welcome to the ZerOne Ticketing System!")
                    .ticketBody(new TicketBody("<b>Welcome to ZeroOne Ticketing System!</b><br><br>" +
                            "Our ticketing system is both efficient and highly functional, designed to streamline your ticket management processes. With our dynamic table feature, you can easily view and organize tickets, making it a breeze to handle customer inquiries, issues, and requests. You have full control to change ticket statuses, ensuring that your team stays on top of every task.<br>" +
                            "<br>Managing users is a breeze with our dedicated user management panel, allowing you to efficiently control access and permissions. You can also generate detailed reports and display insightful charts and graphs to help you gain valuable insights into your support operations<br>" +
                            "<br>But that is not all! We have ve also incorporated a user-friendly client panel, empowering your customers to submit their requests and view the status of their inquiries. This customer-oriented feature enhances communication and transparency between your team and your clients, ultimately improving overall satisfaction<br>" +
                            "<br>At ZeroOne Ticketing System, we are committed to providing you with a comprehensive and user-friendly ticketing solution that streamlines your support processes and ensures efficient ticket management. <br>"+
                            "<br>Best regards! <b>ZeroOne</b>"))
                    .category(categoryRepository.findCategoriesById(5L))
                    .build();
            ticketRepository.save(ticket);
    }

    private void createCategories() {

            categoryRepository.saveAll(List.of(
                    new Category("Application"),
                    new Category("Hardware"),
                    new Category("System"),
                    new Category("Network"),
                    new Category("Other")));
    }

     private void createDefaultRoles() {
        List<Role> roles = List.of(new Role("USER"), new Role( "ADMIN"));
        roleRepository.saveAll(roles);
    }

    public void createStartupConfig() {
        if (!ticketRepository.existsById(1L)) {
            createCategories();
            createDefaultRoles();
            User zeroOne = createZerOneDefaultUser();
            createHelloTicket(zeroOne);
        }
    }

}
