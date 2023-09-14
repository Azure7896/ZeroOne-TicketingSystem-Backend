package com.zeroone.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor

@Getter
@Setter
@Table(name = "tickets_bodies")
public class TicketBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 7000, nullable = false)
    private String ticketBody;

    public TicketBody(String ticketBody) {
        this.ticketBody = ticketBody;
    }


}
