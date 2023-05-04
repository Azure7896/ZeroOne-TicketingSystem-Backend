package com.zeroone.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class TicketBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 3000)
    String ticketBody;

    public TicketBody(String ticketBody) {
        this.ticketBody = ticketBody;
    }
}
