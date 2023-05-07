package com.zeroone.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "tickets_bodies")
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
